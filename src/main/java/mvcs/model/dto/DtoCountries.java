package mvcs.model.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mvcs.model.entity.Country;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class DtoCountries extends ArrayList<DtoCountry> implements Dto<List<Country>> {

	private static final long serialVersionUID = 1L;
	
	public DtoCountries() {
		super();
	}
	
	public DtoCountries(Collection<? extends Country> actors) {
		this.addAll(Lists.transform(Lists.newArrayList(actors.iterator()), new Function<Country, DtoCountry>() {
			@Override
			public DtoCountry apply(Country actor) {
				return new DtoCountry(actor);
			}
		}));
	}
	
	public List<Country> transform() {
		return Lists.transform(this, new Function<DtoCountry, Country>() {
			@Override
			public Country apply(DtoCountry dto) {
				return dto.transform();
			}
		});
	}
	
}