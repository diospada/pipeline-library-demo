#!/usr/bin/env groovy

import com.carel.GlobalVars

 def call(Map params){
        echo "Installing ${params.jarName}.jar under  ${params.installationDir} as linux service named ${params.serviceName}"
        echo "Download script to install jar as linux service from ${GlobalVars.urlRepositoryWindowScript}"
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: GlobalVars.urlRepositoryWindowScript]]])
        fileOperations([fileCopyOperation(excludes: '', flattenFiles: true, includes: "linux/*.*", targetLocation: "${params.installationDir}")])
        //make sure you configure visudo to run this script without typing root password
        sh "sudo ${params.installationDir}/install_linux_service.sh ${params.serviceName} ${params.installationDir} ${params.jarName}"
 }






