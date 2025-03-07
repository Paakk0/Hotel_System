package Commands;

import UI.ColorCode;

import java.util.List;

/**
 * Command implementation for exiting the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to exit the program.
     *
     * @param args The list of arguments for the command (expects no arguments).
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 0) {
            System.exit(0);
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This command requires no arguments!");
        }
    }
}
