# SoapUI TestSuites

## Aufruf der SoapUI Tests mit mvn

### xplan-api-validator-soapui-project

> mvn clean test -Psystem-tests -DtestFileName=xplan-api-validator-soapui-project.xml -Dendpoint=https://xplanbox.lat-lon.de/xvalidator/api/v1 -Dusername=xplanbox -Dpassword='PWD'

### xplan-api-manager-soapui-project

> mvn clean test -Psystem-tests -DtestFileName=xplan-api-manager-soapui-project.xml -DbaseUrlManagerApi=https://xplanbox.lat-lon.de -DbaseUrlServices=https://xplanbox.lat-lon.de -DjdbcUrl=jdbc:postgresql://localhost:5433/xplanbox?user=xplanbox&password=xplanbox -Dusername=xplanbox -Dpassword='PWD'

### xplan-api-dokumente-soapui-project

> mvn clean test -Psystem-tests -DtestFileName=xplan-api-dokumente-soapui-project.xml -DbaseUrlManagerApi=https://xplanbox.lat-lon.de -DbaseUrlDokumentenApi=https://xplanbox.lat-lon.de -Dusername=xplanbox -Dpassword='PWD'

### xplan-manager-web-soapui-project

> mvn clean test -Psystem-tests -DtestFileName=xplan-manager-web-soapui-project.xml -Dendpoint=https://xplanbox.lat-lon.de/xplan-manager-web -Dusername=xplanbox -Dpassword='PWD'

### xplan-webservices-soapui-project

> mvn clean test -Psystem-tests -DtestFileName=xplan-webservices-soapui-project.xml -DbaseUrlServices=https://xplanbox.lat-lon.de -DbaseUrlInspirePlu=https://xplanbox.lat-lon.de -DbaseUrlManagerApi=https://xplanbox.lat-lon.de -DbaseUrlMapServer=https://xplanbox.lat-lon.de -Dusername=xplanbox -Dpassword='PWD'



## Ausführung im Docker container

Die SOAPUI Tests können in einem Docker Container ausgeführt werden

```
docker run --env ... xplanbox/xplan-tests-soapui
```

### Umgebungsvariablen

- `XPLAN_API_MANAGER_BASE_URL`
- `XPLAN_API_MANAGER_USERNAME`
- `XPLAN_API_MANAGER_PASSWORD`

- `XPLAN_API_VALIDATOR_ENDPOINT`

- `XPLAN_API_DOKUMENTE_BASE_URL` optional: XPlanDokumentenAPI Tests werden geskipped, wenn die Umgebungsvariable nicht gesetzt ist.

- `XPLAN_MANAGER_WEB_ENDPOINT`
- `XPLAN_MANAGER_WEB_USERNAME`
- `XPLAN_MANAGER_WEB_PASSWORD`

- `XPLAN_BASE_URL_DIENSTE`
- `XPLAN_BASE_URL_INSPIRE_PLU`
- `XPLAN_BASE_URL_MAPSERVER`

- `XPLAN_SERVICES_API_KEY`

Optional, wenn die Tests der XPlanDB bei Ausführung der XPlanManagerAPI-SopaUI-Tests ausgeführt werden sollen:
- `XPLAN_DB_HOSTNAME`
- `XPLAN_DB_PORT`
- `XPLAN_DB_NAME`
- `XPLAN_DB_USER`
- `XPLAN_DB_PASSWORD`


Der Report im PDF Format kann zu einem S3 Bucket hochgeladen werden, dafür müssen folgende Umgebungsvariable gesetzt werden:

- `XPLAN_UPLOAD_TEST_REPORT`: muss auf `true` gesetzt werden
- `XPLAN_S3_ENDPOINT_URL`: die S3 Url, z.B. https://the.s3.url
- `XPLAN_S3_REPORT_ID` (optional): ein Id, dass im S3-Objektname verwendet werden soll
- `XPLAN_S3_REPORT_PATH`(optional): der Pfad im S3 Bucket (default: `test-reports`)
- `XPLAN_S3_ACCESS_KEY`: der S3-Zugangschlüssel
- `XPLAN_S3_SECRET_ACCESS_KEY`: der S3-Geheimzugangschlüssel
- `XPLAN_S3_REGION`: die S3 Region
- `XPLAN_S3_BUCKET_NAME`: der Name des Buckets

Der Report kann aus S3 lokal kopiert werden, z,B. mit:

	aws s3 cp s3://my-bucket/test-reports/report-2023-05-26T08:57:15.pdf report.pdf --endpoint-url https://the.s3.url

Eine Notification kann nach der Ausführung der Tests zu einem Slack Chanel geschickt werden. Dafür müssen folgende Umgebungsvariable gesetzt werden:

- `XPLAN_NOTIFY_SLACK_CHANNEL`: der Slack Kanal
- `XPLAN_NOTIFY_SLACK_TOKEN`: das Slack Authorisierungstoken