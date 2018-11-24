package app.java.com.model.usecase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.presenter.interfaces.FetchUserDataResultInterface;
import app.java.com.presenter.interfaces.UpdateUserDataResultInterface;

public class UpdateUserDataUseCase extends UseCase {

	private UpdateUserDataResultInterface resultInterface;
	private DataChanges dataChanges;
	
	public class DataChanges {
		
		public List<List<String>> originalData;
		public List<List<String>> changedData;
		public List<List<List<String>>> cellsToChange = new ArrayList<List<List<String>>>();
		public DataChanges() {
			
		}
	}
	public UpdateUserDataUseCase (UpdateUserDataResultInterface resultInterface, List<List<String>> original, List<List<String>> changes) {
		this.resultInterface = resultInterface;
		dataChanges = new DataChanges();
		dataChanges.originalData = original;
		dataChanges.changedData = changes;
	}
	
	public UpdateUserDataUseCase (UpdateUserDataResultInterface resultInterface, DataChanges changes) {
		this.resultInterface = resultInterface;
		this.dataChanges = changes;
	}
	
	
	public void runCompare() {
		for (int i = 0; i < dataChanges.originalData.size(); i++) {
			List<List<String>> rowChanges = new ArrayList<List<String>>();
			for (int j = 0; j < dataChanges.originalData.get(0).size(); j++) {
				if (!dataChanges.originalData.get(i).get(j).equals(dataChanges.changedData.get(i).get(j))) {
					List<String> cellChanges = new ArrayList<String>();
					cellChanges.add(dataChanges.originalData.get(i).get(j));
					cellChanges.add(dataChanges.changedData.get(i).get(j));
					rowChanges.add(cellChanges);
				}
			}
			dataChanges.cellsToChange.add(rowChanges);
		}
	}
	
	
	@Override
	public void run() {
		
	}
}
