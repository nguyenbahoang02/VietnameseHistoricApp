package variable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Festival extends Time {
	private String Location;
	private List<Integer> HistoricalFiguresId = new ArrayList<>();
	private List<String> HistoricalSites = new ArrayList<>();
	private List<String> Events = new ArrayList<>();

	public Festival(String time, String name, String location, List<Integer> historicalFiguresId,
			List<String> historicalSites, List<String> events) {
		super(time, name);
		this.Location = location;
		this.HistoricalFiguresId = historicalFiguresId;
		this.HistoricalSites = historicalSites;
		this.Events = events;
	}

	public Festival() {
	}
	
	public List<String> getEvents() {
		return Events;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public List<Integer> getHistoricalFiguresId() {
		return HistoricalFiguresId;
	}

	public List<String> getHistoricalSites() {
		return HistoricalSites;
	}

	public void addEvents(String event) {
		boolean check = true;
		for (int i = 0; i < Events.size(); i++)
			if (Events.get(i).contains(event) == true)
				check = false;

		if (check == true)
			Events.add(event);
	}

	public void addHistoricalFiguresId(int historicalfigure) {
		boolean check = true;
		for (int i = 0; i < HistoricalFiguresId.size(); i++)
			if (HistoricalFiguresId.get(i)==historicalfigure)
				check = false;

		if (check == true)
			HistoricalFiguresId.add(historicalfigure);
	}

	public void addHistoricalSites(String historicalsites) {
		HistoricalSites.add(historicalsites);
	}

	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if (this != null)
			return gson.toJson(this);
		else
			return this.toString();
	}

}
