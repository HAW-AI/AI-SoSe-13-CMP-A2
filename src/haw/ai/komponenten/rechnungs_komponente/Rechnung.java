package haw.ai.komponenten.rechnungs_komponente;

import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import haw.ai.komponenten.bestell_komponente.*;

public class Rechnung {

	private int id;
	private Date rechnungsDatum;
	private boolean istBezahlt = false;
	private Auftrag auftrag;
	private Set<Zahlungseingang> zahlungseingaenge;
	
	private Rechnung(int id, Date rechnungsDatum, boolean istBezahlt) {
		this.id = id;
		this.rechnungsDatum = rechnungsDatum;
		this.istBezahlt = istBezahlt;
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
