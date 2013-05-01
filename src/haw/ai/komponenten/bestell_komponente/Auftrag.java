package haw.ai.komponenten.bestell_komponente;

import java.util.Date;
import haw.ai.komponenten.rechnungs_komponente.*;
import haw.ai.komponenten.liefer_komponente.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Auftrag {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "istAbgeschlossen")
	private boolean istAbgeschlossen = false;
	@Column(name = "beauftragtAm")
	private Date beauftragtAm;
	@OneToOne()
	private Angebot angebot;
	@OneToOne(mappedBy = "auftrag")
	private Lieferung lieferung;
	@OneToOne(mappedBy = "auftrag")
	private Rechnung rechnung;

	protected Auftrag() {
	}

	protected Auftrag(Angebot angebot, boolean istAbgeschlossen, Date beauftragtAm) {
		this.setAngebot(angebot);
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
