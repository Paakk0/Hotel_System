package Commands;

import Model.Hotel;
import UI.ColorCode;

import java.util.List;

/**
 * Command implementation for checking out guests from a room.
 */
public class CheckOutCommand extends Command {

    /**
     * Executes the command to check out guests from a room.
     *
     * @param args The list of arguments for the command. Expects either 1 or 2 arguments:
     *             - If 1 argument: Room number (String). The second argument defaults to "-1".
     *             - If 2 arguments: Room number (String) and reservation index (String).
     *               If the first argument is "!", checks out all rooms.
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 1 || args.size() == 2) {
            if (args.size() == 1) {
                args.add("-1");
            }
            if (args.get(0).equals("!")) {
                for (int i = 0; i < Hotel.getRooms().size(); i++) {
                    Hotel.checkOut(Hotel.getRooms().get(i).getNumber(), Integer.parseInt(args.get(1)));
                }
                return;
            }
            Hotel.checkOut(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1)));
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This command requires 1 or 2 arguments!");
        }
    }
}
