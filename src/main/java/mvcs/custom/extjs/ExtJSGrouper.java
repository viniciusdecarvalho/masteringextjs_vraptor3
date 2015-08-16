package mvcs.custom.extjs;

import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

public class ExtJSGrouper {

	private String property;

	public Projection getProjection() {
		return Projections.groupProperty(property);
	}
	
}
