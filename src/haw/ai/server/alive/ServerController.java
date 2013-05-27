package haw.ai.server.alive;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerController extends Remote {
	public void changeInstanceState(boolean state) throws RemoteException;
}
