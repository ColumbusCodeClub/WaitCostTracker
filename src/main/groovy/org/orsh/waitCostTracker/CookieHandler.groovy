package org.orsh.waitCostTracker
import ratpack.handling.Handler
import ratpack.handling.Context

public class CookieHandler implements Handler {
		public void handle(Context ctx) throws Exception {
		  String outputHeaderValue = ctx.getRequest().getHeaders().get("set-cookie")
		  if (outputHeaderValue == null) {
			  outputHeaderValue = "foo=bar"
		  }
		  ctx.getResponse().getHeaders().set("set-cookie", outputHeaderValue)
		  ctx.next()
		}
}

