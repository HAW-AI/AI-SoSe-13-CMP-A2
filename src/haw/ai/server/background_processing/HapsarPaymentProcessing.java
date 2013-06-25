package haw.ai.server.background_processing;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import redis.clients.jedis.Jedis;
import haw.ai.common.Log;
import haw.ai.server.HESServer;
import haw.ai.server.common.DateUtil;
import haw.ai.server.rechnungs_komponente.Rechnung;

public class HapsarPaymentProcessing extends Thread {
	private static final String REDIS_HOST = "localhost";
	private static final Integer REDIS_PORT = 6379;
	private static final String HAPSAR_QUEUE_NAME = "hapsar_queue";
	private HESServer hesServer;
	private Jedis jedis;

	public HapsarPaymentProcessing(HESServer hesServer) {
		this.hesServer = hesServer;
	}
	
	public void run() {
		this.jedis = new Jedis(REDIS_HOST, REDIS_PORT);
		String[] request = new String[2];
		request[0] = HAPSAR_QUEUE_NAME;
		request[1] = "0"; // timeout
		
		while(true) {
			List<String> result = jedis.blpop(0, request);
			String[] parts = result.get(1).split(" ");
			List<String> partsList = Arrays.asList(parts);
			
			Integer rechnungsNummer = Integer.valueOf(partsList.get(0));
			Integer rechnungsBetrag = Integer.valueOf(partsList.get(1));
			try {
				Log.log(HapsarPaymentProcessing.class.getSimpleName(), "erstelleZahlungseingang", "rechnungsNummer: " + rechnungsNummer, "rechnungsBetrag: " + rechnungsBetrag);
				Rechnung rechnung = hesServer.getRechnungsFassade().findRechnungByRechnungsnummer(rechnungsNummer);
				hesServer.getRechnungsFassade().erstelleZahlungseingang(rechnung, DateUtil.now(), rechnungsBetrag);
			} catch (RemoteException e) {
				Log.log(HapsarPaymentProcessing.class.getSimpleName(), "run", "erstelleZahlungseingang failed");
			}
		}
	}
}
