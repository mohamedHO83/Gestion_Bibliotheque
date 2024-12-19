package exceptions;

public class LivreNotFound extends RuntimeException {
    public LivreNotFound(String message) {
        super(message);
    }
}
