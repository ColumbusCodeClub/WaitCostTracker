import ratpack.groovy.template.TextTemplateModule

import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json
import static ratpack.groovy.Groovy.groovyTemplate

import org.orsh.waitCostTracker.Timer


ratpack {
	bindings { module TextTemplateModule }
	handlers {
		get { render groovyTemplate("index.html") }
		get("timer/start") {
			render json(new Timer())
		}
		fileSystem "assets", { f -> f.files() }
    }
}