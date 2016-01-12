package org.orsh.waitCostTracker

import org.junit.Test;

import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.handling.internal.DefaultContext;
import ratpack.test.http.TestHttpClient
import ratpack.test.ServerBackedApplicationUnderTest
import spock.lang.Specification
import waitCostTracker.TimerResponse;

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
	  
	  when:
	  TimerResponse expected = new TimerResponse()
	  def expectedJson = DefaultContext.current().render(json(expected))
	  
	  then:
	  assert expectedJson == "foo"
  }


  def cleanup() {
	aut.stop()
  }

}