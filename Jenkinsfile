pipeline {
    agent any

    tools {
        jdk 'jdk-17'          // Changez ce nom pour correspondre à votre configuration réelle
        maven 'maven-3.6'     // Changez ce nom pour correspondre à votre configuration réelle
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    credentialsId: 'votre-credential-id',  // Retirez si repo public
                    url: 'https://github.com/KhalilBerkani/prjIlyass'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
        success {
            echo 'Build et tests réussis.'
        }
        failure {
            echo 'Le build ou les tests ont échoué.'
        }
    }
}
