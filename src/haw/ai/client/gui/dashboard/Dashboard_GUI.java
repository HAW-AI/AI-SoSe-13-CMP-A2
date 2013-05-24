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

	/** Creates new form Dashboard_GUI */
	public Dashboard_GUI() {
		initComponents();
		initialize();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		processList = new javax.swing.JList();
		instanceNameLabel = new javax.swing.JLabel();
		newInstanceButton = new javax.swing.JButton();
		changeStateButton = new javax.swing.JToggleButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel1.setText("Dashboard GUI");

		processList.setModel(new ProcessListModel());
		processList
				.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
					public void valueChanged(
							javax.swing.event.ListSelectionEvent evt) {
						processListValueChanged(evt);
					}
				});
		jScrollPane1.setViewportView(processList);

		instanceNameLabel.setText("HES Instanz - Name");

		newInstanceButton.setText("Starte neue Instanz");
		newInstanceButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						newInstanceButtonActionPerformed(evt);
					}
				});

		changeStateButton.setText("Status");
		changeStateButton.setEnabled(false);
		changeStateButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						changeStateButtonActionPerformed(evt);
					}
				});

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
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				false)
																				.addComponent(
																						newInstanceButton,
																						javax.swing.GroupLayout.Alignment.LEADING,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						jScrollPane1,
																						javax.swing.GroupLayout.Alignment.LEADING,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						139,
																						Short.MAX_VALUE))
																.addGap(28, 28,
																		28)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						changeStateButton,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						instanceNameLabel,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE))))
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel1)
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(31, 31,
																		31)
																.addComponent(
																		instanceNameLabel,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		15,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(20, 20,
																		20)
																.addComponent(
																		changeStateButton))
												.addComponent(
														jScrollPane1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														170,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(newInstanceButton)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	public void initialize() {
		processList.setCellRenderer(new ProcessListCellRenderer());
	}

	public void setProcessState(String instanzname, boolean state) {
		ProcessListModel model = (ProcessListModel) processList.getModel();

		HES_Instanz instanz_in_liste;
		for (int i = 0; i < model.getSize(); i++) {
			instanz_in_liste = (HES_Instanz) model.getElementAt(i);
			if (instanzname.equals(instanz_in_liste.getName())) {
				instanz_in_liste.setState(state);
			}
		}
	}

	private void newInstanceButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_newInstanceButtonActionPerformed
		// test
		ProcessListModel model = (ProcessListModel) processList.getModel();
		HES_Instanz instanz = new HES_Instanz("HES", true);
		model.add(instanz);
		processList.setSelectedValue(instanz, rootPaneCheckingEnabled);
	}// GEN-LAST:event_newInstanceButtonActionPerformed

	private void processListValueChanged(
			javax.swing.event.ListSelectionEvent evt) {// GEN-FIRST:event_processListValueChanged
		if (!changeStateButton.isEnabled())
			changeStateButton.setEnabled(true);

		HES_Instanz instanz = (HES_Instanz) processList.getSelectedValue();
		instanceNameLabel.setText(instanz.getName());
		changeStateButton.setSelected(instanz.getState());
		if (changeStateButton.isSelected())
			changeStateButton.setText("ON");
		else
			changeStateButton.setText("OFF");
	}// GEN-LAST:event_processListValueChanged

	private void changeStateButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_changeStateButtonActionPerformed
		HES_Instanz instanz = (HES_Instanz) processList.getSelectedValue();

		if (changeStateButton.isSelected()) {
			changeStateButton.setSelected(true);
			changeStateButton.setText("ON");
			instanz.setState(true);
			processList.setSelectedValue(instanz, rootPaneCheckingEnabled);
		} else {
			changeStateButton.setSelected(false);
			changeStateButton.setText("OFF");
			instanz.setState(false);
			processList.setSelectedValue(instanz, rootPaneCheckingEnabled);
		}
	}// GEN-LAST:event_changeStateButtonActionPerformed

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Dashboard_GUI.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Dashboard_GUI.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Dashboard_GUI.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Dashboard_GUI.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new Dashboard_GUI().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JToggleButton changeStateButton;
	private javax.swing.JLabel instanceNameLabel;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JButton newInstanceButton;
	private javax.swing.JList processList;
	// End of variables declaration//GEN-END:variables
}
