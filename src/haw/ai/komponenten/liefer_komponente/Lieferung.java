package haw.ai.komponenten.liefer_komponente;

import haw.ai.komponenten.bestell_komponente.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Lieferung {

	@Id
	@GeneratedValue
	private int id;
	@OneToOne()
	private Transportauftrag transportauftrag;
	@OneToOne()
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
		this.transportauftrag = transportauftrag;
	}

	public Auftrag getAuftrag() {
		return auftrag;
	}

	public void setAuftrag(Auftrag auftrag) {
		this.auftrag = auftrag;
	}
}
