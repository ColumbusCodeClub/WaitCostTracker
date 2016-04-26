Timer = function(){
	
	var started = false;
	
	function toggleTimer() {
		started = !started
		if(!started) {
			$.ajax({url: "calculate/costByDuration/100"});
		}
		console.log("Timer status: " + started);
	}
	
	function isStarted() {
		return started;
	}
	
	return {
		toggleTimer: toggleTimer,
		isStarted: isStarted
	};
};
module.exports = Timer;

