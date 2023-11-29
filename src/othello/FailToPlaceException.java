package othello;

public class FailToPlaceException extends Exception {

    // Constructor with only the message
    public FailToPlaceException(String message) {
        super(message);
        // Log the exception message if needed
        logException(message);
    }

    // Constructor with message and cause
    public FailToPlaceException(String message, Throwable cause) {
        super(message, cause);
        // Log the exception message and cause if needed
        logException(message + "; Cause: " + cause.getMessage());
    }

    // Method to log the exception details
    private void logException(String logMessage) {
        // Implement logging logic here
        // For example, you could log to a file, console, or a logging framework
        System.err.println("FailToPlaceException: " + logMessage);
    }
}
