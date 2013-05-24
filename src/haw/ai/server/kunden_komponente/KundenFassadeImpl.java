package haw.ai.server.kunden_komponente;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class KundenFassadeImpl extends UnicastRemoteObject implements KundenFassade {
	
	public final static String bindFassadenName = KundenFassade.class.getName();

	private static final long serialVersionUID = 1710093868780112115L;
	private String instanceName;
	private Registry registry;

	private KundenFassadeImpl(Registry registry, String instanceName) throws RemoteException {
		this.instanceName = instanceName;
		this.registry = registry;
	}

	public Kunde erstelleKunden(String name, String adresse) throws RemoteException {
		return KundenRepository.erstelleKunde(name, adresse);
	}

	public Kunde findeKunden(String name) throws RemoteException {
		Kunde kunde = KundenRepository.findeKunden(name);
		return kunde;
	}

	public void save(Kunde kunde) throws RemoteException {
		if (kunde != null) {
			KundenRepository.save(kunde);
		}
	}
	
	public static KundenFassade createKundenFassade(Registry registry, String instanceName) throws RemoteException {
		KundenFassadeImpl kundenFassade = new KundenFassadeImpl(registry, instanceName);
		registry.rebind(kundenFassade.bindName(), kundenFassade);
		return kundenFassade;
	}
	
	public String bindName() {
		return instanceName + " - " + bindFassadenName;
	}

	@Override
	public void sayHello() throws RemoteException {
		System.out.println("HELLO");
	}
	
}
