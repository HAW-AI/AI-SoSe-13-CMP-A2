package haw.ai.komponenten.bestell_komponente;

import haw.ai.komponenten.common.HESEntity;
import haw.ai.komponenten.kunden_komponente.Kunde;
import haw.ai.komponenten.lager_komponente.Produkt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Angebot extends HESEntity {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "gueltigAb")
	private Date gueltigAb;
	@Column(name = "gueltigBis")
	private Date gueltigBis;
	@Column(name = "gesamtPreis")
	private int gesamtPreis;
	@ManyToOne
	private Kunde kunde;
	@OneToOne(mappedBy = "angebot")
	private Auftrag auftrag;

	@ElementCollection
	@CollectionTable(name = "Angebot_Produkt", joinColumns = @JoinColumn(name = "angebot_id"))
	@MapKeyJoinColumn(name = "produkt_id")
	@Column(name = "menge")
	private Map<Produkt, Integer> produkte;

	protected Angebot() {
	}

	protected Angebot(Kunde kunde, Map<Produkt, Integer> produkte,
			Date gueltigAb, Date gueltigBis, int gesamtPreis) {
		this.setKunde(kunde);
		this.setProdukte(produkte);
		this.setGueltigAb(gueltigAb);
		this.setGueltigBis(gueltigBis);
		this.setGesamtPreis(gesamtPreis);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getGueltigAb() {
		return gueltigAb;
	}

	public void setGueltigAb(Date gueltigAb) {
		this.gueltigAb = gueltigAb;
	}

	public Date getGueltigBis() {
		return gueltigBis;
	}

	public void setGueltigBis(Date gueltigBis) {
		this.gueltigBis = gueltigBis;
	}

	public int getGesamtPreis() {
		return gesamtPreis;
	}

	public void setGesamtPreis(int gesamtPreis) {
		this.gesamtPreis = gesamtPreis;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		if (kunde != null) {
			if (this.kunde == null
					|| (this.kunde != null && (this.kunde.getId() != kunde
							.getId()))) {
				this.kunde = kunde;
				kunde.addAngebot(this);
			}
		}
	}
	
	public void removeKunde() {
		if (this.kunde != null) {
			this.kunde.removeAngebot(this);
			this.kunde = null;
		}
	}

	public Auftrag getAuftrag() {
		return auftrag;
	}

	public void setAuftrag(Auftrag auftrag) {
		if (auftrag != null) {
			if (this.auftrag == null
					|| (this.auftrag != null && (this.auftrag.getId() != auftrag
							.getId()))) {
				this.auftrag = auftrag;
				auftrag.setAngebot(this);
			}
		}
	}

	public void removeAuftrag() {
		if (this.auftrag != null) {
			this.auftrag.removeAngebot();
			this.auftrag = null;
		}
	}
	
	public Map<Produkt, Integer> getProdukte() {
		return produkte;
	}

	public void setProdukte(Map<Produkt, Integer> produkte) {
		if (produkte != null) {
			this.produkte = produkte;
			for (Produkt produkt : produkte.keySet()) {
				produkt.addAngebot(this);
			}
		}
	}

	public void addProdukt(Produkt produkt, Integer menge) {
		if (produkt != null) {
			if (this.produkte == null) {
				this.produkte = new HashMap<Produkt, Integer>();
			}
			if (!this.produkte.containsKey(produkt)) {
				this.produkte.put(produkt, menge);
				produkt.addAngebot(this);
			}
			if (this.produkte.containsKey(produkt)) {
				this.produkte.put(produkt, this.produkte.get(produkt) + menge);
			}
		}
	}

	public void removeProdukt(Produkt produkt) {
		if (produkt != null) {
			if (this.produkte == null) {
				this.produkte = new HashMap<Produkt, Integer>();
			}
			if (this.produkte.containsKey(produkt)) {
				this.produkte.remove(produkt);
				produkt.removeAngebot(this);
			}
		}
	}
	
	public void removeAllProdukte() {
		if (this.produkte != null) {
			for (Produkt p : produkte.keySet()) {
				p.removeAngebot(this);
			}
			this.produkte.clear();
		}
	}
	
	

}
