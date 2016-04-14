var timer = require('../../../../src/main/resources/assets/scripts/test.js')
describe( "timer", function () {
    it("starts the timer", function () {
    	timer.toggleTimer();
        expect(timer.isStarted()).toEqual(true);
    });
});