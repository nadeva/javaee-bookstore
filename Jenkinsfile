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
        echo "docker build -f src/main/docker/Dockerfile -t oltruong/bookstore:$GIT_COMMIT_ID ."
        sh "docker build -f src/main/docker/Dockerfile -t oltruong/bookstore:$GIT_COMMIT_ID ."
        sh "docker run -d -p 80:8080 --name testjenkins oltruong/bookstore:$GIT_COMMIT_ID"
        slackSend message:'['+env.BUILD_TAG+'] Can you check http://localhost/bookstore ?'
      }
    }
        stage('Example') {
            input {
                message "Should we continue?"
                ok "Yes, we should."
                submitter "alice,bob"
                parameters {
                    string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
                }
            }
            steps {
                echo "Hello, ${PERSON}, nice to meet you."
            }
        }
  }

}