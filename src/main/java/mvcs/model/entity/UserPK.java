package mvcs.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the user database table.
 * 
 */
@Embeddable
public class UserPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer id;

	@Column(name="Group_id")
	private Integer groupId;

    public UserPK() {
    }

	public UserPK(Integer userId, Integer groupId) {
		id = userId;
		this.groupId = groupId;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGroupId() {
		return this.groupId;
	}
	public void setGroupId(int group_id) {
		this.groupId = group_id;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserPK)) {
			return false;
		}
		UserPK castOther = (UserPK)other;
		return 
			(this.id == castOther.id)
			&& (this.groupId == castOther.groupId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.groupId;
		
		return hash;
    }
}