/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package haw.ai.client.gui.dashboard;

/**
 *
 * @author Kazura
 */
public class HES_Instanz {
    
    private String name;
    private boolean state;
    
    public HES_Instanz(String name, boolean state) {
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
