package example.weight.tracker.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jfree.data.jdbc.JDBCCategoryDataset;


import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginView extends JDialog {

	private static JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JFrame frmLoginSystem;
	
	private JPanel contentPane;

	
	static Statement myStat;
	
	static LoginView window;
	
	public static int CID;
	public static String UserName;
	
	static Connection myConn;	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 window = new LoginView();

					window.frame.setUndecorated(true);
					frame.setLocationRelativeTo(null);
					window.frame.setVisible(true);		
					
                     //Create connection to my local database 
					 myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weighttrackingdb", "root", "Aphiwe@2174");
					 myStat = myConn.createStatement();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginView() {
		initialize();	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setSize(new Dimension(600, 400));
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBackground(new Color(0, 191, 255));
		frame.setBounds(300, 180, 629, 356);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		JLabel lblNewLabel = new JLabel("Weight Tracking Application");
		lblNewLabel.setForeground(new Color(248, 248, 255));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(215, 11, 381, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Dialog", Font.BOLD, 18));
		lblUsername.setBounds(54, 69, 118, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPassword.setBounds(54, 158, 118, 14);
		frame.getContentPane().add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Dialog", Font.BOLD, 18));
		txtUsername.setBounds(237, 66, 323, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Dialog", Font.BOLD, 18));
		txtPassword.setBounds(237, 158, 323, 25);
		frame.getContentPane().add(txtPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBackground(Color.GREEN);
		btnLogin.setFont(new Font("Dialog", Font.BOLD, 18));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String password = txtPassword.getText();
				String username = txtUsername.getText();
				try {
					
				
					ResultSet myRs = myStat.executeQuery("select * from client");
					
					while (myRs.next()) {
					
					
					if (password.contains(myRs.getString("Passcode")) && username.contains(myRs.getString("Name"))){
						txtPassword.setText(null);
						txtUsername.setText(null);
						JOptionPane.showMessageDialog(null, "Success! Login Created!\nTransferring you to Home screen...");
						
						CID = myRs.getInt("idClient");
						UserName = myRs.getString("Name");
						
						//create object for the home-screen
						HomeView Welcome = new HomeView();
						window.frame.setVisible(false);
						Welcome.setVisible(true);
						dispose();
						break;
			        }
					
					else if(!password.contains(myRs.getString("Passcode")) || !username.contains(myRs.getString("Name"))){
						if (myRs.isLast()) {
							JOptionPane.showMessageDialog(null, "Invalid Login Details","Login Error",JOptionPane.ERROR_MESSAGE);
							txtPassword.setText(null);
							txtUsername.setText(null);
							break;
						}
						else
						continue;		
					}
					
				}
										
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnLogin.setBounds(103, 227, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Dialog", Font.BOLD, 18));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				//create SignUp object
				SignUpView Register = new SignUpView();
				window.frame.setVisible(false);
				Register.setVisible(true);
				dispose();
			}
		});
		btnRegister.setBounds(265, 227, 118, 23);
		frame.getContentPane().add(btnRegister);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setForeground(Color.BLACK);
		btnExit.setBackground(Color.RED);
		btnExit.setFont(new Font("Dialog", Font.BOLD, 18));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLoginSystem = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frmLoginSystem, "Confirm if you want to exit", "Login Systems",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setBounds(472, 227, 89, 23);
		frame.getContentPane().add(btnExit);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(28, 208, 564, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(28, 47, 568, 2);
		frame.getContentPane().add(separator_1);
		
		JLabel user = new JLabel("");
		user.setIcon(new ImageIcon(getClass().getClassLoader().getResource("profile1.png")));
		user.setBounds(154, 55, 57, 44);
		frame.getContentPane().add(user);
		
		JLabel pass = new JLabel("");
		pass.setIcon(new ImageIcon(getClass().getClassLoader().getResource("lock1.jpg")));
		pass.setBounds(154, 144, 67, 33);
		frame.getContentPane().add(pass);
		
		JLabel lblClose = new JLabel("");
		lblClose.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Button-Close-icon.png")));
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				System.exit(0);
			}
		});
		lblClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblClose.setForeground(Color.WHITE);
		lblClose.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setBounds(589, 0, 40, 20);
		frame.getContentPane().add(lblClose);	
	}
}