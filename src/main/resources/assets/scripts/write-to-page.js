PageWriter = function(){
	function writeCost(value) {
		$('#cost').html("$" + value);
	}
	
	function writeTime(value) {
		$('#time').html(value + " minutes");
	}
	
	this.writeTimeCostJson = function(value) {
		console.log("json value: " + value);
		var obj = JSON.parse(value);
		writeCost(obj.cost.value);
		writeTime(obj.duration);
	}

}
module.exports = PageWriter;