package defpackage

import grails.plugins.rest.client.RestBuilder
import spock.lang.Shared
import spock.lang.Specification

class LoginSpec extends Specification implements RestQueries {

    void 'calling /api/validate with a valid token returns a JSON representation'() {

        given:
        def authResponse = sendCorrectCredentials(APP_URL)

		when:
        def response = restBuilder.get("${APP_URL}/api/validate") {
            header 'Authorization', 'Bearer '+authResponse.json.access_token
        }

        then:
        response.status == 200
        response.json.access_token
        //response.json.permissions.size() > 0
    }

    void 'calling /api/validate with an invalid token returns 401'() {
        when:
        def response = restBuilder.get("${APP_URL}/api/validate") {
            header 'Authorization', 'Bearer '+'something-else'
        }

        then:
        response.status == 401
    }
    /*
		currently not working
		void "calling /api/validate without token returns 401"() {
        when:
        def response = restBuilder.get("${APP_URL}/api/validate")

        then:
        response.status == 401
    } */
}