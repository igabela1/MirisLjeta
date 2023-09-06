package ba.unsa.etf.rpr.exceptions;

public class Room_BungalowException extends Exception {

    public Room_BungalowException(String message, Exception e) {
        super(message,e);
    }
    public Room_BungalowException(String message){
        super(message);
    }
}
