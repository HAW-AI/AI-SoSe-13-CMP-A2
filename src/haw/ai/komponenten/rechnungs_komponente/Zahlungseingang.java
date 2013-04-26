package haw.ai.komponenten.rechnungs_komponente;

import java.util.Date;

public class Zahlungseingang {

	private int id;
	private Date eingangsDatum;
	private int betrag;
	
	private Zahlungseingang(int id, Date eingangsDatum, int betrag) {
		this.id = id;
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
	
}
