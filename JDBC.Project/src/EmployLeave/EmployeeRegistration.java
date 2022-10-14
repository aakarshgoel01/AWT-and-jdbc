package EmployLeave;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeeRegistration extends JFrame {

	private JPanel contentPane;
	private JTextField employeenamet;
	private JTextField categoryt;
	private JTextField salaryt;
	
	/**
	 * Launch the application.
	 */
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeRegistration frame = new EmployeeRegistration();
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
	public EmployeeRegistration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 377);
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		contentPane.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel employeenol = new JLabel("Employee No :");
		employeenol.setForeground(Color.WHITE);
		employeenol.setHorizontalAlignment(SwingConstants.LEFT);
		employeenol.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		employeenol.setBounds(10, 36, 163, 40);
		contentPane.add(employeenol);
		
		JLabel categoryl = new JLabel("Category :");
		categoryl.setForeground(Color.WHITE);
		categoryl.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		categoryl.setHorizontalAlignment(SwingConstants.LEFT);
		categoryl.setBounds(10, 133, 150, 38);
		contentPane.add(categoryl);
		
		JLabel employeenamel = new JLabel("Employee Name:");
		employeenamel.setForeground(Color.WHITE);
		employeenamel.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		employeenamel.setBounds(10, 86, 150, 37);
		contentPane.add(employeenamel);
		
		JLabel salaryl = new JLabel("Salary :");
		salaryl.setForeground(Color.WHITE);
		salaryl.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		salaryl.setBounds(10, 188, 150, 29);
		contentPane.add(salaryl);
		
		employeenamet = new JTextField();
		employeenamet.setColumns(10);
		employeenamet.setBounds(194, 89, 150, 35);
		contentPane.add(employeenamet);
		
		categoryt = new JTextField();
		categoryt.setColumns(10);
		categoryt.setBounds(194, 134, 150, 38);
		contentPane.add(categoryt);
		
		salaryt = new JTextField();
		salaryt.setColumns(10);
		salaryt.setBounds(194, 182, 150, 35);
		contentPane.add(salaryt);
		
		JLabel txtno = new JLabel("");
		txtno.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 18));
		txtno.setForeground(Color.WHITE);
		txtno.setBounds(194, 46, 140, 30);
		contentPane.add(txtno);
		
		JButton saveb = new JButton("Save");
		saveb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = employeenamet.getText();
					String category = categoryt.getText();
					String salary = salaryt.getText();
					
					if(name == "" || category == "") {
					 JOptionPane.showMessageDialog(null, "Please enter name and category..");
					}else {
						PreparedStatement pstmt = ConnectionProvider.creatConnection().prepareStatement("Insert into employees (ename,ecatogery,esalary) values (?,?,?) ");
						pstmt.setString(1, name);
						pstmt.setString(2, category);
						pstmt.setString(3, salary);
						pstmt.executeUpdate();
						JOptionPane.showMessageDialog(null,"Data saved..." );
						txtno.setText(""+setEmployeeId());
						employeenamet.requestFocus();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println(e1);
				}
			}
		});
		saveb.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		saveb.setBounds(54, 273, 119, 38);
		contentPane.add(saveb);
		
		JButton cancelb = new JButton("Cancel");
		cancelb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		cancelb.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		cancelb.setBounds(194, 273, 119, 38);
		contentPane.add(cancelb);
		
		txtno.setText(""+setEmployeeId());
		
		
		
	}
	
	
	static int setEmployeeId() {
	    int a = 0;
	try {
		Statement stmt = ConnectionProvider.creatConnection().createStatement();
		ResultSet rs = stmt.executeQuery("Select Max(eid) from employees");
		rs.next();
		a = rs.getInt(1);
		
		
	} catch (SQLException e) {
		System.out.println(e);
	}
	a=a+1;
	return a;
}
	
}
