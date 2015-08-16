package mvcs.model.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import mvcs.model.entity.Actor;
import mvcs.model.entity.Category;
import mvcs.model.entity.Film;
import mvcs.model.entity.FilmActor;
import mvcs.model.entity.FilmCategory;
import mvcs.model.entity.Language;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.gson.annotations.Expose;

public class DtoFilm implements Dto<Film> {
	
	private Integer id;
	private String title;
	private String description;
	private Integer releaseYear; // YYYY
	private Integer languageId;
	private Integer originalLanguageId;
	private Byte rentalDuration;
	private BigDecimal rentalRate;
	private Integer length;
	private BigDecimal replacementCost;
	private String rating;
	private String specialFeatures;
	
	@Expose(deserialize=false)
	private Timestamp lastUpdate;
	private DtoActors actors;
	private DtoCategories categories;
	
	@Expose(deserialize=false, serialize=false)
	private transient Film film;

	public DtoFilm() {
	}
	
	public DtoFilm(Film film) {
		this.id = film.getId();
		this.title = film.getTitle();
		this.description = film.getDescription();
		this.releaseYear = film.getReleaseYear();
		Language language = film.getLanguage1();
		if (language != null) {
			this.languageId = language.getId();
		}
		Language originalLanguage = film.getLanguage2();
		if (originalLanguage != null) {
			this.originalLanguageId = originalLanguage.getId();
		}
		this.rentalDuration = film.getRentalDuration();
		this.rentalRate = film.getRentalRate();
		this.length = film.getLength();
		this.replacementCost = film.getReplacementCost();
		this.rating = film.getRating();
		this.specialFeatures = film.getSpecialFeatures();
		this.lastUpdate = film.getLastUpdate();
//		if (!film.getFilmActors().isEmpty()) {
//			this.actors = new DtoActors();
//			Set<FilmActor> filmActors = film.getFilmActors();
//			for (FilmActor filmActor : filmActors) {
//				this.actors.add(new DtoActor(filmActor.getActor()));
//			}
//		}
//		if (!film.getFilmCategories().isEmpty()) {
//			this.categories = new DtoCategories();
//			Set<FilmCategory> filmCategories = film.getFilmCategories();
//			for (FilmCategory filmCategory : filmCategories) {
//				this.categories.add(new DtoCategory(filmCategory.getCategory()));
//			}
//		}
	}

	@Override	
	public Film transform() {
		film = new Film(id);
		film.setDescription(description);
		film.setTitle(title);
		film.setLength(length);
		film.setReleaseYear(releaseYear);
		film.setLanguage1(new  Language(originalLanguageId));
		film.setLanguage2(new Language(languageId));
		film.setRating(rating);
		film.setRentalDuration(rentalDuration);
		film.setRentalRate(rentalRate);
		film.setReplacementCost(replacementCost);
		film.setSpecialFeatures(specialFeatures);		
		if (actors != null)
			film.setFilmActors(resolveFilmActors(actors.transform()));
		if (categories != null)
			film.setFilmCategories(resolveFilmCategories(categories.transform()));
		return film;
	}

	private List<FilmActor> resolveFilmActors(List<Actor> actors) {
		return Lists.transform(actors, new Function<Actor, FilmActor>() {
			@Override
			public FilmActor apply(Actor actor) {				
				FilmActor filmActor = new FilmActor(film, actor);
				film.getFilmActors().add(filmActor);
				return filmActor;
			}
		});
	}
	
	private List<FilmCategory> resolveFilmCategories(List<Category> categories) {
		return Lists.transform(categories, new Function<Category, FilmCategory>() {
			@Override
			public FilmCategory apply(Category category) {
				FilmCategory filmCategory = new FilmCategory(film, category);
				film.getFilmCategories().add(filmCategory);
				return filmCategory;
			}
		});
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public Integer getOriginalLanguageId() {
		return originalLanguageId;
	}

	public void setOriginalLanguageId(Integer originalLanguageId) {
		this.originalLanguageId = originalLanguageId;
	}

	public Byte getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(Byte rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public BigDecimal getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(BigDecimal rentalRate) {
		this.rentalRate = rentalRate;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public BigDecimal getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(BigDecimal replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

//	public DtoActors getFilmActors() {
//		return actors;
//	}
//
//	public void setFilmActors(DtoActors filmActors) {
//		this.actors = filmActors;
//	}
//
//	public DtoCategories getFilmCategories() {
//		return categories;
//	}
//
//	public void setFilmCategories(DtoCategories filmCategories) {
//		this.categories = filmCategories;
//	}
	
	
	
}