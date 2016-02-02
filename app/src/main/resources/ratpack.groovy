import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json;

import org.orsh.waitCostTracker.Timer


ratpack {
    handlers {
		get("timer/start") {
			render json(new Timer())
		}
    }
}