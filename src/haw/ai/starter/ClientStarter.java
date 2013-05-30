package haw.ai.starter;

import haw.ai.client.Dispatcher;
import haw.ai.client.DispatcherImpl;
import haw.ai.client.HESHealthMonitorThread;
import haw.ai.client.TestClient;
import haw.ai.client.gui.dashboard.Dashboard;
import haw.ai.common.Log;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientStarter {
	public final static String CLIENT_REGISTRY_HOST = "localhost";
	public final static Integer CLIENT_REGISTRY_PORT = 1099;
	private Registry registry;
	private HESHealthMonitorThread hesMonitor;
	private Dispatcher dispatcher;
	private Dashboard dashboard;

	public static void main(String[] args) {
		ClientStarter clientStarter = new ClientStarter();
		try {
			clientStarter.startRegistry();
			clientStarter.startDispatcher();
			clientStarter.startGui();
			clientStarter.startMonitor();
			clientStarter.startTestClient();
			clientStarter.logEverythingInitialized();
		} catch (AccessException e) {
			Log.log(ClientStarter.class.getName(), "main", "AccessException", e.getMessage());
		} catch (RemoteException e) {
			Log.log(ClientStarter.class.getName(), "main", "RemoteException", e.getMessage());
		}
	}

	private void startRegistry() {
		try {
			// create on port 1099
			this.registry = LocateRegistry.createRegistry(1099);
		} catch (Exception e) {
			Log.log(ClientStarter.class.getName(), "startRegistry", "Exception", e.getMessage());
		}
	}

	private void startDispatcher() {
		// The Dispatcher starts a new server process if non exists.
		this.dispatcher = new DispatcherImpl(registry);
		Log.log(ClientStarter.class.getName(), "--- Dispatcher gestartet ---");
	}

	private void startGui() {
		this.dashboard = Dashboard.create(dispatcher);
		this.dispatcher.setDashboard(dashboard);
		Log.log(ClientStarter.class.getName(), "--- Dashboard gestartet ---");
	}

	private void startMonitor() throws AccessException, RemoteException {
		this.hesMonitor = new HESHealthMonitorThread(dispatcher, dashboard, registry);
		((Thread) hesMonitor).start();
		Log.log(ClientStarter.class.getName(), "--- Monitor gestartet ---");
	}

	private void startTestClient() {
		try {
			TestClient test = new TestClient(dispatcher);
			Log.log(ClientStarter.class.getName(), "--- TestClient gestartet ---");
			Thread.sleep(10000);
			test.test();
		} catch (InterruptedException e) {
			Log.log(ClientStarter.class.getName(), "startTestClient", "InterruptedException", e.getMessage());
		}
	}
	
	private void logEverythingInitialized() {
		Log.log(ClientStarter.class.getSimpleName(), "registry not null: " + (registry  != null));
		Log.log(ClientStarter.class.getSimpleName(), "dispatcher not null: " + (dispatcher  != null));
		Log.log(ClientStarter.class.getSimpleName(), "hesmonitor not null: " + (hesMonitor  != null));
		Log.log(ClientStarter.class.getSimpleName(), "dashboard not null: " + (dashboard != null));
	}
}
