package home;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import login.Login;

import javax.swing.JTextField;
import java.awt.Scrollbar;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import java.awt.List;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.Box;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Home extends JFrame {
	
	//JFile Choosers
	private final JFileChooser openFileChooser; 
	private BufferedImage originalBI;
	private JTable table;
	private JFrame frame;

	public static String driver = "com.mysql.cj.jdbc.Driver"; 
	public static String url = "jdbc:mysql://localhost:3306/FILES?autoReconnect=true&useSSL=false";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		

		//JScrollPane 
		JScrollPane scrollPane = new JScrollPane();
		
		//Default Table Model
		DefaultTableModel tableModel = new DefaultTableModel();
		
		//Label tableModel column as FileName
		tableModel.addColumn("FileName");
		
		//Display empty table
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		//Create JFileChooser
		openFileChooser = new JFileChooser();
		
		//Set directory to Desktop
		openFileChooser.setCurrentDirectory(new File("/Users/joshypuu/Desktop"));

		//Set bounds of
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(null);
		
		//Display label which will indicate what file will be uploaded
		JLabel messageLabel = new JLabel(" ");
		messageLabel.setBounds(151, 23, 112, 24);
		getContentPane().add(messageLabel);
		
		//Upload button
		JButton btnUploadFile = new JButton("Upload file");
		btnUploadFile.addActionListener(new ActionListener() {
			
			/*
			 * when btnOpenFile is pressed
			 */
			public void actionPerformed(ActionEvent e) {
				int returnValue = openFileChooser.showOpenDialog(null);
				
				//if returnValue is true
				if(returnValue == JFileChooser.APPROVE_OPTION) { 
					try {
						//find file
						originalBI = ImageIO.read(openFileChooser.getSelectedFile());
						
						//print out that file was found
						messageLabel.setText(openFileChooser.getSelectedFile().getName() + " successfully loaded");
						
						//Added files name to data[][]
						tableModel.insertRow(0, new Object[] {openFileChooser.getSelectedFile().getName()});
						 
						//scrollPane for JTable
						scrollPane.setBounds(45, 63, 359, 212);
						getContentPane().add(scrollPane);
						 	
					}catch (IOException ioe) {
						//if file find fails
						messageLabel.setText("File find fail");
					}
				}else {
					//if no files were chosen
					messageLabel.setText("No file chosen!");
				}
			}
		});
		
		//Upload button
		btnUploadFile.setBounds(151, 22, 117, 29);
		getContentPane().add(btnUploadFile);
		
		//scrollPane for JTable
		scrollPane.setBounds(45, 63, 359, 240);
		getContentPane().add(scrollPane);
		
		JButton btnLogOut = new JButton("Log Out");
		
		btnLogOut.addActionListener(new ActionListener() {
			/*
			 * when btnOpenFile is pressed
			 */
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "logged out successful");
				System.out.println("login");
				//initialize Home object
				Login login = new Login();
				
				//set Home window to visible 
				login.setVisible(true);
				
				//close login window
				setVisble(false);
			}

			
		});
		btnLogOut.setBounds(151, 315, 112, 29);
		getContentPane().add(btnLogOut);
		
		
	}
	public void setVisble(boolean b) {}
}