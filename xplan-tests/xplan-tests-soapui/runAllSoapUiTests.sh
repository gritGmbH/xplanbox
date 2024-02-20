#!/bin/bash
set -e

XPLAN_DIENSTE_BASE_URL=${XPLAN_DIENSTE_BASE_URL:-http://xplan-services}
XPLAN_MANAGER_API_BASE_URL=${XPLAN_MANAGER_API_BASE_URL:-http://xplan-manager-api/xplan-manager-api}
XPLAN_MANAGER_WEB_BASE_URL=${XPLAN_MANAGER_WEB_BASE_URL:-http://xplan-manager-web/xplan-manager-web}
XPLAN_MAPPROXY_BASE_URL=${XPLAN_MAPPROXY_BASE_URL:-http://xplan-mapproxy:8080}
XPLAN_MAPSERVER_BASE_URL=${XPLAN_MAPSERVER_BASE_URL:-http://xplan-mapserver:8080}
XPLAN_VALIDATOR_API_BASE_URL=${XPLAN_VALIDATOR_API_BASE_URL:-http://xplan-validator-api/xplan-validator-api}
MSG_PREFIX=$([ -n "$POD_NAMESPACE" ] && echo "[$POD_NAMESPACE] " || echo "")

############################################
function sendSlackMessage() {
	local message=$MSG_PREFIX$1
	curl --show-error --silent -d "text=$message" -d "channel=$XPLAN_NOTIFY_SLACK_CHANNEL" -H "Authorization: Bearer $XPLAN_NOTIFY_SLACK_TOKEN" -X POST https://slack.com/api/chat.postMessage > /dev/null
}

function waitForRightVersion() {
	local url=$1
	local sleepDuration=10
	local nbRepeat=60
	echo "- checking $url (waiting up to $((nbRepeat * sleepDuration))s)"
	echo -n "   "

	n=1
	while [ $n -le $nbRepeat ]
	do
  		n=$(($n+1))
		local content=$(curl -s $url)
		if [[ "$content" == *"$GIT_REVISION"* ]]; then
  			echo "  [ok]"
  			return 0
  		else
  			echo -n "."
  			sleep $((sleepDuration))s
		fi
	done
	
	echo "  [failed]"
	return 2
}
############################################

if [ -n "$XPLAN_NOTIFY_SLACK_CHANNEL" ] &&  [ -n "$XPLAN_NOTIFY_SLACK_TOKEN" ]; then
	sendSlackMessage "Starting SoapUI tests..."
fi

if [ -z ${XPLAN_DB_HOSTNAME+x} ]; then
  echo "XPLAN_DB_HOSTNAME is not set. jdbcUrl is not used in xplan-manager-api SoapUI Tests"
else
	echo "XPLAN_DB_HOSTNAME is set, jdbcUrl is created and used for xplan-manager-api SoapUI Tests"
	JDBC_URL="jdbc:postgresql://$XPLAN_DB_HOSTNAME:$XPLAN_DB_PORT/$XPLAN_DB_NAME?user=$XPLAN_DB_USER&password=$XPLAN_DB_PASSWORD"
fi

echo "Waiting for services with git revision $GIT_REVISION:"
urlsToCheck="$XPLAN_VALIDATOR_API_BASE_URL/xvalidator/version.txt \
$XPLAN_MANAGER_API_BASE_URL/xmanager/version.txt \
$XPLAN_MANAGER_WEB_BASE_URL/version.txt \
$XPLAN_DIENSTE_BASE_URL/xplan-wfs/version.txt \
$XPLAN_DIENSTE_BASE_URL/xplan-wms/version.txt"

for url in $urlsToCheck; do
	waitForRightVersion $url
done;


echo "Executing tests..."
mvn test -Psystem-tests -DtestFileName=xplan-manager-api-soapui-project.xml -DbaseUrlManagerApi=$XPLAN_MANAGER_API_BASE_URL -DbaseUrlServices=$XPLAN_DIENSTE_BASE_URL -DjdbcUrl=$JDBC_URL -Dusername=$XPLAN_MANAGER_API_USERNAME -Dpassword=$XPLAN_MANAGER_API_PASSWORD

mvn test -Psystem-tests -DtestFileName=xplan-validator-api-soapui-project.xml -Dendpoint=$XPLAN_VALIDATOR_API_BASE_URL/xvalidator/api/v1 -Dusername=$XPLAN_VALIDATOR_API_USERNAME -Dpassword=$XPLAN_VALIDATOR_API_PASSWORD

if [ -z ${XPLAN_API_DOKUMENTE_BASE_URL+x} ];
then
	echo "XPlanDokumentenAPI Tests are skipped!"
else
	waitForRightVersion $XPLAN_DOKUMENTE_API_BASE_URL/xdokumente/version.txt
	mvn test -Psystem-tests -DtestFileName=xplan-dokumente-api-soapui-project.xml -DbaseUrlManagerApi=$XPLAN_MANAGER_API_BASE_URL -DbaseUrlDokumentenApi=$XPLAN_DOKUMENTE_API_BASE_URL -Dusername=$XPLAN_MANAGER_API_USERNAME -Dpassword=$XPLAN_MANAGER_API_PASSWORD
fi

mvn test -Psystem-tests -DtestFileName=xplan-manager-web-soapui-project.xml -Dendpoint=$XPLAN_MANAGER_WEB_BASE_URL -Dusername=$XPLAN_MANAGER_WEB_USERNAME -Dpassword=$XPLAN_MANAGER_WEB_PASSWORD

mvn test -Psystem-tests -DtestFileName=xplan-webservices-soapui-project.xml -DbaseUrlServices=${XPLAN_DIENSTE_BASE_URL} -DbaseUrlInspirePlu=${XPLAN_BASE_URL_INSPIRE_PLU} -DbaseUrlManagerApi=${XPLAN_MANAGER_API_BASE_URL} -DbaseUrlMapServer=${XPLAN_MAPSERVER_BASE_URL} -DbaseUrlMapProxy=${XPLAN_MAPPROXY_BASE_URL} -Dusername=$XPLAN_MANAGER_API_USERNAME -Dpassword=$XPLAN_MANAGER_API_PASSWORD -DapiKey=$XPLAN_SERVICES_API_KEY

echo -e "\n"
echo "Results:"
results=`find target/soapui/ -name 'TEST-*.xml' | xargs cat | grep "<testsuite" | sed 's/>.*/>/' | sed -e 's/[">]//g' -e 's/.*name=//' | sort`
echo -e "$results"
echo -e "\n"


echo "Generating html report..."
mvn surefire-report:report-only -q

REPORT_PATH_PDF=target/test-report.pdf
echo "Transforming to PDF $REPORT_PATH_PDF..."
sed -i 's/display:none;//' target/site/surefire-report.html
weasyprint file://$PWD/target/site/surefire-report.html $REPORT_PATH_PDF
REPORT_PATH_TAR=target/test-report.tar.gz
tar cfz $REPORT_PATH_TAR -C target site soapui

if [ "$XPLAN_UPLOAD_TEST_REPORT" = "true" ];
then
	export AWS_ACCESS_KEY_ID="$XPLAN_S3_ACCESS_KEY"
	export AWS_SECRET_ACCESS_KEY="$XPLAN_S3_SECRET_ACCESS_KEY"
	export AWS_DEFAULT_REGION="$XPLAN_S3_REGION"
	XPLAN_S3_REPORT_ID="${XPLAN_S3_REPORT_ID:-`date +%y-%m-%dT%H:%m:%S`}"
	XPLAN_S3_REPORT_PATH="${XPLAN_S3_REPORT_PATH:-test-reports}"

	S3_PATH_TAR="s3://$XPLAN_S3_BUCKET_NAME/$XPLAN_S3_REPORT_PATH/report-$XPLAN_S3_REPORT_ID.tar.gz"
	S3_PATH_PDF="s3://$XPLAN_S3_BUCKET_NAME/$XPLAN_S3_REPORT_PATH/report-$XPLAN_S3_REPORT_ID.pdf"
	echo "Uploading $REPORT_PATH_TAR to $S3_PATH_TAR..."
	aws --endpoint-url $XPLAN_S3_ENDPOINT_URL s3 cp $REPORT_PATH_TAR $S3_PATH_TAR
	echo "Uploading report to $XPLAN_S3_REPORT_PATH..."
	aws --endpoint-url $XPLAN_S3_ENDPOINT_URL s3 cp $REPORT_PATH_PDF $S3_PATH_PDF
else
	echo "No upload to S3 configured"
fi


if [ -n "$XPLAN_NOTIFY_SLACK_CHANNEL" ] &&  [ -n "$XPLAN_NOTIFY_SLACK_TOKEN" ]; then
	echo "Sending slack notification to $XPLAN_NOTIFY_SLACK_CHANNEL"
	formattedResults=`echo -e "$results" | sed 's/^/%0A* /'`
	message="Finished SoapUI tests.%0A\
$formattedResults"
	if [ -n "$S3_PATH_PDF" ]; then
		message="$message%0A%0A\
Get test reports from S3 bucket with:%0A\
\`\`\`aws --endpoint-url $XPLAN_S3_ENDPOINT_URL s3 cp $S3_PATH_PDF .%0A\
aws --endpoint-url $XPLAN_S3_ENDPOINT_URL s3 cp $S3_PATH_TAR .\`\`\`"
	fi
	sendSlackMessage "$message"
fi

echo "Ready"
