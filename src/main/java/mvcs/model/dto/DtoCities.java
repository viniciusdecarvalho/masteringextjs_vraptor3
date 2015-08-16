package mvcs.model.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mvcs.model.entity.City;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class DtoCities extends ArrayList<DtoCity> implements Dto<List<City>> {

	private static final long serialVersionUID = 1L;
	
	public DtoCities() {
		super();
	}
	
	public DtoCities(Collection<? extends City> cities) {
		this.addAll(Lists.transform(Lists.newArrayList(cities.iterator()), new Function<City, DtoCity>() {
			@Override
			public DtoCity apply(City actor) {
				return new DtoCity(actor);
			}
		}));
	}
	
	public List<City> transform() {
		return Lists.transform(this, new Function<DtoCity, City>() {
			@Override
			public City apply(DtoCity dto) {
				return dto.transform();
			}
		});
	}
	
}