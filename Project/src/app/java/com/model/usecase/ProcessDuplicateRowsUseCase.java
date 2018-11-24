package app.java.com.model.usecase;

import app.java.com.model.Exceptions.InsertException;
import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.presenter.interfaces.ResolveConflictResultInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProcessDuplicateRowsUseCase extends UseCase {

    private ResolveConflictResultInterface resolveConflictResultInterface;
    private List<InsertException> exceptions;

    public ProcessDuplicateRowsUseCase(ResolveConflictResultInterface resolveConflictResultInterface,
                                       List<InsertException> exceptionList) {
        this.resolveConflictResultInterface = resolveConflictResultInterface;
        this.exceptions = exceptionList;
    }

    @Override
    public void run() {

        for(InsertException exception : exceptions) {

            if(!exception.getType().equals("DuplicateKeyException")) {
                continue;
            }

            // otherwise duplicate exception
            List<List<String> > result = getAllDateForTable(exception.getTable());
            List<String> primaryColumns = null;
            System.out.println(exception.getTable() + " is the table");
            String formattedTable = exception.getTable().replaceAll("`", "");
            try {
                primaryColumns = new SelectCommand(formattedTable).getPrimaryKeyColumn();
            } catch (SelectException e) {
                e.printStackTrace();
            }

            List<Integer> primaryKeyColumnIndex = findPrimaryColumnIndex(primaryColumns, formattedTable);

            int rowNum = findDuplicateRow(result, primaryKeyColumnIndex, exception.getErrorValues());

            System.out.println(rowNum + " is the row");

            boolean isSame = compareRows(result.get(rowNum), exception.getErrorValues());

            if(isSame) {
                exception.setFixed(true);
            }

        }

        // done the usecase - pass info to the presenter
        resolveConflictResultInterface.onSuccessProcessingDuplicateRows(exceptions);
    }

    private boolean compareRows(List<String> originalRow, List<String> duplicateRow) {
//        for(int index = 0; index < originalRow.size(); index++) {
//            if(!originalRow.get(index).equals(duplicateRow.get(index))) {
//                return false;
//            }
//        }

        return originalRow.equals(duplicateRow);
    }

    private int findDuplicateRow(List<List<String>> dataResult, List<Integer> primaryKeysIndex, List<String> duplicatedVals) {

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

    public List<List<String>> getAllDateForTable(String table) {

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


    public List<Integer> findPrimaryColumnIndex(List<String> primaryKeyColumn, String table) {

        System.out.println(primaryKeyColumn + " are the primary keys");


        List<String> columns = null;
        List<Integer> result = new ArrayList<>();

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
