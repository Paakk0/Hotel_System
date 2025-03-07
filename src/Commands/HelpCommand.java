package Commands;

import UI.ColorCode;

import java.util.List;

/**
 * Command implementation for displaying help information about supported commands.
 */
public class HelpCommand extends Command {

    /**
     * Executes the command to display help information.
     *
     * @param args The list of arguments for the command (expects no arguments).
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 0) {
            System.out.println(ColorCode.SUGGEST.getCode() +
                    "The following commands are supported:\n" +
                    "open <file>    \topens <file>\n" +
                    "close          \tcloses currently opened file\n" +
                    "save           \tsaves the currently open file\n" +
                    "saveas <file>  \tsaves the currently open file in <file>\n" +
                    "help           \tprints this information\n" +
                    "exit           \texists the program\n" +
                    "\n" +
                    "checkin <room> <from> <to> <note> <guests> \tRoom becomes unavailable for specified duration\n" +
                    "checkout <room> [<numberOfReservation>]    \tRoom becomes available\n" +
                    "find <beds> <from> <to>                    \tShows which rooms are available at chosen period with more or equal beds\n" +
                    "find! <beds> <from> <to>                   \tShows room for important guest, if hotel is full some guests are offered room swap\n" +
                    "availability <date>                        \tShows which rooms are available on that date\n" +
                    "unavailable <room> <from> <to> <note>      \tRoom becomes temporarily unavailable\n" +
                    "report <from> <to>                         \tShows how many days the rooms have been used in a period of time\n" +
                    "enroll <room> <event> <guest>              \tEnrolls room in an event\n" +
                    "unroll <room> <event> <guest>              \tUnrolls room from an event\n" +
                    "showEvents                                 \tprints all possible events\n" +
                    "printEnrolledRooms <event>                 \tprints all rooms that have enrolled in a specific event\n");
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This command requires no arguments!");
        }
    }
}
