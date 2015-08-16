package mvcs.custom.extjs;

public class ExtJSParam {

	private long start;
	private long limit;
	private ExtJSFilters filters;
	private ExtJSSorters sorters;

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getLimit() {
		return limit;
	}

	public void setLimit(long limit) {
		this.limit = limit;
	}

	public ExtJSFilters getFilters() {
		return filters;
	}

	public void setFilters(ExtJSFilters filters) {
		this.filters = filters;
	}

	public ExtJSSorters getSorters() {
		return sorters;
	}

	public void setSorters(ExtJSSorters sorters) {
		this.sorters = sorters;
	}

}
