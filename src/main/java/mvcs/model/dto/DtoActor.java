package mvcs.model.dto;

import java.sql.Timestamp;

import com.google.gson.annotations.Expose;

import mvcs.model.entity.Actor;

public class DtoActor implements Dto<Actor> {

	private Integer id;
	private String firstName;
	private String lastName;
	
	@Expose(serialize=true, deserialize= false)
	private Timestamp lastUpdate;
	
	public DtoActor() {
	}
	
	public DtoActor(Actor actor) {
		id = actor.getId();
		firstName = actor.getFirstName();
		lastName = actor.getLastName();
		lastUpdate = actor.getLastUpdate();
	}

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public Actor transform() {
		Actor actor = new Actor();
		actor.setId(id);
		actor.setFirstName(firstName);
		actor.setLastName(lastName);
		return actor;
	}

	@Override
	public String toString() {
		return "ActorDto [id=" + id + ", name=" + firstName + " " + lastName + "]";
	}
	
}
