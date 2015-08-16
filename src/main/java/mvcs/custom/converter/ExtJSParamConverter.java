package mvcs.custom.converter;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import mvcs.custom.extjs.ExtJSParam;
import mvcs.custom.extjs.ExtJSFilters;
import mvcs.custom.extjs.ExtJSSorters;
import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;

@Convert(ExtJSParam.class)
public class ExtJSParamConverter implements Converter<ExtJSParam> {

	private HttpServletRequest request;
	private FiltersConverter filterConverter;
	private SortersConverter sorterConverter;

	public ExtJSParamConverter(HttpServletRequest request,
			FiltersConverter filterConverter, SortersConverter sorterConverter) {
		this.request = request;
		this.filterConverter = filterConverter;
		this.sorterConverter = sorterConverter;
	}

	@Override
	public ExtJSParam convert(String value, Class<? extends ExtJSParam> type,
			ResourceBundle bundle) {
		ExtJSParam params = new ExtJSParam();
		
		String filterParam = request.getParameter("filters");
		ExtJSFilters filtersParam = filterConverter.convert(filterParam, null, bundle);
		params.setFilters(filtersParam);
		
		String sorterParam = request.getParameter("sorters");
		ExtJSSorters sortersParam = sorterConverter.convert(sorterParam, null, bundle);
		params.setSorters(sortersParam);
		
		String startParam = request.getParameter("start");
		if (!isNullOrEmpty(startParam)) {
			params.setStart(Long.parseLong(startParam));
		}
		
		String limitParam = request.getParameter("limit");
		if (!isNullOrEmpty(limitParam)) {
			params.setLimit(Long.parseLong(limitParam));
		}
		
		return params;
		
	}

}
