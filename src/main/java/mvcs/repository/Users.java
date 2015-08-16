package mvcs.repository;

import java.util.List;

import mvcs.model.entity.Menu;
import mvcs.model.entity.User;

public interface Users {

	User login(String userName, String password);

	List<Menu> getRootMenu(User user);
	
	List<User> list();
	
}
