require('../../../../src/main/resources/assets/scripts/button-styling.js')
describe("button styling", function () {
		var mockButton = {
			html: jasmine.createSpy('html'),
			addClass: jasmine.createSpy('addClass'),
			removeClass: jasmine.createSpy('removeClass')
    	};
    	$ = function(e) {
    		if(e === '.timer-toggle-btn') {
    			return mockButton;
    		} else {
    			return {};
    		}
    	};
    	
    	it("should say stop", function() {
    		buttonStyling.makeSayStop();
    		expect(mockButton.html).toHaveBeenCalledWith("Stop");
    	});
    	it("should add btn-danger class to button", function() {
			buttonStyling.makeSayStop();
			expect(mockButton.addClass).toHaveBeenCalledWith('btn-danger');
    	});
    	it("should remove btn-success class from button", function() {
    		buttonStyling.makeSayStop();
			expect(mockButton.removeClass).toHaveBeenCalledWith('btn-success');
    	});
    });