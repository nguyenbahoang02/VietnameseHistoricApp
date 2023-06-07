package variable;

public abstract class Entity {
	protected static int GLOBAL_ID;
	private int id;
	public Entity() {
		this.id = Entity.GLOBAL_ID++;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return null;
	}
}
