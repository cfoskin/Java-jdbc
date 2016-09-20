package db_manager;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * 
 * @author Colum Foskin - 20062042 - 18/09/2016 Applied Computing - Distributed
 *         Systems - Assignment 1 Database manager class which allows the user
 *         to perform CRUD on a local database called test. 
 *
 */
public class EmployeeManager{
	private ResultSet rs;
	private GUI gui;
	private String Ssn = "", birthDate = "", name = "", address = "",
			salary = "", sex ="", worksFor = "", manages= "", supervises= "";
	private Statement st;
	private Connection con;

	/**
	 * Constructor which opens the db connection and sets up the GUI
	 */
	public EmployeeManager(GUI gui) {
		this.gui = gui;
		connectToDb();// make a new connection to the test db
		addActionListenersToButtons();//add the listeners
	}

	/**
	 * connect to the local database
	 */
	private void connectToDb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
			loadEmployees();//load them up
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * load the employees - used at start-up and after any CRUD action is performed 
	 * to reflect any changes made.
	 */
	private void loadEmployees() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from Employee");
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
				deleteEmployee();
			}
		});

		gui.searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchForEmployee();
			}
		});

		gui.resetSearchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.searchTextField.setText("");
				loadEmployees();
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
				Ssn = rs.getString("Ssn");
				birthDate = rs.getString("Bdate");
				name = rs.getString("Name");
				address = rs.getString("Address");
				salary = rs.getString("Salary");
				sex = rs.getString("Sex");
				worksFor = rs.getString("Works_For");
				manages = rs.getString("Manages");
				supervises = rs.getString("Supervises");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		gui.ssnTextField.setText(Ssn);
		gui.bDateTextField.setText(birthDate);
		gui.nameTextField.setText(name);
		gui.addressTextField.setText(address);
		gui.salaryTextField.setText(salary);
		gui.sexTextField.setText(sex);
		gui.worksForTextField.setText(worksFor);
		gui.managesTextField.setText(manages);
		gui.supervisesTextField.setText(supervises);

	}

	/**
	 * move to the previous record in the db
	 */
	private void goBack() {
		try {
			if (rs.previous()) {
				Ssn = rs.getString("Ssn");
				birthDate = rs.getString("Bdate");
				name = rs.getString("Name");
				address = rs.getString("Address");
				salary = rs.getString("Salary");
				sex = rs.getString("Sex");
				worksFor = rs.getString("Works_For");
				manages = rs.getString("Manages");
				supervises = rs.getString("Supervises");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		gui.ssnTextField.setText(Ssn);
		gui.bDateTextField.setText(birthDate);
		gui.nameTextField.setText(name);
		gui.addressTextField.setText(address);
		gui.salaryTextField.setText(salary);
		gui.sexTextField.setText(sex);
		gui.worksForTextField.setText(worksFor);
		gui.managesTextField.setText(manages);
		gui.supervisesTextField.setText(supervises);
	}

	/**
	 * move to the first record in the db
	 */
	private void goToFirst() {
		try {
			if (rs.first()) {
				Ssn = rs.getString("Ssn");
				birthDate = rs.getString("Bdate");
				name = rs.getString("Name");
				address = rs.getString("Address");
				salary = rs.getString("Salary");
				sex = rs.getString("Sex");
				worksFor = rs.getString("Works_For");
				manages = rs.getString("Manages");
				supervises = rs.getString("Supervises");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		gui.ssnTextField.setText(Ssn);
		gui.bDateTextField.setText(birthDate);
		gui.nameTextField.setText(name);
		gui.addressTextField.setText(address);
		gui.salaryTextField.setText(salary);
		gui.sexTextField.setText(sex);
		gui.worksForTextField.setText(worksFor);
		gui.managesTextField.setText(manages);
		gui.supervisesTextField.setText(supervises);
	}
	
	/**
	 * move to the last record in the db
	 */
	private void goToLast() {
		try {
			if (rs.last()) {
				Ssn = rs.getString("Ssn");
				birthDate = rs.getString("Bdate");
				name = rs.getString("Name");
				address = rs.getString("Address");
				salary = rs.getString("Salary");
				sex = rs.getString("Sex");
				worksFor = rs.getString("Works_For");
				manages = rs.getString("Manages");
				supervises = rs.getString("Supervises");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		gui.ssnTextField.setText(Ssn);
		gui.bDateTextField.setText(birthDate);
		gui.nameTextField.setText(name);
		gui.addressTextField.setText(address);
		gui.salaryTextField.setText(salary);
		gui.sexTextField.setText(sex);
		gui.worksForTextField.setText(worksFor);
		gui.managesTextField.setText(manages);
		gui.supervisesTextField.setText(supervises);
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
		String createQuery = "INSERT INTO Employee (Ssn, Bdate, Name, Address, Salary, Sex, Works_For, Manages, Supervises) VALUES (" + "'" + gui.nameTextField.getText()
				+ "', " + "'" + gui.ssnTextField.getText() + "', " + "'" + gui.bDateTextField.getText() + "'," + "'" + 
				gui.nameTextField.getText() + "'," + "'" +  gui.addressTextField.getText() + "'," +
				"'" + gui.salaryTextField.getText() + "'," + "'" +  gui.sexTextField.getText() + "'," +
				"'" +  gui.worksForTextField.getText() + "'," + "'" +  gui.managesTextField.getText() + "'," + "'" +  gui.supervisesTextField.getText()+ ");";
		executeUpdateQuery(createQuery, "Employee has been added!");
	}

	/**
	 * update an existing employee in the database
	 */
	private void updateMember() {
		String updateQuery = "UPDATE Employee SET Ssn = " + "'" + gui.ssnTextField.getText() + "', Bdate = " + "'"
				+ gui.bDateTextField.getText() + "', Name = " + "'" + gui.nameTextField.getText() +
				"', Address = " + "'" + gui.addressTextField.getText() + "', Salary = " + "'" + gui.salaryTextField.getText() 
				+ "', Sex = " + "'" + gui.sexTextField.getText()+ "'" + " WHERE Ssn = "
				+ Ssn + ";";
		executeUpdateQuery(updateQuery, "Employee has been updated!");
	}

	/**
	 * delete an existing employee in the database
	 */
	private void deleteEmployee() {
		String deleteQuery = "DELETE FROM Employee WHERE Ssn = " + Ssn + ";";
		executeUpdateQuery(deleteQuery, "Employee has been deleted!");
	}

	/**
	 * search for a employee by first name in the db - will bring back as many
	 * entries that partially match
	 */
	private void searchForEmployee() {
		String nameToSearch = gui.searchTextField.getText();
		String searchQuery = "SELECT * FROM Employee WHERE name like '%" + nameToSearch + "%';";
		try {
			st = con.createStatement();
			rs = st.executeQuery(searchQuery);
			infoBox("Employee Found!", "Info!");
			goForward();
		} catch (Exception e) {
			infoBox("Employee not found!", "Warning!");
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
			loadEmployees();
		} catch (Exception e) {
			infoBox("Error Please try again!", "Warning!");
		}
	}

}
