package haw.ai.server.liefer_komponente;

import java.util.Date;

public class TransportauftragJSON {
	
	private int id;
	private Date ausgangsDatum;
	private boolean lieferungErfolgt = false;
	private Date lieferDatum;

	public TransportauftragJSON() {}

	public TransportauftragJSON(int id, Date ausgangsDatum,
			boolean lieferungErfolgt, Date lieferDatum) {
		this.id = id;
		this.ausgangsDatum = ausgangsDatum;
		this.lieferungErfolgt = lieferungErfolgt;
		this.lieferDatum = lieferDatum;
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
}
