package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a guest in the hotel management system.
 */
public class Guest {
    private String identity;
    private int number;
    private List<Event> events;

    /**
     * Constructs a new Guest with the specified identity and number.
     *
     * @param identity The identity of the guest.
     * @param number   The number associated with the guest.
     */
    public Guest(String identity, int number) {
        this.identity = identity;
        this.number = number;
        this.events = new ArrayList<>();
    }

    /**
     * Retrieves the identity of the guest.
     *
     * @return The identity of the guest.
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * Sets the identity of the guest.
     *
     * @param identity The new identity of the guest.
     */
    public void setIdentity(String identity) {
        this.identity = identity;
    }

    /**
     * Retrieves the number associated with the guest.
     *
     * @return The number of the guest.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the number associated with the guest.
     *
     * @param number The new number of the guest.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Retrieves the list of events associated with the guest.
     *
     * @return A list of events the guest is attending.
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Sets the list of events associated with the guest.
     *
     * @param events The new list of events for the guest.
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    /**
     * Adds an event to the guest's list of events.
     *
     * @param e The event to add.
     */
    public void addEvent(Event e) {
        this.events.add(e);
    }

    /**
     * Removes an event from the guest's list of events.
     *
     * @param e The event to remove.
     */
    public void removeEvent(Event e) {
        this.events.remove(e);
    }
}
