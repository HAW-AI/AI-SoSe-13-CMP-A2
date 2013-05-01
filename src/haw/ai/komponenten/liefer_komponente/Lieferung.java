package haw.ai.komponenten.liefer_komponente;

import haw.ai.komponenten.bestell_komponente.*;
import haw.ai.komponenten.common.HESEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
}
