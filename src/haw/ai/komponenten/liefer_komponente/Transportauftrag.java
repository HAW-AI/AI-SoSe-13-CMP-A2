package haw.ai.komponenten.liefer_komponente;

import java.util.Date;

public class Transportauftrag {

	private Date ausgangsDatum;
	private boolean lieferungErfolgt = false;
	private Date lieferDatum;
	private String transportdienstleister;

	private Transportauftrag(Date ausgangsDatum, boolean lieferungErfolgt,
			Date lieferDatum, String transportDienstleister) {
		this.ausgangsDatum = ausgangsDatum;
		this.lieferungErfolgt = lieferungErfolgt;
		this.lieferDatum = lieferDatum;
		this.transportdienstleister = transportDienstleister;
	}
	
	public void setLieferungErfolgt() {
		lieferungErfolgt = true;
	}

}
