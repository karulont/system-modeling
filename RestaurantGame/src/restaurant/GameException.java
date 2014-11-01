package restaurant;

public class GameException extends Throwable {
	public String message;
	public GameException(String message) {
		this.message = message;
	}
}
