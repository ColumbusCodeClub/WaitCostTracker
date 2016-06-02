var timer = require('../../../../src/main/resources/assets/scripts/timer.js')
moment = function() {};
writeCost = function() {};

describe("timer", function () {
	var timerUnderTest;
	var mockWriter = {
			writeCost: function(){},
			writeTime: function(){},
			writeTimeCostJson: function(){}
	};
	
	beforeEach(function() {
		$ = {
			'ajax': function(){}
		};
		timerUnderTest = new timer(mockWriter);
		spyOn($, 'ajax').and.callFake(function(e){
			e.success({});
		});
		spyOn(global, 'moment').and.returnValue(5);
		spyOn(mockWriter, 'writeCost');
		spyOn(mockWriter, 'writeTime');
		spyOn(mockWriter, 'writeTimeCostJson');
		spyOn(buttonStyling, 'makeSayStop');
	});
	
    it("starts the timer", function () {
    	timerUnderTest.toggleTimer();
        expect(timerUnderTest.isRunning()).toEqual(true);
    });
    
    it("stops the timer", function () {
		timerUnderTest.toggleTimer();
		timerUnderTest.toggleTimer();
        expect(timerUnderTest.isRunning()).toEqual(false);
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
    
    it("should call moment on first button press", function() {
    		timerUnderTest.toggleTimer();
    		expect(global.moment).toHaveBeenCalled();
    });

    it("should call moment on both button presses", function() {
    	timerUnderTest.toggleTimer();
    	timerUnderTest.toggleTimer();
    	expect(global.moment).toHaveBeenCalledTimes(2);
    });

    it("should call writeTimeCostJson on stop button presses", function() {
    	timerUnderTest.toggleTimer();
    	timerUnderTest.toggleTimer();
    	expect(mockWriter.writeTimeCostJson).toHaveBeenCalledTimes(1);
    });
    
    it("should call costByDuration url", function() {
    	moment = function() { return minutesToMs(5); };
		timerUnderTest.toggleTimer();
		moment = function() { return minutesToMs(15); }; 
		timerUnderTest.toggleTimer();
		expect($.ajax).toHaveBeenCalledWith(jasmine.objectContaining({url:"calculate/costByDuration/10"}))
    });
    
    it("should round", function() {
    	moment = function() { return minutesToMs(15); };
    	timerUnderTest.toggleTimer();
    	moment = function() { return minutesToMs(15.01); }; 
    	timerUnderTest.toggleTimer();
    	expect($.ajax).toHaveBeenCalledWith(jasmine.objectContaining({url:"calculate/costByDuration/1"}))
    });
    
    it('should make button styled to stop on first toggle', function() {
		timerUnderTest.toggleTimer();
		expect(buttonStyling.makeSayStop).toHaveBeenCalled();
    });
    
    function minutesToMs(minutes) {
    	return minutes * 1000 * 60;
    }
    
});