package haw.ai.komponenten.bestell_komponente;

import haw.ai.komponenten.kunden_komponente.Kunde;
import haw.ai.komponenten.lager_komponente.Produkt;

import java.util.Date;
import java.util.Set;

public class Angebot {

	private int id;
	private Date gueltigAb;
	private Date gueltigBis;
	private int menge;
	private int gesamtPreis;
	private Kunde kunde;
	private Auftrag auftrag;
	private Set<Produkt> produkte;

	private Angebot(int id, Date gueltigAb, Date gueltigBis, int gesamtPreis,
			int menge) {
		this.setId(id);
		this.setGueltigAb(gueltigAb);
		this.setGueltigBis(gueltigBis);
		this.setGesamtPreis(gesamtPreis);
		this.setMenge(menge);
	}

	public static Angebot erstelleAngebot(int kundenID, Date gueltigAb,
			Date gueltigBis, int gesamtPreis, int menge) {
		// Angebot angebot = new Angebot(id, gueltigAb, gueltigBis, gesamtPreis,
		// menge);
		// return BestellRepository.speichereAngebot(kundenID, angebot);
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getGueltigAb() {
		return gueltigAb;
	}

	public void setGueltigAb(Date gueltigAb) {
		this.gueltigAb = gueltigAb;
	}

	public Date getGueltigBis() {
		return gueltigBis;
	}

	public void setGueltigBis(Date gueltigBis) {
		this.gueltigBis = gueltigBis;
	}

	public int getMenge() {
		return menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
	}

	public int getGesamtPreis() {
		return gesamtPreis;
	}

	public void setGesamtPreis(int gesamtPreis) {
		this.gesamtPreis = gesamtPreis;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public Auftrag getAuftrag() {
		return auftrag;
	}

	public void setAuftrag(Auftrag auftrag) {
		this.auftrag = auftrag;
	}

	public Set<Produkt> getProdukte() {
		return produkte;
	}

	public void setProdukte(Set<Produkt> produkte) {
		this.produkte = produkte;
	}

}
