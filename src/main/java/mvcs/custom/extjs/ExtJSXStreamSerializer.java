package mvcs.custom.extjs;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.interceptor.TypeNameExtractor;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.serialization.ProxyInitializer;
import br.com.caelum.vraptor.serialization.xstream.XStreamBuilder;
import br.com.caelum.vraptor.serialization.xstream.XStreamSerializer;

import com.thoughtworks.xstream.XStream;

@Component
public class ExtJSXStreamSerializer implements ExtJSJson {

	private static final Logger logger = LoggerFactory.getLogger(ExtJSXStreamSerializer.class);
	
	private ExtJSWrapper wrapper;
	private XStreamSerializer serializer;
	private XStream xstream;

	public ExtJSXStreamSerializer(HttpServletResponse response,
			TypeNameExtractor extractor, ProxyInitializer initializer,
			XStreamBuilder builder) throws IOException {
		response.setContentType("application/json");
		wrapper = new ExtJSWrapper();
		xstream = getConfiguredXStream(builder);
		serializer = new XStreamSerializer(xstream, response.getWriter(), extractor, initializer);
	}

	protected XStream getConfiguredXStream(XStreamBuilder builder) {
		if (logger.isDebugEnabled()) {
			builder = builder.indented();
		}
		XStream xstream = builder.withoutRoot().jsonInstance();
		xstream.setMode(XStream.NO_REFERENCES);
		xstream.aliasSystemAttribute(null, "class");
		xstream.aliasSystemAttribute(null, "resolves-to");
		xstream.aliasField("data", ExtJSWrapper.class, "list");
		return xstream;
	}

	@Override
	public ExtJSJson from(Object object) {
		wrapper.setData(object);
		serializer.from(object);
		return this;
	}

	@Override
	public ExtJSJson include(String... names) {
        serializer.include(names);
        return this;
    }
	
	@Override
    public ExtJSJson exclude(String... names) {
        serializer.exclude(names);
        return this;
    }
	
	@Override
	public ExtJSJson excludeAll() {
//      serializer.excludeAll();
        return this;
    }

	@Override
	public ExtJSJson serialize() {
		serializer.from(wrapper).recursive().serialize();
		return this;
	}

	@Override
	public ExtJSJson success() {
		wrapper.setSuccess(true);
		return this;
	}

	@Override
	public ExtJSJson success(boolean success) {
		wrapper.setSuccess(success);
		return this;
	}

	@Override
	public ExtJSJson total(Long total) {
		wrapper.setTotal(total);
		return this;
	}

	@Override
	public ExtJSJson message(String message) {
		wrapper.setMessage(message);
		return this;
	}

	@Override
	public ExtJSJson alias(String alias) {
		xstream.aliasField(alias, ExtJSWrapper.class, "data");
		xstream.aliasField(alias, ExtJSWrapper.class, "list");
		return this;
	}

}
