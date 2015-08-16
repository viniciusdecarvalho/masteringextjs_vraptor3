package mvcs.model.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mvcs.model.entity.Group;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class DtoGroups extends ArrayList<DtoGroup> implements Dto<List<Group>> {

	private static final long serialVersionUID = 1L;
	
	public DtoGroups(Collection<? extends Group> categories) {
		this.addAll(Lists.transform(Lists.newArrayList(categories.iterator()), new Function<Group, DtoGroup>() {
			@Override
			public DtoGroup apply(Group group) {
				return new DtoGroup(group);
			}
		}));
	}
	
	public DtoGroups() {
	}

	public List<Group> transform() {
		return Lists.transform(this, new Function<DtoGroup, Group>() {
			@Override
			public Group apply(DtoGroup dto) {
				return dto.transform();
			}
		});
	}
	
}