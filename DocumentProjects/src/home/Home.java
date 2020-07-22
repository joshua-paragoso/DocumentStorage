package home;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
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
import javax.swing.JCheckBox;
import javax.swing.JTextPane;

public class Home extends JFrame {
	
	//JFile Choosers
	private final JFileChooser openFileChooser; 
	private BufferedImage originalBI;
	private JTable table;

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
		
		String [] column = {"FileName"};
		JScrollPane scrollPane = new JScrollPane();
		
		openFileChooser = new JFileChooser();
		openFileChooser.setCurrentDirectory(new File("/Users/joshypuu/Desktop"));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(null);
		
		JLabel messageLabel = new JLabel(" ");
		messageLabel.setBounds(135, 23, 285, 24);
		getContentPane().add(messageLabel);
		
		JButton btnOpenFile = new JButton("Upload file");
		btnOpenFile.addActionListener(new ActionListener() {
			
			/*
			 * when btnOpenFile is pressed
			 */
			public void actionPerformed(ActionEvent e) {
				int returnValue = openFileChooser.showOpenDialog(null);
				//scrollPane for JTable
				
				scrollPane.setBounds(45, 63, 359, 212);
				getContentPane().add(scrollPane);
				//if returnValue is true
				if(returnValue == JFileChooser.APPROVE_OPTION) { 
					try {
						//find file
						originalBI = ImageIO.read(openFileChooser.getSelectedFile());
						
						//print out that file was found
						messageLabel.setText(openFileChooser.getSelectedFile().getName() + " successfully loaded");
						Object[][] data = {
					            {openFileChooser.getSelectedFile().getName() }
					            };
						//JTable
						table = new JTable(data, column);
						scrollPane.setViewportView(table);
						
					}catch (IOException ioe) {
						//
						messageLabel.setText("File find fail");
					}
				}else {
					messageLabel.setText("No file chosen!");
				}
			}
		});
		
		scrollPane.setViewportView(table);
		
		btnOpenFile.setBounds(6, 22, 117, 29);
		getContentPane().add(btnOpenFile);
			
	}
}