package com.bsi.filefollower;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.SwingConstants;

public class DialogAbout extends JInternalFrame {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JLabel label_about;
	private JLabel lbl_copyright;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogAbout dialog = new DialogAbout();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogAbout() {
		setTitle("About");
		setBounds(100, 100, 280, 214);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(127)
					.addComponent(getLabel_about())
					.addContainerGap(127, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(37)
					.addComponent(getLbl_copyright(), GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(35))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(getLabel_about())
					.addGap(18)
					.addComponent(getLbl_copyright(), GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			buttonPane.add(okButton);
		}
	}
	public JLabel getLabel_about() {
		if (label_about == null) {
			label_about = new JLabel("");
		}
		return label_about;
	}
	public JLabel getLbl_copyright() {
		if (lbl_copyright == null) {
			lbl_copyright = new JLabel("<html>\r\n<center>\r\n<p>\r\nCopyright (c) Fernand Boudreau 2013\r\n</p>\r\n<p>\r\nfernand.boudreau@gmail.com\r\n</p>\r\n</center>\r\n</html>");
			lbl_copyright.setVerticalAlignment(SwingConstants.TOP);
		}
		return lbl_copyright;
	}
}
