var timer = require('../../../../src/main/resources/assets/scripts/test.js')
describe( "timer", function () {
    it("starts the timer", function () {
    	timer.toggleTimer();
    	var started = timer.started;
        expect(started).toEqual(true);
    });
});