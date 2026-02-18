package domain.account;

public class ConcurrencyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConcurrencyException(
			final String message) {
		super(message);
	}
	
}
