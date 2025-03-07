package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a room in the hotel management system.
 */
public class Room {
    private int number, numberOfBeds;
    private String note;
    private List<Reservation> reservations;

    /**
     * Constructs a new Room with the specified number, number of beds, and note.
     *
     * @param number       The room number.
     * @param numberOfBeds The number of beds in the room.
     * @param note         Additional notes about the room.
     */
    public Room(int number, int numberOfBeds, String note) {
        this.number = number;
        this.numberOfBeds = numberOfBeds;
        this.note = note;
        this.reservations = new ArrayList<>();
    }

    /**
     * Checks if the room is available for the specified date range.
     *
     * @param from The start date of the desired reservation period.
     * @param to   The end date of the desired reservation period.
     * @return {@code true} if the room is available, {@code false} otherwise.
     */
    public boolean checkIfAvailable(LocalDate from, LocalDate to) {
        if (this.reservations.isEmpty()) {
            return true;
        }
        for (Reservation reservation : this.reservations) {
            if ((from.isBefore(reservation.getTo()) && to.isAfter(reservation.getFrom())) ||
                    from.isEqual(reservation.getFrom()) || to.isEqual(reservation.getTo())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds a reservation to the room.
     *
     * @param r The reservation to add.
     */
    public void addReservation(Reservation r) {
        this.reservations.add(r);
    }

    /**
     * Removes a reservation from the room.
     *
     * @param r The reservation to remove.
     */
    public void removeReservation(Reservation r) {
        this.reservations.remove(r);
    }

    /**
     * Retrieves the room number.
     *
     * @return The room number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the room number.
     *
     * @param number The new room number.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Retrieves the number of beds in the room.
     *
     * @return The number of beds in the room.
     */
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    /**
     * Sets the number of beds in the room.
     *
     * @param numberOfBeds The new number of beds in the room.
     */
    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    /**
     * Retrieves the note about the room.
     *
     * @return The note about the room.
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the note about the room.
     *
     * @param note The new note about the room.
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Retrieves the list of reservations for the room.
     *
     * @return A list of reservations for the room.
     */
    public List<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Sets the list of reservations for the room.
     *
     * @param reservations The new list of reservations for the room.
     */
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
