package haw.ai.komponenten.lager_komponente;

public class Produkt {
	private String name;
	private int lagerbestand;

	private Produkt(String name, int lagerbestand) {
		this.name = name;
		this.lagerbestand = lagerbestand;
	}

	public int getLagerbestand() {
		return lagerbestand;
	}
}
