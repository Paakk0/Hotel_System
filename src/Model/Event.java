package Model;

import UI.ColorCode;

/**
 * Enum representing various types of events that can be hosted at a hotel.
 */
public enum Event {
    Conferences(1),
    Weddings(2),
    Banquets(3),
    Retreats(4),
    Expos(5),
    Workshops(6),
    Networking(7),
    Concerts(8),
    Fashion(9),
    Holidays(10),
    Fundraisers(11),
    Reunions(12),
    Wellness(13),
    Culinary(14);

    private final int id;

    /**
     * Constructor to initialize the event with a specific ID.
     *
     * @param id The unique ID for the event.
     */
    Event(int id) {
        this.id = id;
    }

    /**
     * Retrieves the unique ID of the event.
     *
     * @return The ID of the event.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the event corresponding to the given ID.
     *
     * @param id The ID of the event to retrieve.
     * @return The event associated with the given ID, or null if no such event exists.
     */
    public static Event getEvent(int id) {
        for (Event event : Event.values()) {
            if (event.getId() == id) {
                return event;
            }
        }
        System.out.println(ColorCode.ERROR.getCode() + "No event found with id: " + id);
        return null;
    }
}
