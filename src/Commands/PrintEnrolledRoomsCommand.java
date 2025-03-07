package Commands;

import Model.Event;
import Model.Guest;
import Model.Hotel;
import Model.Reservation;
import UI.ColorCode;

import java.util.List;

/**
 * Command implementation for printing rooms enrolled in a specific event.
 */
public class PrintEnrolledRoomsCommand extends Command {

    /**
     * Executes the command to print rooms enrolled in a specific event.
     *
     * @param args The list of arguments for the command (expects exactly 1 argument: event ID).
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 1) {
            int eventId = Integer.parseInt(args.get(0));
            Event event = Event.getEvent(eventId);
            if (event != null) {
                for (int i = 0; i < Hotel.getRooms().size(); i++) {
                    for (Reservation r : Hotel.getRooms().get(i).getReservations()) {
                        for (Guest g : r.getGuests()) {
                            if (g.getEvents().contains(event)) {
                                System.out.println(ColorCode.DONE.getCode() + "Room " + Hotel.getRooms().get(i).getNumber());
                            }
                        }
                    }
                }
            } else {
                System.out.println(ColorCode.ERROR.getCode() + "Invalid event ID!");
            }
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This command requires 1 argument (event ID)!");
        }
    }
}
