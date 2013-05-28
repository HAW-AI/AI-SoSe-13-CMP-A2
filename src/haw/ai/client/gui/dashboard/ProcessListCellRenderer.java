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
	private static final long serialVersionUID = 513640262629191397L;

@Override
  public Component getListCellRendererComponent(JList list, Object value,
      int index, boolean isSelected, boolean cellHasFocus) {

    HESInstanceState instanz = (HESInstanceState)value;

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
