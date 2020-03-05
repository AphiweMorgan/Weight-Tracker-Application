package example.weight.tracker.ui;

import java.awt.BasicStroke;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.jdbc.JDBCCategoryDataset;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.Timer;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

public class HomeView extends JFrame {

	private JPanel contentPane;
	
	private JLabel dateLab;
	private JLabel timeLab;
	
	private JFrame frmLoginSystem;
	private JTextField showDate;
	
	private JDateChooser dateChooser;
	private JTextField txtWeight;
	private JTextField txtClientID;
	
	static Statement myStat;
	static HomeView frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new HomeView();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
	void showTime() {
		// TODO Auto-generated method stub
		
	  new Timer(0, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Date d = new Date();
			SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a");
			timeLab.setText(s.format(d));
			
		}}).start();
	} 

	void showDate() {
		// TODO Auto-generated method stub
		
		Date d = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		dateLab.setText(s.format(d));		
	}

	/**
	 * Create the frame.
	 */
	public HomeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(420, 280, 729, 456);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblWelcome = new JLabel("WELCOME BACK  .....");
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 23));
		lblWelcome.setBounds(90, 11, 359, 34);
		contentPane.add(lblWelcome);
		
		JLabel lblNewLabel_1 = new JLabel(LoginView.UserName);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1.setBounds(332, 11, 233, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblFetchDate = new JLabel("Todays Date: ");
		lblFetchDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFetchDate.setBounds(363, 75, 96, 14);
		contentPane.add(lblFetchDate);
		
		JLabel lblNewLabel_3 = new JLabel("Week");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(597, 75, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");			
				showDate.setText(df.format(dateChooser.getDate()));
				
				
				//upload client  Weight details to database
				PreparedStatement st;
				int rs;
				String query = "INSERT INTO `weight`(`idClient`, `Date`, `Weight`) VALUE(?, ?, ?)";
	
				//Client data
				Double Weight = Double.parseDouble(txtWeight.getText());
			
				
				try {
                    Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weighttrackingdb", "root", "Aphiwe@2174");

					st = myConn.prepareStatement(query);
					st.setString(1, txtClientID.getText());
					st.setString(2, showDate.getText());
					st.setDouble(3, Weight);
						
					rs = st.executeUpdate();
					
					//Clear All Text boxes
					clearFields();
					
					JOptionPane.showMessageDialog(null, "Success! Your body weight information has been captured correctly ");
									
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}

			private void clearFields() {
				
				txtWeight.setText(null);
				txtClientID.setText(null);
				dateChooser.cleanup();
				
			}
		});
		btnConfirm.setBounds(90, 365, 89, 23);
		contentPane.add(btnConfirm);
		
		JButton btnCancel = new JButton("Close");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLoginSystem = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frmLoginSystem, "Are you sure you want to  close capturing!", "Login Systems",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					
				System.exit(0);
			}
		}});
		btnCancel.setBounds(260, 365, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel lblNewLabel_4 = new JLabel("Date Capturing For: *");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(50, 183, 187, 28);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Enter Your Most Recent Body Weight: ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(50, 237, 299, 34);
		contentPane.add(lblNewLabel_5);
		
		dateLab = new JLabel("Date");
		dateLab.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		dateLab.setBounds(362, 100, 246, 28);
		contentPane.add(dateLab);
		
		timeLab = new JLabel("Time");
		timeLab.setFont(new Font("Tahoma", Font.BOLD, 14));
		timeLab.setBounds(597, 109, 106, 14);
		contentPane.add(timeLab);
		
		JLabel lblWeek = new JLabel("");
		lblWeek.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWeek.setBounds(642, 74, 46, 14);
		contentPane.add(lblWeek);
		
		setUndecorated(true);
		
		showDate();
		showTime();
		
		//Capture The Current Week
		Calendar cal = new GregorianCalendar();
		int day=cal.get(cal.WEEK_OF_YEAR);
		
		lblWeek.setText(""+day+"");
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(334, 191, 125, 20);
		contentPane.add(dateChooser);
		
		showDate = new JTextField();
		showDate.setForeground(Color.BLUE);
		showDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		showDate.setBounds(733, 446, -14, -1);
		contentPane.add(showDate);
		showDate.setColumns(10);
		
		txtWeight = new JTextField();
		txtWeight.setBounds(334, 246, 125, 20);
		contentPane.add(txtWeight);
		txtWeight.setColumns(10);
		
		JLabel lblClientId = new JLabel("Client ID:");
		lblClientId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblClientId.setBounds(50, 136, 169, 28);
		contentPane.add(lblClientId);
		
		txtClientID = new JTextField();
		txtClientID.setEditable(false);
		txtClientID.setBounds(332, 139, 127, 20);
		contentPane.add(txtClientID);
		txtClientID.setColumns(10);
		txtClientID.setText(""+ LoginView.CID);
		
		JLabel lblNewLabel_6 = new JLabel("Kg");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(469, 241, 46, 28);
		contentPane.add(lblNewLabel_6);
		
		JButton btnGraph = new JButton("Proceed to Graphical Visualization");
		btnGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weighttrackingdb", "root", "Aphiwe@2174");
					
					String query = " select Date,Weight from weight WHERE  idClient =" + LoginView.CID;
					JDBCCategoryDataset dataset = new JDBCCategoryDataset(myConn,query);
					dataset.executeQuery(query);
					
					JFreeChart chart = ChartFactory.createLineChart("Line Graph of Date versus Weight", "Date", "Weight", dataset, PlotOrientation.VERTICAL,true, true,true );
					
					
					CategoryPlot plot = chart.getCategoryPlot();
					LineAndShapeRenderer renderer = new LineAndShapeRenderer();
					plot.setRenderer(renderer);
					
					//Setting custom paint color and stroke for the plot�s outlines (chart borders):
					plot.setOutlinePaint(Color.BLUE);
					plot.setOutlineStroke(new BasicStroke(2.0f));
					
					//Setting background color for the plot:
					plot.setBackgroundPaint(Color.CYAN);
					
					//Setting visibility and paint color for the grid lines
					plot.setRangeGridlinesVisible(true);
					plot.setRangeGridlinePaint(Color.BLACK);

					plot.setDomainGridlinesVisible(true);
					plot.setDomainGridlinePaint(Color.BLACK);
					
					
					
					ChartFrame frame = new ChartFrame("Line Graph of Date versus Weight", chart);
					frame.setVisible(true);
					frame.setSize(400,650);
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnGraph.setBounds(452, 365, 236, 23);
		contentPane.add(btnGraph);
		
		setVisible(true);	
	}
}
