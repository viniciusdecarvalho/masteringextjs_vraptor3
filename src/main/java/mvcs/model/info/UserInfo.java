package mvcs.model.info;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import mvcs.model.dto.DtoMenu;
import mvcs.model.dto.DtoMenus;
import mvcs.model.entity.Menu;
import mvcs.model.entity.User;
import br.com.caelum.vraptor.ioc.SessionScoped;

@SessionScoped
public class UserInfo {

	private User user;
	private List<DtoMenu> rootMenu;
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setRootMenu(List<Menu> rootMenu) {
		this.rootMenu = new DtoMenus(rootMenu);
	}
	
	public User getUser() {
		return user;
	}
	
	public void logout() {
		this.user = null;
	}
	
	public List<DtoMenu> getRootMenu() {
		checkArgument(user != null, "Usuario nao logado.");
		return rootMenu;
	}
	
}
