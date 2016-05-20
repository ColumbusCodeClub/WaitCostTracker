package org.orsh.waitCostTracker;

import spock.lang.Specification;

public class CharliePersisterSpec extends Specification {
	def "should create a file and verify it exists"() {
		given:
		def toPersist = "foo"
		def folderLocation = System.getProperty("user.dir").toString() + "/dataFile.json";
		PersistenceHandler underTest = new PersistenceHandler()
		File file = new File(folderLocation);

		when:
		underTest.persist(toPersist)

		then:
		file.exists() == true
		file.delete()
	}

}
