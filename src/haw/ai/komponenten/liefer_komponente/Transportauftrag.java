package haw.ai.komponenten.liefer_komponente;

import java.util.Date;

public class Transportauftrag {

	private int id;
	private Date ausgangsDatum;
	private boolean lieferungErfolgt = false;
	private Date lieferDatum;
	private String transportdienstleister;

	private Transportauftrag(int id, Date ausgangsDatum, boolean lieferungErfolgt,
			Date lieferDatum, String transportDienstleister) {
		this.id = id;
		this.ausgangsDatum = ausgangsDatum;
		this.lieferungErfolgt = lieferungErfolgt;
		this.lieferDatum = lieferDatum;
		this.transportdienstleister = transportDienstleister;
	}
	
	public void setLieferungErfolgt() {
		lieferungErfolgt = true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAusgangsDatum() {
		return ausgangsDatum;
	}

	public void setAusgangsDatum(Date ausgangsDatum) {
		this.ausgangsDatum = ausgangsDatum;
	}

	public boolean isLieferungErfolgt() {
		return lieferungErfolgt;
	}

	public void setLieferungErfolgt(boolean lieferungErfolgt) {
		this.lieferungErfolgt = lieferungErfolgt;
	}

	public Date getLieferDatum() {
		return lieferDatum;
	}

	public void setLieferDatum(Date lieferDatum) {
		this.lieferDatum = lieferDatum;
	}

	public String getTransportdienstleister() {
		return transportdienstleister;
	}

	public void setTransportdienstleister(String transportdienstleister) {
		this.transportdienstleister = transportdienstleister;
	}

}
