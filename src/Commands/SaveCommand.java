package Commands;

import Files.FileManager;
import UI.ColorCode;

import java.util.List;

/**
 * Command implementation for saving hotel data to a file.
 */
public class SaveCommand extends Command {

    /**
     * Executes the save command to save hotel data.
     *
     * @param args The list of arguments for the save command (none expected).
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 0) {
            FileManager.save();
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This command does not require any arguments!");
        }
    }
}
