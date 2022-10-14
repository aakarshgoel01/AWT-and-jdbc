package EmployLeave;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.Choice;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class LeaveTaking extends JFrame {

	private JPanel contentPane;
	private static JTextField yeart;
	private static JComboBox<String> eidc, comboBox_1;
	private JSpinner spinner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeaveTaking frame = new LeaveTaking();
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
	public LeaveTaking() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 405);
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel employidl = new JLabel("Employ id :");
		employidl.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		employidl.setForeground(Color.WHITE);
		employidl.setBounds(44, 71, 136, 40);
		contentPane.add(employidl);

		JLabel namel = new JLabel("Name :");
		namel.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		namel.setForeground(Color.WHITE);
		namel.setBounds(44, 131, 136, 40);
		contentPane.add(namel);

		yeart = new JTextField();
		yeart.setColumns(10);
		yeart.setBounds(273, 135, 142, 37);
		contentPane.add(yeart);

		JButton okb = new JButton("OK");
		okb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String id = (String) eidc.getSelectedItem();
				String category = (String) comboBox_1.getSelectedItem(), leaves = "";
				int leave = 0;
				try {

					PreparedStatement pstmt = ConnectionProvider.creatConnection()
							.prepareStatement("select * from leaves where id = ?");
					pstmt.setString(1, id);
					ResultSet rs = pstmt.executeQuery();
					if (rs.next()) {
						leaves = rs.getString(category);
						leave = Integer.parseInt(leaves);
					}
					ConnectionProvider.creatConnection().close();
					int asked = (int) spinner.getValue(), given = leave - asked;

					if (given >= 0 && category == "casual") {
						JOptionPane.showMessageDialog(null, "Leave granted..");
						PreparedStatement pstmt1 = ConnectionProvider.creatConnection()
								.prepareStatement("update leaves set casual = ? where id = ?");
						pstmt1.setString(1, given + "");
						pstmt1.setInt(2, Integer.parseInt(id));
						pstmt1.executeUpdate();

					} else if (given >= 0 && category == "medical") {
						JOptionPane.showMessageDialog(null, "Leave granted..");
						PreparedStatement pstmt1 = ConnectionProvider.creatConnection()
								.prepareStatement("update leaves set medical = ? where id = ?");
						pstmt1.setString(1, given + "");
						pstmt1.setInt(2, Integer.parseInt(id));
						pstmt1.executeUpdate();

					} else if (given >= 0 && category == "annual") {
						JOptionPane.showMessageDialog(null, "Leave granted..");
						PreparedStatement pstmt1 = ConnectionProvider.creatConnection()
								.prepareStatement("update leaves set annual = ? where id = ?");
						pstmt1.setString(1, given + "");
						pstmt1.setInt(2, Integer.parseInt(id));
						pstmt1.executeUpdate();

					} else {
						JOptionPane.showMessageDialog(null, "You have only " + leaves + " leaves left...");
					}
					ConnectionProvider.creatConnection().close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println(e1);
					;
				}
			}
		});
		okb.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		okb.setBounds(79, 308, 101, 40);
		contentPane.add(okb);

		JButton cancelb = new JButton("Cancel");
		cancelb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});
		cancelb.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		cancelb.setBounds(232, 308, 101, 40);
		contentPane.add(cancelb);

		JList<?> list = new JList<Object>();
		list.setBounds(485, 201, 152, -34);
		contentPane.add(list);

		eidc = new JComboBox<String>();
		eidc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = (String) eidc.getSelectedItem();
					PreparedStatement pstmt1 = ConnectionProvider.creatConnection()
							.prepareStatement("select * from employees where eid = ?");
					pstmt1.setInt(1, Integer.parseInt(id));
					ResultSet rs1 = pstmt1.executeQuery();
					rs1.next();
					yeart.setText(rs1.getString("ename"));
					ConnectionProvider.creatConnection().close();
				} catch (Exception ee) {
					System.out.println(ee);
				}
			}
		});
		eidc.setBounds(273, 71, 142, 34);
		contentPane.add(eidc);

		spinner = new JSpinner();
		spinner.setBounds(273, 195, 141, 37);
		contentPane.add(spinner);

		JLabel daysl = new JLabel("Days :");
		daysl.setForeground(Color.WHITE);
		daysl.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		daysl.setBounds(44, 191, 136, 40);
		contentPane.add(daysl);

		JLabel lblCatogery = new JLabel("Catogery :");
		lblCatogery.setForeground(Color.WHITE);
		lblCatogery.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblCatogery.setBounds(44, 241, 136, 40);
		contentPane.add(lblCatogery);

		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(273, 250, 142, 31);
		contentPane.add(comboBox_1);

		comboBox_1.addItem("casual");
		comboBox_1.addItem("annual");
		comboBox_1.addItem("medical");
		
		try {
			PreparedStatement pstmt = ConnectionProvider.creatConnection().prepareStatement("select * from employees");
			ResultSet rs = pstmt.executeQuery();
			eidc.removeAll();

			while (rs.next()) {

				eidc.addItem(rs.getString("eid"));
System.out.println("Employeess loaded");
			}

			ConnectionProvider.creatConnection().close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		

	}

}
