package haw.ai.komponenten.bestell_komponente;

import java.util.Date;
import haw.ai.komponenten.rechnungs_komponente.*;
import haw.ai.komponenten.liefer_komponente.*;

public class Auftrag {

	private int id;
	private boolean istAbgeschlossen = false;
	private Date beauftragtAm;
	private Angebot angebot;
	private Lieferung lieferung;
	private Rechnung rechnung;

	private Auftrag(int id, boolean istAbgeschlossen, Date beauftragtAm) {
		this.setId(id);
		this.setIstAbgeschlossen(istAbgeschlossen);
		this.setBeauftragtAm(beauftragtAm);
	}

	public void setAuftragAbgeschlossen() {
		this.setIstAbgeschlossen(true);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isIstAbgeschlossen() {
		return istAbgeschlossen;
	}

	public void setIstAbgeschlossen(boolean istAbgeschlossen) {
		this.istAbgeschlossen = istAbgeschlossen;
	}

	public Date getBeauftragtAm() {
		return beauftragtAm;
	}

	public void setBeauftragtAm(Date beauftragtAm) {
		this.beauftragtAm = beauftragtAm;
	}

	public Angebot getAngebot() {
		return angebot;
	}

	public void setAngebot(Angebot angebot) {
		this.angebot = angebot;
	}

	public Lieferung getLieferung() {
		return lieferung;
	}

	public void setLieferung(Lieferung lieferung) {
		this.lieferung = lieferung;
	}

	public Rechnung getRechnung() {
		return rechnung;
	}

	public void setRechnung(Rechnung rechnung) {
		this.rechnung = rechnung;
	}

}
