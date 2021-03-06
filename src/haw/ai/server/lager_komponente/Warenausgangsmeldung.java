package haw.ai.server.lager_komponente;

import haw.ai.server.common.HESEntity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Warenausgangsmeldung extends HESEntity {

	private static final long serialVersionUID = -1140566318482194099L;
	@Id
	@GeneratedValue
	private int id;
	@Column(name = "datum")
	private Date datum;
	@Column(name = "menge")
	private int menge;
	@ManyToOne
	private Produkt produkt;

	protected Warenausgangsmeldung() {
	}

	protected Warenausgangsmeldung(Produkt produkt, Date datum, int menge) {
		this.setProdukt(produkt);
		this.datum = datum;
		this.menge = menge;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getMenge() {
		return menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
	}

	public Produkt getProdukt() {
		return produkt;
	}

	public void setProdukt(Produkt produkt) {
		if (produkt != null) {
			if (this.produkt == null
					|| (this.produkt != null && (this.produkt.getId() != produkt
							.getId()))) {
				this.produkt = produkt;
				produkt.addWarenausgangsmeldung(this);
			}
		}
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void removeProdukt() {
		if (produkt != null) {
			this.produkt.removeWarenausgangsmeldung(this);
			this.produkt = null;
		}
	}

}
