package test.java.com.view.interfaces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;

import org.junit.jupiter.api.Test;

import app.java.com.model.entities.account.TeqAccount;
import app.java.com.view.ui.createReportViews.Report;

public class ReportViewTest {
	TeqAccount fakeAccount = new TeqAccount(1, "ben", "ben", true);
	Report reportTest = new Report(new JFrame(), fakeAccount);
	String wrongQuery = "ALTER TABLE statement";
	String properQuery = "SELECT * FROM Account";
	String fakeReport = "";

	@Test
	public void testInvalidQuery() {
		boolean result = reportTest.isFieldsValid(wrongQuery);
		assertFalse(result);
	}

	@Test
	public void testValidQuery() {
		boolean result = reportTest.isFieldsValid(properQuery);
		assertTrue(result);
	}

	@Test
	public void testTemplateName() throws FileNotFoundException {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String fileName = "report-";
		fileName += timeStamp;
		fileName += ".csv";
		String result = reportTest.sendReport(fakeReport);
		assertEquals(result, fileName);
	}

}
