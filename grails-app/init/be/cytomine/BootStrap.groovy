package be.cytomine

import grails.util.Holders
import ims.DeleteImageFileJob

class BootStrap {

    def init = { servletContext ->
        log.info "Cytomine IMS configuration:"
        Holders.config.flatten().each {
            if ((it.key as String).startsWith("cytomine"))
                log.info "${it.key}: ${it.value}"
        }
        if (!Holders.config.cytomine.ims.server.url) {
            throw new IllegalArgumentException("cytomine.ims.server.url is not set !")
        }
        if (!Holders.config.cytomine.ims.server.privateKey) {
            throw new IllegalArgumentException("cytomine.ims.server.privateKey is not set!")
        }
        if (!Holders.config.cytomine.ims.server.publicKey) {
            throw new IllegalArgumentException("cytomine.ims.server.publicKey is not set!")
        }
        if (Holders.config.cytomine.ims.server.core.url && Holders.config.cytomine.ims.deleteJob.frequency) {
            DeleteImageFileJob.schedule((Holders.config.cytomine.ims.deleteJob.frequency as Long) * 1000 as long , -1, [:])
        }
    }
    def destroy = {
    }
}
