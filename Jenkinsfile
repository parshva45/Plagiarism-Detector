pipeline {
   agent {
       docker {
           image 'maven:3-alpine'
           args '-v /root/.m2:/root/.m2'
       }
   }
   stages {
       stage('Start') {
            steps {
              slackSend (color: '#FFFF00', message: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
            }
       }
       stage('Build') {
           steps {
               echo "Building"
               sh 'mvn compile'
           }
       }
       stage('Test'){
           steps {
               echo "Testing"
               sh 'mvn test'
           }
       }
        stage('SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh ' mvn clean install '
                    sh ' mvn sonar:sonar '
                }
            }
        }
        stage('Quality') {
            steps {
                sh 'sleep 30'
                timeout(time:10, unit:'SECONDS') {
                retry(5) {
                    script {
                    def qg = waitForQualityGate()
                    if (qg.status != 'OK') {
                        error "Pipeline aborted due to quality gate failure: ${qg.status}"
                            }
                           }
                    }
                }
            }
        }
   }

    post {
         success {
              slackSend(color: '#00FF00', message: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
              slackSend(message: '', attachments: "[{'fallback': 'Link to SonarQube Report', color: '#FF00FF', 'actions': [{'type': 'button', 'text': 'SonarQube Report', 'url': 'http://128.31.25.108:9000/dashboard?id=edu.northeastern.cs5500%3Acs5500-project-team207'}]}]")
          }
          failure {
              slackSend(color: '#FF0000', message: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
              slackSend(message: '', attachments: "[{'fallback': 'Link to SonarQube Report', color: '#FF00FF', 'actions': [{'type': 'button', 'text': 'SonarQube Report', 'url': 'http://128.31.25.108:9000/dashboard?id=edu.northeastern.cs5500%3Acs5500-project-team207'}]}]")
          }
        }
}
