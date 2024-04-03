pipeline {
    agent any
    tools {
        maven 'M2_HOME'
    }
    stages {
        stage('GIT') {
            steps {
                git branch: 'cyrine-chouchane',
                url: 'https://github.com/Phenix0103/5INFINI2-G1-Projet2.git'
            }
        }
        stage('MVN CLEAN') {
            steps {
                sh 'mvn clean';
            }
        }
        stage('MVN COMPILE') {
            steps {
                sh 'mvn compile';
            }
        }
        stage('MVN SONARQUBE') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=cyrine -Dmaven.test.skip=true';
            }
        }
        stage('MOCKITO'){
            steps {
                 sh 'mvn test';
            }
        }
        stage('NEXUS'){
            steps {
                 sh 'mvn deploy';
            }
        }
        stage('Docker') {
            steps {
                script {
                    // Build the Docker image with Jenkins BUILD_NUMBER as the version
                    sh 'docker build -t kaddemimage:v${BUILD_NUMBER} -f Dockerfile ./'
                    
                    // Tagging the Docker image for Docker Hub
                    sh 'docker tag kaddemimage:v${BUILD_NUMBER} ceceyphoenix/projetdevops:v${BUILD_NUMBER}'

                    // Login to Docker Hub (Ensure Docker Hub credentials are configured in Jenkins)
                    // The 'dockerhubcredentials' should be the ID of your Docker Hub credentials stored in Jenkins
                    sh 'docker login --username ceceyphoenix --password Princesseflora1'
                    
                    // Push the Docker image to Docker Hub
                    sh 'docker push ceceyphoenix/projetdevops:v${BUILD_NUMBER}'
                    
                    // Run Docker Compose
                    sh "IMAGE_VERSION=v${BUILD_NUMBER} docker compose up -d"
                }
            }
        }
        stage('Grafana') {
            steps {
                sh 'docker compose up -d'
            }
        }
    }
    post {
        success {
            mail to: 'chouchanecyrine@gmail.com',
                 subject: "SUCCESS: Pipeline ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "The pipeline ${env.JOB_NAME} #${env.BUILD_NUMBER} completed successfully."
        }
        failure {
            mail to: 'chouchanecyrine@gmail.com',
                 subject: "FAILURE: Pipeline ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "The pipeline ${env.JOB_NAME} #${env.BUILD_NUMBER} failed."
        }
        unstable {
            mail to: 'chouchanecyrine@gmail.com',
                 subject: "UNSTABLE: Pipeline ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "The pipeline ${env.JOB_NAME} #${env.BUILD_NUMBER} is unstable."
        }
        aborted {
            mail to: 'chouchanecyrine@gmail.com',
                 subject: "ABORTED: Pipeline ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "The pipeline ${env.JOB_NAME} #${env.BUILD_NUMBER} was aborted."
        }
    }
}
