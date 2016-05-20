PageWriter = function(){
	function writeCost(value) {
		console.log("value: " + value);
		$('#cost').html(value);
	}
	
	function writeTime(value) {
		$('#time').html(value);
	}
	
	return {
		writeCost: writeCost,
		writeTime: writeTime
	};
}
module.exports = PageWriter;