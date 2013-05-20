/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package haw.ai.client.gui.dashboard;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

/**
 *
 * @author Kazura
 */
public class ProcessListCellRenderer extends JLabel implements ListCellRenderer {
    
  @Override
  public Component getListCellRendererComponent(JList list, Object value,
      int index, boolean isSelected, boolean cellHasFocus) {

    HES_Instanz instanz = (HES_Instanz)value;

    this.setText(instanz.getName());
    
    this.setOpaque(true);
    
    if(isSelected){
      this.setForeground(UIManager.getColor("List.selectionForeground"));   
      this.setBackground(UIManager.getColor("List.selectionBackground"));
    }
    else{
      this.setBackground(UIManager.getColor("List.background"));
    }
    
    if (instanz.getState() == true) this.setForeground(Color.GREEN);
    else this.setForeground(Color.RED);
    
    return this;
  }
      
}