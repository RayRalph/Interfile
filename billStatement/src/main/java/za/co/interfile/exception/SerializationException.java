package za.co.interfile.exception;


/**
 * Exception that occurs whilst serializing and deserializing.
 *
 */
public class SerializationException extends Exception {

	private static final long serialVersionUID = -6157310279293403805L;

	public SerializationException(String message) {
		super(message);
	}

	public SerializationException(String message, Throwable cause) {
		super(message, cause);
	}
}
