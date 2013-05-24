package haw.ai.client;

import haw.ai.client.gui.dashboard.Dashboard;
import haw.ai.client.Dispatcher;
import haw.ai.server.kunden_komponente.KundenFassade;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Starter {
	
	private Registry registry;
	private HESHealthMonitor hesMonitor;
	private Dispatcher dispatcher;
	private Dashboard dashboard;

	private void startServer() {
		try {
			// create on port 1099
			registry = LocateRegistry.createRegistry(1099);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("system is ready");
	}
	
	private void startMonitor() throws AccessException, RemoteException {
		dispatcher = new DispatcherImpl();
		dashboard = new Dashboard();
		hesMonitor = new HESHealthMonitorImpl(dispatcher, dashboard);
		registry.rebind(HESHealthMonitor.class.getName(), hesMonitor);
	}

	public static void main(String[] args) {
		Starter starter = new Starter();
		try {
			starter.startServer();
			starter.startMonitor();
			Thread.sleep(10000);
			starter.getKundenFassade().sayHello();
		} catch (InterruptedException e) {
		} catch (AccessException e) {
		} catch (RemoteException e) {
		} catch (NotBoundException e) {
		}
	}
	
	public KundenFassade getKundenFassade() throws AccessException, RemoteException, NotBoundException {
		return ((KundenFassade) registry.lookup(findFassade(registry.list(), KundenFassade.class.getName())));
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
