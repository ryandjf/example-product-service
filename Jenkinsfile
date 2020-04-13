pipeline {
  agent {
    kubernetes {
      //cloud 'kubernetes'
      defaultContainer 'gradle'
      yaml """
apiVersion: v1
kind: Pod
spec:
  containers:
    - name: gradle
      image: gradle:6.3-jdk8
      command:
        - cat
      tty: true
      volumeMounts:
        - name: m2-cache-volume
          mountPath: "/root/.m2/repository"
    - name: kaniko
      image: gcr.io/kaniko-project/executor:debug
      imagePullPolicy: Always
      command:
        - /busybox/cat
      tty: true
      volumeMounts:
        - name: aws-secret
          mountPath: /root/.aws/
        - name: docker-registry-config
          mountPath: /kaniko/.docker
  volumes:
    - name: m2-cache-volume
      persistentVolumeClaim:
        claimName: maven-cache
    - name: aws-secret
      secret:
        secretName: aws-secret
    - name: docker-registry-config
      configMap:
        name: ecr-sea-docker-config
"""
    }
  }
  environment {
    SPRING_PROFILES_ACTIVE = 'jenkins'
  }
  stages {
    stage('Build'){
      steps{
        container('gradle'){
          sh 'gradle clean build'
          publishHTML(target : [
            allowMissing: false,
            alwaysLinkToLastBuild: false,
            keepAll: true,
            reportDir: 'build/reports',
            reportFiles: '**/*',
            reportName: 'Build Reports'])
        }
      }
    }
    stage('Build with Kaniko') {
      steps {
        container('kaniko'){
          sh '/kaniko/executor -f `pwd`/Dockerfile -c `pwd` --insecure --skip-tls-verify --destination=521593154378.dkr.ecr.ap-southeast-1.amazonaws.com/example-product-service:$BUILD_NUMBER'
        }
      }
    }
  }
}
