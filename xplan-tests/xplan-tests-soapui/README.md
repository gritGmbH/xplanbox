# SoapUI TestSuites

## Aufruf der SoapUI Tests mit mvn

### xplan-api-validator-soapui-project

> mvn clean test -Psystem-tests -DtestFileName=xplan-api-validator-soapui-project.xml -Dendpoint=https://xplanbox.lat-lon.de/xvalidator/api/v1 -Dusername=xplanbox -Dpassword='PWD'

### xplan-api-manager-soapui-project

> mvn clean test -Psystem-tests -DtestFileName=xplan-api-manager-soapui-project.xml -Dendpoint=https://xplanbox.lat-lon.de/xmanager/api/v1 -Dusername=xplanbox -Dpassword='PWD'

### xplan-manager-web-soapui-project

> mvn clean test -Psystem-tests -DtestFileName=xplan-manager-web-soapui-project.xml -Dendpoint=https://xplanbox.lat-lon.de/xplan-manager-web -Dusername=xplanbox -Dpassword='PWD'

### xplan-webservices-soapui-project

> mvn clean test -Psystem-tests -DtestFileName=xplan-webservices-soapui-project.xml -DbaseUrl=https://xplanbox.lat-lon.de -DbaseUrlInspirePlu=https://xplanbox.lat-lon.de -DbaseUrlManagerApi=https://xplanbox.lat-lon.de -DbaseUrlMapServer=https://xplanbox.lat-lon.de -Dusername=xplanbox -Dpassword='PWD'



