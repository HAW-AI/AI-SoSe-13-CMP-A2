package haw.ai.client.gui.dashboard;

import haw.ai.client.Dispatcher;
import haw.ai.client.Starter;
import haw.ai.client.TestClient;
import haw.ai.common.Log;

public class Dashboard {

	private Dashboard_GUI gui;
	private Dispatcher dispatcher;

	public Dashboard(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
		this.gui = new Dashboard_GUI(this);
		this.gui.setVisible(true);
		Log.log(Dashboard.class.getName(), "--- Dashboard-GUI gestartet ---");
	}

	// wird vom Monitor aufgerufen, um den Status einer Instanz zu aktualisieren
	// (ggf. wird ein neuer Listeneintrag in der GUI angelegt, wenn der
	// Instanzname
	// noch nicht in der Liste steht)
	public void showNewInstanceState(String instanzname, boolean state) {
		Log.log(Dashboard.class.getName(),
				"Monitoraufruf showNewInstanceState mit Instanz " + instanzname
						+ " und Status " + state);
		this.gui.setProcessState(instanzname, state);
	}

	// wird von GUI aufgerufen, wenn Benutzer Instanzen "ON" oder "OFF" schaltet
	public void changeInstanceState(String instanzname, boolean state) {
		Log.log(Dashboard.class.getName(),
				"GUI-Aufruf changeInstanceState mit Instanz " + instanzname
						+ " und Status " + state + " wird an den Dispatcher weitergeleitet");
		this.dispatcher.changeInstanceState(instanzname, state);
	}

}
