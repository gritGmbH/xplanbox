pipeline {
   agent any
   tools {
      maven 'maven-3.9'
      jdk 'openjdk11'
   }
   parameters {
      string(name: 'BRANCH', defaultValue: "main", description: 'Set branch')
      string(name: 'CREDENTIALS_ID', defaultValue: "xplanbox.lat-lon.de", description: 'Set id of Jenkins credentials to login to environment (BASIC AUTH)')
      string(name: 'SERVICES_API_KEY', defaultValue: "xplanbox", description: 'Set ApiKey to acces /config of XPlanDienste')
      string(name: 'ENDPOINT_VALIDATOR_API', defaultValue: "https://xplanbox.lat-lon.de/xvalidator/api/v1", description: 'Set endpoint of XPlanValidatorAPI')
      string(name: 'BASE_URL_DIENSTE', defaultValue: "https://xplanbox.lat-lon.de", description: 'Set base URL of XPlanDienste')
      string(name: 'BASE_URL_INSPIRE_PLU', defaultValue: "https://xplanbox.lat-lon.de", description: 'Set base URL of INSPIRE PLU')
      string(name: 'BASE_URL_MANAGER_API', defaultValue: "https://xplanbox.lat-lon.de", description: 'Set base URL of XPlanManagerAPI')
      string(name: 'BASE_URL_VALIDATOR_WEB', defaultValue: "https://xplanbox.lat-lon.de/xplan-validator-web", description: 'Set base URL of XPlanValidatorWeb')
      string(name: 'BASE_URL_DOKUMENTE_API', defaultValue: "https://xplanbox.lat-lon.de", description: 'Set base URL of XPlanDokumenteAPI')
      string(name: 'BASE_URL_MAPSERVER', defaultValue: "https://xplanbox.lat-lon.de", description: 'Set base URL of MapServer')
      string(name: 'BASE_URL_MAPPROXY', defaultValue: "https://xplanbox.lat-lon.de", description: 'Set base URL of MapProxy')
      string(name: 'SLACK_CHANNEL', defaultValue: "#installationen", description: 'Set slack channel')
      string(name: 'SLACK_TESTED_ENVIRONMENT', defaultValue: "https://xplanbox.lat-lon.de/", description: 'Set name of test environment which shall be printed in slack messages')
      string(name: 'SLACK_TEAM_DOMAIN', defaultValue: "xplanbox", description: 'Set slack team domain')
      string(name: 'SLACK_TOKEN_CREDENTIAL_ID', defaultValue: "slack-integration-id", description: 'Set slack token credential id')
   }
   stages {
      stage('SoapUI-Test XPlanManagerAPI') {
         steps {
            withCredentials([
               usernamePassword(credentialsId:"${CREDENTIALS_ID}", passwordVariable: 'Password', usernameVariable: 'Username')
            ]) {
               sh 'mvn test -pl :xplan-tests-soapui -Psystem-tests -DtestFileName=xplan-manager-api-soapui-project.xml -DbaseUrlManagerApi=${BASE_URL_MANAGER_API} -DbaseUrlServices=${BASE_URL_DIENSTE} -Dusername=$Username -Dpassword=$Password'
            }
         }
      }
      stage('SoapUI-Test XPlanValidatorAPI') {
         steps {
            withCredentials([
               usernamePassword(credentialsId:"${CREDENTIALS_ID}", passwordVariable: 'Password', usernameVariable: 'Username')
            ]) {
               sh 'mvn test -pl :xplan-tests-soapui -Psystem-tests -DtestFileName=xplan-validator-api-soapui-project.xml -Dendpoint=${ENDPOINT_VALIDATOR_API} -Dusername=$Username -Dpassword=$Password'
            }
         }
      }
      stage('SoapUI-Test XPlanDokumenteAPI') {
         steps {
            withCredentials([
               usernamePassword(credentialsId:"${CREDENTIALS_ID}", passwordVariable: 'Password', usernameVariable: 'Username')
            ]) {
               sh 'mvn test -pl :xplan-tests-soapui -Psystem-tests -DtestFileName=xplan-dokumente-api-soapui-project.xml -DbaseUrlManagerApi=${BASE_URL_MANAGER_API} -DbaseUrlDokumenteApi=${BASE_URL_DOKUMENTE_API} -Dusername=$Username -Dpassword=$Password'
            }
         }
      }
      stage('SoapUI-Test XPlanDienste') {
         steps {
            withCredentials([
               usernamePassword(credentialsId:"${CREDENTIALS_ID}", passwordVariable: 'Password', usernameVariable: 'Username')
            ]) {
               sh 'mvn test -pl :xplan-tests-soapui -Psystem-tests -DtestFileName=xplan-webservices-soapui-project.xml -DbaseUrlServices=${BASE_URL_DIENSTE} -DbaseUrlInspirePlu=${BASE_URL_INSPIRE_PLU} -DbaseUrlManagerApi=${BASE_URL_MANAGER_API} -DbaseUrlMapServer=${BASE_URL_MAPSERVER} -DbaseUrlMapProxy=${BASE_URL_MAPPROXY} -Dusername=$Username -Dpassword=$Password -DapiKey=${SERVICES_API_KEY}'
            }
         }
      }
      stage('Selenium-Test XPlanValidatorWeb') {
         steps {
            withCredentials([
               usernamePassword(credentialsId:"${CREDENTIALS_ID}", passwordVariable: 'Password', usernameVariable: 'Username')
            ]) {
               sh 'mvn integration-test -pl :xplan-tests-selenium -Psystem-tests -DbaseUrlValidatorWeb=${BASE_URL_VALIDATOR_WEB} -Dusername=$Username -Dpassword=$Password'
            }
         }
      }
      stage('Results') {
         steps{
            junit '**/**/target/soapui/TEST-*.xml'
            junit '**/**/target/failsafe-reports/TEST-*.xml'
         }
      }
   }
   post {
      success {
         slackSend channel: "${SLACK_CHANNEL}", message: "Die SoapUI Tests (Branch: ${BRANCH}) wurden ohne Fehler gegen die Testumgebung ${SLACK_TESTED_ENVIRONMENT} ausgefuehrt.", teamDomain: "${SLACK_TEAM_DOMAIN}", tokenCredentialId: "${SLACK_TOKEN_CREDENTIAL_ID}"
      }
      failure {
         slackSend channel: "${SLACK_CHANNEL}", message: "ACHTUNG: Bei der Ausfuehrung der SoapUI Tests (Branch: ${BRANCH}) gegen die Testumgebung ${SLACK_TESTED_ENVIRONMENT} traten Fehler auf.", teamDomain: "${SLACK_TEAM_DOMAIN}", tokenCredentialId: "${SLACK_TOKEN_CREDENTIAL_ID}"
      }
      unstable {
         slackSend channel: "${SLACK_CHANNEL}", message: "ACHTUNG: Bei der Ausfuehrung der SoapUI Tests (Branch: ${BRANCH}) gegen die Testumgebung ${SLACK_TESTED_ENVIRONMENT} traten Fehler auf.", teamDomain: "${SLACK_TEAM_DOMAIN}", tokenCredentialId: "${SLACK_TOKEN_CREDENTIAL_ID}"
      }
      always {
          cleanWs notFailBuild: true
      }
   }
}
