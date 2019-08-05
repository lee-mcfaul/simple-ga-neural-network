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
                            sh 'mvn test -p UnitTest'
                        },
                        functional: {
                            sh 'mvn test -p FunctionalTest'
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