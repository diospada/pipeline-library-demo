#!/usr/bin/env groovy

def call(String serviceName, String jarName, String installationDir) {
    echo 'Install as windows service'
    fileOperations([fileCopyOperation(excludes: '', flattenFiles: true, includes: "windows/*.*", targetLocation: "${installationDir}")])
    dir("${installationDir}"){
        powershell label: '', script: "./deploy_service.ps1 -serviceName ${serviceName} -jarName ${jarName}"
        //unistall the service if it is already present
        bat ".\\${serviceName} uninstall"
        bat ".\\${serviceName} install"
    }
}

