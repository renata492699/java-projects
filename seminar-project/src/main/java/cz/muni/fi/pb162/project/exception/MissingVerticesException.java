package cz.muni.fi.pb162.project.exception;


/**
 * This is a class for missing vertices exception.
 *
 * @author Renata Urbanova
 */
public class MissingVerticesException extends RuntimeException{

    /**
     * Generate exception with given message.
     *
     * @param message of an exception
     */
    public MissingVerticesException(String message) {
        super(message);
    }

    /**
     * Generate exception with message and cause object.
     *
     * @param message of an exception
     * @param cause object
     */
    public MissingVerticesException(String message, Throwable cause) {
        super(message, cause);
    }
}
