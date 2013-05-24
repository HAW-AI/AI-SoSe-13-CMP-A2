/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package haw.ai.client.gui.dashboard;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author Kazura
 */
public class ProcessListModel extends AbstractListModel {

    private static List<HES_Instanz> processlist = new ArrayList<HES_Instanz>();
    
    public void add(HES_Instanz h) {
       processlist.add(h);
       fireContentsChanged(this, 0, getSize());
    }

    public ProcessListModel(){
        generateList();
    }
    
    private static List<HES_Instanz> generateList() {
        return processlist;
    }
    
    public List<HES_Instanz> getList() {
        return processlist;
    }
    
    @Override
    public int getSize() {
        return processlist.size();
    }
    
    @Override
    public Object getElementAt(int index) {
        return processlist.get(index);
    }
        
    public void delete(HES_Instanz r) {
        processlist.remove(r);
        fireContentsChanged(this, 0, getSize());
    }
    
    public void deleteAll() {
        processlist.clear();
        fireContentsChanged(this, 0, getSize());
    }
    
    public boolean isEmpty() {
        return processlist.isEmpty();
    }
    
    public boolean contains(HES_Instanz instanz) {
    	return processlist.contains(instanz);
    }
    
    public int getIndex(HES_Instanz r) {
        return processlist.indexOf(r);
    }

}