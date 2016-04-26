package org.orsh.waitCostTracker

import groovy.transform.Immutable

@Immutable class Rate {
	int hourly

	def times(multiplier) {
		System.out.println(hourly * multiplier.value)
		hourly * multiplier.value
	}
}
