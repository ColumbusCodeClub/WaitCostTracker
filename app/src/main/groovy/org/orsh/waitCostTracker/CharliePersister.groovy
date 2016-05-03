package org.orsh.waitCostTracker

class CharliePersister {
	def folderPath
	CharliePersister(folder) {
		folderPath = folder
	}
	
	def persist(toPersist){
		File folder = new File(folderPath)
		
	}
}
