package Commands;

import Model.Event;
import UI.ColorCode;

import java.util.List;

/**
 * Command implementation for displaying all available events.
 */
public class ShowEventsCommand extends Command {

    /**
     * Executes the showEvents command to display all available events.
     *
     * @param args The list of arguments for the showEvents command (none expected).
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 0) {
            for (Event e : Event.values()) {
                System.out.println(ColorCode.SUGGEST.getCode() + e + " - " + e.getId());
            }
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This command does not require any arguments!");
        }
    }
}
