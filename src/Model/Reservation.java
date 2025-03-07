package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a reservation in the hotel management system.
 */
public class Reservation {
    private LocalDate from, to;
    private List<Guest> guests;

    /**
     * Constructs a new Reservation with the specified dates and a single guest.
     *
     * @param from            The start date of the reservation.
     * @param to              The end date of the reservation.
     * @param identity        The identity of the guest making the reservation.
     * @param numberOfGuests  The number of guests for the reservation.
     */
    public Reservation(LocalDate from, LocalDate to, String identity, int numberOfGuests) {
        this.from = from;
        this.to = to;
        this.guests = new ArrayList<>();
        addGuest(new Guest(identity, numberOfGuests));
    }

    /**
     * Constructs a new Reservation with the specified dates and a list of guests.
     *
     * @param from   The start date of the reservation.
     * @param to     The end date of the reservation.
     * @param g      The list of guests for the reservation.
     */
    public Reservation(LocalDate from, LocalDate to, List<Guest> g) {
        this.from = from;
        this.to = to;
        this.guests = g;
    }

    /**
     * Retrieves the start date of the reservation.
     *
     * @return The start date of the reservation.
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Retrieves the end date of the reservation.
     *
     * @return The end date of the reservation.
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * Adds a guest to the reservation.
     *
     * @param g The guest to add.
     */
    public void addGuest(Guest g) {
        this.guests.add(g);
    }

    /**
     * Removes a guest from the reservation.
     *
     * @param g The guest to remove.
     */
    public void removeGuest(Guest g) {
        this.guests.remove(g);
    }

    /**
     * Retrieves the list of guests for the reservation.
     *
     * @return A list of guests for the reservation.
     */
    public List<Guest> getGuests() {
        return guests;
    }
}
