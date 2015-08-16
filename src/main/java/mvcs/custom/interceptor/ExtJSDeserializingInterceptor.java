package mvcs.custom.interceptor;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import mvcs.custom.annotations.ExtJson;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Lazy;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.core.MethodInfo;
import br.com.caelum.vraptor.interceptor.ExecuteMethodInterceptor;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.interceptor.ParametersInstantiatorInterceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.view.ResultException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Lazy
@Intercepts(after=ParametersInstantiatorInterceptor.class, before=ExecuteMethodInterceptor.class)
public class ExtJSDeserializingInterceptor implements Interceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(ExtJSDeserializingInterceptor.class);
	
	private final HttpServletRequest request;
	private final MethodInfo methodInfo;
	
	public ExtJSDeserializingInterceptor(HttpServletRequest request, MethodInfo methodInfo) {
		this.request = request;
		this.methodInfo = methodInfo;
	}
	
	@Override
	public boolean accepts(ResourceMethod method) {		
		return method.getMethod().isAnnotationPresent(ExtJson.class);
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object resourceInstance) throws InterceptionException {
		
		Method javaMethod = method.getMethod();
		String rootName = javaMethod.getAnnotation(ExtJson.class).root();

		try {
			
			Class<?> type = javaMethod.getParameterTypes()[0];
			
			Gson gson = getGson();
			
			String content = getContentOfStream(request.getInputStream());
			logger.debug("json retrieved: " + content);
			JsonElement node = rootNode(rootName, content);
			methodInfo.getParameters()[0] = gson.fromJson(node, type);
			logger.debug("json converted in: " + methodInfo.getParameters()[0]);
			
		} catch (Exception e) {
			throw new ResultException("Unable to deserialize data", e);
		}
		stack.next(method, resourceInstance);
	}

	private JsonElement rootNode(String rootName, String content) {
		JsonParser parser = new JsonParser();
		JsonObject root = parser.parse(content).getAsJsonObject();
		JsonElement node = root.get(rootName);		
		return node;
	}
	
	protected Gson getGson() {
		return new GsonBuilder().create();
	}

	private String getContentOfStream(InputStream stream) throws IOException {
		byte[] bytes = IOUtils.toByteArray(stream);
		stream.read(bytes);
		return new String(bytes);
	}

}
