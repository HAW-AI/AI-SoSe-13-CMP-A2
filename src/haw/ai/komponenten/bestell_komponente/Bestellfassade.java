package haw.ai.komponenten.bestell_komponente;

import java.util.Date;

public class Bestellfassade {

	public Angebot erstelleAngebot(int kundenID, Date gueltigAb, Date gueltigBis, int gesamtPreis, int menge) {
		return Angebot.erstelleAngebot(kundenID, gueltigAb, gueltigBis, gesamtPreis, menge);
	}
	
}
