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

    private static List<HESInstanceState> processlist = new ArrayList<HESInstanceState>();
    
    public void add(HESInstanceState h) {
       processlist.add(h);
       fireContentsChanged(this, 0, getSize());
    }

    public ProcessListModel(){
        generateList();
    }
    
    private static List<HESInstanceState> generateList() {
        return processlist;
    }
    
    public List<HESInstanceState> getList() {
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
        
    public void delete(HESInstanceState r) {
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
    
    public boolean contains(HESInstanceState instanz) {
    	return processlist.contains(instanz);
    }
    
    public int getIndex(HESInstanceState r) {
        return processlist.indexOf(r);
    }

}