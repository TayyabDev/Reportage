package java.com.model.database.api;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class DropCommand implements model.database.api.Command {

    /*
     * Run the drop command directly in the database
     */
    @Override
    public boolean handle(List<String> arguments) throws Exception {
        Connection conn;
        String query = arguments[0];
        try {
            conn = app.java.com.model.database.api.ConnectDatabase.connect();
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            st.close();
            conn.close();
            return true;
        } catch (Exception e) {
            throw new app.java.com.model.Exceptions.DropException();
        }
    }
}
