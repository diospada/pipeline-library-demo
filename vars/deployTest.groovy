#!/usr/bin/env groovy

import com.carel.GlobalVars

 def call(Map params){

     echo "Installing ${params.jarName}.jar under  ${params.installationDir} as linux service named ${params.serviceName}"

 }






