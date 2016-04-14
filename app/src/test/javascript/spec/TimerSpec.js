var timer = require('../../../../src/main/resources/assets/scripts/test.js')
describe( "timer", function () {
    it("starts the timer", function () {
    	console.log("before toggle: timer.started=" + timer.started());
    	timer.toggleTimer();
    	console.log("after toggle: timer.started=" + timer.started());
    	var started = timer.started();
        expect(started).toEqual(true);
    });
});