package haw.ai.server.kunden_komponente;

import haw.ai.common.Log;
import haw.ai.server.HESServerImpl;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class KundenFassadeImpl extends UnicastRemoteObject implements KundenFassade {
	private static final long serialVersionUID = 1710093868780112115L;
	private HESServerImpl hesServer;

	private KundenFassadeImpl(HESServerImpl hesServer) throws RemoteException {
		this.hesServer = hesServer;
	}

	public Kunde erstelleKunden(String name, String adresse) throws RemoteException {
		Log.log(KundenFassadeImpl.class.getName(), hesServer.getInstanceName(), "erstelleKunden");
		return KundenRepository.erstelleKunde(name, adresse);
	}

	public Kunde findeKunden(String name) throws RemoteException {
		Log.log(KundenFassadeImpl.class.getName(), hesServer.getInstanceName(), "findeKunden");
		Kunde kunde = KundenRepository.findeKunden(name);
		return kunde;
	}

	public void save(Kunde kunde) throws RemoteException {
		if (kunde != null) {
			Log.log(KundenFassadeImpl.class.getName(), hesServer.getInstanceName(), "save", "kunde", kunde.getName());
			KundenRepository.save(kunde);
		}
	}

	public static KundenFassade createKundenFassade(HESServerImpl hesServer) {
		Log.log(KundenFassadeImpl.class.getName(), hesServer.getInstanceName(), "createKundenFassade");
		KundenFassade kundenFassade = null;
		try {
			kundenFassade = new KundenFassadeImpl(hesServer);
			Log.log(KundenFassadeImpl.class.getName(), hesServer.getInstanceName(), "createKundenFassade", "binding in serverregistry");
			hesServer.getServerRegistry().rebind(KundenFassade.class.getSimpleName(), kundenFassade);
		} catch (AccessException e) {
		} catch (RemoteException e) {
		}
		return kundenFassade;
	}
	
}
