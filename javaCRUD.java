package language;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class javaCRUD {

	private JFrame frame;
	private JTextField bname;
	private JTextField ename;
	private JTextField pname;
	private JTable table;
	private JTextField bid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					javaCRUD window = new javaCRUD();
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
	public javaCRUD() {
		initialize();
		Connect();
		table_load();
	}
	Connection con;
	PreparedStatement pat;
	ResultSet rs;
	
	public void Connect()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root","abc");
		}
		catch(ClassNotFoundException ex)
		{
			
		}
		catch(SQLException ex)
		{
			
		}
	}
	
	
	public void table_load()
	{
		try
		{
			pat =con.prepareStatement("select * from book");
			rs = pat.executeQuery();
			//table.setModel(DbUtils.resultSetToTableModel(rs));
			
		}
		
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 832, 503);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BOOK SHOP");
		lblNewLabel.setBounds(265, 0, 239, 61);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "REGISTRATION", TitledBorder.CENTER, TitledBorder.TOP, null, Color.RED));
		panel.setBounds(13, 66, 415, 218);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("BOOK NAME");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setBounds(31, 40, 149, 30);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("EDITION");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1_1.setBounds(31, 99, 119, 30);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("PRICE");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1_2.setBounds(31, 157, 94, 30);
		panel.add(lblNewLabel_1_2);
		
		bname = new JTextField();
		bname.setBounds(205, 52, 198, 30);
		panel.add(bname);
		bname.setColumns(10);
		
		ename = new JTextField();
		ename.setColumns(10);
		ename.setBounds(205, 111, 198, 30);
		panel.add(ename);
		
		pname = new JTextField();
		pname.setColumns(10);
		pname.setBounds(205, 169, 198, 30);
		panel.add(pname);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String b,ed,p;
				
				
				b=bname.getText();
				ed=ename.getText();
				p=pname.getText();
				
				
				try {
					
					pat = con.prepareStatement("insert into book(name,edition,price) values(?,?,?)");
					pat.setString(1, b);
					pat.setString(2, ed);
					pat.setString(3, p);
					pat.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Record Added Successfully !!!");
					table_load();
					
					bname.setText("");
					ename.setText("");
					pname.setText("");
					bname.requestFocus();
					
				}
				
				catch(SQLException e1)
				{
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(23, 292, 102, 38);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnExit.setBounds(158, 294, 102, 38);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bname.setText("");
				ename.setText("");
				pname.setText("");
				bid.setText("");
				bname.requestFocus();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnClear.setBounds(292, 292, 102, 38);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(438, 71, 370, 291);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "SEARCH", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(13, 377, 437, 79);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("BOOK ID");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1_1_1.setBounds(10, 27, 119, 30);
		panel_1.add(lblNewLabel_1_1_1);
		
		bid = new JTextField();
		bid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					String s=bid.getText();
					
					pat = con.prepareStatement("select name,edition,price from book where id = ?");
					pat.setString(1, s);
					ResultSet rs =pat.executeQuery();
					
					if(rs.next()==true)
					{
						String b=rs.getString(1);
						String ed=rs.getString(2);
						String p=rs.getString(3);
						
						bname.setText(b);
						ename.setText(ed);
						pname.setText(p);
					}
					else
					{
						bname.setText("");
						ename.setText("");
						pname.setText("");

					}
							
				}
				catch(Exception exp)
				{
					
				}
				
			}
		});
		bid.setColumns(10);
		bid.setBounds(151, 28, 255, 29);
		panel_1.add(bid);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String b,ed,p,bkid;
				
				
				b=bname.getText();
				ed=ename.getText();
				p=pname.getText();
				bkid=bid.getText();
				
				
				try {
					
					pat = con.prepareStatement("update book set name=?, edition=?,price=? where id=?");
					pat.setString(1, b);
					pat.setString(2, ed);
					pat.setString(3, p);
					pat.setString(4, bkid);
					pat.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Record Updated !!!");
					table_load();
					
					bname.setText("");
					ename.setText("");
					pname.setText("");
					bname.requestFocus();
					
				}
				
				catch(SQLException e1)
				{
					e1.printStackTrace();
				}
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnUpdate.setBounds(503, 373, 121, 38);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				String bkid;
				
				
				
				bkid=bid.getText();
				
				
				try {
					
					pat = con.prepareStatement("delete from book  where id=?");
					pat.setString(1, bkid);
					
					pat.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Record Deleetd !!!");
					table_load();
					
					bname.setText("");
					ename.setText("");
					pname.setText("");
					bname.requestFocus();
					
				}
				
				catch(SQLException e1)
				{
					e1.printStackTrace();
				}
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDelete.setBounds(652, 373, 121, 38);
		frame.getContentPane().add(btnDelete);
	}
}
