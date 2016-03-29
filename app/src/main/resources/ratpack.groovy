import ratpack.groovy.template.TextTemplateModule

import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json

import java.time.Duration;

import static ratpack.groovy.Groovy.groovyTemplate

import org.orsh.waitCostTracker.Timer


ratpack {
	bindings { module TextTemplateModule }
	handlers {
		get { render groovyTemplate("index.html") }
		get("timer/start") {
			render json(new Timer())
		}
		get("calculate/costs") {
			render '{"startdate": "2/20/2015 10:00:25", "stopdate": "2/20/2015 12:00:25", "rate": "50.00"}'
		}
		get("calculate/costByDuration/:time") {
			def thing = context.pathTokens['time']
			def isBeyondTwentyFourHours = !thing.isInteger() || thing.toInteger() > 1440
			if (isBeyondTwentyFourHours) {
				raiseTimeLimitError(context)
			} 
			
			render '{"duration": "' + thing + '"}'
		}
		get("calculate/costByDuration/") {
			raiseTimeLimitError(context)
		}
		fileSystem "assets", { f -> f.files() }
    }
}

private raiseTimeLimitError(context) {
	context.getResponse().status(400).send("Oops!  Something has gone afoul!")
}
