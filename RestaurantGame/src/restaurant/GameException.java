package restaurant;

public class GameException extends Throwable {
	private String message;
	public GameException(String message) {
		this.message = message;
	}
	@Override
	public String getMessage() {
		return message;
	}
}
