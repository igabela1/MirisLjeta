package ba.unsa.etf.rpr.Exceptions;

public class Room_BungalowException extends Throwable {

    public Room_BungalowException(String message, Exception e) {
        super(message,e);
    }
    public Room_BungalowException(String message){
        super(message);
    }
}
