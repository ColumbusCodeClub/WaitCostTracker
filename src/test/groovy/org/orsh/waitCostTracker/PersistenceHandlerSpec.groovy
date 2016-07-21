package org.orsh.waitCostTracker;

import spock.lang.Specification;

public class PersistenceHandlerSpec extends Specification {
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

	def "should read from file and verify contents"(){
		given:
		def toPersist = "foo"
		def folderLocation = System.getProperty("user.dir").toString() + "dataFile.json";
		PersistenceHandler underTest = new PersistenceHandler()
		File file = new File(folderLocation);
		underTest.persist(toPersist)

		when:
		def fileContents = underTest.read(toPersist)


		then:
		assert !fileContents.isEmpty()
		




	}

}
