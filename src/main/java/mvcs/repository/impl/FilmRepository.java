package mvcs.repository.impl;

import mvcs.dao.HibernateDao;
import mvcs.model.entity.Film;
import mvcs.repository.Films;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class FilmRepository extends HibernateDao<Film, Integer> implements Films {

	protected FilmRepository(Session session) {
		super(session);
	}

}
