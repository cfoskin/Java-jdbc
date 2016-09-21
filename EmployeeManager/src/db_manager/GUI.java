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
	JTextField ssnTextField, bDateTextField, nameTextField, addressTextField, sexTextField, worksForTextField,
			managesTextField, supervisesTextField, searchTextField, salaryTextField;

	JButton backButton, nextButton, goToFirstButton, goToLastButton, updateButton, deleteButton, searchButton,
			resetSearchButton, createButton, exitButton, clearInfoButton;

	JLabel ssnLabel, bDateLabel, nameLabel, addressLabel, sexLabel, worksForLabel, managesLabel, supervisesLabel,
			searchByNameLabel, salaryLabel;

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
		f.setBounds(100, 100, 635, 450);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBounds(68, 65, 240, 177);
		f.getContentPane().add(panel_1);

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

		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);

		addressLabel = new JLabel("Address");
		panel_5.add(addressLabel);

		addressTextField = new JTextField();
		addressTextField.setColumns(10);
		panel_5.add(addressTextField);

		panel = new JPanel();
		panel_1.add(panel);

		salaryLabel = new JLabel("Salary");
		panel.add(salaryLabel);

		salaryTextField = new JTextField();
		salaryTextField.setColumns(10);
		panel.add(salaryTextField);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(335, 65, 227, 177);
		f.getContentPane().add(panel_6);

		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8);

		sexLabel = new JLabel("Sex     ");
		panel_8.add(sexLabel);

		sexTextField = new JTextField();
		sexTextField.setColumns(10);
		panel_8.add(sexTextField);

		JPanel panel_9 = new JPanel();
		panel_6.add(panel_9);

		worksForLabel = new JLabel("Works For");
		panel_9.add(worksForLabel);

		worksForTextField = new JTextField();
		worksForTextField.setColumns(10);
		panel_9.add(worksForTextField);

		JPanel panel_10 = new JPanel();
		panel_6.add(panel_10);

		managesLabel = new JLabel("Manages");
		panel_10.add(managesLabel);

		managesTextField = new JTextField();
		managesTextField.setColumns(10);
		panel_10.add(managesTextField);

		JPanel panel_11 = new JPanel();
		panel_6.add(panel_11);

		supervisesLabel = new JLabel("Supervises");
		panel_11.add(supervisesLabel);

		supervisesTextField = new JTextField();
		supervisesTextField.setColumns(10);
		panel_11.add(supervisesTextField);

		JPanel panel_12 = new JPanel();
		panel_12.setBounds(100, 254, 500, 66);
		f.getContentPane().add(panel_12);

		backButton = new JButton("Back");
		panel_12.add(backButton);

		nextButton = new JButton("Next");
		panel_12.add(nextButton);

		goToFirstButton = new JButton("Go to First");
		panel_12.add(goToFirstButton);

		goToLastButton = new JButton("Go to Last");
		panel_12.add(goToLastButton);
		
		
		clearInfoButton = new JButton("Clear");
		panel_12.add(clearInfoButton);
		
		createButton = new JButton("Create");
		panel_12.add(createButton);
		
		updateButton = new JButton("Update");
		panel_12.add(updateButton);

		deleteButton = new JButton("Delete");
		panel_12.add(deleteButton);
		
		exitButton = new JButton("Exit");
		panel_12.add(exitButton);

		JPanel panel_13 = new JPanel();
		panel_13.setBounds(33, 347, 282, 43);
		f.getContentPane().add(panel_13);

		searchByNameLabel = new JLabel("Search By Name:");
		panel_13.add(searchByNameLabel);
		searchTextField = new JTextField();
		searchTextField.setColumns(10);
		panel_13.add(searchTextField);

		JPanel panel_14 = new JPanel();
		panel_14.setBounds(311, 347, 282, 43);
		f.getContentPane().add(panel_14);

		JPanel panel_15 = new JPanel();
		panel_14.add(panel_15);
		
		searchButton = new JButton("Search");
		panel_15.add(searchButton);

		resetSearchButton = new JButton("Reset Search");
		panel_14.add(resetSearchButton);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(150, 17, 331, 36);
		f.getContentPane().add(panel_4);

		nameLabel = new JLabel("Name");
		panel_4.add(nameLabel);

		nameTextField = new JTextField();
		nameTextField.setColumns(20);
		panel_4.add(nameTextField);
	}

}
