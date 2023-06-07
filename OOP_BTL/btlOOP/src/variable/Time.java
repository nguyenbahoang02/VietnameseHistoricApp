package variable;

public abstract class Time extends Entity {
	protected String time;
	protected String name;
	public Time(String time, String name) {
		super();
		this.time = time;
		this.name = name;
	}
	
	public Time() {
		
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}