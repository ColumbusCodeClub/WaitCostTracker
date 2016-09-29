package org.orsh.waitCostTracker

import static org.orsh.waitCostTracker.Rate.DEFAULT_RATE
import static groovy.json.JsonOutput.toJson
import ratpack.handling.Context

class ResponseHandler {
	PersistenceHandler persister
	String cookie

	public ResponseHandler(persister, cookie) {
		this.persister = persister
		this.cookie = cookie
	}
	
	def getResponse(minutes) {
		def isBeyondTwentyFourHours = !minutes.isInteger() || minutes.toInteger() > 1440
		if (isBeyondTwentyFourHours) {
			return "raiseTimeLimitError"
		} else {
			def hours = [ value: minutes.toInteger()/60 ]
			def response = '{"name": "' + cookie + '","duration": "' + minutes + '","cost": ' + toJson(DEFAULT_RATE.times(hours)) + '}'
			persister.persist(response)
			response
		}
	}
}
