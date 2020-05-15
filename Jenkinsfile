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
        - name: gradle-cache-volume
          mountPath: "/root/.gradle/caches"
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
    - name: kubectl
      image: lachlanevenson/k8s-kubectl:v1.14.10
      command:
        - cat
      tty: true
    - name: helm
      image: lachlanevenson/k8s-helm:v3.1.2
      command:
        - cat
      tty: true
  volumes:
    - name: m2-cache-volume
      persistentVolumeClaim:
        claimName: maven-cache
    - name: gradle-cache-volume
      persistentVolumeClaim:
        claimName: gradle-cache
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
        }
      }
      post{
        always{
          publishHTML(target : [
            allowMissing: false,
            alwaysLinkToLastBuild: false,
            keepAll: true,
            reportDir: 'build/reports',
            reportFiles: '**/*',
            reportName: 'Build Reports'])
          influxDbPublisher(selectedTarget: '4KeyMetrics_InfluxDB', jenkinsEnvParameterField: 'step=BUILD\n environment=DEV')
        }
      }
    }

    stage('Scan with SonarQube') {
      steps {
        container('gradle') {
          sh "gradle -Dsonar.host.url=http://sonarqube-sonarqube.devops.svc.cluster.local:9000 sonarqube"
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

    stage('Run with Helm') {
      steps {
        container('helm') {
          sh "helm upgrade product-service-release --install --namespace dev --set rbac.create=true --set image.tag=$BUILD_NUMBER ./charts/example-product-service"
        }
      }
    }

    stage('Run with Kubectl') {
      steps {
        container('kubectl') {
          sh "kubectl get pods -n dev"
          sh "kubectl get svc -n dev"
        }
      }
    }

  }
}
