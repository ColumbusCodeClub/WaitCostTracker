package org.orsh.waitCostTracker

import org.junit.Test;

import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.test.http.TestHttpClient
import ratpack.test.ServerBackedApplicationUnderTest
import spock.lang.Specification


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

  def cleanup() {
	aut.stop()
  }

}