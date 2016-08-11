require('../../../../src/main/resources/assets/scripts/button-styling.js')
describe("button styling", function () {
		var mockButton;
    	
    	beforeEach(function() {
    		mockButton = {
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
    	});
    	
    	it("should say stop", function() {
    		buttonStyling.makeSayStop();
    		expect(mockButton.html).toHaveBeenCalledWith('<i class="fa fa-stop" aria-hidden="true"></i>  STOP');
    	});
    	it("should add btn-red class to button", function() {
			buttonStyling.makeSayStop();
			expect(mockButton.addClass).toHaveBeenCalledWith('btn-red');
    	});
    	it("should remove btn-green class from button", function() {
    		buttonStyling.makeSayStop();
			expect(mockButton.removeClass).toHaveBeenCalledWith('btn-green');
    	});
    	it ("should say start", function(){
    		buttonStyling.makeSayStart();
    		expect(mockButton.html).toHaveBeenCalledWith('<i class="fa fa-play" aria-hidden="true"></i> START');
    	});
    	it('should add btn-green class to button', function(){
    		buttonStyling.makeSayStart();
    		expect(mockButton.addClass).toHaveBeenCalledWith('btn-green');
    	});
    	it('should remove btn-red class', function(){
    		buttonStyling.makeSayStart();
    		expect(mockButton.removeClass).toHaveBeenCalledWith('btn-red');
    	});
    });