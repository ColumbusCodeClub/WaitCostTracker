package org.orsh.waitCostTracker
import java.sql.*
import org.sqlite.SQLite
import groovy.sql.Sql
import groovy.json.JsonSlurper

class DatabaseHandler {
	def sql = Sql.newInstance("jdbc:sqlite:sample.db", "org.sqlite.JDBC")
	def waitCostTracker = sql.dataSet("WAIT_COST_TRACKER")
	
	def createWaitCostTrackerTable() {
		sql.execute("create table if not exists WAIT_COST_TRACKER (id integer primary key, name string, time integer, cost real)")
	}

	def addWaitCostTime(name, time, cost) {
		createWaitCostTrackerTable()
		waitCostTracker.add(name:name, time:time, cost:cost)
	}
	
	def getWaitCost(name) {
		def resultingCost
		sql.eachRow("select cost from WAIT_COST_TRACKER where name = ${name}") { resultingCost = it.cost }
		resultingCost
	}
	
	def addJSON(json) {
		def slurper = new JsonSlurper()
		def waitCostObject = slurper.parseText(json)
		
		addWaitCostTime(waitCostObject.name, waitCostObject.time, waitCostObject.cost)
	}
	
	def emptyTable() {
		sql.execute("delete from WAIT_COST_TRACKER");
	}
	
	
}
