package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.domain.Room_Bungalow;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.io.PrintWriter;
import java.util.List;

/**
 * The type App.
 */
public class App {

    private static final Option addRoom = new Option("ar", "add-room", false, "Adding new room to a hotel in the hotel booking database");
    private static final Option deleteRoom = new Option("dr", "delete-room", false, "Deleting a room from a hotel in the hotel booking database");
    private static final Option updateRoom = new Option("ur", "update-room", false, "Updating a room in a hotel in the hotel booking database");
    private static final Option getRooms = new Option("getR", "get-rooms", false, "Printing all rooms from a hotel in the hotel booking database");
    private static final Option roomStatus = new Option(null, "room-status", false, "Defining room status for the next operation");
    private static final Option roomPrice = new Option(null, "room-price", false, "Defining the room price for the next operation");
    private static final Option roomID = new Option(null, "room-id", false, "Defining the room number for the next operation");

    /**
     * Print formatted options.
     *
     * @param options the options
     */
    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar mirisljeta-booking.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    /**
     * Add options options.
     *
     * @return the options
     */
    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addRoom);
        options.addOption(deleteRoom);
        options.addOption(updateRoom);
        options.addOption(getRooms);
        options.addOption(roomID);
        options.addOption(roomPrice);
        options.addOption(roomStatus);
        return options;
    }

    /**
     * Search through rooms room.
     *
     * @param listOfRooms the list of rooms
     * @param roomNumber  the room number
     * @return the room
     */
    public static Room_Bungalow searchThroughRooms(List<Room_Bungalow> listOfRooms, int roomNumber) {
        Room_Bungalow room;
        room = listOfRooms.stream().filter(r -> r.getId() == roomNumber).findAny().orElse(null);
        return room;
    }

    /*
        public static void main( String[] args ) throws Exception, Room_BungalowException {
          System.out.println("Welcome to Miris Ljeta");
            Scanner input =  new Scanner(System.in);
            RoomBungManager bookManager = new RoomBungManager();
            UserManager userManager =  new UserManager();
            while(true) {
                System.out.println("Choose an option from the following list");
                System.out.println("1 - Login as administrator");
                System.out.println("2 - Sign up");
                System.out.println("3 - Login");
                System.out.println("4 - List of all rooms/bungallows");
                System.out.println("5 - Close the application");
                int option = input.nextInt();
                if(option == 1) {
                    List<User> users = FXCollections.observableList(UserManager.getAll());
                    String username, password;
                    Scanner input4 = new Scanner(System.in);
                    System.out.println("Enter username");
                    username = input4.nextLine();
                    int id = 0;
                    boolean valid = true;
                    do {
                        for (int i = 0; i < users.size(); i++) {
                            if (username.equals(users.get(i).getUsername())) {
                                id = users.get(i).getId();
                                break;
                            }
                        }
                        if(id == 0) {
                            System.out.println("Username not found, try again");
                            valid = false;
                            username = input4.nextLine();
                        }
                        else valid = true;
                    }while(!valid);
                    boolean valid1 = false;
                    System.out.println("Enter password");
                    do {
                        password = input4.nextLine();
                        if (password.equals(userManager.getById(id).getPassword()))  {
                            valid1 = true;
                        }
                        else System.out.println("Invalid password, try again");
                    }while (!valid1);
                    Scanner input5 = new Scanner(System.in);
                    if(userManager.getById(id).isAdministrator() == 1) {
                        System.out.println("Welcome back " + userManager.getById(id).getFirstName());
                        while(true) {
                            System.out.println("Choose an option from the following list");
                            System.out.println("1 - Add a user");
                            System.out.println("2 - Delete a user");
                            System.out.println("3 - Add a room/bungalow");
                            System.out.println("4 - Delete a room/bungalow");
                            System.out.println("5 - Close the application");
                            int option2 = input5.nextInt();
                            if(option2 == 1) {
                                User u = new User();
                                Scanner input3 = new Scanner(System.in);
                                String name, lastName, username, passwordd="";
                                System.out.println("First name");
                                name = input3.nextLine();
                                System.out.println("Last name");
                                lastName = input3.nextLine();
                                System.out.println("Username");
                                username = input3.nextLine();
                                boolean val = true;
                                do {
                                    valid = true;
                                    List<User> list = FXCollections.observableList(userManager.getAll());
                                    for (int i = 0; i < list.size(); i++) {
                                        if (username.equals(list.get(i).getUsername())) {
                                            val = false;
                                            System.out.println("Invalid username, try again");
                                            username = input3.nextLine();
                                        }
                                    }
                                    if(username.length()<5) {
                                        val= false;
                                        System.out.println("Invalid username, try again");
                                        username = input3.nextLine();
                                    }
                                }while(!val);
                                System.out.println("Password");
                                passwordd = input3.nextLine();
                                while(passwordd.length()<8) {
                                    System.out.println("Invalid password, try again");
                                    passwordd = input3.nextLine();
                                }
                                u.setFirstName(name);
                                u.setLastName(lastName);
                                u.setUsername(username);
                                u.setPassword(passwordd);
                                userManager.add(u);

                            }
                            if(option2 == 2) {
                                boolean v = false;
                                List<User> user = FXCollections.observableList(userManager.getAll());
                                Scanner input6 = new Scanner(System.in);
                                int indeks = 0;
                                System.out.println("Id:");
                                indeks = input6.nextInt();
                                for(int i = 0; i<user.size(); i++) {
                                    if(user.get(i).getId()==indeks) v = true;
                                }if(v)
                                    userManager.delete(indeks);
                                else System.out.println("User not found");
                            }
                            if(option2 == 3) {
                                Scanner input6 = new Scanner(System.in);
                                String  roombung="";
                                boolean number;
                                roombung = input6.nextLine();
                                System.out.println("Number of available:");
                                number = input6.nextBoolean();
                                Room_Bungalow b = new Room_Bungalow(2, 0, 1, true);
                              //  b.setAvailable(number);
                                RoomBungManager.add(b);
                            }
                            if(option2 == 4) {
                                Scanner input6 = new Scanner(System.in);
                                int i = 0;
                                System.out.println("Id:");
                                i = input6.nextInt();
                                bookManager.delete(i);
                            }
                            if(option2 == 5) return;
                        }
                    }
                    else System.out.println("You don't have administrator access");

                }
                if(option == 2) {
                    User u = new User();
                    Scanner input3 = new Scanner(System.in);
                    String name=" ", lastName="", username="", password="";
                    System.out.println("Enter your name");
                    name = input3.nextLine();
                    System.out.println("Enter your last name");
                    lastName = input3.nextLine();
                    System.out.println("Enter your username");
                    username = input3.nextLine();
                    boolean valid = true;
                    do {
                        valid = true;
                        List<User> users = FXCollections.observableList(UserManager.getAll());
                        for (int i = 0; i < users.size(); i++) {
                            if (username.equals(users.get(i).getUsername())) {
                                valid = false;
                                System.out.println("Invalid username, try again");
                                username = input3.nextLine();
                            }
                        }
                        if(username.length()<5) {
                            valid = false;
                            System.out.println("Invalid username, try again");
                            username = input3.nextLine();
                        }
                    }while(!valid);
                    System.out.println("Enter your password");
                    password = input3.nextLine();
                    while(password.length()<5) {
                        System.out.println("Invalid password, try again");
                        password = input3.nextLine();
                    }

                    u.setFirstName(name);
                    u.setLastName(lastName);
                    u.setUsername(username);
                    u.setPassword(password);
                    UserManager.add(u);
                }
                if(option == 3) {
                    List<User> users = FXCollections.observableList(UserManager.getAll());
                    String username = "", password=" ";
                    Scanner input4 = new Scanner(System.in);
                    System.out.println("Enter username");
                    username = input4.nextLine();
                    int id = 0;
                    boolean valid = true;
                    do {
                        for (int i = 0; i < users.size(); i++) {
                            if (username.equals(users.get(i).getUsername())) {
                                id = users.get(i).getId();
                                break;
                            }
                        }
                        if(id == 0) {
                            System.out.println("Username not found, try again");
                            valid = false;
                            username = input4.nextLine();
                        }
                        else valid = true;
                    }while(!valid);
                    boolean valid1 = false;
                    System.out.println("Enter password");
                    do {
                        password = input4.nextLine();
                        if (password.equals(userManager.getById(id).getPassword()))  {
                            valid1 = true;
                        }
                        else System.out.println("Invalid password, try again");
                    }while (!valid1);

                    System.out.println("Welcome back " + userManager.getById(id).getFirstName());
                }

                if(option == 4) {
                    List<Room_Bungalow> rooms = FXCollections.observableList(RoomBungManager.getAll());
                    for(int i = 0; i<rooms.size(); i++) {
                        System.out.println(rooms.get(i).getId() + " " + rooms.get(i).getStatus());
                    }
                }


                if(option == 5) return;



            }
        }*/
}
