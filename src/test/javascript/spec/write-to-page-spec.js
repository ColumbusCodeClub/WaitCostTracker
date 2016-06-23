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
	
	it("writes time and cost to the page", function() {
		pageWriterUnderTest.writeTimeCostJson('{"duration":"00:05", "cost":{"value":"12.00"}}');
		expect(jQueryMockObject.html).toHaveBeenCalledWith("00:05 minutes");
		expect(jQueryMockObject.html).toHaveBeenCalledWith("$12.00");
	})
});