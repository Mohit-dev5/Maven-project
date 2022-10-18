#!/usr/bin/env groovy

@Library('javahome-demo') _

pipeline {
  agent any
  
    parameters {
        choice(name: "GITHUB_URL", choices: ["production-repo-URL", "staging-repo-URL", "https://github.com/Mohit-dev5/Maven-project.git"], 
                    description: "Sample multi-choice parameter to pick the GITHUB URL")
        choice(name: "BRANCH_NAME", choices: ["Groovy-script", "main", "Dev", "Prod", "test", "qa","stage"],
                    description: "Sample multi-choice parameter to give branch name")
               }
	
    stages {
        stage('Git Checkout') {
            steps {
            gitCheckout(
                branch: "$BRANCH_NAME",
                url: "$GITHUB_URL" )
            }
        }
        stage ('Repo Content'){
          steps {
            sh 'ls -lart'
            sh 'tree'
            sh 'pwd'
            }     
        }
    }
}
