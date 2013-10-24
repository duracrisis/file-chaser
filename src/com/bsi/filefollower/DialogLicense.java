package com.bsi.filefollower;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JFrame;

public class DialogLicense extends JInternalFrame {
	private JScrollPane scrollPane;
	private JTextPane txtpn_TheMitLicense;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DialogLicense frame = new DialogLicense();
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
	public DialogLicense() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setResizable(true);
		setIconifiable(true);
		setTitle("License");
		setBounds(100, 100, 546, 376);
		getContentPane().setLayout(new CardLayout(0, 0));
		getContentPane().add(getScrollPane(), "name_84357927507705");

	}
	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTxtpn_TheMitLicense());
		}
		return scrollPane;
	}
	public JTextPane getTxtpn_TheMitLicense() {
		if (txtpn_TheMitLicense == null) {
			txtpn_TheMitLicense = new JTextPane();
			txtpn_TheMitLicense.setEditable(false);
			txtpn_TheMitLicense.setText("The MIT License (MIT)\r\n\r\nCopyright (c) 2013 Fernand Boudreau\r\n\r\nPermission is hereby granted, free of charge, to any person obtaining a copy of\r\nthis software and associated documentation files (the \"Software\"), to deal in\r\nthe Software without restriction, including without limitation the rights to\r\nuse, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of\r\nthe Software, and to permit persons to whom the Software is furnished to do so,\r\nsubject to the following conditions:\r\n\r\nThe above copyright notice and this permission notice shall be included in all\r\ncopies or substantial portions of the Software.\r\n\r\nTHE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\r\nIMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS\r\nFOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR\r\nCOPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER\r\nIN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN\r\nCONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.");
		}
		return txtpn_TheMitLicense;
	}
}
