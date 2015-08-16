package mvcs.model.dto;

import java.sql.Timestamp;

import mvcs.model.entity.Actor;
import mvcs.model.entity.Film;
import mvcs.model.entity.FilmActor;

import com.google.gson.annotations.Expose;

public class DtoFilmActor implements Dto<FilmActor> {
	
	private Integer actorId;
	private Integer filmId;
	
	@Expose(serialize=true, deserialize= false)
	private Timestamp lastUpdate;
	
	public DtoFilmActor() {
	}
	
	public DtoFilmActor(FilmActor filmActor) {
		actorId = filmActor.getActor().getId();
		filmId = filmActor.getFilm().getId();
		lastUpdate = filmActor.getLastUpdate();
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public FilmActor transform() {
		FilmActor filmActor = new FilmActor();		
		filmActor.setActor(new Actor(actorId));
		filmActor.setFilm(new Film(filmId));
		return filmActor;
	}

	public Integer getActorId() {
		return actorId;
	}

	public void setActorId(Integer actorId) {
		this.actorId = actorId;
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
	
}
