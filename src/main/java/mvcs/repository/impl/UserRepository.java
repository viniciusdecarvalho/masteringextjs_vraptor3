package mvcs.repository.impl;

import java.util.List;

import mvcs.dao.HibernateDao;
import mvcs.model.entity.Menu;
import mvcs.model.entity.User;
import mvcs.model.entity.UserPK;
import mvcs.repository.Users;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class UserRepository extends HibernateDao<User, UserPK> implements Users {

	protected UserRepository(Session session) {
		super(session);
	}

	public User login(String userName, String password) {
		try {
			return (User) getSession().createCriteria(User.class)
						      .add(Restrictions.eq("userName", userName))
						      .add(Restrictions.eq("password", password))
						      .uniqueResult();
		} catch (HibernateException e) {
			throw new IllegalArgumentException("Falha na autenticacao, usuario e/ou senha invalidos", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> getRootMenu(User user) {
		StringBuilder hql = new StringBuilder();
		hql.append("select m from Menu m where exists(");
		hql.append("	select u.group from User u where u.userName = :userName");
		hql.append(	")and m.menu is null ");
		hql.append(	"order by m.text");
		return query(hql.toString())
				.setParameter("userName", user.getUserName())
				.list();
	}
	
}
