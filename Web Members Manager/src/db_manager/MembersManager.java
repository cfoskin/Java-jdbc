package db_manager;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * 
 * @author Colum Foskin - 20062042 - 18/09/2016 Applied Computing - Distributed
 *         Systems - Assignment 1 Database manager class which allows the user
 *         to perform CRUD on a local database called test. test has a table
 *         called web_members
 *
 */
public class MembersManager{
	private ResultSet rs;
	private GUI gui;
	private String currentMemberId = "", name = "", lastName = "", email = "";
	private Statement st;
	private Connection con;

	/**
	 * Constructor which opens the db connection and sets up the GUI
	 */
	public MembersManager() {
		this.gui = new GUI();//create a new GUI for interacting with
		connectToDb();// make a new connection to the test db
		addActionListenersToButtons();//add the listeners
	}

	/**
	 * connect to the local database
	 */
	private void connectToDb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false", "root", "root");
			loadMembers();//load them up
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * load the members - used at start-up and after any CRUD action is performed 
	 * to reflect any changes made.
	 */
	private void loadMembers() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from web_members");
			goForward();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * a method which adds the action listeners to all the buttons on the JPanel
	 */
	void addActionListenersToButtons() {
		gui.nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goForward();
			}
		});

		gui.backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goBack();
			}
		});

		gui.createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createNewMember();
			}
		});

		gui.updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateMember();
			}
		});

		gui.deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteMember();
			}
		});

		gui.searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchForMember();
			}
		});

		gui.resetSearchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.searchTextField.setText("");
				loadMembers();
			}
		});

		gui.goToFirstButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goToFirst();
			}
		});
		gui.goToLastButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goToLast();
			}
		});
	}

	/**
	 * move to the next record in the db
	 */
	private void goForward() {
		try {
			if (rs.next()) {
				currentMemberId = rs.getString("id");
				name = rs.getString("name");
				lastName = rs.getString("last_name");
				email = rs.getString("email");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		gui.nameTextField.setText(name);
		gui.lastNameTextField.setText(lastName);
		gui.emailTextField.setText(email);
	}

	/**
	 * move to the previous record in the db
	 */
	private void goBack() {
		try {
			if (rs.previous()) {
				currentMemberId = rs.getString("id");
				name = rs.getString("name");
				lastName = rs.getString("last_name");
				email = rs.getString("email");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		gui.nameTextField.setText(name);
		gui.lastNameTextField.setText(lastName);
		gui.emailTextField.setText(email);
	}

	/**
	 * move to the first record in the db
	 */
	private void goToFirst() {
		try {
			if (rs.first()) {
				currentMemberId = rs.getString("id");
				name = rs.getString("name");
				lastName = rs.getString("last_name");
				email = rs.getString("email");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		gui.nameTextField.setText(name);
		gui.lastNameTextField.setText(lastName);
		gui.emailTextField.setText(email);
	}
	
	/**
	 * move to the last record in the db
	 */
	private void goToLast() {
		try {
			if (rs.last()) {
				currentMemberId = rs.getString("id");
				name = rs.getString("name");
				lastName = rs.getString("last_name");
				email = rs.getString("email");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		gui.nameTextField.setText(name);
		gui.lastNameTextField.setText(lastName);
		gui.emailTextField.setText(email);
	}
	
	/**
	 * Info box to alert the user to different messages
	 * 
	 * @param infoMessage
	 * @param titleBar
	 */
	private void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * create a new user in the database
	 */
	private void createNewMember() {
		String createQuery = "INSERT INTO web_members (name,last_name,email) VALUES (" + "'" + gui.nameTextField.getText()
				+ "', " + "'" + gui.lastNameTextField.getText() + "', " + "'" + gui.emailTextField.getText() + "'" + ");";
		executeUpdateQuery(createQuery, "Member has been added!");
	}

	/**
	 * update an existing user in the database
	 */
	private void updateMember() {
		String updateQuery = "UPDATE web_members SET name = " + "'" + gui.nameTextField.getText() + "', last_name = " + "'"
				+ gui.lastNameTextField.getText() + "', email = " + "'" + gui.emailTextField.getText() + "'" + " WHERE id = "
				+ currentMemberId + ";";
		executeUpdateQuery(updateQuery, "Member has been updated!");
	}

	/**
	 * delete an existing user in the database
	 */
	private void deleteMember() {
		String deleteQuery = "DELETE FROM web_members WHERE id = " + currentMemberId + ";";
		executeUpdateQuery(deleteQuery, "Member has been deleted!");
	}

	/**
	 * search for a member by first name in the db - will bring back as many
	 * entries that partially match
	 */
	private void searchForMember() {
		String nameToSearch = gui.searchTextField.getText();
		String searchQuery = "SELECT * FROM web_members WHERE name like '%" + nameToSearch + "%';";
		try {
			st = con.createStatement();
			rs = st.executeQuery(searchQuery);
			infoBox("User Found!", "Info!");
			goForward();
		} catch (Exception e) {
			infoBox("User not found!", "Warning!");
		}
	}

	/**
	 * utility method used for all CRUD actions to execute a query
	 * 
	 * @param query
	 *            - the query given
	 * @param message
	 *            - the info message for the info pop-up
	 */
	private void executeUpdateQuery(String query, String message) {
		try {
			st = con.createStatement();
			st.executeUpdate(query);

			infoBox(message, "Info!");
			loadMembers();
		} catch (Exception e) {
			infoBox("Error Please try again!", "Warning!");
		}
	}

}
