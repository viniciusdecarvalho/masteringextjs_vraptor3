package mvcs.custom.converter;

import java.util.Collection;

import br.com.caelum.vraptor.ioc.Component;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;

@Component
public class PersistentBagConverter extends CollectionConverter {

	public PersistentBagConverter(XStream xStream) {
		super(xStream.getMapper());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class type) {
		return Collection.class.isAssignableFrom(type);
	}

}
