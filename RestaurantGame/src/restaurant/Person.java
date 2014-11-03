package restaurant;

public abstract class Person {
	protected String name;
	protected String surname;
	@Override
	public String toString() {
		return name + " " + surname;
	}
}
