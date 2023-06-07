package variable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HistoricalSite extends Location {
	private List<Integer> historicalFiguresId = new ArrayList<>();
	
	public HistoricalSite() {
	}

	public HistoricalSite(String location, String name, List<Integer> historicalFiguresId) {
		super(location, name);
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
