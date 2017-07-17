package net.serenitybdd.taxi.apis;

/**
 * Created by NoteP on 10.07.2017.
 */
public class NotRegisteredException extends RuntimeException {
    public NotRegisteredException(String notRegisteredMessage) {
        super(notRegisteredMessage);
    }
}
