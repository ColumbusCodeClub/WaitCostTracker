package org.orsh.waitCostTracker

import groovy.lang.Delegate
import ratpack.groovy.GroovyRatpackMain
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.test.ServerBackedApplicationUnderTest
import ratpack.test.http.TestHttpClient
import spock.lang.Specification
import ratpack.handling.Context

class RequestCallsPersisterSpec extends Specification {
	
	def "should call persistor"()  {
		setup:
		PersistenceHandler mockPersister = Mock()
		String cookie = "cookies"
		ResponseHandler underTest = new ResponseHandler(mockPersister, cookie)
		def minutes = "1234"
		when:
		underTest.getResponse(minutes)
				
		then:
		1*mockPersister.persist('{"name": "cookies","duration": "1234","cost": {"value":1028.33}}')
	}
}
