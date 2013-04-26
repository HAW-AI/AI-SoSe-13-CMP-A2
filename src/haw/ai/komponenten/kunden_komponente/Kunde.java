package haw.ai.komponenten.kunden_komponente;

public class Kunde {

	private String name;
	private String adresse;

	private Kunde(String name, String adresse) {
		this.name = name;
		this.adresse = adresse;
	}

	public static Kunde erstelleKunde(String name, String adresse) {
		Kunde kunde = new Kunde(name, adresse);
		// TODO persist Kunde
		return kunde;
	}

}
