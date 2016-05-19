package org.orsh.waitCostTracker

class PersistenceHandler {
	def folderPath
	PersistenceHandler(folder) {
		folderPath = folder
	}
	
	PersistenceHandler() {
		folderPath = "/Users/ra3p62b/Documents/workspaces/katas/WaitCostTracker/app/charlie"
	}
	
	def persist(toPersist){
		File file = new File(folderPath)
		file.createNewFile()
		file.write(toPersist)
	}
}
