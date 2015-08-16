package mvcs.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class HomeController extends ExtJSController {

	public HomeController(Result result) {
		this.result = result;
	}
	
	@Path("/")
	public void index() {
		result.redirectTo("/Packt/index.html");
	}
	
}
