package haw.ai.komponenten.bestell_komponente;

import haw.ai.komponenten.kunden_komponente.Kunde;
import haw.ai.komponenten.lager_komponente.Produkt;

import java.util.Date;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;

@Entity
public class Angebot {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "gueltigAb")
	private Date gueltigAb;
	@Column(name = "gueltigBis")
	private Date gueltigBis;
	@Column(name = "menge")
	private int menge;
	@Column(name = "gesamtPreis")
	private int gesamtPreis;
	@ManyToOne
	private Kunde kunde;
	@OneToOne(mappedBy = "angebot")
	private Auftrag auftrag;

	@ElementCollection
    @MapKeyColumn(name="id")
    @Column(name="value")
    @CollectionTable(name="angebot_produkt", joinColumns=@JoinColumn(name="id"))
	private Map<Produkt, Integer> produkte;

	protected Angebot() {
	}

	protected Angebot(Kunde kunde, Map<Produkt, Integer> produkte, Date gueltigAb, Date gueltigBis,
			int gesamtPreis) {
		this.setKunde(kunde);
		this.setProdukte(produkte);
		this.setGueltigAb(gueltigAb);
		this.setGueltigBis(gueltigBis);
		this.setGesamtPreis(gesamtPreis);
		this.setMenge(menge);
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

	public int getMenge() {
		return menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
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
		if (this.kunde.getId() != kunde.getId()) {
			this.kunde = kunde;
			this.kunde.addAngebot(this);
		}
	}

	public Auftrag getAuftrag() {
		return auftrag;
	}

	public void setAuftrag(Auftrag auftrag) {
		this.auftrag = auftrag;
	}

	public Map<Produkt, Integer> getProdukte() {
		return produkte;
	}

	public void setProdukte(Map<Produkt, Integer> produkte) {
		this.produkte = produkte;
	}
	
//	public void addProdukt(Produkt produkt, Integer menge) {
//		if (this.produkte == null) {
//			this.produkte = new HashMap<Produkt, Integer>();
//		}
//		if(this.produkte.put(produkt, menge)) {
//			produkt.addAngebot(this, menge);
//		}
//	}
//
//	public void removeProdukt(Produkt produkt) {
//		if (this.produkte != null) {
//			if (this.produkte.containsKey(produkt)) {
//				this.produkte.remove(produkt);
//				produkt.removeAngebot(this);
//			}
//		}
//	}
//

}
