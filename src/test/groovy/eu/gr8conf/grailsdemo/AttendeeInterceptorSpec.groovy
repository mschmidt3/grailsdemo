package eu.gr8conf.grailsdemo


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(AttendeeInterceptor)
class AttendeeInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test attendee interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"attendee")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
