buttonStyling = {
	makeSayStop: function() {
		var $timerButton = $('.timer-toggle-btn');
		$timerButton.html('<i class="fa fa-stop" aria-hidden="true"></i> Stop');
		$timerButton.addClass('btn-danger');
		$timerButton.removeClass('btn-success');
	},
	makeSayStart: function() {
		var $timerButton = $('.timer-toggle-btn');
		$timerButton.html('<i class="fa fa-play" aria-hidden="true"></i> Start');
		$timerButton.addClass('btn-success');
		$timerButton.removeClass('btn-danger');
	}

};