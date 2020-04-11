def label = 'builder'

podTemplate(label: label, containers: [
    containerTemplate(
        name: 'gradle',
        image: 'gradle:6.3-jdk8',
        command: 'cat',
        ttyEnabled: true),
    containerTemplate(
        name: 'kaniko',
        image: 'gcr.io/kaniko-project/executor:debug',
        alwaysPullImage:true,
        command:'/busybox/sh -c',
        args:'/busybox/cat',
        ttyEnabled: true)
//     containerTemplate(
//         name: 'mysql',
//         image: 'mysql:5.7',
//         alwaysPullImage: false,
//         command:'cat',
//         args:'',
//         resourceRequestCpu: '50m',
//         resourceLimitCpu: '100m',
//         resourceRequestMemory: '100Mi',
//         resourceLimitMemory: '200Mi',
//         ttyEnabled: true,
//         envVars: [
//             envVar(key: 'MYSQL_DATABASE', value: 'example_db'),
//             envVar(key: 'MYSQL_ROOT_PASSWORD', value: 'abcd1234')
//         ],
//         ports: [
//             portMapping(name: 'mysql', containerPort: 3306, hostPort: 3306)
//         ]
//         )
    ], volumes: [
        persistentVolumeClaim(mountPath: '/root/.m2/repository', claimName: 'maven-cache', readOnly: false),
        secretVolume(secretName: 'docker-config-secret', mountPath: '/kaniko/.docker')
    ], envVars:[
        envVar(key: 'SPRING_PROFILES_ACTIVE', value: 'jenkins')
    ]) {

    node(label) {
        checkout scm

        stage('Build') {
            container('gradle') {
                sh 'gradle build'
                publishHTML(target : [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'build/reports',
                    reportFiles: '**/*',
                    reportName: 'Build Reports'])
            }
        }
        stage('Build and push image') {
            container('kaniko') {
                sh '/kaniko/executor -f `pwd`/Dockerfile -c `pwd` --insecure --skip-tls-verify --destination=ryandjf/example-product-service:latest'
            }
        }
        // stage('push-image') {
        //     steps {
        //         sh '''
        //         BUILD_VERSION_NUMBER=0.1.1
        //         ./gradlew jibDockerBuild
        //         docker tag net.thoughtworks/example-product-service:latest $DOCKER_REGISTRY/example-product-service:$BUILD_VERSION_NUMBER
        //         docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD'
        //         docker push $DOCKER_REGISTRY/example-product-service:$BUILD_VERSION_NUMBER
        //         '''
        //     }
        // }
    }
}
