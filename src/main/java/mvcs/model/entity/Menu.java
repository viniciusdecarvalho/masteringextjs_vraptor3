package mvcs.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mvcs.model.Pk;


/**
 * The persistent class for the menu database table.
 * 
 */
@Entity
@Table(name="menu")
public class Menu implements Pk<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String className;

	private String iconCls;

	private String text;

	//bi-directional many-to-one association to Menu
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id")
	private Menu menu;

	//bi-directional many-to-one association to Menu
	@OneToMany(mappedBy="menu", fetch=FetchType.EAGER)
	private List<Menu> menus;

	//bi-directional many-to-many association to Group
    @ManyToMany
	@JoinTable(
		name="permissions"
		, joinColumns={
			@JoinColumn(name="Menu_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Group_id")
			}
		)
	private List<Group> permissions;

    public Menu() {
    	menus = new ArrayList<>();
    	permissions = new ArrayList<>();
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public List<Menu> getMenus() {
		return this.menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
	public List<Group> getGroups() {
		return this.permissions;
	}

	public void setGroups(List<Group> groups) {
		this.permissions = groups;
	}
	
}