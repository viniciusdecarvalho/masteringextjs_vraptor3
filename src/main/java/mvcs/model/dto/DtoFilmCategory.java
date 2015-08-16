package mvcs.model.dto;

import java.sql.Timestamp;

import mvcs.model.entity.Category;
import mvcs.model.entity.Film;
import mvcs.model.entity.FilmCategory;

import com.google.gson.annotations.Expose;

public class DtoFilmCategory implements Dto<FilmCategory> {
	
	private Integer categoryId;
	private Integer filmId;
	
	@Expose(serialize=true, deserialize= false)
	private Timestamp lastUpdate;
	
	public DtoFilmCategory() {
	}
	
	public DtoFilmCategory(FilmCategory filmcategory) {
		categoryId = filmcategory.getCategory().getId();
		filmId = filmcategory.getFilm().getId();
		lastUpdate = filmcategory.getLastUpdate();
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public FilmCategory transform() {
		FilmCategory filmCategory = new FilmCategory();		
		filmCategory.setCategory(new Category(categoryId));
		filmCategory.setFilm(new Film(filmId));
		return filmCategory;
	}

	public Integer getFilmId() {
		return filmId;
	}

	public void setFilmId(Integer filmId) {
		this.filmId = filmId;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
}
