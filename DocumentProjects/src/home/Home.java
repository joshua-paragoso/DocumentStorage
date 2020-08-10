package home;

import login.Login;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.List;
import java.awt.Component;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Scrollbar;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JScrollBar;
import javax.swing.JTable;

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
	private static JFrame frame;

	public static String driver = "com.mysql.cj.jdbc.Driver"; 
	public static String url = "jdbc:mysql://localhost:3306/USERS?autoReconnect=true&useSSL=false";
    public String uname = "root";
    public String pword = "gears114";
    
	PreparedStatement ps;
	static Connection connection;
	
	String userBelongs; 
	static String name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			/**
			 * run home.java
			 */
			public void run() {
				try
				{
					
					//home object
					Home frame = new Home(name, connection);
					
					//set home page to visible
					frame.setVisible(true);
					
				}catch (Exception e) {
					
					e.printStackTrace();
				
				}
			}
		});
	}

	/**
	 * Create the frame for home
	 * @param connection2 
	 */
	public Home(String name, Connection connection) {
			
		setTitle(name + "'s files");
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
		

		try {
			
			//driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//sql command for finding files belonging to user
			String sql = "SELECT * FROM FILES WHERE userName =?";
			
			//prepared statment for sql command
			PreparedStatement statement = connection.prepareStatement(sql);
			
			//uName is name of user
			String uName = name;
			
			//set username
			statement.setString(1, uName);
			
			//ResultSet used fpr execution of query
			ResultSet rs = statement.executeQuery();
			
			//used for keeping track of the # of docuemnts user has on file
			int i = 0;
			
			while(rs.next()) {
				
				//fileName will be name of file
				String fileName = rs.getString("fileName");
				System.out.println(fileName);
				
				//increment i
				i++;
				
				//add row to table model
				tableModel.addRow(new Object[] {fileName});
			}
			System.out.println(i);
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Set bounds of
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(null);
		
		//Display label which will indicate what file will be uploaded
		JLabel messageLabel = new JLabel(" ");
		messageLabel.setBounds(109, 29, 227, 22);
		getContentPane().add(messageLabel);
		
		//Upload button
		JButton btnUploadFile = new JButton("Upload file");
		btnUploadFile.addActionListener(new ActionListener() {
			
			/**
			 * when btnOpenFile is pressed
			 */
			public void actionPerformed(ActionEvent e) {
				int returnValue = openFileChooser.showOpenDialog(null);
				 
				//if returnValue is true
				if(returnValue == JFileChooser.APPROVE_OPTION) { 
					try{
						
						//driver
						Class.forName("com.mysql.cj.jdbc.Driver");
				        
						//connection
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/USERS?autoReconnect=true&useSSL=false", "root", "gears114");
						
				        //find file
						originalBI = ImageIO.read(openFileChooser.getSelectedFile());
						
						//sql statement
						String sql = "INSERT INTO FILES (userName, fileName) VALUES (?, ?)";
						
						//prepared statment to execute sql command
						PreparedStatement statement = connection.prepareStatement(sql);
						
						//set username
						statement.setString(1, name);
						
						//set name of file
						statement.setString(2, openFileChooser.getSelectedFile().getName()); //NAME

						//row is 1 if prepared statement is successfully executed
						int rows = statement.executeUpdate();
						
						//if rows i 1, print successful row insert
						if(rows > 0) {
							
							System.out.println("A row has been inserted");
						
						}
						
						//print out that file was found
						messageLabel.setText(openFileChooser.getSelectedFile().getName() + " successfully loaded");
						
						System.out.println("File added");
						
						//Added filesNames to JTabel to be displayed
						tableModel.insertRow(0, new Object[] {openFileChooser.getSelectedFile().getName()});
						 
						//scrollPane for JTable
						scrollPane.setBounds(45, 63, 359, 212);
						getContentPane().add(scrollPane);
						 	
					}catch (IOException ioe) {
						
						//if file find fails
						messageLabel.setText("File find fail");
						
					} catch (ClassNotFoundException e1) {
						
						e1.printStackTrace();
						
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}else {
					
					//if no files were chosen
					messageLabel.setText("No file chosen!");
				
				}
			}
		});
		
		//Upload button
		btnUploadFile.setBounds(37, 315, 117, 29);
		getContentPane().add(btnUploadFile);
		
		//scrollPane for JTable
		scrollPane.setBounds(45, 63, 359, 240);
		getContentPane().add(scrollPane);
		
		//logOut button
		JButton btnLogOut = new JButton("Log Out");
		
		btnLogOut.addActionListener(new ActionListener() {
			
			/**
			 * actionPerformed when logout button is pressed
			 */
			public void actionPerformed(ActionEvent e) {
				
				//display message that user has logged out
				JOptionPane.showMessageDialog(null, "logged out successful");
				System.out.println("log out successfull");
				
				//close current window
				dispose();
				
				//initialize a Home object
				Login newLogin = new Login();
				
				//set new login window to visible 
				newLogin.frame.setVisible(true);
				
				//close login window
				setVisble(false);
			}

		});
		btnLogOut.setBounds(299, 315, 112, 29);
		getContentPane().add(btnLogOut);
		
	}

	/**
	 * set visible method
	 * @param b
	 */
	public void setVisble(boolean b) {}
}