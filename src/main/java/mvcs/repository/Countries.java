package mvcs.repository;

import java.util.Collection;
import java.util.List;

import mvcs.model.entity.Country;

public interface Countries {

	List<Country> list();
	
	Country get(Integer id);
	
	void saveAll(Collection<Country> actors);
	
	void delete(Country actor);
	
}
