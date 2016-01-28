package org.orsh.waitCostTracker

import org.junit.Test

import com.fasterxml.jackson.databind.ObjectMapper;

import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.handling.internal.DefaultContext;
import ratpack.test.http.TestHttpClient
import ratpack.test.ServerBackedApplicationUnderTest
import spock.lang.Specification
import waitCostTracker.TimerResponse;
import groovy.json.JsonSlurper;

import static ratpack.jackson.Jackson.json;


class CalculateCostsFunctionalSpec extends Specification {
	
	ServerBackedApplicationUnderTest aut = new GroovyRatpackMainApplicationUnderTest()
	@Delegate
	TestHttpClient client = TestHttpClient.testHttpClient(aut)
	
	def "should throw an exception if invalid date object passed"() {
		given:
		
		when:
		get("/calculate/costs")
		def jsonSlurper = new JsonSlurper()
		def object = jsonSlurper.parseText(response.body.text)
		
		then:
		thrown(RuntimeException)
		(Date.parse("MM/dd/yyyy H:m:s", object.startdate)) >> { throw new RuntimeException() }
	}
	
	def cleanup() {
		aut.stop()
	  }
	
}