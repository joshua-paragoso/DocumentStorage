package login;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import home.Home;


public class Login {

	private JFrame frame;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			/*
			 * shows the login in frame
			 */
			public void run() {
				try {
					//Initialize Login window
					Login window = new Login();
					
					//Set login window to visible
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//Initialize JFrame
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Login label
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(198, 24, 61, 16);
		frame.getContentPane().add(lblLogin);
		
		//Username label
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(89, 116, 73, 16);
		frame.getContentPane().add(lblUsername);
		
		//Username textField
		username = new JTextField();
		username.setBounds(161, 111, 130, 26);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		//Password label
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(89, 167, 73, 21);
		frame.getContentPane().add(lblPassword);
	    
		//PasswordField
		password = new JPasswordField();
		password.setBounds(161, 164, 130, 26);
		frame.getContentPane().add(password);
		password.setColumns(10);
		
		//Login button
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//if username space is empty
				if( username.getText().isEmpty() || password.getText().isEmpty()) {
					//display message
					JOptionPane.showMessageDialog(null, "Please enter username and password");
				
				//if username and password match
				}else if(username.getText().equals("joshua") && password.getText().contentEquals("gears114")){
					//display success message
					JOptionPane.showMessageDialog(null, "login success");
					
					//initialize Home object
					Home home = new Home();
					
					//set Home window to visible 
					home.setVisible(true);
				    setVisble(false);
				     			
				}else {
					//display login failure message
					JOptionPane.showMessageDialog(null, "login failed. Retry");
				}
			}
  
			private void setVisble(boolean b) {
			} 
		
		});
		
		//set bounds 
		btnLogin.setBounds(161, 221, 117, 29);
		frame.getContentPane().add(btnLogin);
	}
}
