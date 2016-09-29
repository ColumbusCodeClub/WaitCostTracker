import ratpack.groovy.template.TextTemplateModule

import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json

import java.time.Duration;

import static ratpack.groovy.Groovy.groovyTemplate

import org.orsh.waitCostTracker.DatabaseHandler
import org.orsh.waitCostTracker.PersistenceHandler
import org.orsh.waitCostTracker.ResponseHandler
import org.orsh.waitCostTracker.Rate
import org.orsh.waitCostTracker.Timer
import org.orsh.waitCostTracker.CookieHandler


ratpack {
	DatabaseHandler databaseHandler = new DatabaseHandler()
	PersistenceHandler persister = new PersistenceHandler(databaseHandler)
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
			def minutes = context.pathTokens['time']
			
			CookieHandler cookieHandler = new CookieHandler()
			cookieHandler.handle(context)
			ResponseHandler responseHandler = new ResponseHandler(persister,context.getResponse().getHeaders().get("set-cookie"))
			def response = responseHandler.getResponse(minutes)
			if(response == "raiseTimeLimitError") {
				raiseTimeLimitError(context)
			} else {
				render response
			}
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

