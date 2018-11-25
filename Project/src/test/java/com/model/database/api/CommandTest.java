package test.java.com.model.database.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.AddColumnCommand;
import app.java.com.model.database.api.Command;
import app.java.com.model.database.api.CreateCommand;
import app.java.com.model.database.api.DropCommand;
import app.java.com.model.database.api.InsertCommand;
import app.java.com.model.database.api.SelectCommand;

/*
 * functio testing testing the database api functions tested all functions in CreateCommand,
 * DropCommand, InsertCommand, AddColumnCommand need to add more cases for SelectCommand
 */
public class CommandTest {

	// create test1 table only given table name
	// create test2 table given table name and list of attributes name
	// create test3 table which will be used to test alterCommand
	// create test4 table to test the select command functions
	private final String table1 = "test1";
	private final String table2 = "test2";
	private final String table3 = "test3";
	private final String table4 = "test4";


	@Test
	/*
	 * create a table test with default attribute id insert into test1 (id) values 1 select * from
	 * table test1 drop table test
	 */
	public void testCommands1() {
		Command createComm = new CreateCommand(table1);
		try {
			// will create a test table with attribute id
			createComm.handle();
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// test it with a insert command
		List<String> attr = new ArrayList<>();
		List<String> val = new ArrayList<>();
		Command insertComm = new InsertCommand(table1, attr, val);
		((InsertCommand) insertComm).addAttrVal("id", "1");
		try {
			insertComm.handle();
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// select * from test1 should give [['1']]
		Command selectComm = new SelectCommand(table1);
		List<List<String>> res = null;
		try {
			res = ((SelectCommand) selectComm).selectHandle();
		} catch (SelectException e) {
			fail(e.getMessage());
		}
		assertEquals(res.get(0).get(0), "1");
		// drop the test1 table in database
		Command dropComm = new DropCommand(table1);
		try {
			dropComm.handle();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/*
	 * create a table test2 with attributes [firstName, lastName] insert into test2 (firstName,
	 * lastName) values (j, l) insert into test2 (firstName, lastName) values (v, p) select * from
	 * table test1 -> [[j, l] [v, p]] drop table test
	 */
	@Test
	public void testCommand2() {
		List<String> attrCons = new ArrayList<>();
		attrCons.add("id int not null auto_increment primary key");
		attrCons.add("firstName varchar(255)");
		attrCons.add("lastName varchar(255)");
		Command createComm = new CreateCommand(table2, attrCons);
		try {
			// will create a test table with attribute id
			createComm.handle();
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// inserting 2 values to table
		List<String> attr = new ArrayList<>();
		attr.add("firstName");
		attr.add("lastName");
		List<String> val1 = new ArrayList<>();
		val1.add("j");
		val1.add("l");
		List<String> val2 = new ArrayList<>();
		val2.add("v");
		val2.add("p");
		Command insertComm1 = new InsertCommand(table2, attr, val1);
		try {
			assertEquals(1, ((InsertCommand) insertComm1).insertHandle());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		Command insertComm2 = new InsertCommand(table2, attr, val2);
		try {
			assertEquals(2, ((InsertCommand) insertComm2).insertHandle());
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// select * from test1 should give [[1,j,l], [2,v, p]]
		Command selectComm = new SelectCommand(table2);
		List<List<String>> res = null;
		try {
			res = ((SelectCommand) selectComm).selectHandle();
		} catch (SelectException e) {
			fail(e.getMessage());
		}
		val1.add(0, "1");
		val2.add(0, "2");
		assertEquals(res.get(0), val1);
		assertEquals(res.get(1), val2);
		// drop the test1 table in database
		Command dropComm = new DropCommand(table2);
		try {
			dropComm.handle();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	/*
	 * create a table test3 with default attribute id add column firstName varchar(255) to test3 get
	 * the columnIds from test3 -> [id, firstName](test getColumnIds in SelectCommand) get types
	 * from test3 -> [int, varchar] drop table test3
	 */
	public void testCommand3() {
		Command createComm = new CreateCommand(table3);
		try {
			// will create a test table with attribute id
			createComm.handle();
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// add new columns firstName varchar(255), age int(8)
		List<String> newColName = new ArrayList<>();
		List<String> newCons = new ArrayList<>();
		Command alterComm = new AddColumnCommand(table3, newColName, newCons);
		((AddColumnCommand) alterComm).addNewColCons("firstName", "varchar(255)");
		((AddColumnCommand) alterComm).addNewColCons("age", "int(8)");
		try {
			alterComm.handle();
		} catch (Exception e) {
			fail(e.getMessage());
		}

		Command selectComm = new SelectCommand(table3);
		List<String> resCols = new ArrayList<String>();
		List<String> resTypes = new ArrayList<String>();
		resCols.add("id");
		resCols.add("firstName");
		resCols.add("age");
		resTypes.add("int");
		resTypes.add("varchar");
		resTypes.add("int");
		try {
			List<String> ids = ((SelectCommand) selectComm).getColumnIds();
			assertEquals(ids, resCols);
		} catch (SelectException e) {
			fail(e.getMessage());
		}
		try {
			List<String> cons = ((SelectCommand) selectComm).getConstraints();
			assertEquals(cons, resTypes);
		} catch (SelectException e) {
			fail(e.getMessage());
		}

		// drop the test1 table in database
		Command dropComm = new DropCommand(table3);
		try {
			dropComm.handle();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testCommand4() {
		Command createComm = new CreateCommand(table4);
		try {
			// will create a test table with attribute id
			createComm.handle();
		} catch (Exception e) {
			fail(e.getMessage());
		}
		// test on different SelectCommand constructor
		SelectCommand commGiventableName = new SelectCommand(table4);

		List<String> target = new ArrayList<String>();
		target.add("id");
		SelectCommand commGivenNameTarget = new SelectCommand(target, table4);

		List<String> cons = new ArrayList<String>();
		SelectCommand commGivenNameTargetCons = new SelectCommand(target, table4, cons);

		try {
			List<List<String>> QueryRes = commGiventableName.selectHandle();
			List<List<String>> expectedRes = new ArrayList<List<String>>();
			assertEquals(expectedRes, QueryRes);
		} catch (SelectException e) {
			fail(e.getMessage());
		}

		try {
			List<List<String>> QueryRes = commGivenNameTarget.selectHandle();
			List<List<String>> expectedRes = new ArrayList<List<String>>();
			assertEquals(expectedRes, QueryRes);
		} catch (SelectException e) {
			fail(e.getMessage());
		}

		try {
			List<List<String>> QueryRes = commGivenNameTargetCons.selectHandle();
			List<List<String>> expectedRes = new ArrayList<List<String>>();
			assertEquals(expectedRes, QueryRes);
		} catch (SelectException e) {
			fail(e.getMessage());
		}

		// drop the test1 table in database
		Command dropComm = new DropCommand(table4);
		try {
			dropComm.handle();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
