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
            when {
                allOf {
                    not {
                        changelog "jgitflow-*"
                    }
                    branch "develop"
                }
            }
            steps {
                sh 'git remote set-branches --add origin master'
                sh 'git fetch'
                withCredentials([[$class          : 'UsernamePasswordMultiBinding', credentialsId: 'github_login',
                                  usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD']]) {
                    sh 'mvn -B -Dgit.username=$GIT_USERNAME -Dgit.password=$GIT_PASSWORD clean -DskipTests jgitflow:release-start jgitflow:release-finish'
                }
            }
        }
    }
    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}