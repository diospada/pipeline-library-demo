#!/usr/bin/env groovy

import com.carel.GlobalVars

 def call(Map params){

     Boolean missinParameter = false
     for (String param in ["installationDir", "serviceName", "jarName"]) {
         if (params.containsKey(param)) {
             echo "Missing parameter ${param}"
             missinParameter = true
         }
     }
     if(missinParameter){
         System.exit(0)
     }else {
         echo "Installing ${params.jarName}.jar under  ${params.installationDir} as linux service named ${params.serviceName}"
     }
 }






