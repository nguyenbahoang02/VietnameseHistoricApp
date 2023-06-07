package variable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Event extends Time {
	private List<Integer> historicalFiguresId = new ArrayList<>();
	
	

	public Event() {
	}

	public Event(String date, String name, List<Integer> historicalFiguresId) {
		super(date, name);
		this.historicalFiguresId = historicalFiguresId;
	}

	public List<Integer> getHistoricalFiguresId() {
		return historicalFiguresId;
	}

	public void setHistoricalFiguresId(List<Integer> historicalFiguresId) {
		this.historicalFiguresId = historicalFiguresId;
	}

	@Override
	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}
}
