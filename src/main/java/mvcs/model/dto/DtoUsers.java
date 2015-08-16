package mvcs.model.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mvcs.model.entity.User;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class DtoUsers extends ArrayList<DtoUser> implements Dto<List<User>> {

	private static final long serialVersionUID = 1L;
	
	public DtoUsers() {
		super();
	}
	
	public DtoUsers(Collection<? extends User> users) {
		this.addAll(Lists.transform(Lists.newArrayList(users.iterator()), new Function<User, DtoUser>() {
			@Override
			public DtoUser apply(User user) {
				return new DtoUser(user);
			}
		}));
	}
	
	public List<User> transform() {
		return Lists.transform(this, new Function<DtoUser, User>() {
			@Override
			public User apply(DtoUser dto) {
				return dto.transform();
			}
		});
	}
	
}