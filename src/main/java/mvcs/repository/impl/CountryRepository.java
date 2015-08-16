package mvcs.repository.impl;

import mvcs.dao.HibernateDao;
import mvcs.model.entity.Country;
import mvcs.repository.Countries;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class CountryRepository extends HibernateDao<Country, Integer> implements Countries {

	protected CountryRepository(Session session) {
		super(session);
	}

}
