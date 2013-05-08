package haw.ai.komponenten.lager_komponente;

import java.util.Set;
import java.util.HashSet;
import haw.ai.komponenten.bestell_komponente.*;
import haw.ai.komponenten.common.HESEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Produkt extends HESEntity {

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
		if (angebote != null) {
			this.angebote = angebote;
		}
	}

	public void addAngebot(Angebot angebot) {
		if (angebot != null) {
			if (this.angebote == null) {
				this.angebote = new HashSet<Angebot>();
			}
			this.angebote.add(angebot);
		}
	}

	public Set<Warenausgangsmeldung> getWarenausgangsmeldungen() {
		return warenausgangsmeldungen;
	}

	public void setWarenausgangsmeldungen(
			Set<Warenausgangsmeldung> warenausgangsmeldungen) {
		if (warenausgangsmeldungen != null) {
			this.warenausgangsmeldungen = warenausgangsmeldungen;
			for (Warenausgangsmeldung wa : warenausgangsmeldungen) {
				wa.setProdukt(this);
			}
		}
	}

	public void addWarenausgangsmeldung(
			Warenausgangsmeldung warenausgangsmeldung) {
		if (warenausgangsmeldung != null) {
			if (this.warenausgangsmeldungen == null) {
				this.warenausgangsmeldungen = new HashSet<Warenausgangsmeldung>();
			}
			this.warenausgangsmeldungen.add(warenausgangsmeldung);
			warenausgangsmeldung.setProdukt(this);
		}
	}

	public void setLagerbestand(int lagerbestand) {
		this.lagerbestand = lagerbestand;
	}

	public void removeAngebot(Angebot angebot) {
		if (angebot != null) {
			if (this.angebote == null) {
				this.angebote = new HashSet<Angebot>();
			}
			if (angebote.contains(angebot)) {
				angebote.remove(angebot);
				angebot.removeProdukt(this);
			}

		}
	}

	public void removeWarenausgangsmeldung(
			Warenausgangsmeldung warenausgangsmeldung) {
		if (warenausgangsmeldung != null) {
			if (this.warenausgangsmeldungen == null) {
				this.warenausgangsmeldungen = new HashSet<Warenausgangsmeldung>();
			}
			if (warenausgangsmeldungen.contains(warenausgangsmeldung)) {
				warenausgangsmeldungen.remove(warenausgangsmeldung);
				warenausgangsmeldung.removeProdukt();
			}
		}
	}
	
	public void removeAllWarenausgangsmeldungen() {
		if (this.warenausgangsmeldungen != null) {
			for (Warenausgangsmeldung w : warenausgangsmeldungen) {
				w.removeProdukt();
			}
			this.warenausgangsmeldungen.clear();
		}
	}
	
}
