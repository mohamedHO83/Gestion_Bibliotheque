package exceptions;

/**
 * Represents an exception thrown when a user is not found in the library system.
 */
public class UserNotFound extends RuntimeException {

  /**
   * Constructs a new UserNotFound with the specified detail message.
   *
   * @param message The detail message, which provides more information about the exception.
   */
  public UserNotFound(String message) {
    super(message);
  }
}
