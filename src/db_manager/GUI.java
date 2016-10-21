package db_manager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Colum Foskin - 20062042 - 18/09/2016 Applied Computing - Distributed
 *         Systems - Assignment 1 GUI class which is used by MembersManager for
 *         the user to interact with the db.
 *
 */
public class GUI {
	JFrame f;
	JTextField ssnTextField, bDateTextField, nameTextField, addressTextField, sexTextField, searchTextField,
			salaryTextField;

	JButton backButton, nextButton, goToFirstButton, goToLastButton, deleteButton, searchButton, resetSearchButton,
			createButton, exitButton;

	JLabel ssnLabel, bDateLabel, nameLabel, addressLabel, sexLabel, searchByNameLabel, salaryLabel;

	JPanel panel_1, panel_2, panel_3, panel;

	/**
	 * Create the GUI.
	 */
	public GUI() {
		buildGui();
	}

	/**
	 * Build the GUI
	 */
	private void buildGui() {
		f = new JFrame();
		f.setTitle(" Employee Manager Application");
		f.setBounds(100, 100, 568, 360);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBounds(55, 16, 448, 174);
		f.getContentPane().add(panel_1);

		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);

		nameLabel = new JLabel("Name");
		panel_4.add(nameLabel);

		nameTextField = new JTextField();
		nameTextField.setColumns(20);
		panel_4.add(nameTextField);

		panel_2 = new JPanel();
		panel_1.add(panel_2);

		ssnLabel = new JLabel("SSN");
		panel_2.add(ssnLabel);

		ssnTextField = new JTextField();
		ssnTextField.setColumns(10);
		panel_2.add(ssnTextField);

		panel_3 = new JPanel();
		panel_1.add(panel_3);

		bDateLabel = new JLabel("Date");
		panel_3.add(bDateLabel);

		bDateTextField = new JTextField();
		bDateTextField.setColumns(10);
		panel_3.add(bDateTextField);

		JPanel panel_8 = new JPanel();
		panel_1.add(panel_8);

		sexLabel = new JLabel("Sex     ");
		panel_8.add(sexLabel);

		sexTextField = new JTextField();
		sexTextField.setColumns(10);
		panel_8.add(sexTextField);

		panel = new JPanel();
		panel_8.add(panel);

		salaryLabel = new JLabel("Salary");
		panel.add(salaryLabel);

		salaryTextField = new JTextField();
		salaryTextField.setColumns(10);
		panel.add(salaryTextField);

		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);

		addressLabel = new JLabel("Address");
		panel_5.add(addressLabel);

		addressTextField = new JTextField();
		addressTextField.setColumns(25);
		panel_5.add(addressTextField);

		JPanel panel_12 = new JPanel();
		panel_12.setBounds(32, 202, 525, 66);
		f.getContentPane().add(panel_12);

		backButton = new JButton("Back");
		panel_12.add(backButton);

		nextButton = new JButton("Next");
		panel_12.add(nextButton);

		goToFirstButton = new JButton("Go to First");
		panel_12.add(goToFirstButton);

		goToLastButton = new JButton("Go to Last");
		panel_12.add(goToLastButton);

		createButton = new JButton("Create Fixed Record");
		panel_12.add(createButton);

		deleteButton = new JButton("Delete");
		panel_12.add(deleteButton);

		exitButton = new JButton("Exit");
		panel_12.add(exitButton);

		JPanel panel_13 = new JPanel();
		panel_13.setBounds(32, 280, 257, 43);
		f.getContentPane().add(panel_13);

		searchByNameLabel = new JLabel("Search By Name:");
		panel_13.add(searchByNameLabel);
		searchTextField = new JTextField();
		searchTextField.setColumns(10);
		panel_13.add(searchTextField);

		JPanel panel_14 = new JPanel();
		panel_14.setBounds(301, 280, 246, 43);
		f.getContentPane().add(panel_14);

		searchButton = new JButton("Search");
		panel_14.add(searchButton);

		resetSearchButton = new JButton("Reset Search");
		panel_14.add(resetSearchButton);
	}

}
