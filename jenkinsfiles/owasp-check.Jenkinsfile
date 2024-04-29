pipeline {
  agent any
  triggers {
    cron('H 4 * * 1') // each Monday
  }
  tools {
    maven 'maven-3.9'
    jdk 'openjdk11'
  }
  stages {
    stage('Build with owasp check') {
      steps{
        withMaven(mavenLocalRepo: '.repository', mavenSettingsConfig: 'mvn-empty-settings', options: [junitPublisher(healthScaleFactor: 1.0)], publisherStrategy: 'EXPLICIT') {
          sh 'mvn -B -C clean install -Ddependency-check.skip=false'
        }
      }
    }
  }
  post {
    always {
      junit '**/target/surefire-reports/*.xml'
      dependencyCheckPublisher pattern: '**/target/dependency-check-report.xml'
    }
    failure {
      slackSend channel: '#build', message: "${env.JOB_NAME} failed, Buildnr: ${env.BUILD_NUMBER}", teamDomain: 'xplanbox', tokenCredentialId: 'slack-integration-id'
    }
    unstable {
      slackSend channel: '#build', message: "${env.JOB_NAME} unstable, Buildnr: ${env.BUILD_NUMBER}", teamDomain: 'xplanbox', tokenCredentialId: 'slack-integration-id'
    }
  }
}