package mvcs.model.dto;

import java.sql.Timestamp;

import mvcs.model.entity.Language;

import com.google.gson.annotations.Expose;

/**
 * The persistent class for the language database table.
 * 
 */
public class DtoLanguage implements Dto<Language> {

	private Integer id;
	private String name;

	@Expose(deserialize = false, serialize = true)
	private Timestamp lastUpdate;

	public DtoLanguage(Language language) {
		id = language.getId();
		name = language.getName();
		lastUpdate = language.getLastUpdate();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public Language transform() {
		Language language = new Language();
		language.setId(id);
		language.setName(name);
		return language;
	}

}