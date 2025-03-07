package Commands;

import Files.FileManager;
import UI.ColorCode;

import java.util.List;

/**
 * Command implementation for opening a file.
 */
public class OpenCommand extends Command {

    /**
     * Executes the command to open a file.
     *
     * @param args The list of arguments for the command (expects exactly 1 argument: the file name).
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 1) {
            String fileName = args.get(0);
            if (FileManager.openFile(fileName)) {
                System.out.println(ColorCode.DONE.getCode() + "File " + FileManager.getFile().getName() + " successfully opened!");
            } else {
                System.out.println(ColorCode.ERROR.getCode() + "You need to close the currently opened file!");
            }
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This command requires 1 argument!");
        }
    }
}
