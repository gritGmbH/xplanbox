pipeline {
  agent any
  triggers {
    cron('H 4 * * 1') // each Monday
  }
  options {
      disableConcurrentBuilds()
  }
  tools {
    maven 'maven-3.9'
    jdk 'temurin-jdk17'
  }
  environment {
      MAVEN_OPTS='-Djava.awt.headless=true -Xmx1250m'
  }
  stages {
    stage('Build docker images') {
      steps {
        withMaven(mavenLocalRepo: '.repository', mavenSettingsConfig: 'mvn-empty-settings', publisherStrategy: 'EXPLICIT') {
	        sh 'mvn -B -C -Pdocker,skipAll -Ddocker-image.namePrefix=xplanbox-trivy install'
        }
      }
    }
    stage('Trivy Image check') {
      steps {
        withMaven(mavenLocalRepo: '.repository', mavenSettingsConfig: 'mvn-empty-settings', publisherStrategy: 'EXPLICIT') {
	        sh 'mvn -B -C -Pdocker -Ddocker-image.namePrefix=xplanbox-trivy exec:exec@trivyScanForFixedIssues -fae'
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
