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

public class login {

	private JFrame frame;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
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
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(198, 24, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(89, 116, 73, 16);
		frame.getContentPane().add(lblUsername);
		
		username = new JTextField();
		username.setBounds(161, 111, 130, 26);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(89, 167, 73, 21);
		frame.getContentPane().add(lblPassword);
	    
		password = new JPasswordField();
		password.setBounds(161, 164, 130, 26);
		frame.getContentPane().add(password);
		password.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if( username.getText().isEmpty() || password.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter username and password");
				}else if(username.getText().equals("admin") && password.getText().contentEquals("12345")){
					JOptionPane.showMessageDialog(null, "login success");
				}else {
					JOptionPane.showMessageDialog(null, "login failed. Retry");
				}
			} 
		
		});
		btnLogin.setBounds(161, 221, 117, 29);
		frame.getContentPane().add(btnLogin);
	}
}
