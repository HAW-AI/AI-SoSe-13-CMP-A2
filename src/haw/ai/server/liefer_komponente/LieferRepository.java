package haw.ai.server.liefer_komponente;

import haw.ai.server.bestell_komponente.Auftrag;
import haw.ai.server.persistenz.PersistenzService;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;

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
		Transportauftrag transportauftrag = null;
		try {
			Query query = PersistenzService.getSession().createQuery("from Transportauftrag where id = :transportauftragId");
			query.setParameter("transportauftragId", transportauftragId);
			List<Transportauftrag> list = query.list();
			if (!list.isEmpty()) {
				transportauftrag = list.get(0);		
			}			
		} catch (Exception e) {
		}
		return transportauftrag;
	}

}
