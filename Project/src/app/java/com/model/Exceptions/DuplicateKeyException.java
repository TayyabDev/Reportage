package app.java.com.model.Exceptions;

import app.java.com.model.database.api.QueryOnDatabase;
import app.java.com.model.database.api.SelectCommand;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

public class DuplicateKeyException extends InsertException {

	private List<String> duplicatedVals;
	private String table;
	private List<String> primaryKeyColumn;

	public DuplicateKeyException() {
		super();
	}

	public DuplicateKeyException(String table, List<String> duplicatedVals) {
		this.duplicatedVals = duplicatedVals;
		this.table = table;

        try {
            primaryKeyColumn = new SelectCommand(table).getPrimaryKeyColumn();
            List<List<String>> result = getAllDateForTable(table);
            //Integer[] conflictPoints = findConflictPoint(result);
            //List<String> differences = findMisMatchingColumnsBetweenConflictingRows(result, conflictPoints);

        } catch (SelectException e) {
            primaryKeyColumn = null;
            System.out.println("Error while trying to get the Primary key column");
        }
    }

    private static int findDuplicateRow(List<List<String>> dataResult, List<Integer> primaryKeysIndex, List<String> duplicatedVals) {

        for(int x = 0; x < dataResult.size(); x++) {

            for(int columnIndex = 0; columnIndex < primaryKeysIndex.size(); columnIndex++) {

                String originalRowVal = dataResult.get(x).get(primaryKeysIndex.get(columnIndex));
                String duplicateRowVal = duplicatedVals.get(primaryKeysIndex.get(columnIndex));

                if(originalRowVal.equals(duplicateRowVal)){
                    return x;
                }
            }
        }

        return -1;
    }

    private List<Pair<String, Integer>> differenceInRows(List<List<String>> result, int duplicateRow, List<String> duplicatedVals) {

	    // Here we have to compare the two conflicting rows and try to resolve them
        List<Pair<String, Integer>> differences = null;

        if(result == null) {
            return differences;
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
	    List<List<String> > result = getAllDateForTable("TestingErrors");
	    List<String> primaryColumns = new ArrayList<>();
	    primaryColumns.add("idTestingErrors");

	    List<Integer> primaryKeyColumnIndex = findPrimaryColumnIndex(primaryColumns);

	    List<String> duplicateVals = Arrays.asList("5","5","5");
        int rowNum = findDuplicateRow(result, primaryKeyColumnIndex, duplicateVals);
        System.out.println(rowNum + " is the row");


	}

    public static List<Integer> findPrimaryColumnIndex(List<String> primaryKeyColumn) {

        System.out.println(primaryKeyColumn + " are the primary keys");


        List<String> columns = null;
        List<Integer> result = new ArrayList<>();

        String table = "TestingErrors";

        try {
            columns = new SelectCommand(table).getColumns();
        } catch (SelectException e) {
            e.printStackTrace();
        }

        System.out.println(columns);

        int start = 0;

        if(columns != null) {
            for(int x = 0; x < columns.size(); x++) {
                if(start < primaryKeyColumn.size() && columns.get(x).equals(primaryKeyColumn.get(start))) {
                    result.add(x);
                    start++;
                }
            }
        }

        System.out.println(result);
        return result;
    }
}
