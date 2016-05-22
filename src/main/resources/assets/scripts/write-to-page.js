PageWriter = function(){
	function writeCost(value) {
		console.log("cost value: " + value);
		$('#cost').html(value);
	}
	
	function writeTime(value) {
		console.log("time value: " + value);
		$('#time').html(value);
	}
	
	function writeTimeCostJson(value) {
		console.log("json value: " + value);
		var obj = JSON.parse(value);
		writeCost(obj.cost);
		writeTime(obj.duration);
	}
	
	return {
		writeCost: writeCost,
		writeTime: writeTime,
		writeTimeCostJson: writeTimeCostJson
	};
}
module.exports = PageWriter;