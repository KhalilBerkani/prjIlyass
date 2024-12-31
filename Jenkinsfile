pipeline {
    agent any

    // Définir les outils (versions de Java et Maven doivent être préconfigurées dans Jenkins)
    tools {
        jdk 'Java_17'          // Nom de l'installation JDK configurée dans Jenkins
        maven 'Maven_3.8.6'    // Nom de l'installation Maven configurée dans Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                // Récupération du code source depuis Git
                git branch: 'main',
                    credentialsId: 'git-credentials-id',  // À remplacer par votre credentialsId Jenkins
                    url: 'https://github.com/KhalilBerkani/prjIlyass'  // À remplacer par l’URL de votre repo
            }
        }

        stage('Build') {
            steps {
                // Compiler le projet sans exécuter les tests
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                // Lancer les tests unitaires
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            // Publier les rapports de test JUnit afin d'avoir la vue de test dans Jenkins
            junit '**/target/surefire-reports/*.xml'
        }
        success {
            echo "Le build et les tests unitaires ont été exécutés avec succès."
        }
        failure {
            echo "Le build ou les tests unitaires ont échoué."
        }
    }
}
