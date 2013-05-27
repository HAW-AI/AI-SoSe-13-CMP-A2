package haw.ai.client;

import haw.ai.common.Log;
import haw.ai.server.bestell_komponente.*;
import haw.ai.server.common.DateUtil;
import haw.ai.server.kunden_komponente.Kunde;
import haw.ai.server.lager_komponente.Produkt;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class TestClient {

	private Dispatcher dispatcher;

	public TestClient(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	public void test() {

		try {
			
			while(dispatcher.getRechnungsFassade() == null) {
				Thread.sleep(1000);
			}
			
			dispatcher.getKundenFassade().erstelleKunden("Peter Meier",
					"Einbahnstrasse 10");
			Log.log(TestClient.class.getName(), "Kunde wurde erstellt");
			
			Kunde kunde = dispatcher.getKundenFassade()
					.findeKunden("Peter Meier");
			Log.log(TestClient.class.getName(), "Kunde: " + kunde + " wurde gefunden");

			Produkt produkt1 = dispatcher.getLagerFassade().erstelleProdukt(
					"Computer", 100);
			Log.log(TestClient.class.getName(), "Produkt: " + produkt1 + " wurde erstellt");

			Produkt produkt2 = dispatcher.getLagerFassade().erstelleProdukt(
					"Fernseher", 200);
			Log.log(TestClient.class.getName(), "Produkt: " + produkt2 + " wurde erstellt");

			Map<Produkt, Integer> produkte = new HashMap<Produkt, Integer>();
			produkte.put(produkt1, 1);
			produkte.put(produkt2, 2);

			Angebot angebot = dispatcher.getBestellFassade().erstelleAngebot(
					kunde, produkte, DateUtil.now(), DateUtil.daysFromNow(30),
					3000);
			Log.log(TestClient.class.getName(), "Angebot: " + angebot + " wurde gefunden");

			Auftrag auftrag = dispatcher.getBestellFassade().erstelleAuftrag(angebot,
					DateUtil.now());
			Log.log(TestClient.class.getName(), "Auftrag: " + auftrag + " wurde gefunden");
			
			dispatcher.getBestellFassade().auftragAbschliessen(auftrag);
			Log.log(TestClient.class.getName(), "Auftrag: " + auftrag + " wurde abgeschlossen");

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
		}
	}

}
