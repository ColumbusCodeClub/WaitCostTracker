console.log("HOLY MOLY MACARONI THIS WORKS TOO!!");


Timer = (function(){
	
	var started = false;
	
	function _stopTimer() {
		console.log("TIMER WAS STOPPED!");
		started = !started;
	}
	
	function _startTimer() {
		console.log("TIMER WAS STARTED");
		started = !started;
	}
	
	function toggleTimer() {
		if (started) {
			_stopTimer();
		} else {
			_startTimer();
		}	
	}
	
	return {
		toggleTimer: toggleTimer
	};
})();
