package haw.ai;

import haw.ai.hibernate.HibernateUtil;
import haw.ai.komponenten.kunden_komponente.Kunde;
import haw.ai.komponenten.kunden_komponente.KundenRepository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {

	/**
	 * @param args
	 */
	private static Session session;

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
		Kunde kunde = KundenRepository.erstelleKunde("Foo", "Bar");
		Kunde kunde2 = KundenRepository.erstelleKunde("Foo", "BaZ");
		System.out.println(kunde.getId());
		System.out.println(kunde2.getId());
		session.close();
	}

}
