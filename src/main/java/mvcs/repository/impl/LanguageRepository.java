package mvcs.repository.impl;

import mvcs.dao.HibernateDao;
import mvcs.model.entity.Language;
import mvcs.repository.Languages;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class LanguageRepository extends HibernateDao<Language, Integer> implements Languages {

	protected LanguageRepository(Session session) {
		super(session);
	}

}
