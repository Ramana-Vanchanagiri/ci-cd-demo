
pipeline {
    agent any

    stages {
        stage('Checkout from GitHub') {
            steps {
                checkout scm
                sh 'git log -1 --oneline'
            }
        }

        stage('Build and Test') {
            steps {
                sh '''
                    docker run --rm \
                      --network ci-network \
                      -v "$WORKSPACE:/app" \
                      -w /app \
                      maven:3.9.10-eclipse-temurin-21 \
                      mvn clean verify
                '''
            }
        }

        stage('Build Successful') {
            steps {
                echo 'GitHub code built successfully and all tests passed!'
            }
        }
    }
}
