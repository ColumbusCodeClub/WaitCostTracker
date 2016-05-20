package org.orsh.waitCostTracker

class PersistenceHandler {
	def folderPath

	PersistenceHandler() {
		folderPath = System.getProperty("user.dir").toString() + "/dataFile.json";
	}

	def persist(toPersist){
		File file = new File(folderPath)
		file.createNewFile()
		file.write(toPersist)
	}
}
