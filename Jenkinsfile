
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

        stage('Build Successful') {
            steps {
                echo 'Build, tests, and JAR artifact archival completed!'
            }
        }
    }
}
