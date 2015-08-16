package mvcs.model.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mvcs.model.entity.Category;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class DtoCategories extends ArrayList<DtoCategory> implements Dto<List<Category>> {

	private static final long serialVersionUID = 1L;
	
	public DtoCategories(Collection<? extends Category> categories) {
		this.addAll(Lists.transform(Lists.newArrayList(categories.iterator()), new Function<Category, DtoCategory>() {
			@Override
			public DtoCategory apply(Category category) {
				return new DtoCategory(category);
			}
		}));
	}
	
	public DtoCategories() {
	}

	public List<Category> transform() {
		return Lists.transform(this, new Function<DtoCategory, Category>() {
			@Override
			public Category apply(DtoCategory dto) {
				return dto.transform();
			}
		});
	}
	
}