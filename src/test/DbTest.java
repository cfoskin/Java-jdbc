package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.*;

import db_manager.EmployeeManager;
import db_manager.GUI;
/**
 * 
 * @author colum foskin - test that the db conn is closed properly on exit.
 *
 */
public class DbTest {
	Connection con;
	EmployeeManager eManager;
	GUI gui;
	
	@Before
	public void before() {
		gui = new GUI();
		eManager = new EmployeeManager(gui);
		con = eManager.connectToDb();
	}

	@After
	public void tearDown() throws Exception {
		if(!con.isClosed()){
			con.close();
		}
	}

	@Test
	public void exitShouldCloseConnection() {
		eManager.exit();
		try {
			assertTrue(con.isClosed());
		} catch (SQLException e) {
		}
	}

}
