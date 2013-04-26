package haw.ai.komponenten.kunden_komponente;

import org.hibernate.Session;

import haw.ai.hibernate.HibernateUtil;

public class KundenRepository {
	public static Kunde findeKunden(String name) {
		return null;
	}

	public static Kunde erstelleKunde(String name, String adresse) {
		Kunde kunde = new Kunde(name, adresse);
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(kunde);
		session.getTransaction().commit();
		return kunde;
	}

}
