package mvcs.model.dto;

import java.sql.Timestamp;

import mvcs.model.entity.City;
import mvcs.model.entity.Country;

import com.google.gson.annotations.Expose;



/**
 * The persistent class for the city database table.
 * 
 */
public class DtoCity implements Dto<City> {

	private Integer id;
	private String city;
	private Integer countryId;
	
	@Expose(deserialize=false, serialize=true)
	private Timestamp lastUpdate;
	
	public DtoCity() {
	}
	
	public DtoCity(City city) {
		id = city.getId();
		this.city = city.getCity();
		countryId = city.getCountry().getId();
		lastUpdate = city.getLastUpdate();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public City transform() {
		City city = new  City();
		city.setId(id);
		city.setCity(this.city);
		city.setCountry(new Country(countryId));
		city.setLastUpdate(lastUpdate);
		return city;
	}
	
	
}