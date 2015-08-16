package mvcs.custom.interceptor;

import mvcs.custom.extjs.ExtJSJson;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.ExceptionMapper;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.ExceptionHandlerInterceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts
public class ExtJSExceptionInterceptor extends ExceptionHandlerInterceptor {

	private Result result;

	public ExtJSExceptionInterceptor(ExceptionMapper exceptions, Result result) {
		super(exceptions, result);
		this.result = result;
	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance)
        throws InterceptionException {			
		
		try {
			super.intercept(stack, method, resourceInstance);
		} catch (InterceptionException e) {
			result.use(ExtJSJson.class)
					.message(e.getMessage())
					.success(false)
					.serialize();
		}
    }	
}
