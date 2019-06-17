#!/usr/bin/env groovy

def call(Map pipelineParams) {
    pipeline {

        agent "master"

        environment {
            JAVA_HOME="${tool 'java_8_192'}"
            PATH="${JAVA_HOME}/bin:${PATH}"
            BRANCH="${env.BRANCH_NAME}"
        }

        stages {
            stage('Checkout') {
                steps {
                    git branch: "${BRANCH}", credentialsId: 'gitlab', url: pipelineParams.repoUrl
                }
            }

            stage('Package') {
                steps {
                    withMaven(maven : 'maven_3.6.0', mavenSettingsConfig: 'maven-settings') {
                        sh 'mvn -U -B clean install'
                    }
                    step( [$class: 'ArtifactArchiver', artifacts: '**/target/*.jar', fingerprint: true] )
                }
            }
        }
    }
}




