package mvcs.custom.converter;

import java.util.ResourceBundle;

import mvcs.model.dto.Dto;
import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;

@Convert(Dto.class)
public class DtoConverter implements Converter<Dto<?>> {

	@Override
	public Dto<?> convert(String value, Class<? extends Dto<?>> type,
			ResourceBundle bundle) {
		try {
			return type.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
	}

}
