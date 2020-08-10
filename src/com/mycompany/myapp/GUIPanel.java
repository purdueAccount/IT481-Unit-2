package com.mycompany.myapp;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class GUIPanel extends JPanel implements Runnable {
	
	// constant
	public static final String NAME = "IT481 Unit2 Northwind Database";
	public static final int WIDTH  = 531;
	public static final int HEIGHT = 271;
	
	// field
	private Thread thread;
	private Controller database;
	
	// constructor
	public GUIPanel() {
		super();
		setPreferredSize(new Dimension(GUIPanel.WIDTH, GUIPanel.HEIGHT));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		setFocusable(true);
		requestFocus();
	} // constructor
	
	// thread
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	} // addNotify
	
	// run
	public void run() {
		
		int row = 50;
		
		// labels
		JLabel lblConnect = new JLabel("Connect");
		lblConnect.setBounds(15, (row * 1) + 2, 57, 20);
		add(lblConnect);
		
		JLabel lblCount = new JLabel("Count");
		lblCount.setBounds(15, (row * 2) + 2, 57, 20);
		add(lblCount);
		
		JLabel lblNames = new JLabel("Names");
		lblNames.setBounds(15, (row * 3) + 2, 57, 20);
		add(lblNames);

		// buttons
		JButton btnConnect = new JButton("Connect to DB");
		btnConnect.setBounds(115, (row * 1) - 1, 387, 26);
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String dbURL = 
							"jdbc:sqlserver://DESKTOP-BASE101\\SQLEXPRESS:1433;"
							+"database=Northwind;"
							+"encrypt=false;"
							+"trustServerCertificate=false;"
							+"loginTimeout=10;";
					String user = "sa";
					String pass = "123456";
					database = new Controller(dbURL, user, pass);
					JOptionPane.showMessageDialog(btnConnect, "Connected", "Connected to DB", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(btnConnect, e.getMessage());
				}
			}
		});
		add(btnConnect);
		
		JButton btnCount = new JButton("Customer Count");
		btnCount.setBounds(115, (row * 2) - 1, 387, 26);
		btnCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					if(database != null) {
						String msg = database.getCustomerCount();
						JOptionPane.showMessageDialog(btnCount, msg, "Customer Count", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(btnCount, "Please Connect to DB first!", "The Customer Count", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(btnCount, e.getMessage());
				}
			}
		});
		add(btnCount);

		JButton btnNames = new JButton("Customer Names");
		btnNames.setBounds(115, (row * 3) - 1, 387, 26);
		btnNames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					if(database != null) {
						String msg = database.getCustomerNames();
						JOptionPane.showMessageDialog(btnNames, msg, "Customer Names", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(btnNames, "Please Connect to DB first!", "Customer Names", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(btnNames, e.getMessage());
				}
			}
		});
		add(btnNames);
		
		// exit
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(315, (row * 4) - 1, 187, 26);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				System.exit(0);
			}
		});
		add(btnExit);
		
	} // run
	
	
} // GUIPanel
