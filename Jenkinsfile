/* Requires the Docker Pipeline plugin */
pipeline {
    agent any
    stages {
		stage('SonarQube analysis'){
			steps {
				sh 'mvn clean package sonar:sonar -X -DskipTests -Dsonar.projectKey=com.rttbanking:cardmanagement -Dsonar.host.url=http://192.168.1.100:9000 -Dsonar.login=squ_d27f56461df9b43d67a62102f29a18e75bad65a2 > log.txt'
			}
		}
    }
}