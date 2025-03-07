package Commands;

import Model.Hotel;
import UI.ColorCode;

import java.time.LocalDate;
import java.util.List;

/**
 * Command implementation for finding available rooms within a specified date range and number of beds.
 */
public class FindCommand extends Command {

    /**
     * Executes the command to find available rooms based on specified criteria.
     *
     * @param args The list of arguments for the command (expects 3 arguments: beds, dateFrom, dateTo).
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 3) {
            int beds = Integer.parseInt(args.get(0));
            LocalDate dateFrom = convertStringToDate(args.get(1));
            LocalDate dateTo = convertStringToDate(args.get(2));

            System.out.println(ColorCode.DONE.getCode() + "Number of rooms that are available: " + Hotel.find(beds, dateFrom, dateTo));
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This command requires 3 arguments!");
        }
    }
}
