import static ratpack.groovy.Groovy.ratpack

ratpack {
    handlers {
		get("timer/start") {
			render '"time": "34", "id": "foo"'	
		}
    }
}