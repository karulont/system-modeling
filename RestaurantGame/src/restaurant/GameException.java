package restaurant;

public class GameException extends Throwable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5186236998056220725L;
	private String message;

	public GameException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}