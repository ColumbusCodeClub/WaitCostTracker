package org.orsh.waitCostTracker

import groovy.transform.Immutable

@Immutable class Rate {
	int hourly

	def times(multiplier) {
		System.out.println("HEY YOU CALLED ME!!!")
		hourly * multiplier.value
	}
}
