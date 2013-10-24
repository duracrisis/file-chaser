package com.bsi.filefollower;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.SwingConstants;
import java.awt.ComponentOrientation;


public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktop;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmOpen;
	private JSeparator separator;
	private JMenuItem mntmExit;
	private JMenu mnHelp;
	private JMenuItem mntmAbout;
	private JMenu mnWindow;
	private JMenuItem mntmCascade;
	private JMenuItem mntmTile;
	private JSeparator separator_1;
	private JMenu mnEdit;
	private JMenuItem mntmPreferences;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1021, 710);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setJMenuBar(getMenuBar_1());
		desktop = new JDesktopPane();
		setContentPane(desktop);
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
			menuBar.add(getMnEdit());
			menuBar.add(getMnWindow());
			menuBar.add(Box.createHorizontalGlue()); // move help menu to the right.
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}
	public JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.add(getMntmOpen());
			mnFile.add(getSeparator());
			mnFile.add(getMntmExit());
		}
		return mnFile;
	}
	public JMenuItem getMntmOpen() {
		if (mntmOpen == null) {
			mntmOpen = new JMenuItem("Open ...");
			mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		}
		return mntmOpen;
	}
	public JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	public JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		}
		return mntmExit;
	}
	public JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
//			mnHelp.setHorizontalAlignment(SwingConstants.LEFT);
			mnHelp.add(getMntmAbout());
		}
		return mnHelp;
	}
	public JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		}
		return mntmAbout;
	}
	public JMenu getMnWindow() {
		if (mnWindow == null) {
			mnWindow = new JMenu("Window");
			mnWindow.add(getMntmCascade());
			mnWindow.add(getMntmTile());
			mnWindow.add(getSeparator_1());
		}
		return mnWindow;
	}
	public JMenuItem getMntmCascade() {
		if (mntmCascade == null) {
			mntmCascade = new JMenuItem("Cascade");
		}
		return mntmCascade;
	}
	public JMenuItem getMntmTile() {
		if (mntmTile == null) {
			mntmTile = new JMenuItem("Tile");
		}
		return mntmTile;
	}
	public JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	
	public JDesktopPane getDesktop(){
		return desktop;
	}
	public JMenu getMnEdit() {
		if (mnEdit == null) {
			mnEdit = new JMenu("Edit");
			mnEdit.add(getMntmPreferences());
		}
		return mnEdit;
	}
	public JMenuItem getMntmPreferences() {
		if (mntmPreferences == null) {
			mntmPreferences = new JMenuItem("Preferences");
		}
		return mntmPreferences;
	}
}
