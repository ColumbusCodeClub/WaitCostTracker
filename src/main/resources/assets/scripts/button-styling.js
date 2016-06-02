buttonStyling = {
	makeSayStop: function() {
		var $timerButton = $('.timer-toggle-btn');
		$timerButton.html('Stop');
		$timerButton.addClass('btn-danger');
		$timerButton.removeClass('btn-success');
	}
};