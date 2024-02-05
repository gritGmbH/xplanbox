#!/bin/bash

# start virtual buffer
Xvfb :0 -screen 0 1024x768x24 &
xvfbPid=$!

echo "Executing tests..."
DISPLAY=:0 mvn integration-test -Psystem-tests -DbaseUrlValidatorWeb=$XPLAN_WEB_VALIDATOR_BASE_URL -Dusername=$XPLAN_WEB_VALIDATOR_USERNAME -Dpassword=$XPLAN_WEB_VALIDATOR_PASSWORD -Dwebdriver.chrome.driver=/usr/bin/chromedriver
kill -9 $xvfbPid

echo "Generating html report..."
mvn surefire-report:failsafe-report-only -q

REPORT_PATH_PDF=target/test-report.pdf
echo "Transforming to PDF $REPORT_PATH_PDF..."
sed -i 's/display:none;//' target/site/failsafe-report.html
weasyprint file://$PWD/target/site/failsafe-report.html $REPORT_PATH_PDF
REPORT_PATH_TAR=target/test-report.tar.gz
tar czf $REPORT_PATH_TAR -C target/site .

if [ "$XPLAN_UPLOAD_TEST_REPORT" = "true" ];
then
	export AWS_ACCESS_KEY_ID="$XPLAN_S3_ACCESS_KEY"
	export AWS_SECRET_ACCESS_KEY="$XPLAN_S3_SECRET_ACCESS_KEY"
	export AWS_DEFAULT_REGION="$XPLAN_S3_REGION"
	XPLAN_S3_REPORT_ID="${XPLAN_S3_REPORT_ID:-`date +%y-%m-%dT%H:%m:%S`}"
	XPLAN_S3_REPORT_PATH="${XPLAN_S3_REPORT_PATH:-test-reports}"

	S3_PATH_REPORT_PATH_TAR="s3://$XPLAN_S3_BUCKET_NAME/$XPLAN_S3_REPORT_PATH/report_selenium-$XPLAN_S3_REPORT_ID.tar.gz"
	S3_PATH_PDF="s3://$XPLAN_S3_BUCKET_NAME/$XPLAN_S3_REPORT_PATH/report_selenium-$XPLAN_S3_REPORT_ID.pdf"
	echo "Uploading report to $S3_PATH..."
	aws --endpoint-url $XPLAN_S3_ENDPOINT s3 cp $REPORT_PATH_TAR $S3_PATH_REPORT_PATH_TAR
	aws --endpoint-url $XPLAN_S3_ENDPOINT s3 cp $REPORT_PATH_PDF $S3_PATH_PDF
else
	echo "No upload to S3 configured"
fi


if [ -n "$XPLAN_NOTIFY_SLACK_CHANNEL" ] &&  [ -n "$XPLAN_NOTIFY_SLACK_TOKEN" ]; then
	echo "Sending slack notification to $XPLAN_NOTIFY_SLACK_CHANNEL"
	message="Finished Selenium tests for $XPLAN_WEB_VALIDATOR_BASE_URL and Co."
	if [ -n "$S3_PATH" ]; then
		echo "s3"
		message="$message%0A%0ATest Report available at $S3_PATH"
	fi
	curl -d "text=$message" -d "channel=$XPLAN_NOTIFY_SLACK_CHANNEL" -H "Authorization: Bearer $XPLAN_NOTIFY_SLACK_TOKEN" -X POST https://slack.com/api/chat.postMessage
fi

echo "Ready"
