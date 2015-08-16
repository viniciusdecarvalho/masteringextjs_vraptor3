package mvcs.repository.impl;

import mvcs.dao.HibernateDao;
import mvcs.model.entity.City;
import mvcs.repository.Cities;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class CityRepository extends HibernateDao<City, Integer> implements Cities {

	protected CityRepository(Session session) {
		super(session);
	}

}
