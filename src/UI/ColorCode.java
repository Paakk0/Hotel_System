package UI;

/**
 * Enum representing different color codes for text formatting in a terminal or console.
 */
public enum ColorCode {

    /**
     * Red color code for error messages.
     */
    ERROR("\u001B[31m"),

    /**
     * Cyan color code for normal messages.
     */
    NORMAL("\u001B[36m"),

    /**
     * Green color code for done messages.
     */
    DONE("\u001B[32m"),

    /**
     * Yellow color code for suggestions.
     */
    SUGGEST("\u001B[33m"),

    /**
     * Reset color code to clear formatting.
     */
    CLEAR("\u001B[0m");

    private final String value;

    /**
     * Constructor to initialize the color code.
     *
     * @param value The ANSI escape code representing the color.
     */
    ColorCode(String value) {
        this.value = value;
    }

    /**
     * Retrieves the ANSI escape code for the color.
     *
     * @return The ANSI escape code as a String.
     */
    public String getCode() {
        return this.value;
    }
}
