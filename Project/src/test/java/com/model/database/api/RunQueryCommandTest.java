package test.java.com.model.database.api;

import static org.junit.Assert.*;

import org.junit.Test;

import app.java.com.model.database.api.Command;
import app.java.com.model.database.api.SelectCommand;

public class RunQueryCommandTest {
	Command cmd = new SelectCommand();
	String query = "SELECT * FROM Account";
	String expectedResult = "accountId,password,userName,accountType,registered";
	String result;
	
	@Test
	public void testCommand() throws Throwable {
		result = cmd.runExecuteQuery(query);
		String [] splitResult = result.split("\n");
		assertEquals(expectedResult, splitResult[0]);
	}

}
