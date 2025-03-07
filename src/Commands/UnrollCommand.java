package Commands;

import Model.Event;
import Model.Guest;
import Model.Hotel;
import Model.Reservation;
import UI.ColorCode;

import java.util.List;

/**
 * Command implementation for removing an event enrollment from a guest in a room reservation.
 */
public class UnrollCommand extends Command {

    /**
     * Executes the unroll command to remove an event enrollment from a guest in a room reservation.
     *
     * @param args The list of arguments for the unroll command:
     *             args.get(0): Room number (int)
     *             args.get(1): Event ID (int)
     *             args.get(2): Guest identity (String)
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 3) {
            int number = Integer.parseInt(args.get(0));
            Event event = Event.getEvent(Integer.parseInt(args.get(1)));
            if (event != null) {
                // Find the room and iterate through its reservations
                for (Reservation r : Hotel.getRooms().get(Hotel.findRoom(number)).getReservations()) {
                    // Iterate through guests in each reservation
                    for (Guest g : r.getGuests()) {
                        // Check if guest identity matches the provided identity
                        if (g.getIdentity().equals(args.get(2))) {
                            // Remove the event from the guest's enrolled events
                            g.removeEvent(event);
                            System.out.println(ColorCode.DONE.getCode() + "Event " + event + " successfully removed from room " + number + "!");
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
