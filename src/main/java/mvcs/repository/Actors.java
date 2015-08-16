package mvcs.repository;

import java.util.Collection;
import java.util.List;

import mvcs.custom.extjs.ExtJSFilters;
import mvcs.model.entity.Actor;

public interface Actors {

	List<Actor> list(ExtJSFilters filter);
	
	Actor get(Integer id);
	
	void saveAll(Collection<Actor> actors);
	
	void delete(Actor actor);

	Collection<? extends Actor> getByFilm(int filmId);

}
