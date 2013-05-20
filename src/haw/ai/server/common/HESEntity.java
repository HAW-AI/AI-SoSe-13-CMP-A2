package haw.ai.server.common;

public abstract class HESEntity {
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
