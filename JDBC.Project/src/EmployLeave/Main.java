package EmployLeave;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 651, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Leave..");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		lblNewLabel.setBounds(47, 28, 556, 56);
		contentPane.add(lblNewLabel);
		
		JButton Registrationb = new JButton("Employee Registration");
		Registrationb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeRegistration er = new EmployeeRegistration();
				er.setVisible(true);
			}
		});
		Registrationb.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 24));
		Registrationb.setBounds(140, 116, 327, 56);
		contentPane.add(Registrationb);
		
		JButton btnEmployeeLeave = new JButton("Employee Leave");
		btnEmployeeLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeLeaves el = new EmployeeLeaves();
				el.setVisible(true);
			}
		});
		btnEmployeeLeave.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 24));
		btnEmployeeLeave.setBounds(140, 182, 327, 56);
		contentPane.add(btnEmployeeLeave);
		
		JButton btnLeaveCalculator = new JButton("Leave Calculator");
		btnLeaveCalculator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LeaveTaking lt = new LeaveTaking();
				lt.setVisible(true);
			}
		});
		btnLeaveCalculator.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 24));
		btnLeaveCalculator.setBounds(140, 248, 327, 56);
		contentPane.add(btnLeaveCalculator);
	}
}
