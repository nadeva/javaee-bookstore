pipeline {
  agent any
  environment {
    GIT_COMMIT_ID = "${sh(returnStdout: true, script: 'git log --format="%H" -n 1 | cut -c -12')}"
  }

  stages {

    stage('Build Java') {
      steps {
        slackSend message: "${JOB_NAME}: Starting build"
        sh './mvnw clean verify'
      }
    }
    stage('Build Docker image') {
      steps {
        sh "docker build . -f src/main/docker/Dockerfile -t oltruong/bookstore:$GIT_COMMIT_ID"
        sh "docker run -d -p 80:8080 --name testjenkins oltruong/bookstore:$GIT_COMMIT_ID"
        slackSend message: "${JOB_URL}: Can you check http://localhost/bookstore?"
      }
    }

    stage('Push Docker image') {
      input {
        message "Can you check http://localhost/bookstore?"
        ok "All good"
      }
      steps {
        slackSend message: "${JOB_NAME}: Pushing docker image"
        echo "Pushing image"
        sh "docker push oltruong/bookstore:$GIT_COMMIT_ID"
        sh "docker build . -f src/main/docker/Dockerfile -t oltruong/bookstore:latest"
        sh "docker push oltruong/bookstore:latest"
      }
    }
  }

  post {
    always {
      echo "Clean docker container"
      sh "docker stop testjenkins || true && docker rm testjenkins || true"
    }
  }

}