package mvcs.model.dto;

import java.sql.Timestamp;

import com.google.gson.annotations.Expose;

import mvcs.model.entity.Category;


/**
 * The persistent class for the category database table.
 * 
 */
public class DtoCategory implements Dto<Category> {

	private Integer id;
	private String name;
	
	@Expose(serialize=true, deserialize= false)
	private Timestamp lastUpdate;
	
	public DtoCategory() {
	}
	
	public DtoCategory(Category category) {
		id = category.getId();
		name = category.getName();
		lastUpdate = category.getLastUpdate();
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

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Category transform() {
		Category category = new Category();
		category.setId(id);
		category.setName(name);
		category.setLastUpdate(lastUpdate);
		return category;
	}
	
	
}