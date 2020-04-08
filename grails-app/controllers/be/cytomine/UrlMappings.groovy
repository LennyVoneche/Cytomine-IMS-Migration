package be.cytomine

class UrlMappings {

    static mappings = {

        "/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')

        /* Storage controller */

        "/upload"(controller:"storage"){
            action = [POST:"upload"]
        }

//        "/uploadCrop" (controller:"image") {
//            action = [POST:"uploadCrop"]
//        }

        "/storage/size.$format"(controller:"storage"){
            action = [GET:"size"]
        }


        /* Image controller */

        "/image/associated.$format" (controller:"image") {
            action = [GET:"associated"]
        }

        "/image/nested.$format" (controller:"image") {
            action = [GET:"nested", POST:"nested"]
        }

        "/image/properties.$format" (controller:"image") {
            action = [GET:"properties"]
        }

        "/image/download" (controller: "image") {
            action = [GET: "download"]
        }

        /* Slice controller */

        "/slice/thumb.$format" (controller:"slice") {
            action = [GET:"thumb", POST:"thumb"]
        }

        "/slice/crop.$format" (controller:"slice") {
            action = [GET:"crop", POST:"crop"]
        }

        "/slice/tile" (controller: "slice") {
            action = [GET:"tile"]
        }

        "/slice/histogram.$format" (controller: "slice") {
            action = [GET:"histogram"]
        }

        /* Profile controller */

        "/profile.$format" (controller: "profile") {
            action = [POST: "computeProfile", GET: "extractProfile"]
        }
    }
}
