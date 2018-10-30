package java.com.model.database.api;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class InsertCommand implements model.database.api.Command {

    @Override
    public boolean handle(List<String> arguments) throws Exception {

        if(arguments.size() != 3) {
            throw new app.java.com.model.Exceptions.AlterException();
        }

        String tableName = arguments[0];
        String columnName = arguments[1];
        String constraint = arguments[2];

        String sql = "alter table " + tableName
                + " add column " + columnName + " "+ constraint + ";";
        Connection conn;
        try {
            conn = app.java.com.model.database.api.ConnectDatabase.connect();
            Statement st = conn.createStatement();
            st.execute(sql);
            st.close();
            conn.close();
            return true;
        } catch (Exception e) {
            throw new app.java.com.model.Exceptions.AlterException(tableName, columnName);
        }
    }
}
