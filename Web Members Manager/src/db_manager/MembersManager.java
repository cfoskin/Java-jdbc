package db_manager;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class MembersManager {
	private JButton backButton, nextButton;
	private ResultSet rs;
	private JTextField text1, text2, text3, text4;
	private JFrame f;
	private JLabel label1, label2, label3, label4;
	private String id = "", name = "", lastName = "", email = "";

	public MembersManager() {
		connectToDb();
		setUpComponents();
	}
	
	public static void main(String[] args){
    	MembersManager mm = new MembersManager();
    	mm.addActionListenersToButtons();
    }

	private void connectToDb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false", "root",
					"root");
			Statement st = con.createStatement();
			rs = st.executeQuery("select * from web_members");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void setUpComponents() {
		f = new JFrame();

		label1 = new JLabel("ID: ");
		text1 = new JTextField(20);

		label2 = new JLabel("First Name: ");
		text2 = new JTextField(20);

		label3 = new JLabel("Last Name: ");
		text3 = new JTextField(20);

		label4 = new JLabel("email: ");
		text4 = new JTextField(20);

		backButton = new JButton("Back");
		nextButton = new JButton("Next");

		goForward();//populate first one

		JPanel p=new JPanel(new GridLayout(9,2));
        p.add(label1);
        p.add(text1);
        p.add(label2);
        p.add(text2);
        p.add(label3);
        p.add(text3);
        p.add(label4);
        p.add(text4);
        
        p.add(backButton);
        p.add(nextButton);
        f.add(p);
        f.setVisible(true);
        f.pack();
	}
	
	private void addActionListenersToButtons(){
		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				goForward();
			}

		});

		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				goBack();
			}

		});
	}

	private void goForward() {
		try {
			if (rs.next()) {
				id = rs.getString("id");
				name = rs.getString("name");
				lastName = rs.getString("last_name");
				email = rs.getString("email");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		text1.setText(id);
		text2.setText(name);
		text3.setText(lastName);
		text4.setText(email);
	}

	private void goBack() {
		try {
			if (rs.previous()) {
				id = rs.getString("id");
				name = rs.getString("name");
				lastName = rs.getString("last_name");
				email = rs.getString("email");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		text1.setText(id);
		text2.setText(name);
		text3.setText(lastName);
		text4.setText(email);
	}
	
}
