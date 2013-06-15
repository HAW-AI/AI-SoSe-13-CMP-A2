package haw.ai.server.bestell_komponente;

import haw.ai.server.HESServerImpl;
import haw.ai.server.common.DateUtil;
import haw.ai.server.common.KomponentenBusinessLogik;
import haw.ai.server.lager_komponente.Produkt;
import haw.ai.server.liefer_komponente.Lieferung;
import haw.ai.server.liefer_komponente.Transportauftrag;
import haw.ai.server.rechnungs_komponente.Rechnung;

import java.rmi.RemoteException;
import java.util.Map.Entry;

public class BestellBusinessLogik implements KomponentenBusinessLogik {

	private HESServerImpl hesServer;

	public BestellBusinessLogik(HESServerImpl hesServer) {
		this.hesServer = hesServer;
	}

	public void bearbeiteAuftrag(Auftrag auftrag) throws RemoteException {
		boolean bestandAusreichend = hesServer.getLagerFassade()
				.pruefeLagerbestand(auftrag.getAngebot().getProdukte());

		if (bestandAusreichend) {
			for (Entry<Produkt, Integer> entry : auftrag.getAngebot()
					.getProdukte().entrySet()) {
				hesServer.getLagerFassade().erstelleWarenausgangsmeldung(
						entry.getKey(), DateUtil.now(), entry.getValue());
			}

			Lieferung lieferung = hesServer.getLieferFassade()
					.erstelleLieferung(auftrag);
			auftrag.setLieferung(lieferung);

			String transportDienstleister = "DHL";
			Transportauftrag transportauftrag = hesServer.getLieferFassade()
					.erstelleTransportauftrag(lieferung, DateUtil.now(), false,
							DateUtil.daysFromNow(1), transportDienstleister);

			Rechnung rechnung = hesServer.getRechnungsFassade()
					.erstelleRechnung(DateUtil.now(), auftrag);
			auftrag.setRechnung(rechnung);

			BestellRepository.save(auftrag);
			hesServer.getRechnungsFassade().save(rechnung);
			hesServer.getLieferFassade().save(lieferung);
		}
	}

	public void auftragAbschliessen(Auftrag auftrag) throws RemoteException {
		hesServer.getRechnungsFassade().rechnungBezahltWennZahlungAusreichend(
				auftrag.getRechnung());
		if (auftrag.getRechnung().isIstBezahlt()) {
			auftrag.setAuftragAbgeschlossen();
			BestellRepository.save(auftrag);
		}
		hesServer.getRechnungsFassade().save(auftrag.getRechnung());
	}

}
