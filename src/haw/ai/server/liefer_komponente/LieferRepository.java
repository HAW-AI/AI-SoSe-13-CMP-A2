package haw.ai.server.liefer_komponente;

import haw.ai.server.bestell_komponente.Auftrag;
import haw.ai.server.kunden_komponente.Kunde;
import haw.ai.server.persistenz.PersistenzService;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

public class LieferRepository {

	public static Lieferung erstelleLieferung(Auftrag auftrag) {
		Lieferung lieferung = new Lieferung(auftrag);
		save(lieferung);
		return lieferung;
	}

	public static Transportauftrag erstelleTransportauftrag(
			Lieferung lieferung, Date ausgangsDatum, boolean lieferungErfolgt,
			Date lieferDatum, String transportDienstleister) {
		Transportauftrag transportauftrag = new Transportauftrag(lieferung,
				ausgangsDatum, lieferungErfolgt, lieferDatum,
				transportDienstleister);
		save(transportauftrag);
		return transportauftrag;
	}

	public static void save(Lieferung lieferung) {
		if (lieferung != null) {
			PersistenzService.saveEntity(lieferung);
		}
	}

	public static void save(Transportauftrag transportauftrag) {
		if (transportauftrag != null) {
			PersistenzService.saveEntity(transportauftrag);
		}
	}

	public static Transportauftrag findTransportauftrag(int transportauftragId) {
		return (Transportauftrag) PersistenzService.getSession().get(
				Transportauftrag.class, transportauftragId);
	}

}
