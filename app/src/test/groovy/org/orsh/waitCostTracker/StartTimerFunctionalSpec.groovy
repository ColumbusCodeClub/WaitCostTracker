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


class StartTimerFunctionalSpec extends Specification {

  ServerBackedApplicationUnderTest aut = new GroovyRatpackMainApplicationUnderTest()
  @Delegate
  TestHttpClient client = TestHttpClient.testHttpClient(aut)

  def "Start timer should be ok"() {
	when:
	get("/timer/start")
	
	then:
	response.statusCode == 200
  }
  
  def "start time should respond with current milliseconds"() {
	  when:
	  get("/timer/start")
	  
	  then:
	  response.body.text =~ '"time": "\\d{2,}"'
  }

  def "start time should respond with timer id"() {
	  when:
	  get("/timer/start")
	  
	  then:
	  response.body.text =~ '"id": ".+"'
  }
  
  def "should respond with a response"() {
	  
	  given:
	  ObjectMapper objectMapper = new ObjectMapper();
	 
	  when:
	  get("/timer/start")
	  def response = objectMapper.readValue(response.body.text, TimerResponse.class)
	  
	  then:
	  assert response.id != null
	  assert response.time != null
  }
  
  def "should respond with a json response"() {
	  
	  given:
	  ObjectMapper objectMapper = new ObjectMapper();
	 
	  when:
	  get("/timer/start")
	  def jsonSlurper = new JsonSlurper()
	  def object = jsonSlurper.parseText(response.body.text)
	  def timerResponse = new TimerResponse()
	  timerResponse.id = object.id
	  timerResponse.time = object.time.toInteger()
	  
	  then:
	  assert timerResponse.id == "foo"
	  assert timerResponse.time == 34
  }


  def cleanup() {
	aut.stop()
  }

}