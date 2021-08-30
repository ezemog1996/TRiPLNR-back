pipeline {
   agent any
   
    environment {
        PORT_HOST="8081"
        PORT_CONT="8080"
        IMAGE_TAG="triplnr-image"
        CONTAINER_NAME="triplnr-app"
        P2_DB_URL=credentials('P2_DB_URL')
        P2_DB_USER=credentials('P2_DB_USERNAME')
        P2_DB_PASS=credentials('P2_DB_PASS')
	MAPS_API_KEY=credentials('MAPS_API_KEY')
    }

   stages {
      stage('checkout'){
          steps {
            script {
                properties([pipelineTriggers([githubPush()])])
            }
            git branch: 'main', url: 'https://github.com/071921-LTI/TRiPLNR-back.git'

          }
      }
      stage('clean') {
         steps {
	    sh 'cd ~/TRiPLNR-back/triplnr2.0/'
            sh 'mvn clean'
         }
      }
      stage('package') {
         steps {
	    sh 'cd ~/TRiPLNR-back/triplnr2.0/'
            sh 'mvn package -Dmaven.test.skip=true'
         }
      }
      stage('remove previous image if exists') {
            steps {
                sh 'docker rmi -f ${IMAGE_TAG} || true'
            }
        }

       stage('create image') {
            steps {
		sh 'cd ~/TRiPLNR-back/triplnr2.0/'
                sh 'docker build -t ${IMAGE_TAG} -f Dockerfile .'
            }
        }
        stage('remove previous container if exists') {
            steps {
                sh 'docker stop ${CONTAINER_NAME} || true'
            }
        }
        stage('create container') {
            steps {
                sh 'docker run -e P2_DB_URL=${P2_DB_URL} -e P2_DB_USER=${P2_DB_USER} -e P2_DB_PASS=${P2_DB_PASS} -e MAPS_API_KEY=${MAPS_API_KEY} -d --rm -p ${PORT_HOST}:${PORT_CONT} --name ${CONTAINER_NAME} ${IMAGE_TAG} '
            }
        }
    }
}