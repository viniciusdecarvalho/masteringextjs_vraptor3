package mvcs.custom.extjs;

import static com.google.common.base.Strings.isNullOrEmpty;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.ge;
import static org.hibernate.criterion.Restrictions.gt;
import static org.hibernate.criterion.Restrictions.in;
import static org.hibernate.criterion.Restrictions.le;
import static org.hibernate.criterion.Restrictions.like;
import static org.hibernate.criterion.Restrictions.lt;
import static org.hibernate.criterion.Restrictions.ne;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;

public class ExtJSFilter {

	private String property;
	private Object value;
	private MatchMode matcher = MatchMode.START;
	private String operator = "";	
	
	public void prepareCriteria(Criteria criteria) {		
		
		if (value == null || isNullOrEmpty(property)){
			return;
		}
		
		switch (ExtJSOperator.of(operator)) {
		case NOT_EQUAL:
			criteria.add(ne(property, value));
			break;
		case LESS_THAN:
			criteria.add(lt(property, value));
			break;
		case GREATER_THAN:
			criteria.add(gt(property, value));
			break;
		case LESS_EQUAL:
			criteria.add(le(property, value));
			break;
		case GREATER_EQUAL:
			criteria.add(ge(property, value));
			break;
		case LIKE:
			criteria.add(like(property, (String) value, matcher));
			break;
		case IN:
			criteria.add(in(property, (Object[]) value));
		default:
			criteria.add(eq(property, value));
			break;
		}
		
	}
	
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public MatchMode getMatcher() {
		return matcher;
	}

	public void setMatcher(MatchMode matcher) {
		this.matcher = matcher;
	}

	public ExtJSOperator getOperator() {
		return ExtJSOperator.valueOf(operator.toUpperCase());
	}

	public void setOperator(ExtJSOperator operator) {
		this.operator = operator.getOperator();
	}

}
