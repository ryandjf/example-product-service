pipeline {
    agent any 

    environment {
        DOCKER_USERNAME = credentials('jenkins-docker-secret-username')
        DOCKER_PASSWORD = credentials('jenkins-docker-secret-password')
        DOCKER_REGISTRY = 'ryandjf'
        SPRING_PROFILES_ACTIVE = 'jenkins'
    }

    stages {
        stage('sonar') {
            steps{
                sh './gradlew -Dsonar.host.url=http://sonarqube-sonarqube.devops.svc.cluster.local:9000 -Dsonar.login=a3ab71edd867aecee1f936e34bb5babcc648c275 sonarqube'
            }
        }
        stage('build') {
            steps {
                sh './gradlew clean build'
            }
        }
        stage('push-image') {
            steps {
                sh '''
                BUILD_VERSION_NUMBER=0.1.1
                ./gradlew jibDockerBuild
                docker tag net.thoughtworks/example-product-service:latest $DOCKER_REGISTRY/example-product-service:$BUILD_VERSION_NUMBER
                docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD'
                docker push $DOCKER_REGISTRY/example-product-service:$BUILD_VERSION_NUMBER
                '''
            }
        }
    }
}
