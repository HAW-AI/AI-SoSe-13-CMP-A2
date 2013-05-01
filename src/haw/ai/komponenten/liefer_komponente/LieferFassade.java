package haw.ai.komponenten.liefer_komponente;

import java.util.Date;

import haw.ai.komponenten.bestell_komponente.Auftrag;
import haw.ai.komponenten.rechnungs_komponente.Rechnung;
import haw.ai.komponenten.rechnungs_komponente.RechnungsRepository;

public class LieferFassade {

	public static Lieferung erstelleLieferung(Auftrag auftrag) {
		return LieferRepository.erstelleLieferung(auftrag);
	}

	public static Transportauftrag erstelleTransportauftrag(
			Lieferung lieferung, Date ausgangsDatum, boolean lieferungErfolgt,
			Date lieferDatum, String transportDienstleister) {
		return LieferRepository.erstelleTransportauftrag(lieferung,
				ausgangsDatum, lieferungErfolgt, lieferDatum,
				transportDienstleister);
	}

	public static void markiereTransportErfolgt(
			Transportauftrag transportAuftrag) {
		if (transportAuftrag != null) {
			LieferBusinessLogik.lieferungErfolgt(transportAuftrag);
		}
	}

	public static void save(Lieferung lieferung) {
		LieferRepository.save(lieferung);
	}

	public static void save(Transportauftrag transportauftrag) {
		LieferRepository.save(transportauftrag);
	}

}
