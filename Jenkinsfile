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
                parallel {
                    unit: {
                        sh 'mvn test -Dtestcase.groups=com.lee.mcfaul.github.simple.ga.neural.network.categories.Categories.UnitTest'
                    }
                    functional: {
                        sh 'mvn test -Dtestcase.groups=com.lee.mcfaul.github.simple.ga.neural.network.categories.Categories.FunctionalTest'
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                echo '*not* Deploying....'
            }
        }
    }
}