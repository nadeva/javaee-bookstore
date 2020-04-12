pipeline {
  agent any
    environment {
      GIT_COMMIT_ID = "${sh(returnStdout: true, script: 'git log --format="%H" -n 1 | cut -c -12')}"
    }

  stages {
    stage('Build Java') {
      steps {
        slackSend message: "Starting build ${JOB_NAME}"
        sh './mvnw clean verify'
      }
    }
    stage('Build Docker') {
      steps {
        sh "docker build -t oltruong/bookstore:$GIT_COMMIT_ID ."
        sh "docker run -d -p 80:8080 --name testjenkins oltruong/bookstore:$GIT_COMMIT_ID"
        slackSend message:'['+env.BUILD_TAG+'] Can you check http://localhost/bookstore ?'
      }
    }
    stage('Check Docker') {
        input {
         message: "http://localhost/bookstore. All Good?"
         ok: 'Go ahead'
        }
      steps {
        sh "docker stop testjenkins"
        sh "docker rm testjenkins"
      }
    }
  }

}