package ims

import be.cytomine.client.Cytomine
import be.cytomine.client.CytomineConnection
import be.cytomine.client.collections.Collection
import be.cytomine.client.models.DeleteCommand
import grails.converters.JSON
import grails.util.Holders

class DeleteImageFileJob {
    static triggers = {
    }


    def execute() {
        log.info "Execute DeleteImageFile job"

        String cytomineUrl = Holders.config.cytomine.ims.server.core.url
        println "ok cytomineUrl : $cytomineUrl"
        String pubKey = Holders.config.cytomine.ims.server.publicKey
        println "ok pubKey : $pubKey"
        String privKey = Holders.config.cytomine.ims.server.privateKey
        println "ok privKey : $privKey"
        CytomineConnection imsConn = Cytomine.connection(cytomineUrl, pubKey, privKey, true)
        println "ok imsConn : " + imsConn.toString()

        long timeMargin = Holders.config.cytomine.ims.deleteJob.frequency * 1000 * 2
        println "ok timeMargin : $timeMargin"
        //max between frequency*2 and 48h
        timeMargin = Math.max(timeMargin, 172800000L)
        println "ok timeMargin : $timeMargin"
        Collection<DeleteCommand> commands = new Collection<DeleteCommand>(DeleteCommand.class, 0, 0)
        println "ok commands : " + commands.toString()
        commands.addParams("domain", "uploadedFile")
        println "ok commands : " + commands.toString()
        commands.addParams("after", (new Date().time - timeMargin).toString())
        println "ok commands : " + commands.toString()
//        commands = commands.fetch()
        println "ok commands : " + commands.toString()
        log.info commands.toString()
        println "ok commands : " + commands.toString()

        for (int i = 0; i < commands.size(); i++) {
            DeleteCommand command = (DeleteCommand) commands.list.get(i)
            println "ok command : " + command.toString()
            def data = JSON.parse(command.get("data") as String)

            File fileToDelete = new File(data.path)
            if (fileToDelete.exists()) {
                log.info "Delete file " + fileToDelete.absolutePath
                fileToDelete.delete()
            }
        }
    }
}
