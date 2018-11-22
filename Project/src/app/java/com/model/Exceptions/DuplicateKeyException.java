package app.java.com.model.Exceptions;

import app.java.com.model.database.api.QueryOnDatabase;
import app.java.com.model.database.api.SelectCommand;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

public class DuplicateKeyException extends InsertException {

	private List<String> duplicatedVals;
	private String table;
	private String primaryKeyColumn;

	public DuplicateKeyException() {
		super();
	}

	public DuplicateKeyException(String table, List<String> duplicatedVals) {
		this.duplicatedVals = duplicatedVals;
		this.table = table;

        try {
            primaryKeyColumn = QueryOnDatabase.getPrimaryKeyColumn(table).get(0);
            //List<List<String>> result = getAllDateForTable(primaryKeyColumn, table);
            //Integer[] conflictPoints = findConflictPoint(result);
            //List<String> differences = findMisMatchingColumnsBetweenConflictingRows(result, conflictPoints);



        } catch (SelectException e) {
            primaryKeyColumn = null;
            System.out.println("Error while trying to get the Primary key column");
        }
    }

    private Integer[] findDuplicateRow(List<List<String>> dataResult, int primaryKeyColumnIndex, List<String> duplicatedVals) {
        int rowIndex = 0;
        boolean tableEnd = false;

        Integer[] conflictPoints = new Integer[2];
        conflictPoints[0] = -1;
        conflictPoints[1] = -1;

        String primaryKey = null;

        while(!tableEnd) {

            if(null != dataResult) {
                primaryKey = dataResult.get(0).get(rowIndex);
            }

            if(primaryKey == null) {
                tableEnd = true;
                break;
            }

            // otherwise
            if(keyMap.containsKey(primaryKey)) {
                conflictPoints[0] = keyMap.get(primaryKey);
                conflictPoints[1] = rowIndex;
                return conflictPoints;
            } else { // otherwise
                keyMap.put(primaryKey, rowIndex);
            }

            rowIndex++;
        }

        return conflictPoints;
    }

    private List<Pair<String, Integer>> findMisMatchingColumnsBetweenConflictingRows(List<List<String>> result, Integer[] conflictPoints) {

	    // Here we have to compare the two conflicting rows and try to resolve them
        List<Pair<String, Integer>> differences = null;

        if(result == null) {
            return differences;
        }

        // First let's get the two rows from the database
        List<String> row1 = result.get(conflictPoints[0]);
        List<String> row2 = result.get(conflictPoints[1]);

        for(int column = 0; column < row1.size(); column++) {
            if(!row2.get(column).equals(row1.get(column))) {
                differences.add(new Pair<>(row2.get(column), conflictPoints[1]));
            }
        }

        if(differences == null) {
            // both rows were exact copies of each other and can be resolved by just ignoring the second row
        }

        return null;
    }

    public static List<List<String>> getAllDateForTable(String table) {

        SelectCommand command = new SelectCommand(table);
        List<List<String>> result = null;

        System.out.println(table);

        try {
            result = command.selectHandle();
        } catch (SelectException e) {
            System.out.println("Error fetching row from table " + table);
        }

        System.out.println(result);
        return result;
    }

	@Override
	public String getMessage() {
		String message = duplicatedVals + " already exists in "
				+ table + ".";
		return message;
	}

    @Override
    public String getTable() {
        return table;
    }

    public List<String> getDuplicatedVals() {
        return duplicatedVals;
    }

    public static void main(String[] world) {
	    getAllDateForTable("TestingErrors");
	    findPrimaryColumnIndex("idTestingErrors");

    }

    public static int findPrimaryColumnIndex(String primaryKeyColumn) {

        List<String> columns = null;
        int primaryColumnIndex = -1;

        try {
            columns = QueryOnDatabase.getColumns("TestingErrors");
        } catch (SelectException e) {
            e.printStackTrace();
        }

        System.out.println(columns);

        if(columns != null) {
            for(int x = 0; x < columns.size(); x++) {
                if(columns.get(x).equals(primaryKeyColumn)) {
                    primaryColumnIndex = x;
                }
            }
        }

        System.out.println(primaryColumnIndex);
        return primaryColumnIndex;
    }
}
