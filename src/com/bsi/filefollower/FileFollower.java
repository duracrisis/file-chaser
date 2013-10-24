package com.bsi.filefollower;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;


public class FileFollower extends JInternalFrame {
	private JScrollPane scrollPane;
	private JTextPane textPane;
	private JMenuBar menuBar;
	private JMenu mnOptions;
	private JMenuItem mntmSuspend;
	private JMenuItem mntmResume;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileFollower frame = new FileFollower();
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
	public FileFollower() {
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 557, 389);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getScrollPane(), BorderLayout.CENTER);
		setJMenuBar(getMenuBar_1());
//		getContentPane().add(getTextPane(), BorderLayout.NORTH);

	}

	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane(getTextPane());
		}
		return scrollPane;
	}
	public JTextPane getTextPane() {
		if (textPane == null) {
			textPane = new JTextPane();
			textPane.setContentType("text/html");
		}
		return textPane;
	}
	public JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnOptions());
		}
		return menuBar;
	}
	public JMenu getMnOptions() {
		if (mnOptions == null) {
			mnOptions = new JMenu("Options");
			mnOptions.add(getMntmSuspend());
			mnOptions.add(getMntmResume());
		}
		return mnOptions;
	}
	
	
	public JMenuItem getMntmSuspend() {
		if (mntmSuspend == null) {
			mntmSuspend = new JMenuItem("Suspend");
			mntmSuspend.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		}
		return mntmSuspend;
	}
	public JMenuItem getMntmResume() {
		if (mntmResume == null) {
			mntmResume = new JMenuItem("Resume");
			mntmResume.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		}
		return mntmResume;
	}
}
