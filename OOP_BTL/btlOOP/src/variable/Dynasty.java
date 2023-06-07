package variable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Dynasty extends Time {
	private String CountryName;
	private String Description;
	private List<Integer> HistoricalFiguresId = new ArrayList<>();
	private List<Integer> EventsId = new ArrayList<>();
	private String KeTuc;
	private String TienNhiem;
	
	public String getCountryName() {
		return CountryName;
	}

	public void setCountryName(String countryName) {
		CountryName = countryName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	

	public List<Integer> getHistoricalFiguresId() {
		return HistoricalFiguresId;
	}
	
	public void addHistoricalFiguresId(int historicalfigure) {
		HistoricalFiguresId.add(historicalfigure);
	}

	public void addEventsId(int event) {
		EventsId.add(event);
	}

	public List<Integer> getEventsId() {
		return EventsId;
	}

	

	public String getKeTuc() {
		return KeTuc;
	}

	public void setKeTuc(String keTuc) {
		KeTuc = keTuc;
	}

	public String getTienNhiem() {
		return TienNhiem;
	}

	public void setTienNhiem(String tienNhiem) {
		TienNhiem = tienNhiem;
	}

	public Dynasty(String date, String name, String countryName, String description,
			List<Integer> historicalFiguresId, List<Integer> eventsId, String keTuc, String tienNhiem) {
		super(date, name);
		CountryName = countryName;
		Description = description;
		HistoricalFiguresId = historicalFiguresId;
		EventsId = eventsId;
		KeTuc = keTuc;
		TienNhiem = tienNhiem;
	}
	
	public Dynasty() {
	}
	
	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if(this != null)
		return gson.toJson(this);
		else return this.toString();
	}
	
	

}