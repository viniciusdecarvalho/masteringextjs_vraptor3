package mvcs.model;

import java.io.Serializable;

public interface Pk<I extends Serializable> extends Serializable {

	I getId();

	void setId(I id);

}

