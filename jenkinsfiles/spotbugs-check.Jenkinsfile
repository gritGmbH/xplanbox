pipeline {
  agent any
  triggers {
    cron('H 3 * * 1') // each Monday
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
    stage('analyze') {
      steps {
        withMaven(mavenLocalRepo: '.repository', mavenSettingsConfig: 'mvn-empty-settings', publisherStrategy: 'EXPLICIT') {
            sh 'mvn -B -C install -PskipAll'
            sh 'mvn -B -C spotbugs:spotbugs'
        }
      }
    }
    stage('verify') {
      steps {
        recordIssues qualityGates: [[criticality: 'FAILURE', integerThreshold: 1, threshold: 1.0, type: 'TOTAL_HIGH']],
            sourceCodeEncoding: 'UTF-8',
            sourceCodeRetention: 'LAST_BUILD',
            tools: [spotBugs(reportEncoding: 'UTF-8', useRankAsPriority: true)]
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
