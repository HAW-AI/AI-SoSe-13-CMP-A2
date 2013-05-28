/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Dashboard_GUI.java
 *
 * Created on 20.05.2013, 11:03:37
 */
package haw.ai.client.gui.dashboard;

/**
 * 
 * @author Kazura
 */
public class Dashboard_GUI extends javax.swing.JFrame {

	private static final long serialVersionUID = 4448337400481920417L;
	private Dashboard dashboard;

	/** Creates new form Dashboard_GUI */
	public Dashboard_GUI(Dashboard dashboard) {
		initComponents();
		this.dashboard = dashboard;
		initialize();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		processList = new javax.swing.JList();
		instanceNameLabel = new javax.swing.JLabel();
		changeStateButton = new javax.swing.JToggleButton();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		uptimeLabel = new javax.swing.JLabel();
		downtimeLabel = new javax.swing.JLabel();
		countLabel = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14));
		jLabel1.setText("Dashboard GUI");

		processList.setModel(new ProcessListModel());
		processList
				.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
					public void valueChanged(
							javax.swing.event.ListSelectionEvent evt) {
						processListValueChanged();
					}
				});
		jScrollPane1.setViewportView(processList);

		instanceNameLabel.setText("HES Instanz - Name");

		changeStateButton.setText("Status");
		changeStateButton.setEnabled(false);
		changeStateButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						changeStateButtonActionPerformed(evt);
					}
				});

		jLabel2.setText("Uptime:");
		jLabel3.setText("Downtime:");
		jLabel4.setText("Anzahl Serviceanfragen:");
		uptimeLabel.setText("(Time)");
		downtimeLabel.setText("(Time)");
		countLabel.setText("(Anzahl)");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel1)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jScrollPane1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		139,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(28,
																										28,
																										28)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(
																														jLabel4,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														Short.MAX_VALUE)
																												.addComponent(
																														instanceNameLabel,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														118,
																														Short.MAX_VALUE)
																												.addGroup(
																														layout.createSequentialGroup()
																																.addGroup(
																																		layout.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING)
																																				.addComponent(
																																						jLabel3)
																																				.addComponent(
																																						jLabel2))
																																.addPreferredGap(
																																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																.addGroup(
																																		layout.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING,
																																				false)
																																				.addComponent(
																																						uptimeLabel,
																																						javax.swing.GroupLayout.DEFAULT_SIZE,
																																						javax.swing.GroupLayout.DEFAULT_SIZE,
																																						Short.MAX_VALUE)
																																				.addComponent(
																																						downtimeLabel,
																																						javax.swing.GroupLayout.DEFAULT_SIZE,
																																						javax.swing.GroupLayout.DEFAULT_SIZE,
																																						Short.MAX_VALUE))
																																.addGap(8,
																																		8,
																																		8))))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										changeStateButton)))))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(countLabel).addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel1)
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addComponent(
														jScrollPane1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														170,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		instanceNameLabel,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		15,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(26, 26,
																		26)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						jLabel2)
																				.addComponent(
																						uptimeLabel))
																.addGap(18, 18,
																		18)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						jLabel3)
																				.addComponent(
																						downtimeLabel))
																.addGap(18, 18,
																		18)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						jLabel4)
																				.addComponent(
																						countLabel))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		changeStateButton)))
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	public void initialize() {
		processList.setCellRenderer(new ProcessListCellRenderer());
	}

	// erhoehe die Anzahl Serviceanfragen der Instanz mit "instanzname" um 1
	public void increaseCount(String instanzname) {
		ProcessListModel model = (ProcessListModel) processList.getModel();

		HESInstanceState instanz_in_liste;

		for (int i = 0; i < model.getSize(); i++) {
			instanz_in_liste = (HESInstanceState) model.getElementAt(i);
			if (instanzname.equals(instanz_in_liste.getName())) {
				instanz_in_liste.increaseCount();
			}
		}
		
		this.processListValueChanged();
	}

	// setze Status der Instanz mit "instanzname" neu
	// hier wird auch die aktuelle Up- und Downtime neu berechnet
	public void setInstanceState(String instanzname, boolean state) {
//		ProcessListModel model = (ProcessListModel) processList.getModel();
//
//		HESInstanceState instanz_in_liste = null;
//		boolean neue_instanz = true;
//		
//		for (int i = 0; i < model.getSize(); i++) {
//			instanz_in_liste = (HESInstanceState) model.getElementAt(i);
//			if (instanzname.equals(instanz_in_liste.getName())) {
//				neue_instanz = false;
//				break;
//			}
//		}
//		
//		// Up-/Downtime berechnen, wenn Instanz bereits in der Liste stand
//		if (!neue_instanz) {
//			// Status war ON, wird auf ON gesetzt (Uptime neu berechnen)
//			if (instanz_in_liste.getState() == true && state == true) {
//				long current = System.currentTimeMillis();
//				long old = instanz_in_liste.getUptime();
//				instanz_in_liste.setUptime(old + (current-old));
//			}
//			// Status war OFF, wird auf ON gesetzt (Downtime neu berechnen und Status �ndern)
//			if (instanz_in_liste.getState() == false && state == true) {
//				long current = System.currentTimeMillis();
//				long old = instanz_in_liste.getDowntime();
//				instanz_in_liste.setDowntime(old + (current-old));
//				instanz_in_liste.setState(state);
//			}
//			// Status war ON, wird auf OFF gesetzt (Uptime neu berechnen und Status �ndern)
//			if (instanz_in_liste.getState() == true && state == false) {
//				long current = System.currentTimeMillis();
//				long old = instanz_in_liste.getUptime();
//				instanz_in_liste.setUptime(old + (current-old));
//				instanz_in_liste.setState(state);
//			}
//			// Status war OFF, wird auf OFF gesetzt (Downtime neu berechnen)
//			if (instanz_in_liste.getState() == false && state == false) {
//				long current = System.currentTimeMillis();
//				long old = instanz_in_liste.getDowntime();
//				instanz_in_liste.setDowntime(old + (current-old));
//			}
//		}
//		
//		// wenn neue Instanz, einfach in die Liste eintragen
//		if (neue_instanz) {
//			HESInstanceState instanz = new HESInstanceState(instanzname, state);
//			model.add(instanz);
//		}
//		
//		this.processListValueChanged();
	}

	// Eintrag in der Liste wird selektiert, Daten der Instanz neu anzeigen
	private void processListValueChanged() {// GEN-FIRST:event_processListValueChanged
//		if (!changeStateButton.isEnabled())
//			changeStateButton.setEnabled(true);
//
		 /* 
		  * you cant just do this. what if nothing is selected in the process list.
		  * then you end up with null pointer exceptions which is probably what our problem is.
		  * 
		  */
//		HESInstanceState instanz = (HESInstanceState) processList
//				.getSelectedValue();
//		instanceNameLabel.setText(instanz.getName());
//		changeStateButton.setSelected(instanz.getState());
//		uptimeLabel.setText(instanz.getUptime() + "");
//		downtimeLabel.setText(instanz.getDowntime() + "");
//		countLabel.setText(instanz.getCount() + "");
//
//		if (changeStateButton.isSelected())
//			changeStateButton.setText("ON");
//		else
//			changeStateButton.setText("OFF");
	}// GEN-LAST:event_processListValueChanged

	// Togglebutton (ON/OFF) wurde geklickt, Status der Instanz muss ge�ndert werden
	private void changeStateButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_changeStateButtonActionPerformed
		HESInstanceState instanz = (HESInstanceState) processList
				.getSelectedValue();

		if (changeStateButton.isSelected()) {
			changeStateButton.setSelected(true);
			changeStateButton.setText("ON");
			this.setInstanceState(instanz.getName(), true);
			this.dashboard.changeInstanceState(instanz.getName(), true);
			processList.setSelectedValue(instanz, rootPaneCheckingEnabled);
			this.processListValueChanged();
		} else {
			changeStateButton.setSelected(false);
			changeStateButton.setText("OFF");
			this.setInstanceState(instanz.getName(), false);
			this.dashboard.changeInstanceState(instanz.getName(), false);
			processList.setSelectedValue(instanz, rootPaneCheckingEnabled);
			this.processListValueChanged();
		}
	}// GEN-LAST:event_changeStateButtonActionPerformed

	// /**
	// * @param args
	// * the command line arguments
	// */
	// public static void main(String args[]) {
	// /* Set the Nimbus look and feel */
	// // <editor-fold defaultstate="collapsed"
	// // desc=" Look and feel setting code (optional) ">
	// /*
	// * If Nimbus (introduced in Java SE 6) is not available, stay with the
	// * default look and feel. For details see
	// * http://download.oracle.com/javase
	// * /tutorial/uiswing/lookandfeel/plaf.html
	// */
	// try {
	// for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
	// .getInstalledLookAndFeels()) {
	// if ("Nimbus".equals(info.getName())) {
	// javax.swing.UIManager.setLookAndFeel(info.getClassName());
	// break;
	// }
	// }
	// } catch (ClassNotFoundException ex) {
	// java.util.logging.Logger.getLogger(Dashboard_GUI.class.getName())
	// .log(java.util.logging.Level.SEVERE, null, ex);
	// } catch (InstantiationException ex) {
	// java.util.logging.Logger.getLogger(Dashboard_GUI.class.getName())
	// .log(java.util.logging.Level.SEVERE, null, ex);
	// } catch (IllegalAccessException ex) {
	// java.util.logging.Logger.getLogger(Dashboard_GUI.class.getName())
	// .log(java.util.logging.Level.SEVERE, null, ex);
	// } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	// java.util.logging.Logger.getLogger(Dashboard_GUI.class.getName())
	// .log(java.util.logging.Level.SEVERE, null, ex);
	// }
	// // </editor-fold>
	//
	// /* Create and display the form */
	// java.awt.EventQueue.invokeLater(new Runnable() {
	//
	// public void run() {
	// new Dashboard_GUI().setVisible(true);
	// }
	// });
	// }

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JToggleButton changeStateButton;
	private javax.swing.JLabel countLabel;
	private javax.swing.JLabel downtimeLabel;
	private javax.swing.JLabel instanceNameLabel;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JList processList;
	private javax.swing.JLabel uptimeLabel;
	// End of variables declaration//GEN-END:variables
}
