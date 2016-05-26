require('../../../../src/main/resources/assets/scripts/button-styling.js')
describe("button styling", function () {
		var mockButton = {
			html: jasmine.createSpy('html'),
			addClass: jasmine.createSpy('addClass')
    	};
    	$ = function(e) {
    		if(e === '.timer-toggle-btn') {
    			return mockButton;
    		} else {
    			return {};
    		}
    	};
    	
    	it("should say stop", function() {
    		buttonStyling.makeStop();
    		expect(mockButton.html).toHaveBeenCalledWith("Stop");
    	});
    	it("should add btn-danger class to button", function() {
			buttonStyling.makeStop();
			expect(mockButton.addClass).toHaveBeenCalledWith('btn-danger');
    	});
    });