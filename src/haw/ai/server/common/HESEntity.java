package haw.ai.server.common;

import java.io.Serializable;

public abstract class HESEntity implements Serializable {
	public abstract int getId();

	@Override
	public boolean equals(Object otherEntity) {
		boolean result = false;
		if (otherEntity != null && otherEntity instanceof HESEntity) {
			result = this.getId() == ((HESEntity) otherEntity).getId();
		}
		return result;
	}

//	@Override
//	public int hashCode() {
//		return ((Integer) getId()).hashCode();
//	}
}
