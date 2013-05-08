package haw.ai.komponenten.rechnungs_komponente;

import haw.ai.komponenten.common.HESEntity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Zahlungseingang extends HESEntity {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "eingangsDatum")
	private Date eingangsDatum;
	@Column(name = "betrag")
	private int betrag;
	@ManyToOne
	private Rechnung rechnung;

	protected Zahlungseingang() {
	}

	protected Zahlungseingang(Rechnung rechnung, Date eingangsDatum, int betrag) {
		this.setRechnung(rechnung);
		this.eingangsDatum = eingangsDatum;
		this.betrag = betrag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEingangsDatum() {
		return eingangsDatum;
	}

	public void setEingangsDatum(Date eingangsDatum) {
		this.eingangsDatum = eingangsDatum;
	}

	public int getBetrag() {
		return betrag;
	}

	public void setBetrag(int betrag) {
		this.betrag = betrag;
	}

	public Rechnung getRechnung() {
		return rechnung;
	}

	public void setRechnung(Rechnung rechnung) {
		if (rechnung != null) {
			if (this.rechnung == null
					|| (this.rechnung != null && (this.rechnung.getId() != rechnung
							.getId()))) {
				this.rechnung = rechnung;
				rechnung.addZahlungseingang(this);
			}
		}
	}

	public void removeRechnung() {
		if (this.rechnung != null) {
			this.rechnung.removeZahlungseingang(this);
			this.rechnung = null;
		}
	}

}
