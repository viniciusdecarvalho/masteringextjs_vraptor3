package mvcs.custom.converter;

import java.lang.reflect.Type;
import java.util.List;
import java.util.ResourceBundle;

import mvcs.custom.extjs.ExtJSGrouper;
import mvcs.custom.extjs.ExtJSGroupers;
import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

@Convert(ExtJSGroupers.class)
public class GroupersConverter implements Converter<ExtJSGroupers> {

	@Override
	public ExtJSGroupers convert(String value, Class<? extends ExtJSGroupers> type,
			ResourceBundle bundle) {
		ExtJSGroupers groupers = new ExtJSGroupers();
		
		if (Strings.isNullOrEmpty(value)) {
			return groupers;
		}
		
		try {
			Type typeToken = new TypeToken<List<ExtJSGrouper>>(){}.getType();
			List<ExtJSGrouper> jsonGroupers = new Gson().fromJson(value.replaceAll("Id", ""), typeToken);
			groupers.addAll(jsonGroupers);
		} catch (JsonSyntaxException e) {
			
		}
		return groupers;
	}
	
}
