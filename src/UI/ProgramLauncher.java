package UI;

import Commands.CommandFactory;
import Model.Hotel;

import java.util.Scanner;

/**
 * Class responsible for launching the program and handling the main input loop.
 */
public class ProgramLauncher {

    /**
     * Launches the hotel management program.
     * Initializes the hotel and starts the main input loop for command execution.
     */
    public static void Launch() {
        // Initialize a new hotel instance
        Hotel hotel = new Hotel();

        // Generate rooms for the hotel
        Hotel.generateRooms();

        // Create a new scanner for reading user input
        Scanner scanner = new Scanner(System.in);

        // Main input loop
        while (true) {
            // Prompt the user for input
            System.out.print(ColorCode.NORMAL.getCode() + "> ");

            // Execute the command entered by the user
            CommandFactory.executeCommand(scanner.nextLine());
        }
    }
}
