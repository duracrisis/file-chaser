package com.bsi.filefollower;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JFrame;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JInternalFrame_Preferences extends JInternalFrame {
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JButton button_keyword_add;
	private JButton button_RemoveSelected;
	private JLabel lbl_KeywordOrRegular;
	private JPanel panel_2;
	private JButton button_Close;
	private JScrollPane scrollPane;
	private JTable table_keywords;
	private JPanel panel_1;
	private JLabel lbl_PollingPeriod;
	private JTextField textField;
	private JLabel lbl_Seconds;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JInternalFrame_Preferences frame = new JInternalFrame_Preferences();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JInternalFrame_Preferences() {
		setTitle("Preferences");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 634, 391);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getTabbedPane(), BorderLayout.CENTER);

	}

	public JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.addTab("General", null, getPanel_1(), null);
			tabbedPane.addTab("Keywords", null, getPanel(), null);
		}
		return tabbedPane;
	}
	public JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(getPanel_2(), Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 871, Short.MAX_VALUE)
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(getLbl_KeywordOrRegular())
									.addComponent(getScrollPane(), GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(getButton_keyword_add(), GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
									.addComponent(getButton_RemoveSelected(), GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap())
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(getLbl_KeywordOrRegular())
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(getButton_keyword_add())
								.addPreferredGap(ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
								.addComponent(getButton_RemoveSelected()))
							.addComponent(getScrollPane(), GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(getPanel_2(), GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
			);
			panel.setLayout(gl_panel);
		}
		return panel;
	}
	public JButton getButton_keyword_add() {
		if (button_keyword_add == null) {
			button_keyword_add = new JButton("Add");
		}
		return button_keyword_add;
	}
	public JButton getButton_RemoveSelected() {
		if (button_RemoveSelected == null) {
			button_RemoveSelected = new JButton("Remove Selected");
		}
		return button_RemoveSelected;
	}
	public JLabel getLbl_KeywordOrRegular() {
		if (lbl_KeywordOrRegular == null) {
			lbl_KeywordOrRegular = new JLabel("Keyword or Regular Expression");
		}
		return lbl_KeywordOrRegular;
	}
	public JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			GroupLayout gl_panel_2 = new GroupLayout(panel_2);
			gl_panel_2.setHorizontalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
						.addContainerGap(772, Short.MAX_VALUE)
						.addComponent(getButton_Close())
						.addContainerGap())
			);
			gl_panel_2.setVerticalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
						.addContainerGap(19, Short.MAX_VALUE)
						.addComponent(getButton_Close())
						.addContainerGap())
			);
			panel_2.setLayout(gl_panel_2);
		}
		return panel_2;
	}
	public JButton getButton_Close() {
		if (button_Close == null) {
			button_Close = new JButton("Close");
		}
		return button_Close;
	}
	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable_keywords());
		}
		return scrollPane;
	}
	public JTable getTable_keywords() {
		if (table_keywords == null) {
			table_keywords = new JTable();
			table_keywords.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Keywords", "Highlight Color"
				}
			));
			table_keywords.getColumnModel().getColumn(1).setPreferredWidth(94);
		}
		return table_keywords;
	}
	public JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			GroupLayout gl_panel_1 = new GroupLayout(panel_1);
			gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap()
						.addComponent(getLbl_PollingPeriod())
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(getTextField(), GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(getLbl_Seconds())
						.addContainerGap(445, Short.MAX_VALUE))
			);
			gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(getLbl_PollingPeriod())
							.addComponent(getTextField(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(getLbl_Seconds()))
						.addContainerGap(309, Short.MAX_VALUE))
			);
			panel_1.setLayout(gl_panel_1);
		}
		return panel_1;
	}
	public JLabel getLbl_PollingPeriod() {
		if (lbl_PollingPeriod == null) {
			lbl_PollingPeriod = new JLabel("Polling Period");
		}
		return lbl_PollingPeriod;
	}
	public JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setText("1");
			textField.setColumns(10);
		}
		return textField;
	}
	public JLabel getLbl_Seconds() {
		if (lbl_Seconds == null) {
			lbl_Seconds = new JLabel("second(s)");
		}
		return lbl_Seconds;
	}
}
