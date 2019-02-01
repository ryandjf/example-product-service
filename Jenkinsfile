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
        stage('push-image') {
            steps {
                sh '''
                BUILD_VERSION_NUMBER=0.1.1
                ./gradlew docker
                docker tag ryandjf/example-product-service:latest $DOCKER_REGISTRY/example-product-service:$BUILD_VERSION_NUMBER
                docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD'
                docker push $DOCKER_REGISTRY/example-product-service:$BUILD_VERSION_NUMBER
                '''
            }
        }
    }
}
