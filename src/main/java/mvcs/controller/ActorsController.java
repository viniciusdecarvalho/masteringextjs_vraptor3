	package mvcs.controller;

import java.util.List;

import mvcs.custom.annotations.ExtJson;
import mvcs.custom.extjs.ExtJSParam;
import mvcs.model.dto.DtoActor;
import mvcs.model.dto.DtoActors;
import mvcs.model.entity.Actor;
import mvcs.repository.Actors;

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
public class ActorsController extends ExtJSController {

	private static final Logger logger = LoggerFactory.getLogger(ActorsController.class);
	private Actors actors;
	
	public ActorsController(Result result, Validator validator, Actors actors) {
		this.validator = validator;
		this.result = result;
		this.actors = actors;
	}
	
	@ExtJson
	@Put("actors")
	@Post("actors")
	public void save(DtoActors dtos) {
		List<Actor> actors = dtos.transform();
		this.actors.saveAll(actors);
		logger.debug(dtos.toString() + " saved.");	
		json(new DtoActors(actors));
	}	
	
	@ExtJson
	@Delete("actors")
	public void destroy(DtoActors dtos) {		
		for (DtoActor dto : dtos) {
			final Actor actor = actors.get(dto.getId());
			validate(new Validations(){{
				that(actor.getFilmActors().isEmpty(), "constraint", String.format("actor %s belong to any film, then doesn\'t deleted.", actor.toString()));
			}});
			this.actors.delete(actor);
			logger.debug(actor.toString() + " removed.");
		}
		json("actor destoyed sucessfully", true);
	}
	
	@Get("actors")
	public View list(ExtJSParam param, int filmId) {
		if (filmId != 0) {
			return json(new DtoActors(this.actors.getByFilm(filmId)));
		}
		return json(new DtoActors(this.actors.list(param.getFilters())));
	}
	
	@Get("actors/{id}")
	public View get(int id) {
		return json(new DtoActor(this.actors.get(id)));
	}
	
}
