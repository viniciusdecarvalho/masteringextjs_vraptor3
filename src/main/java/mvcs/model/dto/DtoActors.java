package mvcs.model.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mvcs.model.entity.Actor;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class DtoActors extends ArrayList<DtoActor> implements Dto<List<Actor>> {

	private static final long serialVersionUID = 1L;
	
	public DtoActors() {
		super();
	}
	
	public DtoActors(Collection<? extends Actor> actors) {
		this.addAll(Lists.transform(Lists.newArrayList(actors.iterator()), new Function<Actor, DtoActor>() {
			@Override
			public DtoActor apply(Actor actor) {
				return new DtoActor(actor);
			}
		}));
	}
	
	public List<Actor> transform() {
		return Lists.transform(this, new Function<DtoActor, Actor>() {
			@Override
			public Actor apply(DtoActor dto) {
				return dto.transform();
			}
		});
	}
	
}