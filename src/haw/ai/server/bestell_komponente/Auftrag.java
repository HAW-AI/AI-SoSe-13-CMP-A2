package haw.ai.server.bestell_komponente;

import java.util.Date;

import haw.ai.server.common.HESEntity;
import haw.ai.server.liefer_komponente.*;
import haw.ai.server.rechnungs_komponente.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Auftrag extends HESEntity {

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

	protected Auftrag(Angebot angebot, boolean istAbgeschlossen,
			Date beauftragtAm) {
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
		if (angebot != null) {
			if (this.angebot == null
					|| (this.angebot != null && (this.angebot.getId() != angebot
							.getId()))) {
				this.angebot = angebot;
				angebot.setAuftrag(this);
			}
		}
	}
	
	public void removeAngebot() {
		if (this.angebot != null) {
			this.angebot.removeAuftrag();
			this.angebot = null;
		}
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
				lieferung.setAuftrag(this);
			}
		}
	}
	
	public void removeLieferung() {
		if (this.lieferung != null) {
			this.lieferung.removeAuftrag();
			this.lieferung = null;
		}
	}

	public Rechnung getRechnung() {
		return rechnung;
	}

	public void setRechnung(Rechnung rechnung) {
		if (rechnung != null) {
			if (this.rechnung == null
					|| (this.rechnung != null && (this.rechnung.getId() != rechnung
							.getId()))) {
				this.rechnung = rechnung;
				rechnung.setAuftrag(this);
			}
		}
	}
	
	public void removeRechnung() {
		if (this.rechnung != null) {
			this.rechnung.removeAuftrag();
			this.rechnung = null;
		}
	}

}
