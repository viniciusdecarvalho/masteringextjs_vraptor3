package mvcs.controller;

import java.util.List;

import mvcs.custom.annotations.ExtJson;
import mvcs.model.dto.DtoCategories;
import mvcs.model.dto.DtoCategory;
import mvcs.model.entity.Category;
import mvcs.repository.Categories;

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
import br.com.caelum.vraptor.validator.Validations;

@Resource
public class CategoriesController extends ExtJSController {

	private static final Logger logger = LoggerFactory.getLogger(CategoriesController.class);
	private Categories categories;
	
	public CategoriesController(Result result, Validator validator, Categories categories) {
		this.validator = validator;
		this.result = result;
		this.categories = categories;
	}
	
	@ExtJson
	@Put("categories")
	@Post("categories")
	public void save(DtoCategories dtos) {
		List<Category> categories = dtos.transform();
		this.categories.saveAll(categories);
		logger.debug(dtos.toString() + " saved.");	
		json(new DtoCategories(categories));
	}	
	
	@ExtJson
	@Delete("categories")
	public void destroy(DtoCategories dtos) {		
		for (DtoCategory dto : dtos) {
			final Category category = categories.get(dto.getId());
			validate(new Validations(){{
				that(category.getFilmCategories().isEmpty(), "constraint", String.format("category %s belong to any film, then doesn\'t deleted.", category.getName()));
			}});
			this.categories.delete(category);
			logger.debug(category.toString() + " removed.");
		}
		json("actor destoyed sucessfully", true);
	}
	
	@Get("categories")
	public View list(int filmId) {
		if (filmId != 0) {
			return json(new DtoCategories(categories.getByFilm(filmId)));
		}
		return json(new DtoCategories(categories.list()));
	}
	
}
