public class TooManyLoginAttemptsException extends Exception{
    public TooManyLoginAttemptsException(String description) {
        super(description);
    }
}
