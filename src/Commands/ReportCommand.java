package Commands;

import Model.Hotel;
import UI.ColorCode;

import java.util.List;

/**
 * Command implementation for generating a report based on specified date range.
 */
public class ReportCommand extends Command {

    /**
     * Executes the report command to generate a report based on the specified date range.
     *
     * @param args The list of arguments for the report command (expects exactly 2 arguments: startDate and endDate).
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 2) {
            Hotel.report(convertStringToDate(args.get(0)), convertStringToDate(args.get(1)));
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This command requires 2 arguments!");
        }
    }
}
