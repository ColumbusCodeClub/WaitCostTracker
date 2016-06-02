buttonStyling = {
	makeSayStop: function() {
		var $timerButton = $('.timer-toggle-btn');
		$timerButton.html('Stop');
		$timerButton.addClass('btn-danger');
		$timerButton.removeClass('btn-success');
	},
	makeSayStart: function() {
		var $timerButton = $('.timer-toggle-btn');
		$timerButton.html('Start');
		$timerButton.addClass('btn-success');
		$timerButton.removeClass('btn-danger');
	}

};