#!/usr/bin/env groovy

import com.carel.GlobalVars

def call(Map params){
    echo "Installing ${params.jarName}.jar under  ${params.installationDir} as windows service named ${params.serviceName}"
    echo "Download script to install jar as windows service from ${GlobalVars.urlRepositoryWindowScript}"
    checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: GlobalVars.urlRepositoryWindowScript]]])
    fileOperations([fileCopyOperation(excludes: '', flattenFiles: true, includes: "windows/*.*", targetLocation: "${params.installationDir}")])
    dir("${params.installationDir}"){
        //uninstall the service if it is already present
        bat ".\\${params.serviceName}.exe uninstall"
        bat ".\\${params.serviceName}.exe install"
    }
}

