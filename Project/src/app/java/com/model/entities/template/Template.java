package app.java.com.model.entities.template;

import java.util.List;

public abstract class Template {

	public abstract String getTemplateName();

	public abstract List<String> getColumnNames();

	public abstract List<String> getColumnIds();

	public abstract String getTableName();

	public abstract List<String> getRequiredIds();
}
