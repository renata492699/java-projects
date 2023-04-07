package cz.muni.fi.pb162.project.exception;

/**
 * This is a class for empty drawable exception.
 *
 * @author Renata Urbanova
 */
public class EmptyDrawableException extends Exception{

    /**
     * Generate empty drawable exception with given message.
     *
     * @param message of an exception
     */
    public EmptyDrawableException(String message) {
        super(message);
    }

    /**
     * Generate empty drawable exception with given message and cause object.
     *
     * @param message of an exception
     * @param cause object
     */
    public EmptyDrawableException(String message, Throwable cause) {
        super(message, cause);
    }
}

