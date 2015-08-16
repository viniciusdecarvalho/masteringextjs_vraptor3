package mvcs.repository;

import java.util.Collection;
import java.util.List;

import mvcs.model.entity.City;

public interface Cities {

	List<City> list();
	
	City get(Integer id);
	
	void saveAll(Collection<City> actors);
	
	void delete(City actor);
	
}
