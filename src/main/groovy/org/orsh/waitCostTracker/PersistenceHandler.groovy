package org.orsh.waitCostTracker
import java.util.ArrayList

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

	def read(toRead){
		System.out.println("****" + folderPath)
		File file = new File(folderPath)
		def fileContents = [];
		file.eachLine{
			line -> 
			fileContents << line

		}
	 println(fileContents)
	 return fileContents
	}
}
