package model.database.api;

import java.util.List;

public abstract class Command {

    /*
    * formulate a list of String into a single string with comma separate them
    * and each element in the list will wrap with quote
    */
    public String formulateString(List<String> listOfString, char quote) {
        String result = "";
        for (String s : listOfString) {
        result = result + quote + s + quote +",";
        }
        return "(" + result.substring(0, result.length()-1) + ")";
    }

    /*
    * formulate a list of String into a single string with comma separate them
    * and each element in the list will wrap with single quotation mark (')
    */
    public String formulateData(List<String> data) {
        return formulateString(data, '\'');
    }

    /*
    * formulate a list of String into a single string with comma separate them
    * and each element in the list will wrap with back quotation mark (`)
    */
    public String formulateIds(List<String> columnIds) {
        return formulateString(columnIds, '`');
    }

    public abstract boolean handle(List<String> arguments) throws Exception;
}
