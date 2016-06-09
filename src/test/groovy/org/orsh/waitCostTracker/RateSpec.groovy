package org.orsh.waitCostTracker

import spock.lang.Specification;

class RateSpec extends Specification {

	Rate underTest

	def setup() {
		underTest = new Rate(hourly:50)
	}

	def "should be created"() {
		expect:
		underTest != null
	}
	def "should be immutable"() {
		when:
		underTest.hourly = 25;

		then:
		thrown ReadOnlyPropertyException
	}
	def "should calculate cost"() {

		given:
		def duration = [ value: 2]
		
		when:
		def cost = underTest.times(duration)
		
		then:
		cost.value == 100.00
	}
}