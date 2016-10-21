package db_manager;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * 
 * @author Colum Foskin - 20062042 - 18/09/2016 Applied Computing - Distributed
 *         Systems - Assignment 1 - Employee manager class which allows the user
 *         to perform the required actions from the specification, on a database
 *         called Assignment1. The credentials for this are as follows: username
 *         is root and there is no password. The mysql port is 3306 - this may
 *         differ on another machine.
 *
 */
public class EmployeeManager {
	private ResultSet rs;
	private GUI gui;
	private String Ssn, birthDate, name, address, salary, sex;
	private Statement st;
	private Connection con;
	private int ssnForIncrement = 2000; // ssn which will increment when adding
										// a
										// fixed record. This is so deleting one
										// wont delete
										// all with the same ssn.

	/**
	 * Constructor which opens the db connection
	 */
	public EmployeeManager(GUI gui) {
		this.gui = gui;
		connectToDb();// make a new connection to the db
		addActionListenersToButtons();// add the listeners
	}

	/**
	 * a method which adds the action listeners to all the buttons on the JPanel
	 */
	private void addActionListenersToButtons() {
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
				createNewEmployee();
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
		gui.exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
	}

	/**
	 * connect to the database
	 */
	public Connection connectToDb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Assignment1", "root", "");
			loadEmployees();// load employees up on startup
		} catch (Exception e) {
			infoBox("Error Connecting to the database - Please try again!", "Warning!");
		}
		return con;
	}

	/**
	 * load the employees - used at start-up and after any database action is
	 * performed to reflect any changes made.
	 */
	private void loadEmployees() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from Employee");
			goToFirst();
		} catch (SQLException e) {
			infoBox("Error Loading employees! - Please try again!", "Warning!");
		}
	}

	/**
	 * This section performs the functions of the GUI which are used by the user
	 **/
	
	/**
	 * move to the next employee in the db
	 */
	private void goForward() {
		try {
			if (rs.next()) {
				getTextFields();
			}
		} catch (Exception e) {
			infoBox("This is the last employee!", "Warning!");
		}
		setTextFields();
	}

	/**
	 * move to the previous employee in the db
	 */
	private void goBack() {
		try {
			if (rs.previous()) {
				getTextFields();
			}
		} catch (Exception e) {
			infoBox("This is the first employee!", "Warning!");
		}
		setTextFields();
	}

	/**
	 * move to the first employee in the db
	 */
	private void goToFirst() {
		try {
			if (rs.first()) {
				getTextFields();
			}
		} catch (Exception e) {
			infoBox("Error please start again!", "Warning!");
		}
		setTextFields();
	}

	/**
	 * move to the last employee in the db
	 */
	private void goToLast() {
		try {
			if (rs.last()) {
				getTextFields();
			}
		} catch (Exception e) {
			infoBox("Error please start again!", "Warning!");
		}
		setTextFields();
	}

	/**
	 * create a new fixed record of an employee in the database
	 */
	private void createNewEmployee() {
		String createQuery = "INSERT INTO `Employee` (Ssn, Bdate, Name, Address, Salary, Sex, Works_For, Manages, Supervises) "
				+ "VALUES (" + ssnForIncrement + ", '2016-09-06', 'Zach Jones', 'waterford', '8', 'm', '1', '2', '3');";
		executeUpdateQuery(createQuery, "Employee Zach Jones SSn: " + ssnForIncrement + " has been added!");
		ssnForIncrement += 1;// increment by 1 each time
	}

	/**
	 * delete an existing employee in the database
	 */
	private void deleteEmployee() {
		String deleteQuery = "DELETE FROM Employee WHERE Ssn = " + Ssn + ";";
		executeUpdateQuery(deleteQuery, "Employee " + name + " SSn: " + Ssn + " has been deleted!");
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
			goToFirst();
		} catch (Exception e) {
			infoBox("Employee not found!", "Warning!");
		}
	}
	
	/**
	 * utility method used for all CRUD actions to execute a query
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
			st.close();
			loadEmployees();
		} catch (Exception e) {
			infoBox("Error Please try again!", "Warning!");
		}
	}

	/**
	 * Set the text fields to a new value - used in the next, previous, first,
	 * last methods.
	 */
	private void setTextFields() {
		gui.ssnTextField.setText(Ssn);
		gui.bDateTextField.setText(birthDate);
		gui.nameTextField.setText(name);
		gui.addressTextField.setText(address);
		gui.salaryTextField.setText(salary);
		gui.sexTextField.setText(sex);
	}

	/**
	 * Get the text fields to a new value - used in the next, previous, first,
	 * last methods.
	 */
	private void getTextFields() throws SQLException {
		Ssn = rs.getString("Ssn");
		birthDate = rs.getString("Bdate");
		name = rs.getString("Name");
		address = rs.getString("Address");
		salary = rs.getString("Salary");
		sex = rs.getString("Sex");
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
	 * Exit the app and close the db connection
	 */
	public void exit() {
		try {
			if (con != null)
				infoBox("Closing database connection & Exiting.", "Info!");
				con.close();
			System.exit(0);
		} catch (SQLException e) {
			infoBox("Error Closing! - Please try again!", "Warning!");
		}
	}
}
