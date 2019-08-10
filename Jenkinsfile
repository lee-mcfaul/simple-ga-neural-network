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
        stage('Release') {
            steps{
                sh 'mvn --version'
                sh 'mvn -Dresume=false -DdryRun=true release:prepare -Psign-artifacts-with-ogc -DreleaseVersion=${releaseVersion} -DdevelopmentVersion=${developmentVersion}'
                //sh 'mvn -Dresume=false release:prepare release:perform -Psign-artifacts-with-ogc -DreleaseVersion=${releaseVersion} -DdevelopmentVersion=${developmentVersion}'
            }
        }
        stage('Publication of site') {
            steps{
                sh 'mvn --version'
                sh 'git checkout ${releaseVersion}'
                sh 'mvn clean install site site:stage scm-publish:publish-scm'
            }
        }
    }
    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}