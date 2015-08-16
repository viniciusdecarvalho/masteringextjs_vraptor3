package mvcs.repository;

import java.util.Collection;
import java.util.List;

import mvcs.model.entity.Language;

public interface Languages {

	List<Language> list();
	
	Language get(Integer id);
	
	void saveAll(Collection<Language> actors);
	
	void delete(Language actor);
	
}
