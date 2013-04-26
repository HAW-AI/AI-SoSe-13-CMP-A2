package haw.ai.komponenten.rechnungs_komponente;

import java.util.Date;

public class Rechnung {

	private Date rechnungsDatum;
	private boolean istBezahlt = false;
	
	private Rechnung(Date rechnungsDatum, boolean istBezahlt) {
		this.rechnungsDatum = rechnungsDatum;
		this.istBezahlt = istBezahlt;
	}
	
}
