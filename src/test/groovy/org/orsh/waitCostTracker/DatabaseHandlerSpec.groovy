package org.orsh.waitCostTracker
import java.sql.*
import org.sqlite.SQLite
import groovy.sql.Sql
import spock.lang.Specification

class DatabaseHandlerSpec extends Specification {
	def "should add leo to db"() {
		given:
			def sql = Sql.newInstance("jdbc:sqlite:sample.db", "org.sqlite.JDBC")
		when:
			sql.execute("drop table if exists person")
			sql.execute("create table person (id integer, name string)")
			def people = sql.dataSet("person")
			people.add(id:1, name:"leo")
			people.add(id:2,name:'yui')
			def underTest;
			sql.eachRow("select name from person where id = 1") { underTest = it.name }
		then:
			underTest == "leo"
	}
	
	def "should add kevin to db"() {
		given:
			DatabaseHandler underTest = new DatabaseHandler()
		when:
			underTest.addWaitCostTime("kevin", 20, 100.00)
		then:
			underTest.getWaitCost("kevin") == 100.00
	}
	
	def "should accept json format to add record to db"() {
		given:
			DatabaseHandler underTest = new DatabaseHandler()
		when:
			underTest.addJSON(/{"name":"nate","time":"20", "cost":"100"}/)
		then:
			underTest.getWaitCost("nate") == 100.00
	}
	
	def "should empty the table"() {
		given:
			DatabaseHandler underTest = new DatabaseHandler()
			underTest.addWaitCostTime("kevin", 20, 100.00)
			underTest.addWaitCostTime("brandon", 20, 100.00)
			underTest.addWaitCostTime("nate", 20, 100.00)
			underTest.addWaitCostTime("damon", 20, 100.00)
		when:
			underTest.emptyTable()
		then:
			underTest.getWaitCost("brandon") == null
	}
}