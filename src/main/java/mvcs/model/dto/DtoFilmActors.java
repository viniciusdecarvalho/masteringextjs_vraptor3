package mvcs.model.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mvcs.model.entity.FilmActor;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class DtoFilmActors extends ArrayList<DtoFilmActor> implements Dto<List<FilmActor>> {

	private static final long serialVersionUID = 1L;
	
	public DtoFilmActors() {
		super();
	}
	
	public DtoFilmActors(Collection<? extends FilmActor> filmactors) {
		this.addAll(Lists.transform(Lists.newArrayList(filmactors.iterator()), new Function<FilmActor, DtoFilmActor>() {
			@Override
			public DtoFilmActor apply(FilmActor actor) {
				return new DtoFilmActor(actor);
			}
		}));
	}
	
	public List<FilmActor> transform() {
		return Lists.transform(this, new Function<DtoFilmActor, FilmActor>() {
			@Override
			public FilmActor apply(DtoFilmActor dto) {
				return dto.transform();
			}
		});
	}
	
}