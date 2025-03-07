package Commands;

import UI.ColorCode;

/**
 * Enum representing various command names and their corresponding identifiers.
 */
public enum CommandName {
    OPEN("open"),
    EXIT("exit"),
    HELP("help"),
    CLOSE("close"),
    SAVE("save"),
    SAVEAS("saveas"),
    CHECKIN("checkin"),
    AVAILABILITY("availability"),
    CHECKOUT("checkout"),
    REPORT("report"),
    FIND("find"),
    FINDi("find!"),
    UNAVAILABLE("unavailable"),
    ENROLL("enroll"),
    UNROLL("unroll"),
    SHOWEVENTS("showEvents"),
    PRINTENROLLEDROOMS("printEnrolledRooms");

    private final String id;

    /**
     * Constructor for CommandName enum.
     *
     * @param id The identifier string associated with the command name.
     */
    CommandName(String id) {
        this.id = id;
    }

    /**
     * Gets the identifier string associated with the command name.
     *
     * @return The identifier string.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Retrieves the CommandName enum value corresponding to the given identifier string.
     *
     * @param val The identifier string for which to retrieve the CommandName enum value.
     * @return The CommandName enum value if found, or null if no matching enum value exists.
     */
    public static CommandName getValue(String val) {
        for (CommandName com : CommandName.values()) {
            if (com.getId().equals(val)) return com;
        }
        System.out.println(ColorCode.ERROR.getCode() + "Unknown command: " + val);
        return null;
    }

}
