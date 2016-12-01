package eu.gr8conf.grailsdemo

class TalkController {

    static scaffold = Talk
        def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Talk.list(params), model:[talkCount: Talk.count()]
    }


}
