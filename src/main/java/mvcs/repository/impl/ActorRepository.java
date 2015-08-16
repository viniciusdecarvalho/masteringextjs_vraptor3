package mvcs.repository.impl;

import java.util.Collection;
import java.util.List;

import mvcs.custom.extjs.ExtJSFilter;
import mvcs.custom.extjs.ExtJSFilters;
import mvcs.dao.HibernateDao;
import mvcs.model.entity.Actor;
import mvcs.repository.Actors;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class ActorRepository extends HibernateDao<Actor, Integer> implements Actors {

	protected ActorRepository(Session session) {
		super(session);
	}

	@SuppressWarnings("unchecked")
	private List<Actor> getByFilters(ExtJSFilters filters) {
		Criteria criteria = getCachedCriteria();
		for (ExtJSFilter filter : filters) {
			filter.prepareCriteria(criteria);
		}
		return criteria.list();
	}

	@Override
	public List<Actor> list(ExtJSFilters filters) {
		if (filters.isEmpty() ) {
			return super.list();
		}
		
		return getByFilters(filters);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<? extends Actor> getByFilm(int filmId) {
		return getCachedCriteria()
				.createAlias("filmActors", "fa")
				.add(Restrictions.eq("fa.film.id", filmId))
				.list();
	}

}
