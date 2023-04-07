package cz.muni.fi.pb162.project.exception;


/**
 * This is a class for transparent color exception.
 *
 * @author Renata Urbanova
 */
public class TransparentColorException extends Exception{

    /**
     * Generate exception with message.
     *
     * @param message of an exception
     */
    public TransparentColorException(String message) {
        super(message);
    }

    /**
     * Generate exception with message.
     *
     * @param message of an exception
     * @param cause object
     */
    public TransparentColorException(String message, Throwable cause) {
        super(message, cause);
    }
}
