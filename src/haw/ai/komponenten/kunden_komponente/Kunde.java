package haw.ai.komponenten.kunden_komponente;

import java.util.HashSet;
import java.util.Set;
import haw.ai.komponenten.bestell_komponente.*;
import haw.ai.komponenten.common.HESEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Kunde extends HESEntity {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "adresse")
	private String adresse;
	@OneToMany(mappedBy = "kunde")
	private Set<Angebot> angebote;

	protected Kunde() {
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
		if (angebote != null) {
			this.angebote = angebote;
			for (Angebot angebot : angebote) {
				angebot.setKunde(this);
			}
		}
	}

	public void addAngebot(Angebot angebot) {
		if (angebot != null) {
			if (this.angebote == null) {
				this.angebote = new HashSet<Angebot>();
			}
			this.angebote.add(angebot);
			angebot.setKunde(this);
		}
	}

	public void removeAngebot(Angebot angebot) {
		if (angebot != null) {
			if (this.angebote != null) {
				if (this.angebote.contains(angebot)) {
					this.angebote.remove(angebot);
					angebot.removeKunde();
				}
			}
		}
	}

	public void removeAllAngebote() {
		if (this.angebote != null) {
			for (Angebot a : angebote) {
				a.removeKunde();
			}
			this.angebote.clear();
		}
	}

}
