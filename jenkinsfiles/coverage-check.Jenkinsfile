pipeline {
  agent any
  triggers {
    cron('H 1 * * 1') // each Monday
  }
  options {
      disableConcurrentBuilds()
  }
  tools {
    maven 'maven-3.9'
    jdk 'temurin-jdk17'
  }
  stages {
    stage('Build with code coverage') {
      steps {
        withMaven(mavenLocalRepo: '.repository', maven: 'maven-3.9', options: [junitPublisher(healthScaleFactor: 1.0), jacocoPublisher()], publisherStrategy: 'EXPLICIT', mavenSettingsConfig: 'mvn-empty-settings') {
            sh 'mvn clean install -Djacoco.skip=false -Dmaven.test.failure.ignore=true -Ddependency-check.skip=true'
        }
      }
    }
  }
  post {
    failure {
      slackSend channel: '#build', message: "${env.JOB_NAME} failed, Buildnr: ${env.BUILD_NUMBER}", teamDomain: 'xplanbox', tokenCredentialId: 'slack-integration-id'
    }
    unstable {
      slackSend channel: '#build', message: "${env.JOB_NAME} unstable, Buildnr: ${env.BUILD_NUMBER}", teamDomain: 'xplanbox', tokenCredentialId: 'slack-integration-id'
    }
    always {
        cleanWs notFailBuild: true
        sh 'docker rmi -f $(docker images --filter="reference=xplanbox-trivy/*:*" -aq) || true'
    }
  }
}