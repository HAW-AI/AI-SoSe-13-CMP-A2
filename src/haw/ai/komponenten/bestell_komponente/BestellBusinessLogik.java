package haw.ai.komponenten.bestell_komponente;

import java.util.Date;
import java.util.Map.Entry;

import haw.ai.komponenten.common.DateUtil;
import haw.ai.komponenten.common.KomponentenBusinessLogik;
import haw.ai.komponenten.lager_komponente.LagerFassade;
import haw.ai.komponenten.lager_komponente.Produkt;
import haw.ai.komponenten.liefer_komponente.LieferFassade;
import haw.ai.komponenten.liefer_komponente.Lieferung;
import haw.ai.komponenten.rechnungs_komponente.Rechnung;
import haw.ai.komponenten.rechnungs_komponente.RechnungsFassade;

public class BestellBusinessLogik implements KomponentenBusinessLogik {

	public static void bearbeiteAuftrag(Auftrag auftrag) {
		boolean bestandAusreichend = LagerFassade.pruefeLagerbestand(auftrag
				.getAngebot().getProdukte());

		if (bestandAusreichend) {
			for (Entry<Produkt, Integer> entry : auftrag.getAngebot()
					.getProdukte().entrySet()) {
				LagerFassade.erstelleWarenausgangsmeldung(entry.getKey(),
						DateUtil.now(), entry.getValue());
			}

			Lieferung lieferung = LieferFassade.erstelleLieferung(auftrag);
			auftrag.setLieferung(lieferung);

			String transportDienstleister = "DHL";
			LieferFassade.erstelleTransportauftrag(lieferung, DateUtil.now(),
					false, DateUtil.daysFromNow(1), transportDienstleister);

			Rechnung rechnung = RechnungsFassade.erstelleRechnung(
					DateUtil.now(), auftrag);
			auftrag.setRechnung(rechnung);

			BestellRepository.save(auftrag);
			RechnungsFassade.save(rechnung);
			LieferFassade.save(lieferung);
		}
	}

	public static void auftragAbschliessen(Auftrag auftrag) {
		RechnungsFassade.rechnungBezahltWennZahlungAusreichend(auftrag
				.getRechnung());
		auftrag.setAuftragAbgeschlossen();
		RechnungsFassade.save(auftrag.getRechnung());
		BestellRepository.save(auftrag);
	}

}
