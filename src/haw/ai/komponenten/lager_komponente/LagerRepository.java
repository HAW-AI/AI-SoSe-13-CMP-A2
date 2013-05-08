package haw.ai.komponenten.lager_komponente;

import haw.ai.komponenten.persistenz.PersistenzService;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

public class LagerRepository {

	public static Produkt erstelleProdukt(String name, int lagerbestand) {
		Produkt produkt = new Produkt(name, lagerbestand);
		save(produkt);
		return produkt;
	}

	public static Warenausgangsmeldung erstelleWarenausgangsmeldung(
			Produkt produkt, Date datum, int menge) {
		Warenausgangsmeldung warenausgangsmeldung = new Warenausgangsmeldung(
				produkt, datum, menge);
		save(warenausgangsmeldung);
		return warenausgangsmeldung;
	}

	public static void save(Produkt produkt) {
		if (produkt != null) {
			PersistenzService.saveEntity(produkt);
		}
	}

	public static void save(Warenausgangsmeldung warenausgangsmeldung) {
		if (warenausgangsmeldung != null) {
			PersistenzService.saveEntity(warenausgangsmeldung);
		}
	}

	public static Produkt findeProdukt(Integer produktId) {
		return (Produkt) PersistenzService.getSession().get(Produkt.class, produktId);
	}

	public static Produkt findeProdukt(String produktName) {
		Session session = PersistenzService.getSession();
		List<Produkt> produkte = session
				.createQuery("FROM Produkt produkt WHERE produkt.name = :name")
				.setParameter("name", produktName).list();
		Produkt produkt = null;
		if (!produkte.isEmpty()) {
			produkt = produkte.get(0);
		}
		return produkt;
	}
}
