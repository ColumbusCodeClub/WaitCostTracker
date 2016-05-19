package org.orsh.waitCostTracker

import groovy.lang.Delegate
import ratpack.groovy.GroovyRatpackMain;
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.test.ServerBackedApplicationUnderTest;
import ratpack.test.http.TestHttpClient;
import spock.lang.Specification

class RequestCallsPersisterSpec extends Specification {
	
	def "should call persistor"()  {
		setup:
		PersistenceHandler mockCharliePersister = Mock()
		ResponseHandler underTest = new ResponseHandler(mockCharliePersister)
		//get("/calculate/costByDuration/120") >> { mockCharliePersister.persist(mockCharliePersister) }

		when:
		underTest.getResponse("1234")
				
		then:
		1*mockCharliePersister.persist("foo: brian")
	}
}
