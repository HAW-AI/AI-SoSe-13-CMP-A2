package haw.ai.komponenten.bestell_komponente;

import java.util.Date;

public class Auftrag {

	private boolean istAbgeschlossen = false;
	private Date beauftragtAm;
	
	private Auftrag(boolean istAbgeschlossen, Date beauftragtAm) {
		this.istAbgeschlossen = istAbgeschlossen;
		this.beauftragtAm = beauftragtAm;
	}
	
	public void setAuftragAbgeschlossen() {
		this.istAbgeschlossen = true;
	}
	
}
