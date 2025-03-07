package Files;

import UI.ColorCode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class manages file operations related to hotel data.
 */
public class FileManager {
    private static File file;

    /**
     * Creates a new file with the given file name if it does not already exist.
     *
     * @param fileName The name of the file to be created.
     */
    public static void createNewFile(String fileName) {
        if (!file.exists()) {
            try {
                file = new File(fileName);
                System.out.println(ColorCode.SUGGEST.getCode() + "Creating file " + fileName + "..");
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else System.out.println(ColorCode.ERROR.getCode() + "You need to close the currently opened file!");
    }

    /**
     * Opens a file with the given file name.
     * If the file does not exist, it creates a new one.
     * Loads data from the file using {@link DataHandler#loadData()}.
     *
     * @param fileName The name of the file to open.
     * @return true if the file was successfully opened, false otherwise.
     */
    public static boolean openFile(String fileName) {
        if (file == null) {
            if (!fileName.contains(".xml")) {
                fileName += ".xml";
            }
            file = new File(fileName);
            if (!file.exists()) {
                createNewFile(fileName);
            }
            DataHandler.loadData();
            return true;
        }
        return false;
    }

    /**
     * Closes the currently opened file.
     *
     * @return true if a file was successfully closed, false otherwise.
     */
    public static boolean closeFile() {
        if (file == null) {
            return false;
        }
        file = null;
        return true;
    }

    /**
     * Saves the hotel data to the currently opened file.
     * Uses {@link DataHandler#saveData()} to get the data to save.
     */
    public static void save() {
        if (file != null) {
            try {
                FileWriter writer = new FileWriter(getFile());
                writer.write(DataHandler.saveData());
                writer.close();
                System.out.println(ColorCode.DONE.getCode() + "Hotel data successfully saved!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else System.out.println(ColorCode.ERROR.getCode() + "There is no opened file to save!");
    }

    /**
     * Returns the currently opened file.
     *
     * @return The currently opened file, or null if no file is opened.
     */
    public static File getFile() {
        return file;
    }
}
