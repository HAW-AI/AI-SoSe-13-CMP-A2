package haw.ai.server.kunden_komponente;

import java.rmi.Remote;
import java.rmi.RemoteException;

import haw.ai.server.common.KomponentenFassade;

public interface KundenFassade extends KomponentenFassade, Remote {
	public Kunde erstelleKunden(String name, String adresse) throws RemoteException;
	public Kunde findeKunden(String name) throws RemoteException;
	public void save(Kunde kunde) throws RemoteException;
	public void sayHello() throws RemoteException;
	public String bindName();
}
