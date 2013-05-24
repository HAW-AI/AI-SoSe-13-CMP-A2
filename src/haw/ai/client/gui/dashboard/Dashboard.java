package haw.ai.client.gui.dashboard;

import haw.ai.client.Dispatcher;

public class Dashboard {

	private Dashboard_GUI gui;
	private Dispatcher dispatcher;
	
    public Dashboard(Dispatcher dispatcher) {
    	this.dispatcher = dispatcher;
        this.gui = new Dashboard_GUI(this);
        this.gui.setVisible(true);
    }
    
    // wird vom Monitor aufgerufen, um den Status einer Instanz zu aktualisieren
    // (ggf. wird ein neuer Listeneintrag in der GUI angelegt, wenn der Instanzname
    // noch nicht in der Liste steht)
    public void showNewInstanceState(String instanzname, boolean state) {
    	this.gui.setProcessState(instanzname, state);
    }
    
    // wird von GUI aufgerufen, wenn Benutzer Instanzen "ON" oder "OFF" schaltet
    public void changeInstanceState(String instanzname, boolean state) {
    	this.dispatcher.changeInstanceState(instanzname, state);
    }
    
}
