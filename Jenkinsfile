pipeline {
    agent any

    tools {
        jdk   'Java_17'        // Nom du JDK configuré dans Jenkins
        maven 'Maven_3.8.6'    // Nom de l'installation Maven configurée dans Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                // Récupérer le code source depuis Git
                git branch: 'main',
                    credentialsId: 'mon-credential-id',  // Identifiant Jenkins si repo privé
                    url: 'https://github.com/mon-user/mon-repo.git'
            }
        }

        stage('Build') {
            steps {
                // Compiler le projet (vous pouvez retirer -DskipTests si vous voulez exécuter les tests ici)
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                // Exécuter les tests unitaires
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            // Archive les rapports de tests JUnit
            junit '**/target/surefire-reports/*.xml'
        }
        success {
            echo 'Build et tests réussis !'
        }
        failure {
            echo 'Build ou tests en échec.'
        }
    }
}
