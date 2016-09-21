package db_manager;

import java.awt.EventQueue;

/**
 * 
 * @author Colum Foskin - 20062042 - 18/09/2016 Applied Computing - Distributed
 *         Systems - Assignment 1 - Class to run the Application.
 */
public class App {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 GUI gui = new GUI();
					gui.f.setVisible(true);
					new EmployeeManager(gui);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
