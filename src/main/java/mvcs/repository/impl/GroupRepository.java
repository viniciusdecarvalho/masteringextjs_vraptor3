package mvcs.repository.impl;

import mvcs.dao.HibernateDao;
import mvcs.model.entity.Group;
import mvcs.repository.Groups;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class GroupRepository extends HibernateDao<Group, Integer> implements Groups {

	protected GroupRepository(Session session) {
		super(session);
	}
	
}
