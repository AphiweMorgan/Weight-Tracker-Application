package example.weight.tracker.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class SignUpView extends JFrame {

	private JPanel contentPane;
	private JTextField textName;
	private JTextField textSurname;
	private JTextField textPhone_No;
	private JTextField textHealthRating;
	private JTextField textPassword;
	private JTextField textReType_Password;
	
	static Statement myStat;
	
	private JFrame frmLoginSystem;
	
	static SignUpView frameOne;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				    frameOne = new SignUpView();
				    frameOne.setLocationRelativeTo(null);
					frameOne.setVisible(true);
					
                    Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weighttrackingdb", "root", "Aphiwe@2174");					
					myStat = myConn.createStatement();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignUpView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(420, 280, 729, 456);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to Weight Tracker Registration ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel.setBounds(155, 11, 467, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New Client Details");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(34, 76, 161, 20);
		contentPane.add(lblNewLabel_1);
		
		textName = new JTextField();
		textName.setBounds(166, 139, 284, 20);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textSurname = new JTextField();
		textSurname.setBounds(166, 184, 284, 20);
		contentPane.add(textSurname);
		textSurname.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(34, 142, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Surname");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(34, 187, 105, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Phone-Number");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(34, 238, 122, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Health-Rating (1-10)");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(34, 275, 136, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Password");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(34, 318, 105, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Re-enter Password");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_7.setBounds(30, 356, 126, 14);
		contentPane.add(lblNewLabel_7);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.setBackground(new Color(0, 255, 255));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//upload client details to database
				PreparedStatement st;
				int rs;
				String query = "INSERT INTO `client`(`Name`, `Surname`, `Phone_No`, `HealthRating`,`Passcode`) VALUE(?, ?, ?, ?, ?)";

				
				//Client data
				Integer PhoneNumber = Integer.parseInt(textPhone_No.getText());
				Double HealthRating = Double.parseDouble(textHealthRating.getText());			
				
				try {
					
                    Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weighttrackingdb", "root", "Aphiwe@2174");

					st = myConn.prepareStatement(query);
					st.setString(1, textName.getText());
					st.setString(2, textSurname.getText());
					st.setLong(3, PhoneNumber);
					st.setDouble(4, HealthRating);
					st.setString(5, textPassword.getText());
									
					rs = st.executeUpdate();
					
					//Clear All Text boxes
					clearFields();
					
					JOptionPane.showMessageDialog(null, "Success! Profile created.");
					
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
				}
		});
		btnSubmit.setBounds(61, 411, 89, 23);
		contentPane.add(btnSubmit);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Agree to the Terms And Conditions");
		chckbxNewCheckBox.setBounds(40, 377, 309, 23);
		contentPane.add(chckbxNewCheckBox);
		
		textPhone_No = new JTextField();
		textPhone_No.setColumns(10);
		textPhone_No.setBounds(166, 235, 284, 20);
		contentPane.add(textPhone_No);
		
		textHealthRating = new JTextField();
		textHealthRating.setColumns(10);
		textHealthRating.setBounds(166, 272, 284, 20);
		contentPane.add(textHealthRating);
		
		textPassword = new JPasswordField();
		textPassword.setColumns(10);
		textPassword.setBounds(166, 315, 284, 20);
		contentPane.add(textPassword);
		
		textReType_Password = new JPasswordField();
		textReType_Password.setColumns(10);
		textReType_Password.setBounds(166, 353, 284, 20);
		contentPane.add(textReType_Password);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.setBackground(new Color(0, 255, 255));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLoginSystem = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frmLoginSystem, "Confirm if you want to cancel Registration!", "Login Systems",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					
					
				//	NEED TO TRY AND FIX THIS TO ALLOW ME TO GO BACK TO LOGIN SCREEN
				  LoginView m = new LoginView();
					frameOne.setVisible(false);
					m.setVisible(true);
					dispose();
					
					System.exit(0);
					
				}
			}
		});
		btnCancel.setBounds(205, 411, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnHome = new JButton("HOME");
		btnHome.setBackground(new Color(0, 255, 255));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection myConn;
				try {
					myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weighttrackingdb", "root", "Aphiwe@2174");
					
					Statement myStat = myConn.createStatement();
					
					ResultSet myRs = myStat.executeQuery("select * from client");
					
					while (myRs.next()) {
						if(myRs.isLast())
						LoginView.CID = myRs.getInt("idClient");
						LoginView.UserName =  myRs.getString("Name");
					}
								
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//create object
				HomeView Welcome = new HomeView();
				frameOne.setVisible(false);
				Welcome.setVisible(true);
				dispose();
			}
		});
		btnHome.setBounds(344, 411, 89, 23);
		contentPane.add(btnHome);
		
		setUndecorated(true);
		
	}
	//set all fields to empty null
	 private void clearFields() {
		     textName.setText(null);;
			 textSurname.setText(null);;
			 textPhone_No.setText(null);;
			 textHealthRating.setText(null);;
			 textPassword.setText(null);;
			 textReType_Password.setText(null);;
				
			}
}