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

public class Home extends JFrame {
	
	//JFile Choosers
	private final JFileChooser openFileChooser; 
	private BufferedImage originalBI;

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
		
		openFileChooser = new JFileChooser();
		openFileChooser.setCurrentDirectory(new File("/Users/joshypuu"));
//		openFileChooser.setFileFilter(new FileNameExtensionFilter("PNG images, png"));
//		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(null);
		
		JLabel messageLabel = new JLabel("New label");
		messageLabel.setBounds(135, 23, 285, 24);
		getContentPane().add(messageLabel);
		
		JButton btnOpenFile = new JButton("Open file");
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnValue = openFileChooser.showOpenDialog(null);
				
				if(returnValue == JFileChooser.APPROVE_OPTION) { 
					try {
						originalBI = ImageIO.read(openFileChooser.getSelectedFile());
						messageLabel.setText("Image file successfully loaded");
					}catch (IOException ioe) {
						messageLabel.setText("File find fail");
					}
				}else {
					messageLabel.setText("No file chosen!");
				}
			}
		});
		btnOpenFile.setBounds(6, 22, 117, 29);
		getContentPane().add(btnOpenFile);
		
		
	}
}