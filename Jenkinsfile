pipeline {
    agent any

    tools {
           maven '3.9.9'
        }

        stages {
            stage('---clean---') {
                steps {
                    sh '''
                        ls -la
                        mvn clean
                        ls -la
                    '''
                }
            }

            stage('---compile---') {
                steps {
                    sh 'mvn compile'
                }
            }

            stage('---test---') {
                steps {
                    sh 'mvn test'
                }
            }
            stage('---package---') {
                steps {
                    sh '''
                        mvn package
                        ls -la
                    '''
                }
            }
        }
}