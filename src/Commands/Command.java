package Commands;

import UI.ColorCode;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Abstract class representing a command.
 * Subclasses should implement the Command method to define specific command behavior.
 */
public abstract class Command {

    /**
     * Executes the command with the given arguments.
     *
     * @param args The list of arguments passed to the command.
     */
    public abstract void Command(List<String> args);

    /**
     * Converts a string representation of a date to a {@link LocalDate} object.
     *
     * @param str The string representation of the date. Should be in format "YYYY-MM-DD".
     *            Can be "null" to represent a null date.
     * @return The {@link LocalDate} object parsed from the string, or null if parsing fails.
     */
    protected LocalDate convertStringToDate(String str) {
        try {
            if ("null".equals(str)) {
                return null;
            } else {
                return LocalDate.parse(str);
            }
        } catch (DateTimeParseException e) {
            System.out.println(ColorCode.ERROR.getCode() + "Incorrect date format(YYYY-MM-DD)");
        }
        return null;
    }
}
