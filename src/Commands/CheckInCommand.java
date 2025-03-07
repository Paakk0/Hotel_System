package Commands;

import Model.Hotel;
import UI.ColorCode;

import java.util.List;

/**
 * Command implementation for checking in guests to a room.
 */
public class CheckInCommand extends Command {

    /**
     * Executes the command to check in guests to a room.
     *
     * @param args The list of arguments for the command. Expects either 4 or 5 arguments:
     *             - If 4 arguments: Room number (String), check-in date (String), check-out date (String), note (String).
     *             - If 5 arguments: Room number (String), check-in date (String), check-out date (String), note (String), guests (String).
     *             - If 3 arguments and first argument is "!", checks in all rooms with provided check-in and check-out dates and a default note and guest name.
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 3 && args.get(0).equals("!")) {
            for (int i = 0; i < Hotel.getRooms().size(); i++) {
                Hotel.checkIn(Hotel.getRooms().get(i).getNumber(), convertStringToDate(args.get(1)), convertStringToDate(args.get(2)), "TEST", "TestFamily0");
            }
            return;
        }
        if (args.size() == 4 || args.size() == 5) {
            if (args.size() == 4) {
                args.add("0");
            }
            Hotel.checkIn(Integer.parseInt(args.get(0)), convertStringToDate(args.get(1)), convertStringToDate(args.get(2)), args.get(3), args.get(4));
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This command requires 4 or 5 arguments!");
        }
    }
}
