package org.orsh.waitCostTracker

class PersistenceHandler {
	DatabaseHandler databaseHandler

	PersistenceHandler(databaseHandler) {
		this.databaseHandler = databaseHandler
	}

	def persist(toPersist){
		databaseHandler.addJSON(toPersist)
	}
}
