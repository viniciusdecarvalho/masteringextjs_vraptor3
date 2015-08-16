package mvcs.model.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mvcs.model.entity.Menu;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class DtoMenus extends ArrayList<DtoMenu> implements Dto<List<Menu>> {

	private static final long serialVersionUID = 1L;
	
	public DtoMenus() {
		super();
	}
	
	public DtoMenus(Collection<? extends Menu> menus) {
		this.addAll(Lists.transform(Lists.newArrayList(menus.iterator()), new Function<Menu, DtoMenu>() {
			@Override
			public DtoMenu apply(Menu menu) {
				return new DtoMenu(menu);
			}
		}));
	}
	
	public List<Menu> transform() {
		return Lists.transform(this, new Function<DtoMenu, Menu>() {
			@Override
			public Menu apply(DtoMenu dto) {
				return dto.transform();
			}
		});
	}
	
}