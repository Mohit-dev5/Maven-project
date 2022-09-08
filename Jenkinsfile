#!/usr/bin/env groovy

@Library('javahome-demo')
def gv

pipeline {
  agent any
     
   environment {
        registryName = "maven-registry"
        registryCredential = 'ACR'
        dockerImage = ''
        registryUrl = 'defsloc.azurecr.io'
    }
    
    stages {
        stage("init") {
            steps {
                script {
                    sh "ls -la"
                    sh "pwd"
                    sh "whoami"
                    sh "ls -la target/"
                    gv = load "jenkins/script.groovy"
                }
            }
        }
      
          stage('sonar') { 
            steps {
              withSonarQubeEnv(credentialsId: 'mysorarqube', installationName: 'sample_java') { 
              build("Sonar")
         }
      }
 }
        stage("Build jar") {
            steps {
                script {
                    echo "building jar"
                      build("Clean")
                    //gv.buildJar()
                }
            }
        }

        stage('Generate Junit Test Report') {
            steps {
              script {
                    echo "generating report"
                     build("Test") 
                    //gv.testReport()
                }
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
      
        stage('Building the Jar') {
            steps {
              script {
                    echo "creating jar in the target folder"
                     build("Install") 
                }
            }
        }
      
        stage("Build Image") {
            steps {
                script {
                    echo "building image"
                      buildImage()
                    //gv.buildImage()
                }
            }
        }
      
        
        // Uploading Docker images into ACR
        stage('Upload Image to ACR') {
           steps {   
             script {
                 echo "Pushing Build image into ACR"
                 pushImage()
                 echo "After Pushing Build image into ACR"
                 //gv.pushImage()
            }
          }
        }
      
        // Stopping Docker containers for cleaner Docker run
         stage('Stop Previous Containers') {
             steps {
               script {
                gv.destroy()
                }
             }
           }

        stage('Docker Run') {
         steps{
             script {
                gv.runImage()
                }
             }
         }
      
        stage('Remove Unused docker image') {
           steps{
             script {
                gv.deleteImage()
              }
           }
        }
      
        stage("Deploy to Environment") {
            steps {
                script {
                    echo "deploying"
                    gv.deployApp()
                }
            }
        }
    }   
}
