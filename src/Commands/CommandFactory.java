package Commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * The CommandFactory class manages the creation and execution of commands based on user input.
 * It maps command names to their corresponding Command implementations using a static map.
 */
public class CommandFactory {

    // Map to store CommandName enum values mapped to corresponding Command creation functions
    private static final Map<CommandName, Function<List<String>, Command>> commandMap = new HashMap<>();

    static {
        // Initialize the commandMap with mappings from CommandName enum to Command creation functions
        commandMap.put(CommandName.OPEN, args -> new OpenCommand());
        commandMap.put(CommandName.EXIT, args -> new ExitCommand());
        commandMap.put(CommandName.HELP, args -> new HelpCommand());
        commandMap.put(CommandName.CLOSE, args -> new CloseCommand());
        commandMap.put(CommandName.SAVE, args -> new SaveCommand());
        commandMap.put(CommandName.SAVEAS, args -> new SaveAsCommand());
        commandMap.put(CommandName.CHECKIN, args -> new CheckInCommand());
        commandMap.put(CommandName.AVAILABILITY, args -> new AvailabilityCommand());
        commandMap.put(CommandName.CHECKOUT, args -> new CheckOutCommand());
        commandMap.put(CommandName.REPORT, args -> new ReportCommand());
        commandMap.put(CommandName.FIND, args -> new FindCommand());
        commandMap.put(CommandName.FINDi, args -> new FindNowCommand());
        commandMap.put(CommandName.UNAVAILABLE, args -> new UnavailableCommand());
        commandMap.put(CommandName.ENROLL, args -> new EnrollCommand());
        commandMap.put(CommandName.UNROLL, args -> new UnrollCommand());
        commandMap.put(CommandName.SHOWEVENTS, args -> new ShowEventsCommand());
        commandMap.put(CommandName.PRINTENROLLEDROOMS, args -> new PrintEnrolledRoomsCommand());
    }

    /**
     * Executes a command based on the provided input string.
     *
     * @param input The input string representing the command and its arguments.
     */
    public static void executeCommand(String input) {
        // Split input into parts based on spaces and convert to a list
        List<String> commandParts = List.of(input.split(" "));

        // Retrieve the CommandName enum from the first part of commandParts
        CommandName commandName = CommandName.getValue(commandParts.get(0));

        // Create a list of arguments (if any) by copying elements from commandParts, excluding the commandName
        List<String> args = commandParts.size() > 1 ? new ArrayList<>(commandParts.subList(1, commandParts.size())) : new ArrayList<>();

        // Retrieve the Command creation function from commandMap based on commandName
        Function<List<String>, Command> functionList = commandMap.get(commandName);

        // If a valid function is found, create the Command instance and execute it with args
        if (functionList != null) {
            Command command = functionList.apply(args);
            command.Command(args);
        }
    }
}
