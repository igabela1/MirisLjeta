package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.RoomBungManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Room_Bungalow;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.Room_BungalowException;
import org.apache.commons.cli.*;

import java.io.PrintWriter;

public class App {
    private static final Option getUsers = new Option("u", "get-users", false, "Get all users");
    private static final Option getRooms = new Option("r", "get-rooms", false, "Get all rooms");
    private static final Option getReservations = new Option("res", "get-reservations", false, "Get all reservations");

    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar mirisLjeta.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    public static void main(String[] args) {
        CommandLineParser commandLineParser = new DefaultParser();
        Options options = addOptions();

        try {
            CommandLine commandLine = commandLineParser.parse(options, args);
            if (commandLine.hasOption("u")) {
                // Poziv metode za dobavljanje i ispis svih korisnika
                printAllUsers();
            } else if (commandLine.hasOption("r")) {
                // Poziv metode za dobavljanje i ispis svih soba
                printAllRooms();
            } else if (commandLine.hasOption("res")) {
                // Poziv metode za dobavljanje i ispis svih rezervacija
                printAllReservations();
            } else {
                System.out.println("Invalid option entered.");
                printFormattedOptions(options);
            }
            // ...
        } catch (ParseException e) {
            System.out.println("Error while parsing the command line arguments: " + e.getMessage());
            printFormattedOptions(options);
        }
    }

    // Metoda za dodavanje opcija
    public static Options addOptions() {
        Options options = new Options();
        options.addOption(getUsers);
        options.addOption(getRooms);
        options.addOption(getReservations);
        return options;
    }

    // Metoda za dobavljanje i ispis svih korisnika
    public static void printAllUsers() {
        try {
            UserManager userManager = new UserManager();
            for (User user : userManager.getAll()) {
                System.out.println("User ID: " + user.getId());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Full Name: " + user.getFirstName()+user.getLastName());
                System.out.println("Email: " + user.getEmail());
                System.out.println();
            }
            System.out.println("All users fetched successfully.");
        } catch (Room_BungalowException e) {
            System.err.println("Error while fetching users: " + e.getMessage());
        }
    }

    // Metoda za dobavljanje i ispis svih soba
    public static void printAllRooms() {
        try {
            RoomBungManager roomManager = new RoomBungManager();
            for (Room_Bungalow room : roomManager.getAll()) {
                System.out.println("Room ID: " + room.getId());
                System.out.println("Capacity: " + room.getCapacity());
                System.out.println("Price Per Night: " + room.getPricePerNight());
                System.out.println("Status: " + (room.getStatus() ? "Available" : "Booked"));
                System.out.println();
            }
            System.out.println("All rooms fetched successfully.");
        } catch (Room_BungalowException e) {
            System.err.println("Error while fetching rooms: " + e.getMessage());
        }
    }

    // Metoda za dobavljanje i ispis svih rezervacija
    public static void printAllReservations() {
        try {
            ReservationManager reservationManager = new ReservationManager();
            for (Reservation reservation : reservationManager.getAll()) {
                System.out.println("Reservation ID: " + reservation.getId());
                System.out.println("Check-In Date: " + reservation.getCheckIn());
                System.out.println("Check-Out Date: " + reservation.getCheckOut());
                System.out.println("Total Price: " + reservation.getTotal());
                System.out.println("Username: " + reservation.getUsername().getUsername());
                System.out.println("Room ID: " + reservation.getRoomId().getId());
                System.out.println();
            }
            System.out.println("All reservations fetched successfully.");
        } catch (Room_BungalowException e) {
            System.err.println("Error while fetching reservations: " + e.getMessage());
        }
    }

}
