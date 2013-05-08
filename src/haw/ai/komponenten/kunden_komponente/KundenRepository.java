package haw.ai.komponenten.kunden_komponente;

import haw.ai.komponenten.persistenz.PersistenzService;

import java.util.List;

import org.hibernate.Session;

public class KundenRepository {
	
	public static Kunde findeKunden(String name) {
		Session session = PersistenzService.getSession();
		List<Kunde> kunden = session
				.createQuery("FROM Kunde kunde WHERE kunde.name = :name")
				.setParameter("name", name).list();
		Kunde kunde = null;
		if (!kunden.isEmpty()) {
			kunde = kunden.get(0);
		}
		return kunde;
	}

	public static Kunde erstelleKunde(String name, String adresse) {
		Kunde kunde = new Kunde(name, adresse);
		save(kunde);
		return kunde;
	}

	public static void save(Kunde kunde) {
		if (kunde != null) {
			PersistenzService.saveEntity(kunde);
		}
	}

}
