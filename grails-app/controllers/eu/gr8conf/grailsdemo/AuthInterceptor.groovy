package eu.gr8conf.grailsdemo

class AuthInterceptor {
    AuthInterceptor() {
        matchAll()
            .excludes(controller:"login")
    }

    boolean before() { 
        println "AuthInterceptor.before"
        true
    }


    boolean after() { 
        // println "AuthInterceptor.after"
        true 
    }

    void afterView() {
        // no-op
    }
}
