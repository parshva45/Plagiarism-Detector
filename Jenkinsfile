pipeline {
   agent {
       docker {
           image 'maven:3-alpine'
           args '-v /root/.m2:/root/.m2'
       }
   }

   stages {
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
}
