package mvcs.custom.interceptor;

import mvcs.controller.ExtJSController;
import mvcs.custom.extjs.ExtJSJson;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

//@Intercepts(after = ExecuteMethodInterceptor.class, before = {})
public class ValidatorInterceptor implements Interceptor {

	private Validator validator;
	private Result result;

	public ValidatorInterceptor(Validator validator, Result result) {
		this.validator = validator;
		this.result = result;
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object obj) throws InterceptionException {
		try {
			if (validator.hasErrors()) 
				throw new IllegalStateException("campos com erros");
			stack.next(method, obj);
		} catch (Exception e) {
			result.use(ExtJSJson.class).from(validator.getErrors())
				.message(ExtJSController.FIELDS_NOT_VALID)
				.success(false).serialize();
			throw new InterceptionException(e);
		}
	}

	@Override
	public boolean accepts(ResourceMethod method) {
		return method.getMethod().isAnnotationPresent(Post.class) || 
				method.getMethod().isAnnotationPresent(Put.class);
	}
}
