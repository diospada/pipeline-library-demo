#!/usr/bin/env groovy

import com.carel.GlobalVars

def call(String serviceName, String jarName, String installationDir) {
    echo GlobalVars.urlRepositoryWindowScript
    checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: GlobalVars.urlRepositoryWindowScript]]])
    fileOperations([fileCopyOperation(excludes: '', flattenFiles: true, includes: "windows/*.*", targetLocation: "${installationDir}")])
    echo 'Install as windows service'
    dir("${installationDir}"){
        powershell label: '', script: "./deploy_service.ps1 -serviceName ${serviceName} -jarName ${jarName}"
        //uninstall the service if it is already present
        bat ".\\${serviceName} uninstall"
        bat ".\\${serviceName} install"
    }
}

