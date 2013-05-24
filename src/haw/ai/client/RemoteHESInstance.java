package haw.ai.client;

import haw.ai.server.alive.ServerController;
import haw.ai.server.bestell_komponente.BestellFassade;
import haw.ai.server.kunden_komponente.KundenFassade;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RemoteHESInstance {
	private String hesInstanceName;
	private String hesInstanceHostame;
	private Integer hesInstanceRegistryPort;
	private Registry registry;

	public RemoteHESInstance(String hesInstanceName, String hesInstanceHostame,
			Integer hesInstanceRegistryPort) {
		this.setHesInstanceName(hesInstanceName);
		this.hesInstanceHostame = hesInstanceHostame;
		this.hesInstanceRegistryPort = hesInstanceRegistryPort;
		locateHESServerRegistry();
	}

	public String getHesInstanceName() {
		return hesInstanceName;
	}
	
	public ServerController getServerController() {
		ServerController serverController = null;
		try {
			serverController = (ServerController) registry.lookup(ServerController.class.getSimpleName());
		} catch (AccessException e) {
		} catch (RemoteException e) {
		} catch (NotBoundException e) {
		}
		return serverController;
	}
	
	public KundenFassade getKundenFassade() {
		KundenFassade kundenFassade = null;
		try {
			kundenFassade = (KundenFassade) registry.lookup(KundenFassade.class.getSimpleName());
		} catch (AccessException e) {
		} catch (RemoteException e) {
		} catch (NotBoundException e) {
		}
		return kundenFassade;
	}
	
	public BestellFassade getBestellFassade() {
		BestellFassade bestellFassade = null;
		try {
			bestellFassade = (BestellFassade) registry.lookup(BestellFassade.class.getSimpleName());
		} catch (AccessException e) {
		} catch (RemoteException e) {
		} catch (NotBoundException e) {
		}
		return bestellFassade;
	}
	
	private void locateHESServerRegistry() {
		try {
			this.registry = LocateRegistry.getRegistry(hesInstanceHostame, hesInstanceRegistryPort);
		} catch (RemoteException e) {
		}
	}

	private void setHesInstanceName(String hesInstanceName) {
		this.hesInstanceName = hesInstanceName;
	}
}
