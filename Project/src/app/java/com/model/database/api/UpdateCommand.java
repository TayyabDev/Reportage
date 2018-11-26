package app.java.com.model.database.api;

import java.sql.SQLException;
import java.util.List;

import app.java.com.model.Exceptions.ConnectionFailedException;
import app.java.com.model.Exceptions.InvalidCommandException;
import app.java.com.model.Exceptions.UpdateException;

public class UpdateCommand extends Command {

	// update tableName set tagets[0] = vals[0], ... where constraints[0], ...;
	private List<String> targets;
	private String tableName;
	private List<String> constraints;
	private List<String> vals; // need to include '' before passing. don't change it

	public UpdateCommand(String tableName, List<String> targets, List<String> vals,
			List<String> constraints) {
		this.tableName = tableName;
		this.constraints = constraints;
		this.targets = targets;
		this.vals = vals;
	}

	private String matchTargetVals() throws InvalidCommandException {
		if (targets.size() != vals.size()) {
			// command invalid
			throw new InvalidCommandException();
		}
		String res = "";
		for (int i = 0; i < targets.size(); i++) {
			res += "`" + targets.get(i) + "`" + " = " + vals.get(i) + ", ";
		}
		return res.substring(0, res.length() - 2);
	}

	private String formatConstraints() throws InvalidCommandException {
		if (constraints.size() == 0) {
			throw new InvalidCommandException();
		} else {
			String constraintsStr = "";
			for (int i = 0; i < constraints.size(); i++) {
				constraintsStr += constraints.get(i) + " AND ";
			}
			return constraintsStr.substring(0, constraintsStr.length() - 5);
		}
	}

	@Override
	public boolean handle() throws UpdateException {
		String sql;
		try {
			sql = "update "
					+ tableName
					+ " set "
					+ matchTargetVals()
					+ " where "
					+ formatConstraints()
					+ ";";
		} catch (InvalidCommandException e) {
			throw new UpdateException();
		}
		try {
			System.out.println(sql);
			runExecuteUpdate(sql);
		} catch (SQLException | ConnectionFailedException e) {
			throw new UpdateException();
		}

		return true;
	}
}
