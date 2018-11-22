package app.java.com.model.usecase;

import java.util.List;

import app.java.com.model.entities.template.Template;
import app.java.com.model.utilities.templateFile.TemplateFileCsvImpl;
import app.java.com.model.utilities.templateFile.TemplateFileExcelImpl;
import app.java.com.model.utilities.templateFile.TemplateFileInterface;
import app.java.com.presenter.interfaces.CreateTemplateResultInterface;

public class CreateTemplateUsingFileUseCase extends UseCase{

	
	private String filePath;
	private CreateTemplateResultInterface resultInteface;
	private boolean lock = false;
	private String sheetName;
	
	
	public CreateTemplateUsingFileUseCase(CreateTemplateResultInterface resultInterface, String filePath) {
		this.resultInteface = resultInterface;
		this.filePath = filePath;
	}
	
	
	@Override
	public void run() {
		getTemplate(filePath);
		
	}

	public void sheetSelected(String sheetName) {
		this.sheetName = sheetName;
		this.lock = true;
	}
	
	private Template getTemplate(String filePath) {
		String formulatedFileName = filePath.replace("\\", "\\\\");
		TemplateFileInterface file = null;
		if (formulatedFileName.substring(formulatedFileName.length()-4).equals("xlsx")) {
			// get the sheetNames ask client to choose
			file = new TemplateFileExcelImpl(filePath);
			List<String> sheetNames = ((TemplateFileExcelImpl) file).getSheetNames();
			resultInteface.fetchSheetNames(this, sheetNames);
			
			// wait client to choose sheet
			while ((!lock) && sheetName == null) {}
			
			// cancel the dialog, not selected sheet
			if (lock && sheetName == null) {
				return null;
			}
			
			// get here only when selected a sheet
			
			
			
		}
		else if (formulatedFileName.substring(formulatedFileName.length()-3).equals("csv")) {
			file = new TemplateFileCsvImpl(formulatedFileName);
		}
		return null;
	}
}
