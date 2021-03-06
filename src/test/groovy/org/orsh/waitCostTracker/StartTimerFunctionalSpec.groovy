package org.orsh.waitCostTracker

import static ratpack.jackson.Jackson.json
import groovy.json.JsonSlurper
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.test.ServerBackedApplicationUnderTest
import ratpack.test.http.TestHttpClient
import spock.lang.Specification
import groovy.json.JsonSlurper;


class StartTimerFunctionalSpec extends Specification {

	ServerBackedApplicationUnderTest aut = new GroovyRatpackMainApplicationUnderTest()
	@Delegate
	TestHttpClient client = TestHttpClient.testHttpClient(aut)

	def slurper = new JsonSlurper()

	def "Start timer should be ok"() {

		when:
		get("/timer/start")

		then:
		response.statusCode == 200
	}

	def "timer should have id"() {

		when:
		get("/timer/start")

		then:
		assert responseAsTimer().id != null
	}

	def responseAsTimer() {
		slurper.parseText(response.body.text)
	}


	def "timer should have start time"() {

		when:
		get("/timer/start")

		then:
		assert responseAsTimer().startTime != null
	}

	def cleanup() {
		aut.stop()
	}
}