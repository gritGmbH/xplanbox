#!/bin/bash
set -e

XPLAN_VALIDATOR_WEB_BASE_URL=${XPLAN_VALIDATOR_WEB_BASE_URL:-http://xplan-validator-web/xplan-validator-web}
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

echo "Waiting for services with git revision $GIT_REVISION:"
waitForRightVersion $XPLAN_VALIDATOR_WEB_BASE_URL/version.txt

# start virtual buffer
Xvfb :0 -screen 0 1024x768x24 &
xvfbPid=$!

echo "Executing tests..."
DISPLAY=:0 mvn integration-test -Psystem-tests -DbaseUrlValidatorWeb=$XPLAN_VALIDATOR_WEB_BASE_URL -Dusername=$XPLAN_VALIDATOR_WEB_USERNAME -Dpassword=$XPLAN_VALIDATOR_WEB_PASSWORD -Dwebdriver.chrome.driver=/usr/bin/chromedriver
kill -9 $xvfbPid

echo "Generating html report..."
mvn surefire-report:failsafe-report-only -q

REPORT_PATH_PDF=target/test-report.pdf
echo "Transforming to PDF $REPORT_PATH_PDF..."
sed -i 's/display:none;//' target/site/failsafe-report.html
weasyprint file://$PWD/target/site/failsafe-report.html $REPORT_PATH_PDF
REPORT_PATH_TAR=target/test-report.tar.gz
tar cfz $REPORT_PATH_TAR -C target site failsafe-reports

if [ "$XPLAN_UPLOAD_TEST_REPORT" = "true" ];
then
	export AWS_ACCESS_KEY_ID="$XPLAN_S3_ACCESS_KEY"
	export AWS_SECRET_ACCESS_KEY="$XPLAN_S3_SECRET_ACCESS_KEY"
	export AWS_DEFAULT_REGION="$XPLAN_S3_REGION"
	XPLAN_S3_REPORT_ID="${XPLAN_S3_REPORT_ID:-`date +%y-%m-%dT%H:%m:%S`}"
	XPLAN_S3_REPORT_PATH="${XPLAN_S3_REPORT_PATH:-test-reports}"

	S3_PATH_TAR="s3://$XPLAN_S3_BUCKET_NAME/$XPLAN_S3_REPORT_PATH/report_selenium-$XPLAN_S3_REPORT_ID.tar.gz"
	S3_PATH_PDF="s3://$XPLAN_S3_BUCKET_NAME/$XPLAN_S3_REPORT_PATH/report_selenium-$XPLAN_S3_REPORT_ID.pdf"
	echo "Uploading $REPORT_PATH_TAR to $S3_PATH_TAR..."
	aws --endpoint-url $XPLAN_S3_ENDPOINT_URL s3 cp $REPORT_PATH_TAR $S3_PATH_TAR
	echo "Uploading report to $XPLAN_S3_REPORT_PATH..."
	aws --endpoint-url $XPLAN_S3_ENDPOINT_URL s3 cp $REPORT_PATH_PDF $S3_PATH_PDF
else
	echo "No upload to S3 configured"
fi


if [ -n "$XPLAN_NOTIFY_SLACK_CHANNEL" ] &&  [ -n "$XPLAN_NOTIFY_SLACK_TOKEN" ]; then
	echo "Sending slack notification to $XPLAN_NOTIFY_SLACK_CHANNEL"
	message="Finished Selenium tests"
	if [ -n "$S3_PATH_PDF" ]; then
		message="$message%0A%0A\
Get test reports from S3 bucket with:%0A\
\`\`\`aws --endpoint-url $XPLAN_S3_ENDPOINT_URL s3 cp $S3_PATH_PDF .%0A\
aws --endpoint-url $XPLAN_S3_ENDPOINT_URL s3 cp $S3_PATH_TAR .\`\`\`"
	fi
	sendSlackMessage "$message"
fi

echo "Ready"
