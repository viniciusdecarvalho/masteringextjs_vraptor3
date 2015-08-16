package mvcs.dao;

import java.util.List;

public class Page<T> {

	private final List<T> elements;
	private final int start;
	private final int limit;
	private final long total;
	
	public Page(List<T> elements, int start, int limit, long total) {
		this.elements = elements;
		this.start = start;
		this.limit = limit;
		this.total = total;
	}

	public List<T> getElements() {
		return elements;
	}

	public int getStart() {
		return start;
	}

	public int getLimit() {
		return limit;
	}

	public long getTotal() {
		return total;
	}
	
}
