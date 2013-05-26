package haw.ai.server.alive;

import java.rmi.Remote;

public interface ServerController extends Remote {
	
	public void changeInstanceState(boolean state);
	
}
