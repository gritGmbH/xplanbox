#!/bin/bash
set -e

function sendSlackMessage() {
	local message=$1
	curl -d "text=$message" -d "channel=$XPLAN_NOTIFY_SLACK_CHANNEL" -H "Authorization: Bearer $XPLAN_NOTIFY_SLACK_TOKEN" -X POST https://slack.com/api/chat.postMessage
}

if [ -n "$XPLAN_NOTIFY_SLACK_CHANNEL" ] &&  [ -n "$XPLAN_NOTIFY_SLACK_TOKEN" ]; then
	sendSlackMessage "Starting SoapUI tests for $XPLAN_API_MANAGER_BASE_URL and Co..."
fi

if [ -z ${XPLAN_DB_HOSTNAME+x} ]; then
  echo "XPLAN_DB_HOSTNAME is not set. jdbcUrl is not used in xplan-api-manager SoapUI Tests"
else
	echo "XPLAN_DB_HOSTNAME is set, jdbcUrl is created and used for xplan-api-manager SoapUI Tests"
	JDBC_URL="jdbc:postgresql://$XPLAN_DB_HOSTNAME:$XPLAN_DB_PORT/$XPLAN_DB_NAME?user=$XPLAN_DB_USER&password=$XPLAN_DB_PASSWORD"
fi

echo "Executing tests..."
mvn test -Psystem-tests -DtestFileName=xplan-api-manager-soapui-project.xml -DbaseUrlManagerApi=$XPLAN_API_MANAGER_BASE_URL -DbaseUrlServices=$XPLAN_BASE_URL_DIENSTE -DjdbcUrl=$JDBC_URL -Dusername=$XPLAN_API_MANAGER_USERNAME -Dpassword=$XPLAN_API_MANAGER_PASSWORD

mvn test -Psystem-tests -DtestFileName=xplan-api-validator-soapui-project.xml -Dendpoint=$XPLAN_API_VALIDATOR_ENDPOINT -Dusername=$XPLAN_API_VALIDATOR_USERNAME -Dpassword=$XPLAN_API_VALIDATOR_PASSWORD

if [ -z ${XPLAN_API_DOKUMENTE_BASE_URL+x} ];
then
	echo "XPlanDokumentenAPI Tests are skipped!"
else
	mvn test -Psystem-tests -DtestFileName=xplan-api-dokumente-soapui-project.xml -DbaseUrlManagerApi=$XPLAN_API_MANAGER_BASE_URL -DbaseUrlDokumentenApi=$XPLAN_API_DOKUMENTE_BASE_URL -Dusername=$XPLAN_API_MANAGER_USERNAME -Dpassword=$XPLAN_API_MANAGER_PASSWORD
fi

mvn test -Psystem-tests -DtestFileName=xplan-manager-web-soapui-project.xml -Dendpoint=$XPLAN_MANAGER_WEB_ENDPOINT -Dusername=$XPLAN_MANAGER_WEB_USERNAME -Dpassword=$XPLAN_MANAGER_WEB_PASSWORD

mvn test -Psystem-tests -DtestFileName=xplan-webservices-soapui-project.xml -DbaseUrlServices=${XPLAN_BASE_URL_DIENSTE} -DbaseUrlInspirePlu=${XPLAN_BASE_URL_INSPIRE_PLU} -DbaseUrlManagerApi=${XPLAN_API_MANAGER_BASE_URL} -DbaseUrlMapServer=${XPLAN_BASE_URL_MAPSERVER} -Dusername=$XPLAN_API_MANAGER_USERNAME -Dpassword=$XPLAN_API_MANAGER_PASSWORD -DapiKey=$XPLAN_SERVICES_API_KEY

echo -e "\n"
echo "Results:"
results=`find target/soapui/ -name 'TEST-*.xml' | xargs cat | grep "<testsuite" | sed 's/>.*/>/' | sed -e 's/[">]//g' -e 's/.*name=//'`
echo -e "$results"
echo -e "\n"


echo "Generating html report..."
mvn surefire-report:report-only -q

REPORT_PATH_PDF=target/test-report.pdf
echo "Transforming to PDF $REPORT_PATH_PDF..."
sed -i 's/display:none;//' target/site/surefire-report.html
weasyprint file://$PWD/target/site/surefire-report.html $REPORT_PATH_PDF
REPORT_PATH_TAR=target/test-report.tar.gz
tar cfz $REPORT_PATH_TAR -C target/site .

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
	message="Finished SoapUI tests for $XPLAN_API_MANAGER_BASE_URL and Co.%0A\
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
