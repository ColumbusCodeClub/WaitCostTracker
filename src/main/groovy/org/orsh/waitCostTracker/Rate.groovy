package org.orsh.waitCostTracker

import groovy.transform.Immutable
import java.math.RoundingMode;

@Immutable class Rate {
	
	static Rate DEFAULT_RATE = new Rate(hourly: 50)
	
	float hourly

	def times(multiplier) {
		System.out.println(hourly * multiplier.value)
		roundToHundredths(hourly * multiplier.value)
	}
	
	def roundToHundredths(input) {
		input.round(2)
	}
}
