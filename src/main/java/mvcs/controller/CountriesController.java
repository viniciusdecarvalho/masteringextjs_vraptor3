package mvcs.controller;

import java.util.List;

import mvcs.custom.annotations.ExtJson;
import mvcs.model.dto.DtoCountries;
import mvcs.model.dto.DtoCountry;
import mvcs.model.entity.Country;
import mvcs.repository.Countries;

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
public class CountriesController extends ExtJSController {

	private static final Logger logger = LoggerFactory.getLogger(CountriesController.class);
	private Countries countries;
	
	public CountriesController(Result result, Validator validator, Countries countries) {
		this.validator = validator;
		this.result = result;
		this.countries = countries;
	}
	
	@ExtJson
	@Put("countries")
	@Post("countries")
	public void save(DtoCountries dtos) {
		List<Country> countries = dtos.transform();
		this.countries.saveAll(countries);
		logger.debug(dtos.toString() + " saved.");	
		json(new DtoCountries(countries));
	}	
	
	@ExtJson
	@Delete("countries")
	public void destroy(DtoCountries dtos) {		
		for (DtoCountry dto : dtos) {
			final Country country = countries.get(dto.getId());
			validate(new Validations(){{
				that(country.getCities().isEmpty(), "constraint", String.format("country %s belong to any city, then doesn\'t deleted.", country.toString()));
			}});
			this.countries.delete(country);
			logger.debug(country.toString() + " removed.");
		}
		json("country destoyed sucessfully", true);
	}
	
	@Get("countries")
	public void list() {
		json(new DtoCountries(countries.list()));
	}
	
}
