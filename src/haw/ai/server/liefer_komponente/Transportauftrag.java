package haw.ai.server.liefer_komponente;

import haw.ai.server.common.HESEntity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Transportauftrag", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id"),
		@UniqueConstraint(columnNames = "lieferung_id") })
public class Transportauftrag extends HESEntity {

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	@Column(name = "ausgangsDatum")
	private Date ausgangsDatum;
	@Column(name = "lieferungErfolgt")
	private boolean lieferungErfolgt = false;
	@Column(name = "lieferDatum")
	private Date lieferDatum;
	@Column(name = "transportdienstleister")
	private String transportdienstleister;
	@OneToOne
	private Lieferung lieferung;

	protected Transportauftrag() {
	}

	protected Transportauftrag(Lieferung lieferung, Date ausgangsDatum,
			boolean lieferungErfolgt, Date lieferDatum,
			String transportDienstleister) {
		this.setLieferung(lieferung);
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

	public Lieferung getLieferung() {
		return lieferung;
	}

	public void setLieferung(Lieferung lieferung) {
		if (lieferung != null) {
			if (this.lieferung == null
					|| (this.lieferung != null && (this.lieferung.getId() != lieferung
							.getId()))) {
				this.lieferung = lieferung;
				lieferung.setTransportauftrag(this);
			}
		}
	}

	public void removeLieferung() {
		if (this.lieferung != null) {
			this.lieferung.removeTransportauftrag();
			this.lieferung = null;
		}
	}

	public TransportauftragJSON toTransportauftragJSON() {
		return new TransportauftragJSON(this.id, this.ausgangsDatum,
				this.lieferungErfolgt, this.lieferDatum);
	}
}
