pipeline {
    agent any

    tools {
        maven '3.9.9'
    }

    stages {
        stage('clean') {
            steps {
               echo 'cleaning..'
               sh 'mvn clean'
            }
        }
        stage('Build') {
            steps {
               echo 'Building..'
               sh 'mvn install'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                sh 'mvn deploy'
            }
        }
    }
}