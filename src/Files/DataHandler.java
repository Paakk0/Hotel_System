package Files;

import Model.Event;
import Model.Hotel;
import Model.Reservation;
import Model.Room;
import Model.Guest;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for handling data conversion between XML representation and Java objects.
 */
public class DataHandler {

    /**
     * Saves the current state of the hotel data into an XML structure.
     *
     * @return A string representation of the XML structure.
     */
    public static String saveData() {
        return XmlStructure.createXmlStructure();
    }

    /**
     * Loads data from XML representation and converts it back into Hotel, Room, Reservation,
     * and Guest objects.
     */
    public static void loadData() {
        try {
            convert(XmlStructure.fetchXmlData());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts XML data into Hotel, Room, Reservation, and Guest objects.
     *
     * @param data The XML data to be converted.
     */
    private static void convert(String data) {
        List<String> contents = List.of(data.split("</" + XmlStructure.ROOM.getValue() + ">"));
        List<Room> newHotel = Hotel.getRooms();
        Room r;
        String line;
        for (int i = 0; i < contents.size() - 1; i++) {
            line = contents.get(i);
            if (extractNumber(line) == Hotel.getRooms().get(i).getNumber()) {
                r = newHotel.get(i);
                r.setNumber(extractNumber(line));
                r.setNumberOfBeds(extractNumberOfBeds(line));
                r.setNote(extractNote(line));
                r.setReservations(extractReservations(line));
            }
        }
        Hotel.setRooms(newHotel);
    }

    /**
     * Extracts the room number from XML data.
     *
     * @param xmlData The XML data containing room information.
     * @return The room number extracted from XML.
     */
    private static int extractNumber(String xmlData) {
        String numberStr = xmlData.substring(xmlData.indexOf(XmlStructure.ROOM.getValue() + " " + XmlStructure.NUMBER.getValue() + "=\"") + XmlStructure.ROOM.getValue().length() + XmlStructure.NUMBER.getValue().length() + 3, xmlData.indexOf("\">"));
        return Integer.parseInt(numberStr);
    }

    /**
     * Extracts the number of beds from XML data.
     *
     * @param xmlData The XML data containing room information.
     * @return The number of beds extracted from XML.
     */
    private static int extractNumberOfBeds(String xmlData) {
        String bedsStr = xmlData.substring(xmlData.indexOf("<" + XmlStructure.NUMBEROFBEDS.getValue() + ">") + XmlStructure.NUMBEROFBEDS.getValue().length() + 2, xmlData.indexOf("</" + XmlStructure.NUMBEROFBEDS.getValue() + ">"));
        return Integer.parseInt(bedsStr);
    }

    /**
     * Extracts the note associated with a room from XML data.
     *
     * @param xmlData The XML data containing room information.
     * @return The note extracted from XML.
     */
    private static String extractNote(String xmlData) {
        return xmlData.substring(xmlData.indexOf("<" + XmlStructure.NOTE.getValue() + ">") + XmlStructure.NOTE.getValue().length() + 2, xmlData.indexOf("</" + XmlStructure.NOTE.getValue() + ">"));
    }

    /**
     * Extracts reservations associated with a room from XML data.
     *
     * @param xmlData The XML data containing room information.
     * @return The list of reservations extracted from XML.
     */
    private static List<Reservation> extractReservations(String xmlData) {
        List<Reservation> reservations = new ArrayList<>();
        if (xmlData.contains("<" + XmlStructure.RESERVATIONS.getValue() + ">")) {
            String reservationsStr = xmlData.substring(
                    xmlData.indexOf("<" + XmlStructure.RESERVATIONS.getValue() + ">") + XmlStructure.RESERVATIONS.getValue().length() + 2,
                    xmlData.indexOf("</" + XmlStructure.RESERVATIONS.getValue() + ">")
            );
            String[] reservationStrArray = reservationsStr.split("</" + XmlStructure.RESERVATION.getValue() + ">");
            for (String reservationStr : reservationStrArray) {
                if (reservationStr.contains("<" + XmlStructure.DATEFROM.getValue() + ">")) {
                    LocalDate dateFrom = extractDateFrom(reservationStr);
                    LocalDate dateTo = extractDateTo(reservationStr);
                    List<Guest> guests = extractGuests(reservationStr);

                    if (!guests.isEmpty()) {
                        Guest guest = guests.get(0);
                        Reservation reservation = new Reservation(dateFrom, dateTo, guests);
                        for (int i = 1; i < guests.size(); i++) {
                            reservation.addGuest(guests.get(i));
                        }
                        reservations.add(reservation);
                    }
                }
            }
        }
        return reservations;
    }

    /**
     * Extracts the start date of a reservation from XML data.
     *
     * @param xmlData The XML data containing reservation information.
     * @return The start date of the reservation extracted from XML.
     */
    private static LocalDate extractDateFrom(String xmlData) {
        String dateFromStr = xmlData.substring(xmlData.indexOf("<" + XmlStructure.DATEFROM.getValue() + ">") + XmlStructure.DATEFROM.getValue().length() + 2, xmlData.indexOf("</" + XmlStructure.DATEFROM.getValue() + ">"));
        if ("null".equals(dateFromStr)) {
            return null;
        } else {
            return LocalDate.parse(dateFromStr);
        }
    }

    /**
     * Extracts the end date of a reservation from XML data.
     *
     * @param xmlData The XML data containing reservation information.
     * @return The end date of the reservation extracted from XML.
     */
    private static LocalDate extractDateTo(String xmlData) {
        String dateToStr = xmlData.substring(xmlData.indexOf("<" + XmlStructure.DATETO.getValue() + ">") + XmlStructure.DATETO.getValue().length() + 2, xmlData.indexOf("</" + XmlStructure.DATETO.getValue() + ">"));
        if ("null".equals(dateToStr)) {
            return null;
        } else {
            return LocalDate.parse(dateToStr);
        }
    }

    /**
     * Extracts guests associated with a reservation from XML data.
     *
     * @param xmlData The XML data containing reservation information.
     * @return The list of guests extracted from XML.
     */
    private static List<Guest> extractGuests(String xmlData) {
        List<Guest> guests = new ArrayList<>();
        if (xmlData.contains("<" + XmlStructure.GUESTS.getValue() + ">")) {
            String guestsStr = xmlData.substring(xmlData.indexOf("<" + XmlStructure.GUESTDETAILS.getValue() + ">") + XmlStructure.GUESTDETAILS.getValue().length() + 2, xmlData.indexOf("</" + XmlStructure.GUESTDETAILS.getValue() + ">"));
            String[] guestStrArray = guestsStr.split("</" + XmlStructure.GUESTS.getValue() + ">");
            for (String guestStr : guestStrArray) {
                if (guestStr.contains("<" + XmlStructure.IDENTITY.getValue() + ">")) {
                    String identity = extractIdentity(guestStr);
                    int numberOfGuests = extractNumberOfGuests(guestStr);
                    Guest g = new Guest(identity, numberOfGuests);
                    g.setEvents(extractEvents(guestStr));
                    guests.add(g);
                }
            }
        }
        return guests;
    }

    /**
     * Extracts events associated with a guest from XML data.
     *
     * @param xmlData The XML data containing guest information.
     * @return The list of events extracted from XML.
     */
    private static List<Event> extractEvents(String xmlData) {
        List<Event> events = new ArrayList<>();
        if (xmlData.contains("<" + XmlStructure.EVENTS.getValue() + ">")) {
            String eventsStr = xmlData.substring(xmlData.indexOf("<" + XmlStructure.EVENTS.getValue() + ">") + XmlStructure.EVENTS.getValue().length() + 2, xmlData.indexOf("</" + XmlStructure.EVENTS.getValue() + ">"));
            String[] eventStrArray = eventsStr.split("</" + XmlStructure.EVENT.getValue() + ">");
            for (String eventStr : eventStrArray) {
                if (eventStr.contains("<" + XmlStructure.EVENT.getValue() + ">")) {
                    events.add(Event.getEvent(Integer.parseInt(eventStr.substring(eventStr.indexOf("<" + XmlStructure.EVENT.getValue() + ">") + XmlStructure.EVENT.getValue().length() + 2))));
                }
            }
        }
        return events;
    }

    /**
     * Extracts the identity of a guest from XML data.
     *
     * @param xmlData The XML data containing guest information.
     * @return The identity of the guest extracted from XML.
     */
    private static String extractIdentity(String xmlData) {
        return xmlData.substring(xmlData.indexOf("<" + XmlStructure.IDENTITY.getValue() + ">") + XmlStructure.IDENTITY.getValue().length() + 2, xmlData.indexOf("</" + XmlStructure.IDENTITY.getValue() + ">"));
    }

    /**
     * Extracts the number of guests associated with a guest from XML data.
     *
     * @param xmlData The XML data containing guest information.
     * @return The number of guests extracted from XML.
     */
    private static int extractNumberOfGuests(String xmlData) {
        String guestsStr = xmlData.substring(xmlData.indexOf("<" + XmlStructure.NUMBEROFGUESTS.getValue() + ">") + XmlStructure.NUMBEROFGUESTS.getValue().length() + 2, xmlData.indexOf("</" + XmlStructure.NUMBEROFGUESTS.getValue() + ">"));
        return Integer.parseInt(guestsStr);
    }
}

