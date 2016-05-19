package org.orsh.waitCostTracker;

import spock.lang.Specification;

public class CharliePersisterSpec extends Specification {
	def "should create a file and verify it exists"() {
		given:
		def toPersist = "foo"
		def folderLocation = "/Users/ra3p62b/Documents/workspaces/katas/WaitCostTracker/app/charlie"
		PersistenceHandler underTest = new PersistenceHandler(folderLocation)
		File file = new File(folderLocation);

		when:
		underTest.persist(toPersist)

		then:
		file.exists() == true
		file.delete()
	}

	def "should persist to file"() {
		given:
		def toPersist = "foo"
		def folderLocation = "/Users/ra3p62b/Documents/workspaces/katas/WaitCostTracker/app/charlie"
		PersistenceHandler underTest = new PersistenceHandler(folderLocation)
		File file = new File(folderLocation);

		when:
		underTest.persist(toPersist)

		then:
		file.readLines()[0] == toPersist
		file.delete()
	}
}
