package org.orsh.waitCostTracker

import groovy.transform.Immutable

@Immutable class Rate {
	int hourly

	def times(multiplier) {
		hourly * multiplier.value
	}
}
