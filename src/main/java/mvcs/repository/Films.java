package mvcs.repository;

import java.util.Collection;
import java.util.List;

import mvcs.dao.Page;
import mvcs.model.entity.Film;


public interface Films {

	Collection<Film> list(Integer start, Integer limit);
	
	Page<Film> page(Integer start, Integer limit);
	
	Long total();
	
	void save(Film film);
	
	void delete(Film film);
	
	Film get(Integer id);

	List<Film> list();
}
