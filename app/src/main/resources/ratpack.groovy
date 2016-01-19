import static ratpack.groovy.Groovy.ratpack
import waitCostTracker.TimerResponse

ratpack {
    handlers {
		get("timer/start") {
			render new TimerResponse()
		}
    }
}