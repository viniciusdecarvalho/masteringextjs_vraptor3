package mvcs.custom.extjs;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.serialization.JSONSerialization;
import br.com.caelum.vraptor.serialization.Serializer;

//@Component
public class ExtJSGsonSerializer implements ExtJSJson {

	private static final Logger logger = LoggerFactory.getLogger(ExtJSGsonSerializer.class);
	
	private ExtJSWrapper wrapper;
	private JSONSerialization serialization;
	private Serializer serializer;
	
	public ExtJSGsonSerializer(JSONSerialization serialization) throws IOException {
		this.serialization = serialization;
	 	wrapper = new ExtJSWrapper();
	}

	@Override
	public ExtJSJson from(Object object) {
		wrapper.setData(object);
		if (logger.isDebugEnabled()) {
			serialization = serialization.indented();
		}
		serializer = serialization.withoutRoot().from(object);
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
//        serializer.excludeAll();
        return this;
    }

	@Override
	public ExtJSJson serialize() {
		serialization.from(wrapper).recursive().serialize();
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
		return this;
	}

}
