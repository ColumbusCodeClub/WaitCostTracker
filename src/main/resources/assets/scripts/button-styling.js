var rotation = 6;
var tickAnimation;
buttonStyling = {
	makeSayStop: function() {
		var $timerButton = $('.timer-toggle-btn');
		$timerButton.html('<i class="fa fa-stop" aria-hidden="true"></i>  STOP');
		$timerButton.addClass('btn-red');
		$timerButton.removeClass('btn-green');
		
		tickAnimation = setInterval(function() {
			$('#stopwatch-hand').attr('transform', 'rotate(' + rotation + ' 125 125)');
			rotation += 6;
		}, 1000);
		
	},
	makeSayStart: function() {
		var $timerButton = $('.timer-toggle-btn');
		$timerButton.html('<i class="fa fa-play" aria-hidden="true"></i> START');
		$timerButton.addClass('btn-green');
		$timerButton.removeClass('btn-red');
		clearInterval(tickAnimation);
		rotation = 6;
		$('#stopwatch-hand').removeAttr('transform');
	}

};