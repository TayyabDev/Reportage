package java.com.model.database.api;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import model.database.api.Command;

public class CreateCommand extends Command {

    /*
     * Run the Create command directly in the database
     */
    @Override
    public boolean handle(List<String> arguments) throws Exception {
        Connection conn;

        String query = "";
        if(arguments.size() == 1) {
            query = arguments[0];
        } else if(arguments.size() == 3) {

        }

        try {
            conn = app.java.com.model.database.api.ConnectDatabase.connect();
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            st.close();
            conn.close();
            return true;
        } catch (Exception e) {
            throw new app.java.com.model.Exceptions.CreateException();
        }
    }
}
