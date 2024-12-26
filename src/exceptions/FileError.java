package exceptions;

public class FileError extends RuntimeException {
  public FileError(String message) {
    super(message);
  }
}
