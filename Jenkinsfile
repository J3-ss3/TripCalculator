pipeline {
    agent any
    tools {
        maven 'MVN'
        dockerTool 'docker'
    }
    environment {
        DOCKERHUB_CREDENTIALS_ID = 'dockercred'
        DOCKERHUB_REPO = 'jess3/trip_calculator'
        DOCKER_IMAGE_TAG = 'latest_v1'
        PATH = "/usr/local/bin:$PATH"
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'mail', url: 'https://github.com/J3-ss3/TripCalculator.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Code Coverage') {
            steps {
                sh 'mvn jacoco:report'
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
        stage('Verify Docker Installation') {
            steps {
                sh 'docker --version'
            }
        }
        stage('Check Network Connectivity') {
                    steps {
                        sh 'curl -v https://index.docker.io/v1/'
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
                    withEnv(["PATH+DOCKER=/usr/local/bin"]) {
                        docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                            docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                        }
                    }
                }
            }
        }
    }
}