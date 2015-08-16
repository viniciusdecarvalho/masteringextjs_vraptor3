package mvcs.custom.extjs;

import com.google.common.base.Strings;

public enum ExtJSOperator {
	
	EQUAL("="), 
	NOT_EQUAL("!="), 
	LESS_THAN("<"), 
	GREATER_THAN(">"), 
	LESS_EQUAL("<="), 
	GREATER_EQUAL(">="), 
	LIKE("%"), 
	IN("()");
	
	private String operator;
	
	private ExtJSOperator(String operator) {
		this.operator = operator;
	}
	
	public String getOperator() {
		return operator;
	}
	
	public static ExtJSOperator of(String value) {
		if (Strings.isNullOrEmpty(value)) {
			return ExtJSOperator.EQUAL;
		}
		
		if ("=".equals(value)) {
			return ExtJSOperator.EQUAL;
		}
		if ("!=".equals(value)) {
			return ExtJSOperator.NOT_EQUAL;
		}
		if ("<".equals(value)) {
			return ExtJSOperator.LESS_THAN;
		}
		if (">".equals(value)) {
			return ExtJSOperator.GREATER_THAN;
		}
		if ("<=".equals(value)) {
			return ExtJSOperator.LESS_EQUAL;
		}
		if ("=>".equals(value)) {
			return ExtJSOperator.GREATER_EQUAL;
		}
		if ("%".equals(value)) {
			return ExtJSOperator.LIKE;
		}
		if ("()".equals(value)) {
			return ExtJSOperator.IN;
		}
		
		return ExtJSOperator.EQUAL;
	}
	
}
