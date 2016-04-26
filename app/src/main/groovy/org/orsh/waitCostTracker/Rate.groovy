package org.orsh.waitCostTracker

import groovy.transform.Immutable

@Immutable class Rate {
	
	static Rate DEFAULT_RATE = new Rate(hourly: 50)
	
	int hourly

	def times(multiplier) {
		System.out.println(hourly * multiplier.value)
		hourly * multiplier.value
	}
}
