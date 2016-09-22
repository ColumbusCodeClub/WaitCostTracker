package org.orsh.waitCostTracker

import static ratpack.jackson.Jackson.json
import static org.orsh.waitCostTracker.Rate.DEFAULT_RATE

import groovy.json.JsonSlurper
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.test.ServerBackedApplicationUnderTest
import ratpack.test.http.TestHttpClient
import spock.lang.Specification

import ratpack.test.handling.RequestFixture;
import ratpack.test.handling.HandlingResult;
import static org.junit.Assert.assertEquals;

class CookiesHandlerSpec extends Specification {
	def "should not overwrite cookie value if it exists"() {
		when:
		
		HandlingResult result = RequestFixture.handle(new CookieHandler(), {fixture ->
			fixture.header("set-cookie", "foo=chocolate")}
		)

		then:
		assertEquals("foo=chocolate", result.getHeaders().get("set-cookie"))
	}
	
	def "should set cookie value if it does not exist"() {
		when:
		HandlingResult result = RequestFixture.handle(new CookieHandler(), {})

		then:
		assertEquals("foo=bar", result.getHeaders().get("set-cookie"))
	}
}