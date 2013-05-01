package haw.ai.komponenten.rechnungs_komponente;

import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import haw.ai.komponenten.bestell_komponente.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Rechnung {

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
	
	protected Rechnung() {}

	protected Rechnung(Date rechnungsDatum, boolean istBezahlt, Auftrag auftrag) {
		this.rechnungsDatum = rechnungsDatum;
		this.istBezahlt = istBezahlt;
		this.auftrag = auftrag;
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
		this.auftrag = auftrag;
	}

	public Set<Zahlungseingang> getZahlungseingaenge() {
		return zahlungseingaenge;
	}

	public void setZahlungseingaenge(Set<Zahlungseingang> zahlungseingaenge) {
		this.zahlungseingaenge = zahlungseingaenge;
	}
	
}
