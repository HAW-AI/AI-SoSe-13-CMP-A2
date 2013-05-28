package haw.ai.client;

import haw.ai.server.alive.ServerControllerImpl;
import haw.ai.server.bestell_komponente.BestellFassade;
import haw.ai.server.kunden_komponente.KundenFassade;
import haw.ai.server.lager_komponente.LagerFassade;
import haw.ai.server.liefer_komponente.LieferFassade;
import haw.ai.server.rechnungs_komponente.RechnungsFassade;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/*  Klasse zur Darstellung der externen Objektreferenz einer HES-Instanz.
 *  Traegt den gleichen Namen (hesInstanceName) wie die entfernte HES-Instanz
 *  Holt entsprechende Fassaden der HES-Instanz aus der Registry und gibt sie zurueck
 */

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
	
	public ServerControllerImpl getServerController() {
		ServerControllerImpl serverController = null;
		try {
			serverController = (ServerControllerImpl) registry.lookup(ServerControllerImpl.class.getSimpleName());
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
	
	public LagerFassade getLagerFassade() {
		LagerFassade lagerFassade = null;
		try {
			lagerFassade = (LagerFassade) registry.lookup(LagerFassade.class.getSimpleName());
		} catch (AccessException e) {
		} catch (RemoteException e) {
		} catch (NotBoundException e) {
		}
		return lagerFassade;
	}
	
	public LieferFassade getLieferFassade() {
		LieferFassade lieferFassade = null;
		try {
			lieferFassade = (LieferFassade) registry.lookup(LieferFassade.class.getSimpleName());
		} catch (AccessException e) {
		} catch (RemoteException e) {
		} catch (NotBoundException e) {
		}
		return lieferFassade;
	}
	
	public RechnungsFassade getRechnungsFassade() {
		RechnungsFassade rechnungsFassade = null;
		try {
			rechnungsFassade = (RechnungsFassade) registry.lookup(RechnungsFassade.class.getSimpleName());
		} catch (AccessException e) {
		} catch (RemoteException e) {
		} catch (NotBoundException e) {
		}
		return rechnungsFassade;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((hesInstanceName == null) ? 0 : hesInstanceName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RemoteHESInstance other = (RemoteHESInstance) obj;
		if (hesInstanceName == null) {
			if (other.hesInstanceName != null)
				return false;
		} else if (!hesInstanceName.equals(other.hesInstanceName))
			return false;
		return true;
	}
}
