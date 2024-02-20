# Selenium Tests

Die Tests in diesem Projekt sind nicht gedacht, um direkt als Teil vom Build ausgeführt zu werden sondern eher zu einem späteren Zeitpunkt, wenn die Anwendungen schon deployt worden sind.

## Ausführung mit Maven

```
mvn clean integration-test -Psystem-tests -DbaseUrlValidatorWeb=https://xplanbox.lat-lon.de/xplan-validator-web -Dpassword='PWD' -Dusername=xplanbox
```

## Ausführung im Docker Container

Die Selenium Tests können in einem Docker Container ausgeführt werden

```
docker run --env XPLAN_VALIDATOR_WEB_BASE_URL=... xplanbox/xplan-tests-selenium
```

### Umgebungsvariablen

- `XPLAN_VALIDATOR_WEB_BASE_URL`
- `XPLAN_VALIDATOR_WEB_USERNAME`
- `XPLAN_VALIDATOR_WEB_PASSWORD`

Der Report im PDF Format kann zu einem S3 Bucket hochgeladen werden, dafür müssen folgende Umgebungsvariablen gesetzt werden:

- `XPLAN_UPLOAD_TEST_REPORT`: muss auf `true` gesetzt werden
- `XPLAN_S3_ENDPOINT`: die S3 Url
- `XPLAN_S3_REPORT_ID` (optional): ein Id, dass im S3-Objektname verwendet werden soll
- `XPLAN_S3_REPORT_PATH`(optional): der Pfad im S3 Bucket (default: `test-reports`)
- `XPLAN_S3_ACCESS_KEY`: der S3-Zugangschlüssel
- `XPLAN_S3_SECRET_ACCESS_KEY`: der S3-Geheimzugangschlüssel
- `XPLAN_S3_REGION`: die S3 Region
- `XPLAN_S3_BUCKET_NAME`: der Name des Buckets

Der Report kann aus S3 lokal kopiert werden, z,B. mit:

	aws s3 cp s3://my-bucket/test-reports/report-2023-05-26T08:57:15.pdf report.pdf --endpoint-url https://the.s3.url

Eine Notification kann nach der Ausführung der Tests zu einem Slack Chanel geschickt werden. Dafür müssen folgende Umgebungsvariablen gesetzt werden:

- `XPLAN_NOTIFY_SLACK_CHANNEL`: der Slack Kanal
- `XPLAN_NOTIFY_SLACK_TOKEN`: das Slack Authorisierungstoken
