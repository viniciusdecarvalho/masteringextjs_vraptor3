package mvcs.custom.converter;

import java.lang.reflect.Type;
import java.util.List;
import java.util.ResourceBundle;

import mvcs.custom.extjs.ExtJSFilter;
import mvcs.custom.extjs.ExtJSFilters;
import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Convert(ExtJSFilters.class)
public class FiltersConverter implements Converter<ExtJSFilters> {

	@Override
	public ExtJSFilters convert(String value, Class<? extends ExtJSFilters> type,
			ResourceBundle bundle) {
		ExtJSFilters filters = new ExtJSFilters();
		
		if (Strings.isNullOrEmpty(value)) {
			return filters;
		}
		
		Type typeToken = new TypeToken<List<ExtJSFilter>>(){}.getType();
		List<ExtJSFilter> paramFilters = new Gson().fromJson(value, typeToken);
		filters.addAll(paramFilters);

		return filters;
	}
	
}
