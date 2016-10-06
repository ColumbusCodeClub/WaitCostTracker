package org.orsh.waitCostTracker

import static ratpack.jackson.Jackson.json
import static org.orsh.waitCostTracker.Rate.DEFAULT_RATE

import groovy.json.JsonSlurper
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.test.ServerBackedApplicationUnderTest
import ratpack.test.http.TestHttpClient
import spock.lang.Specification
import ratpack.handling.Context
import ratpack.http.Request

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
		given:
		UniqueId uniqueId = new UniqueId()
		CookieHandler underTest = new CookieHandler(uniqueId)
		when:
		HandlingResult result = RequestFixture.handle(underTest, {})

		then:
		assertEquals("boop123", result.getHeaders().get("set-cookie"))
	}
	
	def "should call UniqueId to get cookie name"() {
		given:
			UniqueId mockUniqueId = Mock()
			CookieHandler underTest = new CookieHandler(mockUniqueId)
			
			when:
				HandlingResult result = RequestFixture.handle(underTest, {})
				
				then:
					1*mockUniqueId.getId()
	}
}