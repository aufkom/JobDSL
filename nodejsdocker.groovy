job('NodeJS Docker example with Push AUTO-GENERATED') {
    scm {
        git('git://github.com/adastrix/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('adastrix')
            node / gitConfigEmail('adastrix@gmail.com')
        }
    }
    triggers {
        scm('* * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('adastrix/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
