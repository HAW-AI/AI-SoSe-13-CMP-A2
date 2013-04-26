package haw.ai.komponenten.rechnungs_komponente;

import java.util.Date;

public class Zahlungseingang {

	private Date eingangsDatum;
	private int betrag;
	
	private Zahlungseingang(Date eingangsDatum, int betrag) {
		this.eingangsDatum = eingangsDatum;
		this.betrag = betrag;
	}
	
}
