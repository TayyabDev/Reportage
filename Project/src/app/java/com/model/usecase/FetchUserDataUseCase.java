package app.java.com.model.usecase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.presenter.interfaces.FetchUserDataResultInterface;

public class FetchUserDataUseCase extends UseCase {

	private String query = null;
	private List<String> selections = null;
	private FetchUserDataResultInterface resultInterface;
	
	public FetchUserDataUseCase (FetchUserDataResultInterface resultInterface, String query) {
		this.resultInterface = resultInterface;
		this.query = query;
	}
	
	public FetchUserDataUseCase (FetchUserDataResultInterface resultInterface, List<String> selections) {
		this.resultInterface = resultInterface;
		this.selections = selections;
	}
	
	@Override
	public void run() {
		List<List<String>> data = null;
		
		SelectCommand selectCommand = new SelectCommand(selections.get(0));
		
		try {
			if (this.query != null) {	
				data = getListFromResultSet(SelectCommand.RunExecuteQuery(query));
			} 
			else if (this.selections != null) {
				data = selectCommand.selectHandle();
			}
        } catch (SelectException e) {
            resultInterface.onErrorSelectTable("failed when select " + e.getMessage());
        } catch (Exception e) {
        	resultInterface.onErrorSelectTable("failed when executing query " + e.getMessage());
		}
		
		if (data == null) {
			resultInterface.onErrorSelectTable("No Data Returned");
		}
		else {
			resultInterface.onSuccessSelectTable(data);
		}
	}
	
	private static List<List<String>> getListFromResultSet(ResultSet rs) {
		List<List<String>> result = new ArrayList<List<String>>();
		int columns = 0;
		try {
			columns = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				List<String> row = new ArrayList<String>();
				for (int i = 1; i < columns + 1; i++) {
					row.add(rs.getString(i));
				}
				result.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
