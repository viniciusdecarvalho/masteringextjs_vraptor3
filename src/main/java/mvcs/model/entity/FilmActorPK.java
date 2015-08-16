package mvcs.model.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the film_actor database table.
 * 
 */
@Embeddable
public class FilmActorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="actor_id")
	private Integer actorId;

	@Column(name="film_id")
	private Integer filmId;

    public FilmActorPK() {
    }
	public int getActorId() {
		return this.actorId;
	}
	public void setActorId(Integer actorId) {
		this.actorId = actorId;
	}
	public int getFilmId() {
		return this.filmId;
	}
	public void setFilmId(Integer filmId) {
		this.filmId = filmId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FilmActorPK)) {
			return false;
		}
		FilmActorPK castOther = (FilmActorPK)other;
		return 
			(this.actorId == castOther.actorId)
			&& (this.filmId == castOther.filmId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.actorId;
		hash = hash * prime + this.filmId;
		
		return hash;
    }
}