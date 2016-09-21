package db_manager;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * 
 * @author Colum Foskin - 20062042 - 18/09/2016 Applied Computing - Distributed
 *         Systems - Assignment 1 Employee manager class which allows the user
 *         to perform CRUD & search actions on a database called Assignment1.
 *         The credentials for this are as follows: username is root password is
 *         "". The mysql port is 3306 - this may differ on another machine.
 *
 */
public class EmployeeManager {
	private ResultSet rs;
	private GUI gui;
	private String Ssn, birthDate, name, address, salary, sex, worksFor, manages, supervises;
	private Statement st;
	private Connection con;

	/**
	 * Constructor which opens the db connection
	 */
	public EmployeeManager(GUI gui) {
		this.gui = gui;
		connectToDb();// make a new connection to the test db
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

		gui.updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateEmployee();
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
		gui.clearInfoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearEmployeeInfo();
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
			loadEmployees();// load them up
		} catch (Exception e) {
			infoBox("Error Connectimg to the database - Please try again!", "Warning!");
		}
		return con;
	}

	/**
	 * load the employees - used at start-up and after any CRUD action is
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
			System.out.println(e);
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
			System.out.println(e);
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
			System.out.println(e);
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
			System.out.println(e);
		}
		setTextFields();
	}

	/**
	 * create a new employee in the database
	 */
	private void createNewEmployee() {
		String createQuery = "INSERT INTO Employee (Ssn, Bdate, Name, Address, Salary, Sex, Works_For, Manages, Supervises) VALUES ("
				+ "'" + gui.ssnTextField.getText() + "', " + "'" + gui.bDateTextField.getText() + "', " + "'"
				+ gui.nameTextField.getText() + "'," + "'" + gui.addressTextField.getText() + "'," + "'"
				+ gui.salaryTextField.getText() + "'," + "'" + gui.sexTextField.getText() + "'," + "'"
				+ gui.worksForTextField.getText() + "'," + "'" + gui.managesTextField.getText() + "'," + "'"
				+ gui.supervisesTextField.getText() + "'" + ");";
		executeUpdateQuery(createQuery, "Employee has been added!");
	}

	/**
	 * update an existing employee in the database
	 */
	private void updateEmployee() {
		String updateQuery = "UPDATE Employee SET Ssn = " + "'" + gui.ssnTextField.getText() + "', Bdate = " + "'"
				+ gui.bDateTextField.getText() + "', Name = " + "'" + gui.nameTextField.getText() + "', Address = "
				+ "'" + gui.addressTextField.getText() + "', Salary = " + "'" + gui.salaryTextField.getText()
				+ "', Sex = " + "'" + gui.sexTextField.getText() + "'" + " WHERE Ssn = " + Ssn + ";";
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
			goToFirst();
		} catch (Exception e) {
			infoBox("Employee not found!", "Warning!");
		}
	}

	/**********
	 * This section contains the utility methods used by the GUI features
	 ***********/

	/**
	 * Clear the employee info text fields before creating a new one.
	 */
	private void clearEmployeeInfo() {
		Ssn = "";
		birthDate = "";
		name = "";
		address = "";
		salary = "";
		sex = "";
		worksFor = "";
		manages = "";
		supervises = "";
		setTextFields();
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
		gui.worksForTextField.setText(worksFor);
		gui.managesTextField.setText(manages);
		gui.supervisesTextField.setText(supervises);
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
		worksFor = rs.getString("Works_For");
		manages = rs.getString("Manages");
		supervises = rs.getString("Supervises");
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
				con.close();
			System.exit(0);
		} catch (SQLException e) {
			infoBox("Error Closing! - Please try again!", "Warning!");
		}
	}
}
