pipeline {
    agent any
    environment {
        // Define Docker Hub credentials ID
        DOCKERHUB_CREDENTIALS_ID = 'Docker_hub_jenkins'
        // Define Docker Hub repository name
        DOCKERHUB_REPO = 'jess3/trip_calculator'
        // Define Docker image tag
        DOCKER_IMAGE_TAG = 'latest_v1'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'mail', url: 'https://github.com/J3-ss3/TripCalculator.git'
            }
        }
        stage('Verify Path') {
            steps {
                withEnv(["PATH+EXTRA=/bin:/usr/bin"]) {
                    sh 'pwd' // Print the current working directory
                    sh 'ls -la' // List the contents of the directory
                }
            }
        }
        stage('Build') {
            steps {
                withEnv(["PATH+EXTRA=/bin:/usr/bin"]) {
                    sh 'mvn clean install'
                }
            }
        }
        stage('Test') {
            steps {
                withEnv(["PATH+EXTRA=/bin:/usr/bin"]) {
                    sh 'mvn test'
                }
            }
        }
        stage('Code Coverage') {
            steps {
                withEnv(["PATH+EXTRA=/bin:/usr/bin"]) {
                    sh 'mvn jacoco:report'
                }
            }
        }
        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                }
            }
        }
        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                        docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                    }
                }
            }
        }
    }
}