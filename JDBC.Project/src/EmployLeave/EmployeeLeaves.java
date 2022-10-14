package EmployLeave;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class EmployeeLeaves extends JFrame {

	private JPanel contentPane;
	private JTextField yeart;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeLeaves frame = new EmployeeLeaves();
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
	public EmployeeLeaves() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 399);
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel casualLeavel = new JLabel("Casual Leave :");
		casualLeavel.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		casualLeavel.setForeground(Color.WHITE);
		casualLeavel.setBounds(52, 52, 140, 42);
		contentPane.add(casualLeavel);
		
		JLabel annualLeavel = new JLabel("Annual Leave :");
		annualLeavel.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		annualLeavel.setForeground(Color.WHITE);
		annualLeavel.setBounds(52, 105, 140, 42);
		contentPane.add(annualLeavel);
		
		JLabel medicalLeavel = new JLabel("Medical Leave :");
		medicalLeavel.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		medicalLeavel.setForeground(Color.WHITE);
		medicalLeavel.setBounds(52, 157, 140, 42);
		contentPane.add(medicalLeavel);
		
		JLabel yearl = new JLabel("Year :");
		yearl.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		yearl.setForeground(Color.WHITE);
		yearl.setBounds(52, 211, 140, 42);
		contentPane.add(yearl);
		
		JSpinner casualLeaves = new JSpinner();
		casualLeaves.setBounds(221, 61, 81, 26);
		contentPane.add(casualLeaves);
		
		JSpinner annualLeaves = new JSpinner();
		annualLeaves.setBounds(221, 120, 81, 26);
		contentPane.add(annualLeaves);
		
		JSpinner medicalLeaves = new JSpinner();
		medicalLeaves.setBounds(221, 172, 81, 26);
		contentPane.add(medicalLeaves);
		
		yeart = new JTextField();
		yeart.setBounds(202, 220, 115, 32);
		contentPane.add(yeart);
		yeart.setColumns(10);
		
		JButton okb = new JButton("OK");
		okb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String casual =  casualLeaves.getValue().toString();
				String annual = annualLeaves.getValue().toString();
				String medical = medicalLeaves.getValue().toString();
				String year = yeart.getText();
				
				try {
					PreparedStatement stmt = ConnectionProvider.creatConnection().prepareStatement("select eid from employees");
					ResultSet rs = stmt.executeQuery();
					
					while (rs.next()) {
					PreparedStatement pstmt = ConnectionProvider.creatConnection().prepareStatement("insert into leaves (casual,annual,medical,year,id) values (?,?,?,?,?)");
					pstmt.setString(1,casual) ;
					pstmt.setString(2,annual) ;
					pstmt.setString(3,medical) ;
					pstmt.setString(4,year) ;
					pstmt.setInt(5,rs.getInt(1)) ;
					pstmt.executeUpdate();
					}
					
					ConnectionProvider.creatConnection().close();
					rs.close();
					
					JOptionPane.showMessageDialog(null, "Leave data added.....");
					
				} catch (SQLException e1) {
					System.out.println(e1);
				}
				
				
			}
		});
		okb.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		okb.setBounds(69, 281, 102, 42);
		contentPane.add(okb);
		
		JButton cancleb = new JButton("Cancle");
		cancleb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(4);
			}
		});
		cancleb.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		cancleb.setBounds(202, 281, 102, 42);
		contentPane.add(cancleb);
	}
}
