package mvcs.model.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mvcs.model.entity.FilmCategory;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class DtoFilmCategories extends ArrayList<DtoFilmCategory> implements Dto<List<FilmCategory>> {

	private static final long serialVersionUID = 1L;
	
	public DtoFilmCategories() {
		super();
	}
	
	public DtoFilmCategories(Collection<? extends FilmCategory> filmCategories) {
		this.addAll(Lists.transform(Lists.newArrayList(filmCategories.iterator()), new Function<FilmCategory, DtoFilmCategory>() {
			@Override
			public DtoFilmCategory apply(FilmCategory category) {
				return new DtoFilmCategory(category);
			}
		}));
	}
	
	public List<FilmCategory> transform() {
		return Lists.transform(this, new Function<DtoFilmCategory, FilmCategory>() {
			@Override
			public FilmCategory apply(DtoFilmCategory dto) {
				return dto.transform();
			}
		});
	}
	
}