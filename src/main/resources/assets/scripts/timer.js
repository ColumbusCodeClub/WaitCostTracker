Timer = function(writer){
	
	var startMs;
	var endMs;
	function toggleTimer() {
		if(isRunning()) {
			stopTimer();
			resetTimer();
		} else {
			startTimer();
		}
	}
	
	function startTimer() {
		startMs = moment();
	}
	
	function stopTimer() {
		endMs = moment();
		$.ajax({
			url: "calculate/costByDuration/" + durationInMinutes(), 
			success: function(result) {
				writer.writeTimeCostJson(result);				
			}
		});
	}
	
	function isRunning() {
		return startMs !== undefined;
	}
	
	function durationInMinutes() {
		return msToMinutes(endMs - startMs);
	}
	
	function msToMinutes(ms) {
		return Math.ceil(ms/1000/60);
	}
	
	function resetTimer() {
		startMs = undefined;
	}
	
	return {
		toggleTimer: toggleTimer,
		isRunning: isRunning
	};
};
module.exports = Timer;

