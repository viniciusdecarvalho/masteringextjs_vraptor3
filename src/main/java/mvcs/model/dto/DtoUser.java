package mvcs.model.dto;

import mvcs.model.entity.Group;
import mvcs.model.entity.User;
import mvcs.model.entity.UserPK;

public class DtoUser implements Dto<User> {

	private int id;
	private String name;
	private String userName;
	private String email;
	private String picture;
	private int appGroupId;
	private String password;

	public DtoUser(User user) {
		this.id = user.getId().getId();
		this.appGroupId = user.getId().getGroupId();
		this.userName = user.getUserName();
		this.email = user.getEmail();
		this.picture = user.getPicture();
		this.password = user.getPassword();
	}

	@Override
	public User transform() {
		User user = new User(new UserPK(id, appGroupId));
		user.setGroup(new Group(appGroupId));
		user.setName(name);
		user.setUserName(userName);
		user.setEmail(email);
		user.setPicture(picture);
		user.setPassword(password);
		return user;
	}

}
