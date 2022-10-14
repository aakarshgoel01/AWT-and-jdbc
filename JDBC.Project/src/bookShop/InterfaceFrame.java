package bookShop;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InterfaceFrame {

	private JFrame frame;
	private JTextField booknamet;
	private JTextField pricet;
	private JTextField bookidt;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
 	EventQueue.invokeLater(new Runnable() {
 		public void run() {
				try {
					InterfaceFrame window = new InterfaceFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfaceFrame() {
	
		initialize();
        showTable();
	}
	
	int k =0;
	
	void showTable() {
		try {
			PreparedStatement pstmt = ConnectionProvider.giveConnection().prepareStatement("select * from booksshop ");
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			int cols = rsmd.getColumnCount();
			String[] colname = new String[cols];
			for(int i =0 ; i<cols ; i++)
				colname[i] = rsmd.getColumnName(i+1);
			model.setColumnIdentifiers(colname);
			String id,name,price,edition;
			while(rs.next()) {
				id = ""+rs.getInt(1);
				name = rs.getString(2);
				price = rs.getString(3);
				edition = ""+rs.getInt(4);
				String[] row = {id,name,price,edition};
				model.addRow(row);
				k++;
			}
			
		   ConnectionProvider.giveConnection().close();
		   rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 705, 453);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lable = new JLabel("Book Shop");
		lable.setFont(new Font("Yu Gothic Medium", Font.BOLD, 24));
		lable.setBounds(247, 10, 140, 36);
		frame.getContentPane().add(lable);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 46, 346, 254);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lable1 = new JLabel("Book Name");
		lable1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lable1.setBounds(23, 36, 114, 43);
		panel.add(lable1);
		
		booknamet = new JTextField();
		booknamet.setBounds(147, 36, 170, 43);
		panel.add(booknamet);
		booknamet.setColumns(10);
		
		JLabel lblEdition = new JLabel("Edition ");
		lblEdition.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblEdition.setBounds(23, 86, 114, 43);
		panel.add(lblEdition);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblPrice.setBounds(23, 139, 114, 43);
		panel.add(lblPrice);
		
		JTextField editiont = new JTextField();
		editiont.setColumns(10);
		editiont.setBounds(147, 89, 170, 43);
		panel.add(editiont);
		
		pricet = new JTextField();
		pricet.setColumns(10);
		pricet.setBounds(147, 139, 170, 43);
		panel.add(pricet);
		
		JButton saveb = new JButton("Save");
		saveb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = booknamet.getText();
				String price = pricet.getText();
				int edition = Integer.parseInt(editiont.getText());
				
				try {
					PreparedStatement pstmt = ConnectionProvider.giveConnection().prepareStatement("insert into booksshop (name,price,edition) values (?,?,?) ");
				    
					pstmt.setString(1,name);
					pstmt.setString(2, price);
					pstmt.setInt(3, edition);
					
					pstmt.executeUpdate();
					
					ConnectionProvider.giveConnection().close();
					
					JOptionPane.showMessageDialog(null, "Data added !!!!!!!!!");
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					k++;
					String[] update = {""+k,name,price,""+edition};
					
					model.addRow(update);
					
					
					booknamet.setText("");
					pricet.setText("");
					editiont.setText("");
					booknamet.requestFocus();
					
				} catch (SQLException e1) {
					System.out.println(e1);
				}
				
				
				
				}
		});
		saveb.setBounds(23, 192, 85, 43);
		panel.add(saveb);
		
		JButton exitb = new JButton("Exit");
		exitb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    System.exit(0);
			}
		});
		exitb.setBounds(115, 192, 85, 43);
		panel.add(exitb);
		
		JButton clearb = new JButton("Clear");
		clearb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				booknamet.setText("");
				editiont.setText("");
				pricet.setText("");
				
			}
		});
		clearb.setBounds(210, 192, 85, 43);
		panel.add(clearb);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 310, 346, 66);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Id");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 20, 82, 37);
		panel_2.add(lblNewLabel);
		
		bookidt = new JTextField();
		bookidt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String id = bookidt.getText();
				try {
					
					PreparedStatement pstmt = ConnectionProvider.giveConnection().prepareStatement("select name,price,edition from booksshop where id = ?");
					pstmt.setInt(1, Integer.parseInt(id));
					ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					String name = rs.getString(1);
					String edition = (rs.getInt(3)+"");
					String price = rs.getString(2);
					
					booknamet.setText(name);
					editiont.setText(edition);
					pricet.setText(price);
				} else {
					booknamet.setText("");
					editiont.setText("");
					pricet.setText("");
				}
					
					ConnectionProvider.giveConnection().close();
					rs.close();
				} catch (Exception e1) {
					System.out.println(e1);
				}
				
			}
		});
		bookidt.setBounds(79, 20, 200, 30);
		panel_2.add(bookidt);
		bookidt.setColumns(10);
		
		JButton updateb = new JButton("Update");
		updateb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = bookidt.getText();
				if (id == "") {
					JOptionPane.showMessageDialog(null, "Enter valid book id..");
				}else {
					try {
						PreparedStatement pstmt = ConnectionProvider.giveConnection().prepareStatement("update booksshop set name = ?,edition = ? , price = ? where id = ?");
						
						String name = booknamet.getText();
						String price = pricet.getText();
						String edition = editiont.getText();
						
						pstmt.setString(1, name);
						pstmt.setInt(2, Integer.parseInt(edition));
						pstmt.setString(3, price);
						pstmt.setInt(4, Integer.parseInt(id));
						
						pstmt.executeUpdate();
//...................................................................................................................//
						//To delete all items of the table
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						for(int i = 0 ; i<model.getRowCount();i++) {
								model.removeRow(i);
								i--;
						}
						
						
//................................................................................................................//						
						showTable();
						ConnectionProvider.giveConnection().close();
						
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		updateb.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		updateb.setBounds(366, 310, 128, 66);
		frame.getContentPane().add(updateb);
		
		JButton deleteb = new JButton("Delete");
		deleteb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id  = bookidt.getText();
				try {
					PreparedStatement pstmt = ConnectionProvider.giveConnection().prepareStatement("delete from booksshop where id = ?");
                    pstmt.setInt(1, Integer.parseInt(id));
                    pstmt.executeUpdate();
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    for(int i = 0 ;i<model.getRowCount();i++) {
                    	model.removeRow(i);
                    	i--;
                    }
                    
                    showTable();
					
				} catch (SQLException e1) {
     				e1.printStackTrace();
				} 
				
			}
		});
		deleteb.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		deleteb.setBounds(504, 310, 128, 66);
		frame.getContentPane().add(deleteb);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(366, 56, 325, 242);
		frame.getContentPane().add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
	}
}
