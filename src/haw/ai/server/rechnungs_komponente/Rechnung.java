package haw.ai.server.rechnungs_komponente;

import java.util.Date;
import java.util.Set;
import java.util.HashSet;

import haw.ai.server.bestell_komponente.*;
import haw.ai.server.common.HESEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Rechnung extends HESEntity {

	private static final long serialVersionUID = -5100833633512489076L;
	@Id
	@GeneratedValue
	private int id;
	@Column(name = "rechnungsDatum")
	private Date rechnungsDatum;
	@Column(name = "istBezahlt")
	private boolean istBezahlt = false;
	@OneToOne()
	private Auftrag auftrag;
	@OneToMany
	private Set<Zahlungseingang> zahlungseingaenge;

	protected Rechnung() {
	}

	protected Rechnung(Date rechnungsDatum, boolean istBezahlt, Auftrag auftrag) {
		this.rechnungsDatum = rechnungsDatum;
		this.istBezahlt = istBezahlt;
		this.setAuftrag(auftrag);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getRechnungsDatum() {
		return rechnungsDatum;
	}

	public void setRechnungsDatum(Date rechnungsDatum) {
		this.rechnungsDatum = rechnungsDatum;
	}

	public boolean isIstBezahlt() {
		return istBezahlt;
	}

	public void setIstBezahlt(boolean istBezahlt) {
		this.istBezahlt = istBezahlt;
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
				auftrag.setRechnung(this);
			}
		}
	}

	public void removeAuftrag() {
		if (this.auftrag != null) {
			this.auftrag.removeRechnung();
			this.auftrag = null;
		}
	}

	public Set<Zahlungseingang> getZahlungseingaenge() {
		return zahlungseingaenge;
	}

	public void setZahlungseingaenge(Set<Zahlungseingang> zahlungseingaenge) {
		if (zahlungseingaenge != null) {
			this.zahlungseingaenge = zahlungseingaenge;
			for (Zahlungseingang ze : zahlungseingaenge) {
				ze.setRechnung(this);
			}
		}
	}

	public Integer summeAllerZahlungseingaenge() {
		Integer result = 0;
		if (getZahlungseingaenge() != null) {
			for (Zahlungseingang zahlung : getZahlungseingaenge()) {
				result += zahlung.getBetrag();
			}
		}
		return result;
	}

	public void addZahlungseingang(Zahlungseingang zahlungseingang) {
		if (zahlungseingang != null) {
			if (this.zahlungseingaenge == null) {
				this.zahlungseingaenge = new HashSet<Zahlungseingang>();
			}
			this.zahlungseingaenge.add(zahlungseingang);
			zahlungseingang.setRechnung(this);
		}
	}

	public void removeZahlungseingang(Zahlungseingang zahlungseingang) {
		if (zahlungseingang != null) {
			if (this.zahlungseingaenge != null) {
				if (this.zahlungseingaenge.contains(zahlungseingang)) {
					zahlungseingaenge.remove(zahlungseingang);
					zahlungseingang.removeRechnung();
				}
			}
		}
	}

	public void removeAllZahlungseingaenge() {
		if (this.zahlungseingaenge != null) {
			for (Zahlungseingang ze : zahlungseingaenge) {
				ze.removeRechnung();
			}
			zahlungseingaenge.clear();
		}
	}

}
