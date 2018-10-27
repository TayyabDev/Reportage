package com.app.model.entities.template;

import java.util.List;

public abstract class Template {
    
    public abstract List<String> getTemplateColumnNames();

    public abstract List<String> getColumnIds();
}
