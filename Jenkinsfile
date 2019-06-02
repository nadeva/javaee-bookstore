node {
    stage ('Checkout')

    slackSend message:'['+env.BUILD_TAG+']Starting Job'

//  Greatly inspired from https://github.com/alecharp/simple-app
    checkout scm
    sh 'git rev-parse HEAD > GIT_COMMIT'
    short_commit = readFile('GIT_COMMIT').trim().take(7)
    sh 'rm GIT_COMMIT'

    currentBuild.description = "${short_commit}"

    stage ('Build')
    withMaven {
        sh './mvnw clean verify'
    }

    step([$class: 'JUnitResultArchiver', testResults: 'target/surefire-reports/*.xml'])
    step([$class: 'JUnitResultArchiver', testResults: 'target/failsafe-reports/*.xml'])

    dir('target') {
        archive "bookstore.war"
    }

    slackSend message:'Build OK, now building Docker'

    stage ('Build Docker')
    stash name: 'binary', includes: "target/bookstore.war"
    dir('src/main/docker') {
        stash name: 'dockerfile', includes: 'Dockerfile'
    }
}

node {
    unstash 'dockerfile'
    unstash 'binary'

    stage ('Building Docker Img')

    sh "docker build -t oltruong/bookstore:${short_commit} ."
    sh "docker run -d -p 80:8080 --name testjenkins oltruong/bookstore:${short_commit}"
}

stage 'Container validation'
try {
    slackSend message:'['+env.BUILD_TAG+'] Can you check http://localhost/bookstore ?'
    input message: "http://localhost/bookstore. All Good?", ok: 'Go ahead'
} finally {
    node() {
        sh "docker stop testjenkins"
        sh "docker rm testjenkins"
    }
}

node {
    slackSend message:'['+env.BUILD_TAG+'] OK, pushing Docker image'

    sh "docker push oltruong/bookstore:${short_commit}"

    stage 'Building latest image'
    sh "docker build -t oltruong/bookstore:latest ."
    sh "docker push oltruong/bookstore:latest"
    slackSend message:'['+env.BUILD_TAG+'] Pushing done :checkered_flag:'
}

// Custom step
def withMaven(def body) {
    def javaHome = tool name: 'current', type: 'hudson.model.JDK'

    withEnv(["JAVA_HOME=${javaHome}", "PATH+MAVEN=${mavenHome}/bin"]) {
        body.call()
    }
}
