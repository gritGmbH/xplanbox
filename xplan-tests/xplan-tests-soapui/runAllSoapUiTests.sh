#!/bin/bash

echo "Executing tests..."
mvn test -Psystem-tests -DtestFileName=xplan-api-manager-soapui-project.xml -Dendpoint=$XPLAN_API_MANAGER_ENDPOINT -Dusername=$XPLAN_API_MANAGER_USERNAME -Dpassword=$XPLAN_API_MANAGER_PASSWORD

mvn test -Psystem-tests -DtestFileName=xplan-api-validator-soapui-project -Dendpoint=$XPLAN_API_VALIDATOR_ENDPOINT -Dusername=$XPLAN_API_VALIDATOR_USERNAME -Dpassword=$XPLAN_API_VALIDATOR_PASSWORD

mvn test -Psystem-tests -DtestFileName=xplan-manager-web-soapui-project.xml -Dendpoint=$XPLAN_MANAGER_WEB_ENDPOINT -Dusername=$XPLAN_MANAGER_WEB_USERNAME -Dpassword=$XPLAN_MANAGER_WEB_PASSWORD

mvn test -Psystem-tests -DtestFileName=xplan-webservices-soapui-project.xml -DbaseUrl=${XPLAN_BASE_URL_DIENSTE} -DbaseUrlInspirePlu=${XPLAN_BASE_URL_INSPIRE_PLU} -DbaseUrlManagerApi=${XPLAN_BASE_URL_API_MANAGER} -DbaseUrlMapServer=${XPLAN_BASE_URL_MAPSERVER} -Dusername=$XPLAN_SERVICES_USERNAME -Dpassword=$XPLAN_SERVICES_PASSWORD


echo -e "\n"
find target/soapui/ -name 'TEST-*TestSuite.xml' | xargs cat | grep "<testsuite" | sed 's/>.*/>/'
echo -e "\n"


echo "Generating html report..."
mvn surefire-report:report-only -q

REPORT_PATH=target/test-report.pdf
echo "Transforming to PDF $REPORT_PATH..."
sed -i 's/display:none;//' target/site/surefire-report.html
weasyprint file://$PWD/target/site/surefire-report.html $REPORT_PATH


if [ "$XPLAN_UPLOAD_TEST_REPORT" = "true" ];
then
	export AWS_ACCESS_KEY_ID="$XPLAN_S3_ACCESS_KEY"
	export AWS_SECRET_ACCESS_KEY="$XPLAN_S3_SECRET_ACCESS_KEY"
	export AWS_DEFAULT_REGION="$XPLAN_S3_REGION"
	XPLAN_S3_REPORT_ID="${XPLAN_S3_REPORT_ID:-`date +%y-%m-%dT%H:%m:%S`}"
	XPLAN_S3_REPORT_PATH="${XPLAN_S3_REPORT_PATH:-test-reports}"
	S3_PATH="s3://$XPLAN_S3_BUCKET_NAME/$XPLAN_S3_REPORT_PATH/report-$XPLAN_S3_REPORT_ID.pdf"
	echo "Uploading report to $S3_PATH..."
	aws --endpoint-url $XPLAN_S3_ENDPOINT s3 cp $REPORT_PATH $S3_PATH
else
	echo "No upload to S3 configured"
fi


if [ -n "$XPLAN_NOTIFY_SLACK_CHANNEL" ] &&  [ -n "$XPLAN_NOTIFY_SLACK_TOKEN" ]; then
	echo "Sending slack notification to $XPLAN_NOTIFY_SLACK_CHANNEL"
	message="Finished SoapUI tests for $XPLAN_API_MANAGER_ENDPOINT and Co."
	if [ -n "$S3_PATH" ]; then
		echo "s3"
		message="$message%0A%0ATest Report available at $S3_PATH"
	fi
	curl -d "text=$message" -d "channel=$XPLAN_NOTIFY_SLACK_CHANNEL" -H "Authorization: Bearer $XPLAN_NOTIFY_SLACK_TOKEN" -X POST https://slack.com/api/chat.postMessage
fi

echo "Ready"
