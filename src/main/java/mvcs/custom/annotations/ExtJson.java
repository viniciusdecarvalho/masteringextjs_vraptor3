package mvcs.custom.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({METHOD})
@Documented
@Retention(RUNTIME)
public @interface ExtJson {

	String root() default "data";
	
}
