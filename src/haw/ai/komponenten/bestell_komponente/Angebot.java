package haw.ai.komponenten.bestell_komponente;

import java.util.Date;

public class Angebot {

	private int id;
	private Date gueltigAb;
	private Date gueltigBis;
	private int gesamtPreis;
	private int menge;

	private Angebot(Date gueltigAb, Date gueltigBis, int gesamtPreis, int menge) {
		this.gueltigAb = gueltigAb;
		this.gueltigBis = gueltigBis;
		this.gesamtPreis = gesamtPreis;
		this.menge = menge;
	}

	public static Angebot erstelleAngebot(int kundenID, Date gueltigAb, Date gueltigBis, int gesamtPreis, int menge) {
		Angebot angebot = new Angebot(gueltigAb, gueltigBis, gesamtPreis, menge);
		return BestellRepository.speichereAngebot(kundenID, angebot);
	}
	
}
