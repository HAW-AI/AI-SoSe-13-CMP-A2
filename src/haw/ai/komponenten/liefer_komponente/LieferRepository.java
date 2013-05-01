package haw.ai.komponenten.liefer_komponente;

import java.util.Date;

import org.hibernate.Session;
import haw.ai.hibernate.HibernateUtil;
import haw.ai.komponenten.bestell_komponente.Auftrag;
import haw.ai.komponenten.kunden_komponente.Kunde;

public class LieferRepository {

	public static Lieferung erstelleLieferung(Auftrag auftrag) {
		Lieferung lieferung = new Lieferung(auftrag);
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(lieferung);
		session.getTransaction().commit();
		return lieferung;
	}

	public static Transportauftrag erstelleTransportauftrag(Lieferung lieferung,
			Date ausgangsDatum, boolean lieferungErfolgt, Date lieferDatum,
			String transportDienstleister) {
		Transportauftrag transportauftrag = new Transportauftrag(lieferung,
				ausgangsDatum, lieferungErfolgt, lieferDatum,
				transportDienstleister);
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(transportauftrag);
		session.getTransaction().commit();
		return transportauftrag;
	}

}
