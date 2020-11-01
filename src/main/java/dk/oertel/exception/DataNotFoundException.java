package dk.oertel.exception;

public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 2417300453948495013L;

    public DataNotFoundException(String message) {
        super(message);
    }
}