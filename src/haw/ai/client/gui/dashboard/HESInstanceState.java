package haw.ai.client.gui.dashboard;

public class HESInstanceState {
    
    private String name;
    private boolean state;
    private long uptime;
    private long uptime_diff;
    private long downtime;
    private long downtime_diff;
    private int count;
    
    public HESInstanceState(String name, boolean state) {
        this.name = name;
        this.state = state;
        this.uptime = System.currentTimeMillis();
        this.uptime_diff = 0;
        this.downtime = System.currentTimeMillis();
        this.downtime_diff = 0;
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

	public long getUptime_diff() {
		return uptime_diff;
	}

	public void setUptime_diff(long uptime_diff) {
		this.uptime_diff = uptime_diff;
	}

	public long getDowntime_diff() {
		return downtime_diff;
	}

	public void setDowntime_diff(long downtime_diff) {
		this.downtime_diff = downtime_diff;
	}
    
}
