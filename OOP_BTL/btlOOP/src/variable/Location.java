package variable;

public abstract class Location extends Entity{
	protected String location;
	protected String name;
	
	public Location() {
	}
	
	public Location(String location, String name) {
		super();
		this.location = location;
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
