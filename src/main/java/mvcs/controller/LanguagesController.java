package mvcs.controller;

import java.util.List;

import mvcs.custom.annotations.ExtJson;
import mvcs.model.dto.DtoLanguage;
import mvcs.model.dto.DtoLanguages;
import mvcs.model.entity.Language;
import mvcs.repository.Languages;

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
public class LanguagesController extends ExtJSController {

	private static final Logger logger = LoggerFactory.getLogger(LanguagesController.class);
	private Languages languages;
	
	public LanguagesController(Result result, Validator validator, Languages languages) {
		this.validator = validator;
		this.result = result;
		this.languages = languages;
	}
	
	@ExtJson
	@Put("languages")
	@Post("languages")
	public void save(DtoLanguages dtos) {
		List<Language> languages = dtos.transform();
		this.languages.saveAll(languages);
		logger.debug(dtos.toString() + " saved.");	
		json(new DtoLanguages(languages));
	}	
	
	@ExtJson
	@Delete("languages")
	public void destroy(DtoLanguages dtos) {		
		for (DtoLanguage dto : dtos) {
			final Language actor = languages.get(dto.getId());
			validate(new Validations(){{
				that(actor.getFilms1().isEmpty(), "constraint", String.format("language %s belong to any film, then doesn\'t deleted.", actor.toString()));
				that(actor.getFilms2().isEmpty(), "constraint", String.format("language %s belong to any film, then doesn\'t deleted.", actor.toString()));
			}});
			this.languages.delete(actor);
			logger.debug(actor.toString() + " removed.");
		}
		json("actor destoyed sucessfully", true);
	}
	
	@Get("languages")
	public void list() {
		json(new DtoLanguages(languages.list()));
	}
	
}
