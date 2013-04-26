package haw.ai.komponenten.kunden_komponente;

import java.util.Set;
import java.util.HashSet;
import haw.ai.komponenten.bestell_komponente.*;

public class Kunde {

	private int id;
	private String name;
	private String adresse;
	private Set<Angebot> angebote;

	protected Kunde(int id, String name, String adresse) {
		this.id = id;
		this.name = name;
		this.adresse = adresse;
	}

	protected Kunde(String name, String adresse) {
		this.name = name;
		this.adresse = adresse;
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Set<Angebot> getAngebote() {
		return angebote;
	}

	public void setAngebote(Set<Angebot> angebote) {
		this.angebote = angebote;
	}

}
