pipeline {
    agent any 

    environment {
        DOCKER_USERNAME = credentials('jenkins-docker-secret-username')
        DOCKER_PASSWORD = credentials('jenkins-docker-secret-password')
        DOCKER_REGISTRY = 'ec2-52-81-55-170.cn-north-1.compute.amazonaws.com.cn:30500'
    }

    stages {
        stage('build') {
            steps {
                sh './gradlew clean build'
            }
        }
    }
}

