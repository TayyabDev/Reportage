package app.java.com.model.utilities;

public class FileTypeFinder {

    /**
     *
     * @param filePath
     * @return boolean indicating type of file - true means CSV and false means Excel
     */
    public static boolean isCSVFile(String filePath) {

        System.out.println(filePath);
        String fileCSVEnding = filePath.substring(filePath.length() - 3);

        if (fileCSVEnding.equals("csv")) {
            return true;
        }

        return false;
    }

}
