package exceptions;

/**
 * Represents an exception thrown when a book is not found in the library system.
 */
public class LivreNotFound extends RuntimeException {

    /**
     * Constructs a new LivreNotFound with the specified detail message.
     *
     * @param message The detail message, which provides more information about the exception.
     */
    public LivreNotFound(String message) {
        super(message);
    }
}
