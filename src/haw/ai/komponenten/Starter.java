package haw.ai.komponenten;

import haw.ai.komponenten.kunden_komponente.KundenFassade;
import haw.ai.komponenten.kunden_komponente.KundenFassadeImpl;

import java.rmi.RemoteException;
import java.util.UUID;

public class Starter {
	private String instanceName;
	private KundenFassade kundenFassade;

	public static void main(String[] args) throws RemoteException {
		Starter starter = new Starter();
		starter.kundenFassade = KundenFassadeImpl.createKundenFassade(starter.instanceName);
	}
	
	private Starter() {
		this.instanceName = UUID.randomUUID().toString();
	}
}
