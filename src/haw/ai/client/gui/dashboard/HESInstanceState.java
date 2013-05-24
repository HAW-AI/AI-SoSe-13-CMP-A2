package haw.ai.client.gui.dashboard;

public class HESInstanceState {
    
    private String name;
    private boolean state;
    
    public HESInstanceState(String name, boolean state) {
        this.name = name;
        this.state = state;
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean getState() {
        return this.state;
    }
    
    public void setState(boolean new_state) {
        this.state = new_state;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
}
