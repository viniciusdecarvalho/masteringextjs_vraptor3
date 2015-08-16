package mvcs.custom.extjs;

import br.com.caelum.vraptor.View;

public interface ExtJSJson extends View {

	ExtJSJson from(Object object);

	ExtJSJson alias(String alias);
	
	ExtJSJson include(String... names);
	
    ExtJSJson exclude(String... names);
    
    ExtJSJson excludeAll();

	ExtJSJson success();

	ExtJSJson success(boolean success);

	ExtJSJson serialize();

	ExtJSJson total(Long total);

	ExtJSJson message(String message);
	
}
