package haw.ai.server.bestell_komponente;

import java.util.Date;
import java.util.Map.Entry;

import haw.ai.server.HESServer;
import haw.ai.server.HESServerImpl;
import haw.ai.server.common.DateUtil;
import haw.ai.server.common.KomponentenBusinessLogik;
import haw.ai.server.lager_komponente.LagerFassadeImpl;
import haw.ai.server.lager_komponente.Produkt;
import haw.ai.server.liefer_komponente.LieferFassadeImpl;
import haw.ai.server.liefer_komponente.Lieferung;
import haw.ai.server.rechnungs_komponente.Rechnung;
import haw.ai.server.rechnungs_komponente.RechnungsFassadeImpl;

public class BestellBusinessLogik implements KomponentenBusinessLogik {
	
	private HESServerImpl hesServer;

	public BestellBusinessLogik(HESServerImpl hesServer) {
		this.hesServer = hesServer;
	}

	public void bearbeiteAuftrag(Auftrag auftrag) {
		boolean bestandAusreichend = hesServer.getInstanceLocalLagerFassade().pruefeLagerbestand(auftrag
				.getAngebot().getProdukte());

		if (bestandAusreichend) {
			for (Entry<Produkt, Integer> entry : auftrag.getAngebot()
					.getProdukte().entrySet()) {
				hesServer.getInstanceLocalLagerFassade().erstelleWarenausgangsmeldung(entry.getKey(),
						DateUtil.now(), entry.getValue());
			}

			Lieferung lieferung = hesServer.getInstanceLocalLieferFassade().erstelleLieferung(auftrag);
			auftrag.setLieferung(lieferung);

			String transportDienstleister = "DHL";
			hesServer.getInstanceLocalLieferFassade().erstelleTransportauftrag(lieferung, DateUtil.now(),
					false, DateUtil.daysFromNow(1), transportDienstleister);

			Rechnung rechnung = hesServer.getInstanceLocalRechnungsFassade().erstelleRechnung(
					DateUtil.now(), auftrag);
			auftrag.setRechnung(rechnung);

			BestellRepository.save(auftrag);
			hesServer.getInstanceLocalRechnungsFassade().save(rechnung);
			hesServer.getInstanceLocalLieferFassade().save(lieferung);
		}
	}

	public void auftragAbschliessen(Auftrag auftrag) {
		hesServer.getInstanceLocalRechnungsFassade().rechnungBezahltWennZahlungAusreichend(auftrag
				.getRechnung());
		if (auftrag.getRechnung().isIstBezahlt()) {
			auftrag.setAuftragAbgeschlossen();
			BestellRepository.save(auftrag);
		}
		hesServer.getInstanceLocalRechnungsFassade().save(auftrag.getRechnung());
	}

}
