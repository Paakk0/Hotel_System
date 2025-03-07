package Model;

import UI.ColorCode;

import java.time.LocalDate;
import java.util.*;

/**
 * Class representing a hotel in the hotel management system.
 */
public class Hotel {
    private static List<Room> rooms;

    /**
     * Constructs a new Hotel with an empty list of rooms.
     */
    public Hotel() {
        rooms = new LinkedList<>();
    }

    /**
     * Generates rooms for the hotel with a predefined configuration.
     */
    public static void generateRooms() {
        int beds = 5;
        for (int i = 1; i <= 400; i = i + 100) {
            for (int j = 0; j <= 30; j++) {
                if (i == 1) {
                    j = 1;
                    i = 0;
                }
                rooms.add(new Room(i + j, beds, null));
            }
            beds--;
        }
    }

    /**
     * Marks a room as unavailable for a specified date range with a note.
     *
     * @param room     The room number.
     * @param dateFrom The start date of the unavailable period.
     * @param dateTo   The end date of the unavailable period.
     * @param note     The note explaining the unavailability.
     */
    public static void unavailable(int room, LocalDate dateFrom, LocalDate dateTo, String note) {
        checkIn(room, dateFrom, dateTo, note, "Staff0");
    }

    /**
     * Checks the availability of all rooms on a specific date.
     *
     * @param date The date to check availability for.
     */
    public static void availability(LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
            System.out.println(ColorCode.SUGGEST.getCode() + "Using today's date for this search!");
        }
        for (Room r : rooms) {
            if (r.checkIfAvailable(date, date)) {
                System.out.println(ColorCode.DONE.getCode() + "Room " + r.getNumber() + " is available at " + date);
            }
        }
    }

    /**
     * Generates a report of room usage for a specified date range.
     *
     * @param dateFrom The start date of the report period.
     * @param dateTo   The end date of the report period.
     */
    public static void report(LocalDate dateFrom, LocalDate dateTo) {
        if (dateFrom == null || dateTo == null) {
            System.out.println(ColorCode.ERROR.getCode() + "Dates cannot be null!");
            return;
        }
        boolean overallDataFound = false;

        for (Room r : rooms) {
            boolean dataFound = false;
            int days = 0;
            for (Reservation res : r.getReservations()) {
                if (!(dateTo.isBefore(res.getFrom()) || dateFrom.isAfter(res.getTo()))) {
                    days += res.getTo().compareTo(res.getFrom());
                    dataFound = true;
                    overallDataFound = true;
                }
            }
            if (dataFound) {
                System.out.println(ColorCode.DONE.getCode() + "Room " + r.getNumber() + " is being used for " + days + " days in the specified period.");
            }
        }

        if (!overallDataFound) {
            System.out.println(ColorCode.ERROR.getCode() + "No data of room use at that period!");
        }
    }

    /**
     * Checks a guest into a room for a specified date range with a note and identity.
     *
     * @param number    The room number.
     * @param dateFrom  The start date of the reservation.
     * @param dateTo    The end date of the reservation.
     * @param note      The note associated with the reservation.
     * @param identity  The identity of the guest.
     */
    public static void checkIn(int number, LocalDate dateFrom, LocalDate dateTo, String note, String identity) {
        int index = findRoom(number);
        if (index != -1 && getRooms().get(index).checkIfAvailable(dateFrom, dateTo)) {
            if (dateFrom.isBefore(LocalDate.now()) || dateTo.isBefore(LocalDate.now())) {
                System.out.println(ColorCode.SUGGEST.getCode() + "Going back in time?\n" + ColorCode.ERROR.getCode() + "Reservations cannot be made for dates in the past!");
                return;
            }
            int guests = intParser(identity);
            if (rooms.get(index).getNumberOfBeds() >= guests) {
                rooms.get(index).setNote(note);
                if (guests == 0) {
                    guests = rooms.get(index).getNumberOfBeds();
                }
                rooms.get(index).addReservation(new Reservation(dateFrom, dateTo, identity.substring(0, identity.length() - 1), guests));
                System.out.println(ColorCode.DONE.getCode() + "Room " + rooms.get(index).getNumber() + " has been checked in!");
            } else {
                System.out.println(ColorCode.ERROR.getCode() + "This room only has " + rooms.get(index).getNumberOfBeds());
            }
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This room is unavailable!");
        }
    }

    /**
     * Parses the number of guests from the given identity string.
     *
     * @param value The identity string.
     * @return The number of guests parsed from the identity string.
     */
    private static int intParser(String value) {
        char ch = value.charAt(value.length() - 1);
        try {
            return Integer.parseInt(String.valueOf(ch));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Finds the index of a room by its number.
     *
     * @param number The room number.
     * @return The index of the room, or -1 if not found.
     */
    public static int findRoom(int number) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getNumber() == number) {
                return i;
            }
        }
        System.out.println(ColorCode.ERROR.getCode() + "There is no such room!");
        return -1;
    }

    /**
     * Finds the number of available rooms with a specified number of beds for a date range.
     *
     * @param beds     The number of beds required.
     * @param dateFrom The start date of the search period.
     * @param dateTo   The end date of the search period.
     * @return The number of available rooms, or -1 if the number of beds is out of range.
     */
    public static int find(int beds, LocalDate dateFrom, LocalDate dateTo) {
        if (beds >= 1 && beds <= 5) {
            int count = 0;
            for (Room r : getRooms()) {
                if ((r.getNumberOfBeds() >= beds) && r.checkIfAvailable(dateFrom, dateTo)) {
                    System.out.println(ColorCode.DONE.getCode() + "Room number " + r.getNumber() + " is available!");
                    count++;
                }
            }
            return count;
        }
        System.out.println(ColorCode.ERROR.getCode() + "The hotel contains rooms with 1-5 beds!");
        return -1;
    }

    /**
     * Finds available rooms for a specified number of beds and date range, considering current reservations.
     *
     * @param beds     The number of beds required.
     * @param dateFrom The start date of the search period.
     * @param dateTo   The end date of the search period.
     * @return A 3D array representing potential room switches for guest accommodation, or null if the hotel is full.
     */
    public static Integer[][][] findNow(int beds, LocalDate dateFrom, LocalDate dateTo) {
        List<Room> potentialRooms = new LinkedList<>();
        for (Room r : rooms) {
            Reservation res = getReservedPeriodIndex(r, dateFrom, dateTo);
            assert res != null;
            if (beds <= r.getNumberOfBeds() - calcGuests(res)) {
                potentialRooms.add(r);
            }
        }
        return roomSwitchAdvisor(potentialRooms, beds, dateFrom, dateTo);
    }

    /**
     * Gets the reservation for the specified room and date range.
     *
     * @param room The room to check.
     * @param from The start date of the period.
     * @param to   The end date of the period.
     * @return The reservation for the specified period, or null if none found.
     */
    private static Reservation getReservedPeriodIndex(Room room, LocalDate from, LocalDate to) {
        for (Reservation res : room.getReservations()) {
            if (from.isBefore(res.getTo()) && to.isAfter(res.getFrom())) {
                return res;
            }
        }
        return null;
    }

    /**
     * Calculates the total number of guests for a reservation.
     *
     * @param reservation The reservation to check.
     * @return The total number of guests.
     */
    private static int calcGuests(Reservation reservation) {
        int sum = 0;
        for (Guest g : reservation.getGuests()) {
            sum += g.getNumber();
        }
        return sum;
    }

    /**
     * Advises potential room switches based on availability and guest requirements.
     *
     * @param potentialRooms List of rooms with potential availability for switching.
     * @param beds           Number of beds required.
     * @param from           The start date of the period.
     * @param to             The end date of the period.
     * @return A 3D array representing potential room switches, or null if no switches are possible.
     */
    private static Integer[][][] roomSwitchAdvisor(List<Room> potentialRooms, int beds, LocalDate from, LocalDate to) {
        if (!potentialRooms.isEmpty()) {
            Integer[][][] indexLink = new Integer[potentialRooms.size()][rooms.size()][2];
            Reservation resM, resN;
            int m, n, counter;
            for (int i = 0; i < indexLink.length; i++) {
                m = findRoom(potentialRooms.get(i).getNumber());
                counter = 0;
                for (int j = 0; j < rooms.size(); j++) {
                    n = j;
                    resM = getReservedPeriodIndex(rooms.get(m), from, to);
                    resN = getReservedPeriodIndex(rooms.get(n), from, to);
                    if (rooms.get(m).getNumber() != rooms.get(n).getNumber() &&
                            (rooms.get(m).getNumberOfBeds() - calcGuests(resM)) >= calcGuests(resN) &&
                            rooms.get(n).getNumberOfBeds() >= beds) {
                        indexLink[i][counter][0] = m;
                        indexLink[i][counter][1] = n;
                        counter++;
                    }
                }
            }
            if (indexLink.length != 0) {
                for (Integer[][] integers : indexLink) {
                    for (Integer[] integer : integers) {
                        if (integer[0] != null) {
                            System.out.println(ColorCode.NORMAL.getCode() + "Room " + rooms.get(integer[0]).getNumber() + " (" + calcGuests(getReservedPeriodIndex(rooms.get(integer[0]), from, to)) + " | " + rooms.get(integer[0]).getNumberOfBeds() + " guests) <- Room " + rooms.get(integer[1]).getNumber() + " (" + calcGuests(getReservedPeriodIndex(rooms.get(integer[1]), from, to)) + " | " + rooms.get(integer[1]).getNumberOfBeds() + " guests)");
                        }
                    }
                }
                return indexLink;
            }
        }
        System.out.println(ColorCode.ERROR.getCode() + "Hotel is completely full!");
        return null;
    }


    /**
     * Moves guests from one room to another based on the room switch advisor's suggestions.
     *
     * @param indexLink The 3D array representing potential room switches.
     * @param from      The start date of the period.
     * @param to        The end date of the period.
     */
    public static void moveGuests(Integer[][][] indexLink, LocalDate from, LocalDate to) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(ColorCode.NORMAL.getCode() + "Do you want to move the guests to another room?\n" +
                ColorCode.DONE.getCode() + "Yes" + ColorCode.CLEAR.getCode() + " or " + ColorCode.ERROR.getCode() + "No" +
                ColorCode.NORMAL.getCode() + "\n");
        System.out.print(">");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("yes")) {
            System.out.println(ColorCode.NORMAL.getCode() + "Select the room you want to move other guests to:");
            List<Integer> temp = new ArrayList<>();
            for (Integer[][] integers : indexLink) {
                for (Integer[] integer : integers) {
                    if (integer[0] != null) {
                        if (!temp.contains(integer[0])) {
                            temp.add(integer[0]);
                            System.out.print(rooms.get(integer[0]).getNumber() + ", ");
                        }
                    }
                }
            }
            System.out.print("\n>");
            answer = scanner.nextLine();
            if (temp.contains(findRoom(Integer.parseInt(answer)))) {
                int receiverId = findRoom(Integer.parseInt(answer));
                System.out.println(ColorCode.NORMAL.getCode() + "Select the room that will be cleared:");
                temp.clear();
                for (Integer[][] integers : indexLink) {
                    for (Integer[] integer : integers) {
                        if (integer[0] != null) {
                            temp.add(integer[1]);
                            System.out.print(rooms.get(integer[1]).getNumber() + ", ");
                        }
                    }
                }
                System.out.print("\n>");
                answer = scanner.nextLine();
                if (temp.contains(findRoom(Integer.parseInt(answer)))) {
                    int senderId = findRoom(Integer.parseInt(answer));
                    Reservation receiver = getReservedPeriodIndex(rooms.get(receiverId), from, to);
                    Reservation sender = getReservedPeriodIndex(rooms.get(senderId), from, to);
                    assert sender != null;
                    assert receiver != null;
                    receiver.getGuests().addAll(sender.getGuests());
                    rooms.get(senderId).removeReservation(sender);
                    System.out.println(ColorCode.DONE.getCode() + "Room swap complete!");
                }
                return;
            }
            System.out.println(ColorCode.ERROR.getCode() + "This room isn't listed");
            return;
        }
        System.out.println(ColorCode.NORMAL.getCode() + "No changes have been made!");
    }

    /**
     * Checks out a guest from a room.
     *
     * @param number The room number.
     * @param resId  The reservation ID to be checked out.
     */
    public static void checkOut(int number, Integer resId) {
        int index = findRoom(number);
        int id = resId - 1;
        if (index != -1) {
            if (!rooms.get(index).getReservations().isEmpty()) {
                if (resId == -1) {
                    rooms.get(index).getReservations().clear();
                    System.out.println(ColorCode.DONE.getCode() + "Room " + rooms.get(index).getNumber() + " has been checked out!");
                    return;
                } else if (rooms.get(index).getReservations().size() >= resId) {
                    rooms.get(index).getReservations().remove(id);
                    System.out.println(ColorCode.DONE.getCode() + "Room " + rooms.get(index).getNumber() + " has been checked out!");
                    return;
                }
                System.out.println(ColorCode.ERROR.getCode() + "Reservation doesn't exist!");
                return;
            }
            System.out.println(ColorCode.ERROR.getCode() + "This room is already available!");
            return;
        }
        System.out.println(ColorCode.ERROR.getCode() + "Room number " + number + " doesn't exist!");
    }

    /**
     * Returns the list of rooms in the hotel.
     *
     * @return The list of rooms.
     */
    public static List<Room> getRooms() {
        return rooms;
    }

    /**
     * Sets the list of rooms in the hotel.
     *
     * @param rs The list of rooms to set.
     */
    public static void setRooms(List<Room> rs) {
        rooms = rs;
    }
}
