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
        // Étape Checkout (Clonage du code)
        stage('Checkout SCM') {
            steps {
                echo 'Checking out the project from SCM...'
                git branch: 'main', url: "${env.GIT_REPO}"
            }
        }

        // Étape Build (Construction du projet avec Maven)
        stage('Build') {
            steps {
                echo 'Building the project using Maven...'
                sh 'mvn clean package'
            }
        }

        // Étape Run Tests (Exécution des tests Maven)
        stage('Run Tests') {
            steps {
                echo 'Running Maven tests...'
                sh 'mvn test'
            }
        }

        // Étape Build Docker Image (Construction de l'image Docker)
        stage('Build Docker Image') {
            steps {
                echo 'Building Docker image...'
                sh '''
                docker build -t ${DOCKER_USERNAME}/${DOCKER_IMAGE_NAME}:latest .
                '''
            }
        }

        // Étape Push Docker Image (Pousser l'image Docker vers DockerHub)
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

    // Post Actions (Actions après le pipeline)
    post {
        success {
            echo 'Pipeline executed successfully!' // Succès
        }
        failure {
            echo 'Pipeline failed. Please check the logs.' // Échec
        }
    }
}
