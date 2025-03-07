package Commands;

import Model.Hotel;
import UI.ColorCode;

import java.util.List;

/**
 * Command implementation for marking a room as unavailable for a specific period.
 */
public class UnavailableCommand extends Command {

    /**
     * Executes the unavailable command to mark a room as unavailable for a specified period.
     *
     * @param args The list of arguments for the unavailable command:
     *             args.get(0): Room number (int)
     *             args.get(1): Start date (String in "YYYY-MM-DD" format)
     *             args.get(2): End date (String in "YYYY-MM-DD" format)
     *             args.get(3): Reason (String)
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 4) {
            Hotel.unavailable(Integer.parseInt(args.get(0)), convertStringToDate(args.get(1)),
                    convertStringToDate(args.get(2)), args.get(3));
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This command requires 4 arguments!");
        }
    }
}
