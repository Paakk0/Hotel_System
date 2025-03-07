package Commands;

import Files.FileManager;
import UI.ColorCode;

import java.util.List;

/**
 * Command implementation for saving hotel data to a new file.
 */
public class SaveAsCommand extends Command {

    /**
     * Executes the save-as command to save hotel data to a new file.
     *
     * @param args The list of arguments for the save-as command (expects exactly 1 argument: the new filename).
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() != 1) {
            System.out.println(ColorCode.ERROR.getCode() + "This command requires exactly 1 argument (the filename)!");
            return;
        }

        if (FileManager.getFile() != null) {
            String currentFile = FileManager.getFile().getName();
            FileManager.closeFile();
            FileManager.openFile(args.get(0));
            FileManager.save();
            FileManager.closeFile();
            FileManager.openFile(currentFile);
        } else {
            FileManager.openFile(args.get(0));
            FileManager.save();
            FileManager.closeFile();
        }
    }
}
