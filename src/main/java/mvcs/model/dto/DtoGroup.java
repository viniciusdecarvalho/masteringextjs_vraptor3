package mvcs.model.dto;

import mvcs.model.entity.Group;


/**
 * The persistent class for the category database table.
 * 
 */
public class DtoGroup implements Dto<Group> {

	private Integer id;
	private String name;
	
	public DtoGroup() {
	}
	
	public DtoGroup(Group group) {
		id = group.getId();
		name = group.getName();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Group transform() {
		Group group = new Group(id);
		group.setName(name);
		return group;
	}
	
	
}