package mvcs.model.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mvcs.model.entity.Film;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class DtoFilms extends ArrayList<DtoFilm> implements Dto<List<Film>> {

	private static final long serialVersionUID = 1L;
	
	public DtoFilms() {
		super();
	}
	
	public DtoFilms(Collection<? extends Film> actors) {
		this.addAll(Lists.transform(Lists.newArrayList(actors.iterator()), new Function<Film, DtoFilm>() {
			@Override
			public DtoFilm apply(Film actor) {
				return new DtoFilm(actor);
			}
		}));
	}
	
	public List<Film> transform() {
		return Lists.transform(this, new Function<DtoFilm, Film>() {
			@Override
			public Film apply(DtoFilm dto) {
				return dto.transform();
			}
		});
	}
	
}