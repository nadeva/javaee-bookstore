pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        slackSend message: "Starting build ${JOB_NAME}"
        sh './mvnw clean verify'
      }
    }
  }

}