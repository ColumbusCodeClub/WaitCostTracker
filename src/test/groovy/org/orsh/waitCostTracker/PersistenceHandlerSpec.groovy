package org.orsh.waitCostTracker;

import spock.lang.Specification;
import groovy.json.JsonSlurper;

public class PersistenceHandlerSpec extends Specification {
    def slurper = new JsonSlurper()

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

    def "should read from file and verify contents"() {
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

    def "should read from file and verify json fields"() {

        given:
        def toPersist = '{\"startdate\": \"2/20/2015 10:00:25\", \"stopdate\": \"2/20/2015 12:00:25\", \"rate\": \"50.00\"}'
        def folderLocation = System.getProperty("user.dir").toString() + "dataFile.json";
        PersistenceHandler underTest = new PersistenceHandler()
        File file = new File(folderLocation);
        underTest.persist(toPersist)

        when:
        def jsonContents = slurper.parseText(underTest.read(toPersist))

        then:
        assert jsonContents.startdate == '2/20/2015 10:00:25'
        assert jsonContents.stopdate == '2/20/2015 12:00:25'
        assert jsonContents.rate == '50.00'
    }


}
