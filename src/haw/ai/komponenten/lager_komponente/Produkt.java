package haw.ai.komponenten.lager_komponente;

import java.util.Set;
import java.util.HashSet;
import haw.ai.komponenten.bestell_komponente.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Produkt {
	
	@Id
	@GeneratedValue
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "lagerbestand")
	private int lagerbestand;
	@ManyToMany()
	private Set<Angebot> angebote;
	@OneToMany
	private Set<Warenausgangsmeldung> warenausgangsmeldungen;

	protected Produkt() {
	}

	protected Produkt(String name, int lagerbestand) {
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
