
pipeline {
    agent any

    stages {
        stage('Checkout from GitHub') {
            steps {
                sh 'git log -1 --oneline'
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean verify'
            }
        }

        stage('Archive JAR Artifact') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar',
                                 fingerprint: true
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t ci-cd-demo:1.0 .'
            }
        }

        stage('Build Successful') {
            steps {
                echo 'Tests passed, JAR archived, and Docker image built!'
            }
        }
    }
}
