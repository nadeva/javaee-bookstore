pipeline {
  agent any
    environment {
      GIT_COMMIT_ID = "${sh(returnStdout: true, script: 'git log --format="%H" -n 1 | cut -c -12')}"
    }

  stages {

        stage('Example') {
            input {
                message "Can you check http://localhost/bookstore?"
                ok "All good"
            }
            steps {
                echo "Hello, nice to meet you."
            }
        }
  }

}