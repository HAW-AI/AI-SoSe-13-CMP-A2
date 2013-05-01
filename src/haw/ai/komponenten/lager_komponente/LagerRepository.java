package haw.ai.komponenten.lager_komponente;

import java.util.Date;

import haw.ai.hibernate.HibernateUtil;
import haw.ai.komponenten.bestell_komponente.Auftrag;

import org.hibernate.Session;

public class LagerRepository {

	public static Produkt erstelleProdukt(String name, int lagerbestand) {
		Produkt produkt = new Produkt(name, lagerbestand);
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(produkt);
		session.getTransaction().commit();
		return produkt;
	}

	public static Warenausgangsmeldung erstelleWarenausgangsmeldung(
			Produkt produkt, Date datum, int menge) {
		Warenausgangsmeldung warenausgangsmeldung = new Warenausgangsmeldung(
				produkt, datum, menge);
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(warenausgangsmeldung);
		session.getTransaction().commit();
		return warenausgangsmeldung;
	}

	public static void save(Produkt produkt) {
		if (produkt != null) {
			Session session = HibernateUtil.getSession();
			session.beginTransaction();
			session.save(produkt);
			session.getTransaction().commit();
		}
	}

	public static void save(Warenausgangsmeldung warenausgangsmeldung) {
		if (warenausgangsmeldung != null) {
			Session session = HibernateUtil.getSession();
			session.beginTransaction();
			session.save(warenausgangsmeldung);
			session.getTransaction().commit();
		}
	}
}