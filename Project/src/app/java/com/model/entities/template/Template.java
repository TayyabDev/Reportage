package app.java.com.model.entities.template;

import java.util.List;

public interface Template {

	public String getTemplateName();

	public List<String> getColumnNames();

	public List<String> getColumnIds();

	public String getTableName();

	public List<String> getRequiredIds();
}
