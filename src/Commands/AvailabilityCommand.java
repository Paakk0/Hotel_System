package Commands;

import Model.Hotel;
import UI.ColorCode;

import java.util.List;

/**
 * Command implementation for checking room availability on a specific date.
 */
public class AvailabilityCommand extends Command {

    /**
     * Executes the command to check room availability on a specific date.
     *
     * @param args The list of arguments for the command. Expects 1 argument:
     *             - The date (String) in "YYYY-MM-DD" format to check availability.
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 1) {
            Hotel.availability(convertStringToDate(args.get(0)));
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This command requires 1 argument!");
        }
    }
}
