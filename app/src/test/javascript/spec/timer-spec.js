var timer = require('../../../../src/main/resources/assets/scripts/timer.js')
$ = {
	'ajax': function() {}
};

moment = function() {}

describe("timer", function () {
	var timerUnderTest;
	beforeEach(function() {
		timerUnderTest = new timer();
		spyOn($, 'ajax');
		spyOn(global, 'moment');
	});
	
    it("starts the timer", function () {
    		timerUnderTest.toggleTimer();
        expect(timerUnderTest.isStarted()).toEqual(true);
    });
    
    it("stops the timer", function () {
    		timerUnderTest.toggleTimer();
    		timerUnderTest.toggleTimer();
        expect(timerUnderTest.isStarted()).toEqual(false);
    });
    
    it("should send timer data on timer stop", function() {
		timerUnderTest.toggleTimer();
		timerUnderTest.toggleTimer();
		expect($.ajax).toHaveBeenCalled();
    });
    
    it("should not send timer data on timer start", function() {
    		timerUnderTest.toggleTimer();
    		expect($.ajax).not.toHaveBeenCalled();
    });
    
    it("should call costByDuration url", function() {
    		timerUnderTest.toggleTimer();
    		timerUnderTest.toggleTimer();
    		expect($.ajax).toHaveBeenCalledWith(jasmine.objectContaining({url:"calculate/costByDuration/100"}))
    });
    
    it("should call moment on first button press", function() {
    		timerUnderTest.toggleTimer();
    		expect(global.moment).toHaveBeenCalled();
    });
});