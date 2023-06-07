package variable;

public abstract class Person extends Entity {
	protected String name;
	protected String homeTown;
	public Person(String name, String homeTown) {
		super();
		this.name = name;
		this.homeTown = homeTown;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHomeTown() {
		return homeTown;
	}
	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}
}
