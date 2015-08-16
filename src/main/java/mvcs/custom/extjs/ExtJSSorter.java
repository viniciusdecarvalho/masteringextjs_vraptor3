package mvcs.custom.extjs;

import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Order.desc;

import org.hibernate.criterion.Order;

public class ExtJSSorter {

	private String direction;
	private String property;

	public Order getOrder() {
		return "ASC".equalsIgnoreCase(direction) ? asc(property) : desc(property); 
	}
	
}
