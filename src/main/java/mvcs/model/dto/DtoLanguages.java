package mvcs.model.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mvcs.model.entity.Language;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class DtoLanguages extends ArrayList<DtoLanguage> implements Dto<List<Language>> {

	private static final long serialVersionUID = 1L;
	
	public DtoLanguages() {
		super();
	}
	
	public DtoLanguages(Collection<? extends Language> actors) {
		this.addAll(Lists.transform(Lists.newArrayList(actors.iterator()), new Function<Language, DtoLanguage>() {
			@Override
			public DtoLanguage apply(Language actor) {
				return new DtoLanguage(actor);
			}
		}));
	}
	
	public List<Language> transform() {
		return Lists.transform(this, new Function<DtoLanguage, Language>() {
			@Override
			public Language apply(DtoLanguage dto) {
				return dto.transform();
			}
		});
	}
	
}