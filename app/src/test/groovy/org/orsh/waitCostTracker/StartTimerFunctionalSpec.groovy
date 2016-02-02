package org.orsh.waitCostTracker

import static ratpack.jackson.Jackson.json
import groovy.json.JsonParser;
import groovy.json.JsonSlurper
import groovy.transform.Immutable;
import groovy.json.JsonOutput;
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.handling.internal.DefaultContext;
import ratpack.test.http.TestHttpClient
import ratpack.test.ServerBackedApplicationUnderTest
import spock.lang.Specification
import spock.lang.Unroll;

import java.beans.Transient
import java.util.Date;;


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
		assert slurpResponse().id != null
	}

	def slurpResponse() {
		slurper.parseText(response.body.text)
	}


	def "timer should have start time"() {

		when:
		get("/timer/start")

		then:
		assert slurpResponse().startTime != null
	}

	def cleanup() {
		aut.stop()
	}
}