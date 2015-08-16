package mvcs.model.dto;

import java.sql.Timestamp;

import mvcs.model.entity.Country;

import com.google.gson.annotations.Expose;


/**
 * The persistent class for the country database table.
 * 
 */
public class DtoCountry implements Dto<Country> {

	private int id;
	private String country;
	
	@Expose(deserialize=false, serialize=true)
	private Timestamp lastUpdate;
	
	public DtoCountry() {
	}
	
	public DtoCountry(Country country) {
		this.id = country.getId();
		this.country = country.getCountry();
		this.lastUpdate = country.getLastUpdate();
	}

	@Override
	public Country transform() {
		Country country = new Country();
		country.setId(id);
		country.setCountry(this.country);
		country.setLastUpdate(lastUpdate);
		return country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}