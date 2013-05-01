package haw.ai.komponenten.liefer_komponente;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Transportauftrag {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "ausgangsDatum")
	private Date ausgangsDatum;
	@Column(name = "lieferungErfolgt")
	private boolean lieferungErfolgt = false;
	@Column(name = "lieferDatum")
	private Date lieferDatum;
	@Column(name = "transportdienstleister")
	private String transportdienstleister;
	@OneToOne(mappedBy = "lieferung")
	private Lieferung lieferung;

	protected Transportauftrag() {
	}

	protected Transportauftrag(Lieferung lieferung, Date ausgangsDatum,
			boolean lieferungErfolgt, Date lieferDatum,
			String transportDienstleister) {
		this.lieferung =lieferung;
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
