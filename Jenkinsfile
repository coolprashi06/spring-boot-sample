pipeline {
    agent any
    tools {
        maven 'Maven 3.3.9'
        jdk 'jdk8'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Build') {
            steps {
                sh 'mvn clean install -s settings.xml',
                sh 'echo Now deploying to Artifcatory server'
                sh 'mkdir fileupload'
                sh 'cp target/spring-boot-sample-0.0.1-SNAPSHOT.jar fileupload/'
            }
        }

        stage('Deploy'){
            steps {
                sh 'curl -u myUser:myP455w0rd! -X PUT "http://localhost:8081/artifactory/libs-snapshot/com/prashast/spring-boot-sample" -T fileupload/spring-boot-sample-0.0.1-SNAPSHOT.jar'
                sh 'echo Deployed artifact to Artifactory server'
            }
        }
    }
}