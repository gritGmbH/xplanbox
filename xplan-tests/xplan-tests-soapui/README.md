# SoapUI TestSuites

## Aufruf der SoapUI Tests mit mvn

### xplan-api-validator-soapui-project

	mvn clean test -Psystem-tests -DtestFileName=xplan-api-validator-soapui-project.xml -Dendpoint=https://xplanbox.lat-lon.de/xvalidator/api/v1 -Dusername=xplanbox -Dpassword='PWD'

### xplan-api-manager-soapui-project

> mvn clean test -Psystem-tests -DtestFileName=xplan-api-manager-soapui-project.xml -Dendpoint=https://xplanbox.lat-lon.de/xmanager/api/v1 -Dusername=xplanbox -Dpassword='PWD'

### xplan-manager-web-soapui-project

> mvn clean test -Psystem-tests -DtestFileName=xplan-manager-web-soapui-project.xml -Dendpoint=https://xplanbox.lat-lon.de/xplan-manager-web -Dusername=xplanbox -Dpassword='PWD'

### xplan-webservices-soapui-project

> mvn clean test -Psystem-tests -DtestFileName=xplan-webservices-soapui-project.xml -DbaseUrl=https://xplanbox.lat-lon.de -DbaseUrlInspirePlu=https://xplanbox.lat-lon.de -DbaseUrlManagerApi=https://xplanbox.lat-lon.de -DbaseUrlMapServer=https://xplanbox.lat-lon.de -Dusername=xplanbox -Dpassword='PWD'



## Ausführung im Docker container

Die SOAPUI Tests können in einem Docker Container ausgeführt werden

```
docker run --env ... xplanbox/xplan-tests-soapui
```

### Umgebungsvariablen

- `XPLAN_API_MANAGER_ENDPOINT`

- `XPLAN_API_MANAGER_USERNAME`
- `XPLAN_API_MANAGER_PASSWORD`

- `XPLAN_API_VALIDATOR_ENDPOINT`
- `XPLAN_API_VALIDATOR_USERNAME`
- `XPLAN_API_VALIDATOR_PASSWORD`

- `XPLAN_MANAGER_WEB_ENDPOINT`
- `XPLAN_MANAGER_WEB_USERNAME`
- `XPLAN_MANAGER_WEB_PASSWORD`

- `XPLAN_BASE_URL_DIENSTE`
- `XPLAN_BASE_URL_INSPIRE_PLU`
- `XPLAN_BASE_URL_API_MANAGER`
- `XPLAN_BASE_URL_MAPSERVER`
- `XPLAN_SERVICES_USERNAME`
- `XPLAN_SERVICES_PASSWORD`



Der Report im PDF Format kann zu einem S3 Bucket hochgeladen werden, dafür müssen folgende Umgebungsvariable gesetzt werden:

- `XPLAN_UPLOAD_TEST_REPORT`: muss auf `true` gesetzt werden
- `XPLAN_S3_ENDPOINT`: die S3 Url
- `XPLAN_S3_REPORT_ID` (optional): ein Id, dass im S3-Objektname verwendet werden soll
- `XPLAN_S3_REPORT_PATH`(optional): der Pfad im S3 Bucket (default: `test-reports`)
- `XPLAN_S3_ACCESS_KEY`: der S3-Zugangschlüssel
- `XPLAN_S3_SECRET_ACCESS_KEY`: der S3-Geheimzugangschlüssel
- `XPLAN_S3_REGION`: die S3 Region

Der Report kann aus S3 lokal kopiert werden, z,B. mit:

	aws s3 cp s3://my-bucket/test-reports/report-2023-05-26T08:57:15.pdf report.pdf --endpoint-url https://the.s3.url

Eine Notification kann nach der Ausführung der Tests zu einem Slack Chanel geschickt werden. Dafür müssen folgende Umgebungsvariable gesetzt werden:

- `XPLAN_NOTIFY_SLACK_CHANNEL`: der Slack Kanal
- `XPLAN_NOTIFY_SLACK_TOKEN`: das Slack Authorisierungstoken