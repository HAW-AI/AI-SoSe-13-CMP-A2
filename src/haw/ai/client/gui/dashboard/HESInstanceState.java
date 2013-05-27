package haw.ai.client.gui.dashboard;

public class HESInstanceState {
    
    private String name;
    private boolean state;
    private long uptime;
    private long downtime;
    private int count;
    
    public HESInstanceState(String name, boolean state) {
        this.name = name;
        this.state = state;
        this.uptime = 0;
        this.downtime = 0;
        this.count = 0;
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean getState() {
        return this.state;
    }
    
    public long getUptime() {
    	return this.uptime;
    }
    
    public long getDowntime() {
    	return this.downtime;
    }
    
    public int getCount() {
    	return this.count;
    }
    
    public void setState(boolean new_state) {
        this.state = new_state;
    }
    
    public void setUptime(long uptime) {
    	this.uptime = uptime;
    }
    
    public void setDowntime(long downtime) {
    	this.downtime = downtime;
    }
    
    public void increaseCount() {
    	this.count += 1;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
}
