package mvcs.repository;

import java.util.Collection;
import java.util.List;

import mvcs.model.entity.Category;

public interface Categories {

	List<Category> list();
	
	Category get(Integer id);
	
	void saveAll(Collection<Category> actors);
	
	void delete(Category actor);

	Collection<? extends Category> getByFilm(int filmId);
}
