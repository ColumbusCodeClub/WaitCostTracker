package org.orsh.waitCostTracker

import static org.orsh.waitCostTracker.Rate.DEFAULT_RATE

class ResponseHandler {
	PersistenceHandler persister;

	public ResponseHandler(persister) {
		this.persister = persister
	}
	
	def getResponse(minutes) {
		def isBeyondTwentyFourHours = !minutes.isInteger() || minutes.toInteger() > 1440
		if (isBeyondTwentyFourHours) {
			return "raiseTimeLimitError"
		} else {
			def hours = [ value: minutes.toInteger()/60 ]
			def response = '{"duration": "' + minutes + '","cost": "' + DEFAULT_RATE.times(hours) + '"}'
			persister.persist("foo: brian")
			response
		}
	}
}
