#!/usr/bin/env groovy
package com.carel

class GlobalVars {
    static String urlRepositoryWindowScript = "https://github.com/diospada/scriptToDeployJarAsWindowsService.git"

    // refer to this in a pipeline using:
    //
    // import com.carel.GlobalVars
    // println GlobalVars.urlRepositoryWindowScript
}
