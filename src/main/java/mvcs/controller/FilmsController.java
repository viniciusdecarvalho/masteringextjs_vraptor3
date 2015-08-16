package mvcs.controller;

import java.io.InputStream;
import java.util.Iterator;

import mvcs.custom.annotations.ExtJson;
import mvcs.dao.Page;
import mvcs.model.dto.DtoFilm;
import mvcs.model.dto.DtoFilms;
import mvcs.model.entity.Film;
import mvcs.model.entity.FilmActor;
import mvcs.model.entity.FilmCategory;
import mvcs.repository.Films;
import mvcs.service.Pdf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.View;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.download.InputStreamDownload;

@Resource
public class FilmsController extends ExtJSController {

	private static final Logger logger = LoggerFactory.getLogger(FilmsController.class);
	private Films films;
	
	public FilmsController(Result result, Validator validator, Films films) {
		this.validator = validator;
		this.result = result;
		this.films = films;
	}
	
	@ExtJson
	@Put("films")
	@Post("films")
	public View save(DtoFilm dto) {
		Film film = dto.transform();
		validate(film);
		
		films.save(film);
		
		Iterator<FilmActor> itFilmActors = film.getFilmActors().iterator();
		while (itFilmActors.hasNext()) {
			itFilmActors.next().setFilm(film);
		}
		
		Iterator<FilmCategory> itFilmCategories = film.getFilmCategories().iterator();
		while (itFilmCategories.hasNext()) {
			itFilmCategories.next().setFilm(film);
		}

		logger.info(film.getTitle() + " saved.");
		return json(dto);
	}
	
	@ExtJson
	@Delete("films")
	public View destroy(DtoFilm dto) {
		Film film = films.get(dto.getId());
		films.delete(film);
		logger.info(dto.getTitle() + " removed.");
		return json(dto);
	}

	@Get("films")
	public void list(int start, int limit) {
		Page<Film> page = this.films.page(start, limit);
		DtoFilms dtoFilms = new DtoFilms(page.getElements());
		json(dtoFilms, page.getTotal());
	}
	
	@Get("pdf")
	public Download pdf() {
		InputStream stream = new Pdf().films(this.films.list());
		return new InputStreamDownload(stream, "application/pdf", "films.pdf");
	}
	
}
