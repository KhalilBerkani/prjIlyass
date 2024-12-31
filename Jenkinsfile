pipeline {
    agent any

    tools {
        // Remplacez par les noms exacts configurés dans "Global Tool Configuration"
        jdk 'Java_17'
        maven 'Maven_3.8.6'
    }

    stages {
        stage('Checkout') {
            steps {
                // Cloner le dépôt Git
                git branch: 'main',
                    credentialsId: 'votre-credential-id', // Si repo privé, sinon retirez cette ligne
                    url: 'https://github.com/mon-utilisateur/mon-repo.git'
            }
        }

        stage('Build') {
            steps {
                // Compile l'application sans exécuter les tests
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                // Exécute les tests unitaires et d'intégration
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            // Archiver les rapports de tests pour affichage dans Jenkins
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
