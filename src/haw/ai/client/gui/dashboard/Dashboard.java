package haw.ai.client.gui.dashboard;

import haw.ai.client.Dispatcher;
import haw.ai.common.Log;

public class Dashboard {

	private Dashboard_GUI gui;
	private Dispatcher dispatcher;

	public Dashboard(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
		Log.log(Dashboard.class.getName(), "--- Dashboard-GUI gestartet ---");
	}

	// wird vom Monitor aufgerufen, um den Status einer Instanz zu aktualisieren
	// (ggf. wird ein neuer Listeneintrag in der GUI angelegt, wenn der
	// Instanzname noch nicht in der Liste steht)
	public void showNewInstanceState(String instanzname, boolean state) {
		Log.log(Dashboard.class.getName(),
				"Monitoraufruf showNewInstanceState mit Instanz " + instanzname
						+ " und Status " + state);
		// bei diesem Aufruf setzt die GUI die Up-/Downtime neu!
		this.gui.setInstanceState(instanzname, state);
	}

	// wird von GUI aufgerufen, wenn Benutzer Instanzen "ON" oder "OFF" schaltet
	public void changeInstanceState(String instanzname, boolean state) {
		Log.log(Dashboard.class.getName(),
				"GUI-Aufruf changeInstanceState mit Instanz " + instanzname
						+ " und Status " + state + " wird an den Dispatcher weitergeleitet");
		this.dispatcher.changeInstanceState(instanzname, state);
	}
	
	// wird von Dispatcher aufgerufen, wenn eine Serviceanfrage an diese Instanz gestellt wird
	// erhoehe Serviceanfragen dieser Instanz um 1
	public void increaseCount(String instanzname) {
		gui.increaseCount(instanzname);
	}
	
	public static Dashboard create(Dispatcher dispatcher) {
		Dashboard dashboard = new Dashboard(dispatcher);
		dashboard.setGui(new Dashboard_GUI(dashboard));
		return dashboard;
	}

	private void setGui(Dashboard_GUI dashboard_GUI) {
		this.gui = dashboard_GUI;
		this.gui.setVisible(true);
	}

}
