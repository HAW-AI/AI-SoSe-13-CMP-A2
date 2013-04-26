package haw.ai.komponenten.liefer_komponente;

import java.util.Set;
import java.util.HashSet;
import haw.ai.komponenten.bestell_komponente.*;

public class Lieferung {

	private int id;
	private Transportauftrag transportauftrag;
	private Auftrag auftrag;

	private Lieferung(int id) {
		this.id = id;
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
