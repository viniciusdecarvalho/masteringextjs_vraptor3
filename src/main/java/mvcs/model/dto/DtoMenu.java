package mvcs.model.dto;

import java.util.ArrayList;
import java.util.List;

import mvcs.model.entity.Menu;


/**
 * The persistent class for the menu database table.
 * 
 */
public class DtoMenu implements Dto<Menu> {
	
	private Integer id;
	private String className;
	private String iconCls;
	private String text;
	private Boolean expanded;
	private Boolean checked;
	private Boolean leaf;
	private List<DtoMenu> children;
	
	public DtoMenu() {
	}
	
	public DtoMenu(Menu menu) {
		id = menu.getId();
		className = menu.getClassName();
		iconCls = menu.getIconCls();
		text = menu.getText();
		expanded = true;
		checked = true;
		leaf = false;
		List<Menu> children = menu.getMenus();
		if (!children.isEmpty()) {
			this.children = new ArrayList<DtoMenu>();
			for (Menu c : children) {
				DtoMenu child = new DtoMenu(c);				
				child.checked = true;
				child.leaf = true;
				this.children.add(child);
			}
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<DtoMenu> getChildren() {
		return children;
	}

	public void setChildren(List<DtoMenu> children) {
		this.children = children;
	}

	@Override
	public Menu transform() {
		Menu menu = new Menu();
		menu.setId(id);
		menu.setClassName(className);
		menu.setIconCls(iconCls);
		menu.setText(text);
		return menu;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}