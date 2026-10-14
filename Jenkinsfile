
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

        stage('Build Successful') {
            steps {
                echo 'GitHub code built successfully and all tests passed!'
            }
        }
    }
}
