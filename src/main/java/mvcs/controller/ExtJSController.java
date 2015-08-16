package mvcs.controller;

import java.util.Collection;
import java.util.List;

import mvcs.custom.extjs.ExtJSJson;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Message;
import br.com.caelum.vraptor.validator.ValidationException;
import br.com.caelum.vraptor.validator.Validations;

public abstract class ExtJSController {

	private static final String ROOT_NAME = "data";
	public static final String FIELDS_NOT_VALID = "Existem campos obrigatorios nao informados ou preenchidos incorretamente";
	public static final String PARAMS_NOT_VALID = "Parametros nao informados ou inválidos";
	
	protected Result result;
	protected Validator validator;
	
	public ExtJSController() {
	}
	
	public ExtJSController(Result result, Validator validator) {
		this.result = result;
		this.validator = validator;
	}
	
	protected void validate(Object model) {
		validator.validate(model);
		sendErrors(FIELDS_NOT_VALID);
	}

	protected void sendErrors(String message) {
		if (validator.hasErrors()) {
			List<Message> errors = validator.getErrors();
			validator.onErrorUse(ExtJSJson.class).from(errors).alias("errors")
					.message(message).success(false).serialize();
			throw new ValidationException(errors);
		}
	}
	
	protected void validate(final boolean assertion, final String category, final String reason) {
		validate(new Validations(){{
			that(assertion, category, reason);
		}});
	}
	
	protected void validate(Validations validations) {
		validator.checking(validations);
		sendErrors(PARAMS_NOT_VALID);
	}

	protected ExtJSJson json(String root, Object object, boolean success, Long total,
			String message, String... excludes) {
		return extjs().from(object)
				.alias(root).message(message)
				.total(total).exclude(excludes)
				.success(success).serialize();
	}

	private ExtJSJson extjs() {
		return result.use(ExtJSJson.class);
	}

	protected ExtJSJson json(Object object, String... exceto) {
		return json(ROOT_NAME, object, true, null, null, exceto);
	}

	protected ExtJSJson json(Collection<?> collection, Long total, String... exceto) {
		return json(ROOT_NAME, collection, true, total, null, exceto);
	}

	protected ExtJSJson json(String message, boolean success) {
		return extjs().message(message).success(success).serialize();
	}
	
}
