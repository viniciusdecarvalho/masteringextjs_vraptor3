package mvcs.model.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mvcs.model.Pk;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
public class User implements Pk<UserPK> {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserPK id;

	private String email;

	private String name;

	private String password;

	private String picture;

	private String userName;

	//bi-directional many-to-one association to Group
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Group_id", insertable=false, updatable=false)
	private Group group;
	
    public User() {
    }
    
    public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
    }

	public User(UserPK id) {
		this.id = id;
	}

	public UserPK getId() {
		return this.id;
	}

	public void setId(UserPK id) {
		this.id = id;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

}