package app.java.com.model.usecase;

import app.java.com.model.entities.DataChanges;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.presenter.interfaces.FetchUserDataResultInterface;
import app.java.com.presenter.interfaces.UpdateUserDataResultInterface;

public class UpdateUserDataUseCase extends UseCase {

	private UpdateUserDataResultInterface resultInterface;
	private List<List<String>> originalData;
	private List<List<String>> newData;
	private List<DataChanges> changesList;
	private DataChanges dataChanges;

	public UpdateUserDataUseCase (UpdateUserDataResultInterface resultInterface, List<List<String>> originalData, List<List<String>> newData) {
		this.resultInterface = resultInterface;
		this.originalData = originalData;
		this.newData = newData;
		this.changesList = new ArrayList<>();
	}
	
	public UpdateUserDataUseCase (UpdateUserDataResultInterface resultInterface, DataChanges changes) {
		this.resultInterface = resultInterface;
		this.dataChanges = changes;
	}
	
	
	public void runCompare() {
		for (int i = 0; i < originalData.size(); i++) {
			for (int j = 0; j < originalData.get(i).size(); j++) {

				String originalValue = originalData.get(i).get(j);
				String newValue = newData.get(i).get(j);

				if(!originalValue.equals(newValue)) {
					DataChanges dataChanges = new DataChanges(i, j, originalValue, newValue);
					changesList.add(dataChanges);
				}
			}
		}
	}
	
	
	@Override
	public void run() {
		
	}
}
