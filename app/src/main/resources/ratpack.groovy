import ratpack.groovy.template.TextTemplateModule

import static ratpack.groovy.Groovy.ratpack
import static ratpack.groovy.Groovy.groovyTemplate


ratpack {
	bindings {
		module TextTemplateModule
	}
    handlers {	
		get {
			render groovyTemplate("index.html")
		}
		get("timer/start") {
			render '{"time": "34", "id": "foo"}'	
		}
    }
}