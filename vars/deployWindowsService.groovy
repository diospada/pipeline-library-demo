#!/usr/bin/env groovy

import com.carel.GlobalVars

def call(Map params){
    echo "Test"
    String installationDirectory =   ${params.installationDir} + File.separator + ${params.serviceName}
    echo "Installing ${params.jarName}.jar under ${installationDirectory} as windows service named ${params.serviceName}"
    echo "Download script to install jar as windows service from ${GlobalVars.urlRepositoryWindowScript}"
    checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: GlobalVars.urlRepositoryWindowScript]]])
    fileOperations([fileCopyOperation(excludes: '', flattenFiles: true, includes: "windows/*.*", targetLocation: "${installationDirectory}")])
    dir("${installationDirectory}"){
        powershell label: '', script: "./deploy_service.ps1 -serviceName ${params.serviceName} -jarName ${params.jarName}"
    }
}





