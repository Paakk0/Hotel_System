package Commands;

import Model.Event;
import Model.Guest;
import Model.Hotel;
import Model.Reservation;
import UI.ColorCode;

import java.util.List;

/**
 * Command implementation for enrolling a guest in an event for a specific room reservation.
 */
public class EnrollCommand extends Command {

    /**
     * Executes the command to enroll a guest in an event for a specific room reservation.
     *
     * @param args The list of arguments for the command (expects 3 arguments: room number, event ID, guest identity).
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 3) {
            int number = Integer.parseInt(args.get(0));
            Event event = Event.getEvent(Integer.parseInt(args.get(1)));
            if (event != null) {
                for (Reservation r : Hotel.getRooms().get(Hotel.findRoom(number)).getReservations()) {
                    for (Guest g : r.getGuests()) {
                        if (g.getIdentity().equals(args.get(2))) {
                            g.addEvent(event);
                            System.out.println(ColorCode.DONE.getCode() + "Event " + event + " successfully added to room " + number + "!");
                            return;
                        }
                    }
                }
            }
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This command requires 3 arguments!");
        }
    }
}
