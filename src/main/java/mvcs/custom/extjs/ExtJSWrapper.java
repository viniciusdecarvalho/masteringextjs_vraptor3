package mvcs.custom.extjs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExtJSWrapper {

	private Object data;
	private List<Object> list;
	private Boolean success;
	private Long total;
	private String message;

	public ExtJSWrapper() {
	}
	
	public ExtJSWrapper(Object object) {
		setData(object);
	}

	public void setData(Object object) {
		if (object instanceof Collection) {
			list = new ArrayList<Object>((Collection<?>) object);
		} else {
			data = object;
		}
	}

	public Object getData() {
		if (list != null) {
			return list;
		}
		return data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
