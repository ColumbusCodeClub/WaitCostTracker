package org.orsh.waitCostTracker;

import spock.lang.Specification;

public class CharliePersisterSpec extends Specification {
	def "should charlie"() {
		given:
		def toPersist = "foo"
		def folderLocation = "path/to/file"
		CharliePersister underTest = new CharliePersister(folderLocation)
		File folder = new File(folderLocation);
		
		when:
		underTest.persist(toPersist)
		
		then:
		folder.exists() == true
		folder.delete()
	}
}
