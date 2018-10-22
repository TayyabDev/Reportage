package com.test.model.database;

import static org.junit.Assert.*;

import org.junit.Test;
import com.app.model.database.QueryOnDatabase;

public class QueryOnDatabaseTest {
	private String sql1Create = "create table test1("
			+ "testId int primary key);";
	
	private String sql1Drop = "drop table test1;";
	
	private String sql2Create = "create table test1("
			+ "testId int primary key,"
			+ "constraint a"
			+ "foreign key (testId)"
			+ "references a(testId));";
	
	@Test
	public void testCreateOneTableSuccessful() {
		assertTrue(QueryOnDatabase.runCreate(sql1Create));
		assertTrue(QueryOnDatabase.runDrop(sql1Drop));
	}
	
	@Test
	/*
	 * duplicate tables
	 */
	public void testCreateDuplicateTable() {
		if (QueryOnDatabase.runCreate(sql1Create)) {
			assertFalse(QueryOnDatabase.runCreate(sql1Create));
		}
		assertTrue(QueryOnDatabase.runDrop(sql1Drop));
	}
	
	@Test
	/*
	 * wrong constraint
	 */
	public void testCreateWrongConstaintTable() {
		assertFalse(QueryOnDatabase.runCreate(sql2Create));
	}
}
