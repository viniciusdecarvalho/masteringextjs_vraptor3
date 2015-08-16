package mvcs.custom.converter;

import java.util.ResourceBundle;

import mvcs.custom.extjs.ExtJSOperator;
import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;

@Convert(ExtJSOperator.class)
public class OperatorConverter implements Converter<ExtJSOperator> {

	@Override
	public ExtJSOperator convert(String value, Class<? extends ExtJSOperator> type,
			ResourceBundle bundle) {		
		return ExtJSOperator.of(value);
	}

	
}
