package variable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HistoricalFigure extends Person {
	String dateOfBirth;
	String dateOfDeath;
	String otherName;
	String dynasty;
	List<Integer> historicalSitesId = new ArrayList<>();
	List<Integer> eventsId = new ArrayList<>();
	String role;

	public HistoricalFigure(String name, String homeTown, String dateOfBirth, String dateOfDeath, String otherName,
			String dynasty, List<Integer> historicalSitesId, List<Integer> eventsId, String role) {
		super(name, homeTown);
		this.dateOfBirth = dateOfBirth;
		this.dateOfDeath = dateOfDeath;
		this.otherName = otherName;
		this.dynasty = dynasty;
		this.historicalSitesId = historicalSitesId;
		this.eventsId = eventsId;
		this.role = role;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(String dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public String getDynasty() {
		return dynasty;
	}

	public void setDynasty(String dynasty) {
		this.dynasty = dynasty;
	}

	public List<Integer> getHistoricalSitesId() {
		return historicalSitesId;
	}

	public void setHistoricalSitesId(List<Integer> historicalSitesId) {
		this.historicalSitesId = historicalSitesId;
	}

	public List<Integer> getEventsId() {
		return eventsId;
	}

	public void setEventsId(List<Integer> eventsId) {
		this.eventsId = eventsId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void addHistoricalSiteId(int historicalSiteId) {
		this.historicalSitesId.add(historicalSiteId);
	}

	public void addEventId(int eventId) {
		this.eventsId.add(eventId);
	}

	@Override
	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}
}
