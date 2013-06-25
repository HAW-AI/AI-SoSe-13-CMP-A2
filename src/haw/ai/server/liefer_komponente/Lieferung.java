package haw.ai.server.liefer_komponente;

import haw.ai.server.bestell_komponente.*;
import haw.ai.server.common.HESEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
public class Lieferung extends HESEntity {

	@Id
	@GeneratedValue
	private int id;
	@OneToOne(mappedBy = "lieferung")
	private Transportauftrag transportauftrag;
	@OneToOne
	private Auftrag auftrag;

	protected Lieferung() {
	}

	protected Lieferung(Auftrag auftrag) {
		this.setAuftrag(auftrag);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Transportauftrag getTransportauftrag() {
		return transportauftrag;
	}

	public void setTransportauftrag(Transportauftrag transportauftrag) {
		if (transportauftrag != null) {
			if (this.transportauftrag == null
					|| (this.transportauftrag != null && (this.transportauftrag
							.getId() != transportauftrag.getId()))) {
				this.transportauftrag = transportauftrag;
				transportauftrag.setLieferung(this);
			}
		}
	}
	
	public void removeTransportauftrag() {
		if (this.transportauftrag != null) {
			this.transportauftrag.removeLieferung();
			this.transportauftrag = null;
		}
	}

	public Auftrag getAuftrag() {
		return auftrag;
	}

	public void setAuftrag(Auftrag auftrag) {
		if (auftrag != null) {
			if (this.auftrag == null
					|| (this.auftrag != null && (this.auftrag.getId() != auftrag
							.getId()))) {
				this.auftrag = auftrag;
				auftrag.setLieferung(this);
			}
		}
	}

	public void removeAuftrag() {
		if (this.auftrag != null) {
			this.auftrag.removeLieferung();
			this.auftrag = null;
		}
	}
}
