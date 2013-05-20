package haw.ai.server.bestell_komponente;

import java.util.Date;
import java.util.Map.Entry;

import haw.ai.server.common.DateUtil;
import haw.ai.server.common.KomponentenBusinessLogik;
import haw.ai.server.lager_komponente.LagerFassade;
import haw.ai.server.lager_komponente.Produkt;
import haw.ai.server.liefer_komponente.LieferFassade;
import haw.ai.server.liefer_komponente.Lieferung;
import haw.ai.server.rechnungs_komponente.Rechnung;
import haw.ai.server.rechnungs_komponente.RechnungsFassade;

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
		if (auftrag.getRechnung().isIstBezahlt()) {
			auftrag.setAuftragAbgeschlossen();
			BestellRepository.save(auftrag);
		}
		RechnungsFassade.save(auftrag.getRechnung());
	}

}
