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
            when {
                branch 'master'
            }
            steps {
                sh 'mvn --version'
                configFileProvider([configFile(fileId: 'maven_setings_ga', variable: 'MAVEN_SETTINGS_XML')]) {
                    sh 'mvn -B -s $MAVEN_SETTINGS_XML -Dresume=false release:prepare release:perform -DreleaseVersion=${releaseVersion} -DdevelopmentVersion=${developmentVersion} -DskipTests=true'
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