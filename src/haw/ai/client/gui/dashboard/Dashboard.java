package haw.ai.client.gui.dashboard;

public class Dashboard {

	private Dashboard_GUI gui;
	
    public Dashboard() {
        this.gui = new Dashboard_GUI();
        this.gui.setVisible(true);
    }
    
    public void showInstanceState(String instanzname, boolean state) {
    	this.gui.setProcessState(instanzname, state);
    }
    
}
