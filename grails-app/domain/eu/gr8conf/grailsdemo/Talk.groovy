package eu.gr8conf.grailsdemo

class Talk {

    String title
    String presenter
    Date startsAt

    Date dateCreated
    Date lastUpdated

    static belongsTo = Attendee // Where the addTo method will work from i.e. cascading save
    static hasMany = [attendees: Attendee,ratings: Rating]

    static constraints = {
        title blank: false
        presenter blank: false
    }

    @Override
    String toString() {
        title
    }

}
