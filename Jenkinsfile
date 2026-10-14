pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Source code checked out from GitHub'
            }
        }

        stage('Build and Test') {
            steps {
                sh '''
                    docker run --rm \
                      --network ci-network \
                      -v /Users/ramanavanchanagiri/coding/ci-cd-demo:/app \
                      -w /app \
                      maven:3.9.10-eclipse-temurin-21 \
                      mvn clean verify
                '''
            }
        }

        stage('Build Successful') {
            steps {
                echo 'Java build and tests passed!'
            }
        }
    }
}
