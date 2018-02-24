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
       stage('Deploy') {
           steps {
               echo "Deploy"
           }
       }
   }
}
