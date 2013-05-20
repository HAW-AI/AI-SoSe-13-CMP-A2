package haw.ai.client;

import haw.ai.komponenten.kunden_komponente.KundenFassade;
import haw.ai.komponenten.kunden_komponente.KundenFassadeImpl;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	private static Registry registry;

	private void startServer() {
		try {
			// create on port 1099
			registry = LocateRegistry.createRegistry(1099);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("system is ready");
	}

	public static void main(String[] args) {
		Client client = new Client();
		client.startServer();
		try {
			Thread.sleep(10000);
			getKundenFassade().sayHello();
		} catch (InterruptedException e) {
		} catch (AccessException e) {
		} catch (RemoteException e) {
		} catch (NotBoundException e) {
		}
	}
	
	public static KundenFassade getKundenFassade() throws AccessException, RemoteException, NotBoundException {
		return ((KundenFassade) registry.lookup(findFassade(registry.list(), KundenFassadeImpl.bindFassadenName)));
	}
	
	private static String findFassade(String[] fassaden, String fassadenArt) {
		String result = null;
		for (String fassade : fassaden) {
			if (fassade.contains(fassadenArt)) {
				result = fassade;
				break;
			}
		}
		return result;
	}
}
