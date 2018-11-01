package test.java.com.model.utilities;

import org.junit.Test;

import app.java.com.model.entities.template.Template;
import app.java.com.model.utilities.createTemplate.CreateTemplateParamCSVImpl;
import app.java.com.model.utilities.createTemplate.CreateTemplateParamExcelImpl;
import app.java.com.model.utilities.createTemplate.CreateTemplateParamInterface;

public class CreateTemplateParamTest {

	String file1 = "./src/test/java/com/model/utilities/testFile1.xlsx";
	String file2 = "./src/test/java/com/model/utilities/exampleTemplate.csv";

	
	@Test
	public void testExcel() {
		CreateTemplateParamInterface templateParam = new CreateTemplateParamExcelImpl();
		Template template = templateParam.getTemplateNameColumns(file1, 0);
		System.out.println(template);
	}
	
	@Test
	public void testCSVl() {
		CreateTemplateParamInterface templateParam = new CreateTemplateParamCSVImpl();
		Template template = templateParam.getTemplateNameColumns(file2, 0);
		System.out.println(template);
	}

}
