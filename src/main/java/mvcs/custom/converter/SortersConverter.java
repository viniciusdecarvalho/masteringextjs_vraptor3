package mvcs.custom.converter;

import java.lang.reflect.Type;
import java.util.List;
import java.util.ResourceBundle;

import mvcs.custom.extjs.ExtJSSorter;
import mvcs.custom.extjs.ExtJSSorters;
import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

@Convert(ExtJSSorters.class)
public class SortersConverter implements Converter<ExtJSSorters> {

	@Override
	public ExtJSSorters convert(String value, Class<? extends ExtJSSorters> type,
			ResourceBundle bundle) {
		ExtJSSorters sorters = new ExtJSSorters();

		if (Strings.isNullOrEmpty(value)) {
			return sorters;
		}

		try {
			Type typeToken = new TypeToken<List<ExtJSSorter>>() {}.getType();
			List<ExtJSSorter> jsonSorters = new Gson().fromJson(value.replaceAll("Id", ""), typeToken);
			sorters.addAll(jsonSorters);
		} catch (JsonSyntaxException e) {
		}
		return sorters;
	}

}
