package mvcs.repository.impl;

import java.util.Collection;

import mvcs.dao.HibernateDao;
import mvcs.model.entity.Category;
import mvcs.repository.Categories;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class CategoryRepository extends HibernateDao<Category, Integer> implements Categories {

	protected CategoryRepository(Session session) {
		super(session);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<? extends Category> getByFilm(int filmId) {		
		return getCachedCriteria()
				.createAlias("filmCategories", "fc")
				.add(Restrictions.eq("fc.film.id", filmId))
				.list();
	}

}
