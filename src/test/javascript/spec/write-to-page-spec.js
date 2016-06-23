var pageWriter = require('../../../../src/main/resources/assets/scripts/write-to-page.js')

jQueryMockObject = {
	'html': function(){}
}

describe("writer", function () {
	var pageWriterUnderTest = new pageWriter();
	
	beforeEach(function(){
		$ = function(){};
		spyOn(global, '$').and.callFake(function(param){
			if(param === '#cost' || param === '#time') {
				return jQueryMockObject
			} else {
				return {};
			}
		});
		
		spyOn(jQueryMockObject, 'html');		
	});
	
	it("writes hello world to the page", function() {
		pageWriterUnderTest.writeCost("hello world");
		expect(jQueryMockObject.html).toHaveBeenCalledWith('hello world');
	});

	it("writes cost to the page", function() {
		pageWriterUnderTest.writeCost('at 100.00 per hr');
		expect(jQueryMockObject.html).toHaveBeenCalledWith('at 100.00 per hr');
	});
	
	it("writes time to the page", function() {
		pageWriterUnderTest.writeTime('1 hour');
		expect(jQueryMockObject.html).toHaveBeenCalledWith('1 hour');
	});
	
	it("writes time and cost to the page", function() {
		pageWriterUnderTest.writeTimeCostJson('{"duration":"00:05", "cost":{"value":"12.00"}}');
		expect(jQueryMockObject.html).toHaveBeenCalledWith("00:05");
		expect(jQueryMockObject.html).toHaveBeenCalledWith("12.00");
	})
});