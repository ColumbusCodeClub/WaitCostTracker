package org.orsh.waitCostTracker

import static ratpack.jackson.Jackson.json
import static org.orsh.waitCostTracker.Rate.DEFAULT_RATE

import groovy.json.JsonSlurper
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.test.ServerBackedApplicationUnderTest
import ratpack.test.http.TestHttpClient
import spock.lang.Specification


class SessionSpec extends Specification {
	
	ServerBackedApplicationUnderTest aut = new GroovyRatpackMainApplicationUnderTest()
	@Delegate
	TestHttpClient client = TestHttpClient.testHttpClient(aut)

	def "should create a cookie"() {
		given:
		def minutes = 20

		when:
		get("/calculate/costByDuration/" + minutes)
		def cookies = getCookies()
		println "cookies: " + cookies
		
		then:
		!cookies.isEmpty()
	}
	
}