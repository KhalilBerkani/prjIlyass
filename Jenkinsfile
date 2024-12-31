pipeline {
    agent any

    environment {
        DOCKER_USERNAME = 'berkanikhalil'
        DOCKER_IMAGE_NAME = 'prjilyass'
        GIT_REPO = 'https://github.com/KhalilBerkani/prjIlyass'
        MAVEN_HOME = tool 'Maven 3.8.7' 
    }

    tools {
        maven 'Maven 3.8.7' 
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Cloning project from GitHub...'
                git branch: 'main', url: "${env.GIT_REPO}"
            }
        }

        stage('Build Project') {
            steps {
                echo 'Building the project using Maven...'
                sh 'mvn clean package'
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running Maven tests...'
                sh 'mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building Docker image...'
                sh '''
                docker build -t ${DOCKER_USERNAME}/${DOCKER_IMAGE_NAME}:latest .
                '''
            }
        }

        stage('Push Docker Image') {
            steps {
                echo 'Pushing Docker image to DockerHub...'
                withCredentials([string(credentialsId: 'docker-hub-password', variable: 'DOCKER_PASSWORD')]) {
                    sh '''
                    echo $DOCKER_PASSWORD | docker login -u ${DOCKER_USERNAME} --password-stdin
                    docker push ${DOCKER_USERNAME}/${DOCKER_IMAGE_NAME}:latest
                    '''
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed. Please check the logs.'
        }
    }
}
