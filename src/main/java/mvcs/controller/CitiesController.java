package mvcs.controller;

import java.util.List;

import mvcs.custom.annotations.ExtJson;
import mvcs.model.dto.DtoCity;
import mvcs.model.dto.DtoCities;
import mvcs.model.entity.City;
import mvcs.repository.Cities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

@Resource
public class CitiesController extends ExtJSController {

	private static final Logger logger = LoggerFactory.getLogger(CitiesController.class);
	private Cities cities;
	
	public CitiesController(Result result, Validator validator, Cities cities) {
		this.validator = validator;
		this.result = result;
		this.cities = cities;
	}
	
	@ExtJson
	@Put("cities")
	@Post("cities")
	public void save(DtoCities dtos) {
		List<City> cities = dtos.transform();
		this.cities.saveAll(cities);
		logger.debug(dtos.toString() + " saved.");	
		json(new DtoCities(cities));
	}	
	
	@ExtJson
	@Delete("cities")
	public void destroy(DtoCities dtos) {		
		for (DtoCity dto : dtos) {
			final City city = cities.get(dto.getId());
			validate(new Validations(){{
				that(city.getAddresses().isEmpty(), "constraint", String.format("city %s belong to any address, then doesn\'t deleted.", city.toString()));
			}});
			this.cities.delete(city);
			logger.debug(city.toString() + " removed.");
		}
		json("actor destoyed sucessfully", true);
	}
	
	@Get("cities")
	public void list() {
		json(new DtoCities(cities.list()));
	}
	
}
