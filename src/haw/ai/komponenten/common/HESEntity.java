package haw.ai.komponenten.common;

public abstract class HESEntity {
	public abstract int getId();

	public boolean equals(HESEntity otherEntity) {
		if (!(otherEntity instanceof HESEntity)) {
			return false;
		}
		return this.getId() == otherEntity.getId();
	}
}
