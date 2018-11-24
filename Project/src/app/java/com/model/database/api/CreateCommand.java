package app.java.com.model.database.api;

import java.util.ArrayList;
import java.util.List;
import app.java.com.model.database.api.Command;

import app.java.com.model.database.api.CreateCommand;

import app.java.com.model.Exceptions.CreateException;

public class CreateCommand extends Command {

	private String tableName;
	private List<String> attrs;
	private static String defaultAttr = "id int not null auto_increment primary key";

	/*
	 * In case you have a raw query you want to run
	 */
	public CreateCommand() {

    }

    /*
	 * to create an empty table given the table name
	 */
	public CreateCommand(String tableName) {
		this.tableName = tableName;
		this.attrs = new ArrayList<String>();
	}

	/*
	 * to create the specific table given the specific attributes
	 */
	public CreateCommand(String tableName, List<String> attrs) {
		this.tableName = tableName;
		this.attrs = attrs;
	}
	
	public boolean addAttr(String attr) {
		return this.attrs.add(attr);
	}
	
	public static String formulateAttrs(List<String> attrs) {
		String res = "";
		if (attrs.size() == 0) {
			attrs.add(defaultAttr);
		}
		for (String s : attrs) {
			res = res + s + ", ";
		}
		return res.substring(0, res.length()-2);
		
	}
	
	@Override
	public boolean handle() throws CreateException {
		String sql = "create table " + tableName + " ( " + formulateAttrs(attrs) + " );" ;
		try {
			runExecuteUpdate(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateException(tableName);
		}
	}
}
