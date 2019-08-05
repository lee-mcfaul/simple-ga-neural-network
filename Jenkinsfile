pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn compile'
            }
        }
        stage('Test') {
            steps {
                parallel(
                        unit: {
                            sh 'mvn test -P UnitTest'
                        },
                        functional: {
                            sh 'mvn test -P FunctionalTest'
                        }
                )
            }
        }
        stage('Deploy') {
            steps {
                echo '*not* Deploying....'
            }
        }
    }
}