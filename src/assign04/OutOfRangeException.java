package assign04;

public class OutOfRangeException extends RuntimeException {
    public OutOfRangeException(String dataTypeName) {
        super("The value is too large for the " + dataTypeName + " data type.");
    }
}
