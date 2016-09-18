package db_manager;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * 
 * @author Colum Foskin - 20062042 - 18/09/2016 Applied Computing - Distributed
 *         Systems - Assignment 1 GUI class which is used by MembersManager for
 *         the user to interact with the db.
 *
 */
public class GUI {
	JButton backButton, nextButton, createButton, updateButton, deleteButton, searchButton, 
	resetSearchButton, goToLastButton, goToFirstButton;
	ResultSet rs;
	JTextField nameTextField, lastNameTextField, emailTextField, searchTextField;
	JFrame f;
	JLabel firstNameLabel, lastNameLabel, emailAddressLabel, infoLabel, searchByNameLabel;

	/**
	 * Build a new GUI
	 */
	public GUI() {
		f = new JFrame();
		f.setTitle("Web Members Database Manager");

		firstNameLabel = new JLabel("First Name: ");
		nameTextField = new JTextField(20);

		lastNameLabel = new JLabel("Last Name: ");
		lastNameTextField = new JTextField(20);

		emailAddressLabel = new JLabel("Email Address: ");
		emailTextField = new JTextField(20);

		infoLabel = new JLabel("Enter new member info into the text fields above");

		searchByNameLabel = new JLabel("Enter name to search");
		searchTextField = new JTextField(15);

		backButton = new JButton("Previous");
		nextButton = new JButton("Next");
		createButton = new JButton("Create");
		updateButton = new JButton("Update");
		deleteButton = new JButton("Delete");
		searchButton = new JButton("Search");
		resetSearchButton = new JButton("Reset Search");
		goToFirstButton = new JButton("Go to First"); 
		goToLastButton = new JButton("Go to Last"); 

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(backButton);
		buttonPanel.add(nextButton);
		buttonPanel.add(createButton);
		buttonPanel.add(updateButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(goToFirstButton);
		buttonPanel.add(goToLastButton);

		JPanel searchPanel = new JPanel();
		searchPanel.add(searchByNameLabel);
		searchPanel.add(searchTextField);
		searchPanel.add(searchButton);
		searchPanel.add(resetSearchButton);

		JPanel mainPanel = new JPanel(new GridLayout(9, 2));

		mainPanel.add(firstNameLabel);
		mainPanel.add(nameTextField);
		mainPanel.add(lastNameLabel);
		mainPanel.add(lastNameTextField);
		mainPanel.add(emailAddressLabel);
		mainPanel.add(emailTextField);
		mainPanel.add(infoLabel);
		mainPanel.add(buttonPanel);
		mainPanel.add(searchPanel);
		f.add(mainPanel);
		f.setVisible(true);
		f.pack();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//center the frame next
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);
	}

}
