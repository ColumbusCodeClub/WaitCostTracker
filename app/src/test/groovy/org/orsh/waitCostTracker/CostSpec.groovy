package org.orsh.waitCostTracker

import org.orsh.waitCostTracker.Cost;
import spock.lang.Specification;
import waitCostTracker.TimerResponse;
import groovy.json.JsonSlurper;


class CostSpec extends Specification {
	
	Cost underTest
	def rate
	
	def setup() {
		rate = new Rate(hourly:50)		
	}
	
    def "should calculate 100 dollars as the cost for 2 hours"() {
        given:
        def totalHours = 2;
		underTest = new Cost(rate, totalHours)
		
		// we could do
		def multiplier = new Rate(hourly:50)
		def duration = new Duration(hours:2)
		
		assert duration.times(multiplier) == 100
        
		// and also 
		def days = new Days(count:5)
		def averagePerDay = new Duration(hours:2)
		
		assert duration.times(multiplier) == 10
		//

		        when:
		def actual = underTest.calculate()
        
        then:
        actual == 100.00
    }
	
	class Duration {
		def hours
		
		def times(multiplier) {
			return hours * multiplier.value()
		}
	}
	class Days {
		def count
		
		def value() {
			count
		}
	}
	def "should calculate 50 dollars as the cost for 1 hours"() {
		given:
		def totalHours = 1;
		
		when:
		def actual = underTest.calculate(rate, totalHours)
		
		then:
		actual == 50.00
	}
	
}