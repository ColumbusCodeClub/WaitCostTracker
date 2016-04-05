console.log("HOLY MOLY MACARONI THIS WORKS TOO!!");


Timer = (function(){
	
	var started = false;
	
	function _stopTimer() {
		console.log("TIMER WAS STOPPED!");
		started = !started;
		
		$.ajax({
			url: "/calculate/costByDuration/83",
			type: "GET",
			contentType: 'application/json; charset=utf-8',
            success: function(resultData) {
                console.log(resultData);

            },
            error : function(jqXHR, textStatus, errorThrown) {
            },

            timeout: 120000
		});
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
