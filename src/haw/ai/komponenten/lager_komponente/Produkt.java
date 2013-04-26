package haw.ai.komponenten.lager_komponente;

import java.util.Set;
import java.util.HashSet;
import haw.ai.komponenten.bestell_komponente.*;

public class Produkt {
	
	private int id;
	private String name;
	private int lagerbestand;
	private Set<Angebot> angebote;
	private Set<Warenausgangsmeldung> warenausgangsmeldungen;

	private Produkt(int id, String name, int lagerbestand) {
		this.id = id;
		this.name = name;
		this.lagerbestand = lagerbestand;
	}

	public int getLagerbestand() {
		return lagerbestand;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Angebot> getAngebote() {
		return angebote;
	}

	public void setAngebote(Set<Angebot> angebote) {
		this.angebote = angebote;
	}

	public Set<Warenausgangsmeldung> getWarenausgangsmeldungen() {
		return warenausgangsmeldungen;
	}

	public void setWarenausgangsmeldungen(
			Set<Warenausgangsmeldung> warenausgangsmeldungen) {
		this.warenausgangsmeldungen = warenausgangsmeldungen;
	}

	public void setLagerbestand(int lagerbestand) {
		this.lagerbestand = lagerbestand;
	}
}
