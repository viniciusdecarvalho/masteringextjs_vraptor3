package mvcs.controller;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import mvcs.model.dto.DtoGroups;
import mvcs.model.dto.DtoMenus;
import mvcs.model.dto.DtoUsers;
import mvcs.model.entity.Menu;
import mvcs.model.entity.User;
import mvcs.model.info.UserInfo;
import mvcs.repository.Groups;
import mvcs.repository.Users;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.View;
import br.com.caelum.vraptor.validator.Validations;

@Resource
public class SecurityController extends ExtJSController {

	private Groups groups;
	private Users users;
	private UserInfo userInfo;
	
	public SecurityController(Result result, Validator validator, Groups groups, Users users, UserInfo userInfo) {
		this.result = result;
		this.validator = validator;
		this.groups = groups;
		this.users = users;
		this.userInfo = userInfo;
	}

	@Post("security/users/login")
	public void login(final String username, final String password) {
		validate(new Validations() {{
			that(!isNullOrEmpty(username), "usuario.nome", "usuario.nome.obrigatorio");	
			that(!isNullOrEmpty(password), "usuario.senha", "usuario.senha.obrigatorio");	
		}});
		
		User user = users.login(username, password);
		List<Menu> rootMenu = users.getRootMenu(user);
		userInfo.setUser(user);
		userInfo.setRootMenu(rootMenu);
		json("Usuario autenticado com sucesso!", true);
	}
	
	@Get("security/users/logout")
	public void logout() {
		userInfo.logout();
	}
	
	@Get("security/users/menu")
	public void menu() {	
		json(userInfo.getRootMenu());
	}
	
	@Get("security/users")
	public View users() {
		return json(new DtoUsers(users.list()));
	}
	
	@Get("security/groups")
	public View groups() {
		return json(new DtoGroups(groups.list()));
	}
	
	@Get("security/menu/permissions")
	public View permissions(Integer groupId) {
		List<Menu> permissions = groups.get(groupId).getMenus();
		permissions = Lists.transform(permissions, new Function<Menu, Menu>() {
			@Override
			public Menu apply(Menu menu) {
				if (!menu.getMenus().isEmpty()) {
					return menu;
				}
				return null;
			}
		});
		permissions.removeAll(Lists.newArrayList(null, null));
		return json(new DtoMenus(permissions));
	}
	
}
