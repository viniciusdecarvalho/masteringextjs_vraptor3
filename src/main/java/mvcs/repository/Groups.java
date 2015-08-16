package mvcs.repository;

import java.util.Collection;

import mvcs.model.entity.Group;

public interface Groups {

	public Collection<Group> list();

	public Group get(Integer id);

}