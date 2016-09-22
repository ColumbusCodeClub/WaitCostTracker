package org.orsh.waitCostTracker;

import spock.lang.Specification;

public class PersistenceHandlerSpec extends Specification {

	def "should persist call addJSON"() {
		given:
		def jsonToPersist = /{"name":"nate","time":"20", "cost":"100"}/
		DatabaseHandler mockDatabaseHandler = Mock()
		PersistenceHandler underTest = new PersistenceHandler(mockDatabaseHandler)
		
		when:
		underTest.persist(jsonToPersist)
		
		then:
		1*mockDatabaseHandler.addJSON(jsonToPersist)
	}	
}
