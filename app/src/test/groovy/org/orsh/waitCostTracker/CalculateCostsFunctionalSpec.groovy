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
	float RATE = ratePerMin(50)
	
	def ratePerMin(ratePerHour) {
		return ratePerHour / 60
	}
	
	def "should parse a date from json"() {
		given:
		
		when:
		get("/calculate/costs")
		def jsonSlurper = new JsonSlurper()
		def object = jsonSlurper.parseText(response.body.text)
		
		then:
		Date.parse("MM/dd/yyyy HH:mm:ss", object.startdate) instanceof Date
	}
	
	def "should calculate 120 minutes between 2 dates that differ by two hours"() {
		given:
		
		when:
		get("/calculate/costs")
		def jsonSlurper = new JsonSlurper()
		def object = jsonSlurper.parseText(response.body.text)
		def dateDiff = Date.parse("MM/dd/yyyy HH:mm:ss", object.stopdate).time - Date.parse("MM/dd/yyyy HH:mm:ss", object.startdate).time
		
		then:
		dateDiff / (60 * 1000) == 120
	}
	
	def "should calculate 100 dollars as the cost for the 2 dates that differ by 2 hours"() {
		given:
		
		when:
		get("/calculate/costs")
		def jsonSlurper = new JsonSlurper()
		def object = jsonSlurper.parseText(response.body.text)
		def dateDiff = Date.parse("MM/dd/yyyy HH:mm:ss", object.stopdate).time - Date.parse("MM/dd/yyyy HH:mm:ss", object.startdate).time
		def totalHours = dateDiff / (60 * 60* 1000)
		def rate = object.rate.toFloat()
		
		then:
		(rate * totalHours).round(2) == 100.00
	}
	
	def "should calculate 100 dollars as the cost for a duration of 120 minutes"() {
		given:
		
		when:
		get("/calculate/costByDuration")
		def jsonSlurper = new JsonSlurper()
		def object = jsonSlurper.parseText(response.body.text)
		def totalHours = object.duration.toFloat()
		
		then:
		(RATE * totalHours).round(2) == 100.00
	}
	
	def "should calculate 0 dollars as the cost for a duration of 0 minutes"() {
		given:
		
		when:
		get("/calculate/costByDuration")
		def jsonSlurper = new JsonSlurper()
		def object = jsonSlurper.parseText(response.body.text)
		def totalHours = object.duration.toFloat()
		
		then:
		(RATE * totalHours).round(2) == 0.00
	}

	
	def cleanup() {
		aut.stop()
	  }
	
}