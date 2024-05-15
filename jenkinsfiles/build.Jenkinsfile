pipeline {
  agent any
  options {
      disableConcurrentBuilds()
  }
  tools {
    maven 'maven-3.9'
    jdk 'openjdk11'
  }
  environment {
      MAVEN_OPTS='-Djava.awt.headless=true -Xmx1250m'
  }
  stages {
    stage('Preparation') {
      steps {
        sh 'mvn --version'
      }
    }
    stage('Build') {
      steps {
        withMaven(mavenLocalRepo: '.repository', mavenSettingsConfig: 'mvn-empty-settings', options: [junitPublisher(healthScaleFactor: 1.0)], publisherStrategy: 'EXPLICIT') {
            sh 'mvn -B -C clean install -Ddependency-check.skip=true -Pdistribution'
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
      }
  }
}
