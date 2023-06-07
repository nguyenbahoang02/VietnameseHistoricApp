package variable;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import resolve.RSDynasty;
import resolve.RSEvent;
import resolve.RSFestival;
import resolve.RSHistoricalFigure;
import resolve.RSHistoricalSite;

public class Data {
	
	private ObservableList<Dynasty> listDynasties = FXCollections.observableArrayList();
	private ObservableList<HistoricalSite> listHistoricalSites = FXCollections.observableArrayList();
	private ObservableList<Event> listEvents = FXCollections.observableArrayList();
	private ObservableList<HistoricalFigure> listHistoricalFigures = FXCollections.observableArrayList();
	private ObservableList<Festival> listFestivals = FXCollections.observableArrayList();
	private ObservableList<Entity> listEntities = FXCollections.observableArrayList();
	
	public Data(ObservableList<Dynasty> listDynasties, ObservableList<HistoricalSite> listHistoricalSites, 
			ObservableList<Event> listEvents, ObservableList<HistoricalFigure> listHistoricalFigures, 
			ObservableList<Festival> listFestivals) {
		this.listDynasties = listDynasties;
		this.listHistoricalSites = listHistoricalSites;
		this.listEvents = listEvents;
		this.listHistoricalFigures = listHistoricalFigures;
		this.listFestivals = listFestivals;
	}
	
	public Data() {
		
	}
	
	public ObservableList<Entity> getListEntities() {
		return listEntities;
	}

	public ObservableList<Dynasty> getListDynasties() {
		return listDynasties;
	}
	public ObservableList<HistoricalSite> getListHistoricalSites() {
		return listHistoricalSites;
	}
	public ObservableList<Event> getListEvents() {
		return listEvents;
	}
	public ObservableList<HistoricalFigure> getListHistoricalFigures() {
		return listHistoricalFigures;
	}
	public ObservableList<Festival> getListFestivals() {
		return listFestivals;
	}
	
	public HistoricalFigure findHistoricalFigure(String string) {
		for(int i = 0; i<listHistoricalFigures.size(); i++) {
			if(listHistoricalFigures.get(i).getName().equals(string)) {
				return listHistoricalFigures.get(i);
			}
		}
		return null;
	}
	
	public ObservableList<HistoricalFigure> searchHistoricalFigure(String string) {
		ObservableList<HistoricalFigure> historicalFigures = FXCollections.observableArrayList();
		for(int i = 0; i<listHistoricalFigures.size(); i++) {
			if(listHistoricalFigures.get(i).getName().toUpperCase().contains(string.toUpperCase())) {
				historicalFigures.add(listHistoricalFigures.get(i));
			}
		}
		return historicalFigures;
	}
	
	public HistoricalSite findHistoricalSite(String string) {
		for(int i = 0; i<listHistoricalSites.size(); i++) {
			if(listHistoricalSites.get(i).getName().equals(string)) {
				return listHistoricalSites.get(i);
			}
		}
		return null;
	}
	
	public ObservableList<HistoricalSite> searchHistoricalSite(String string) {
		ObservableList<HistoricalSite> historicalSites = FXCollections.observableArrayList();
		for(int i = 0; i<listHistoricalSites.size(); i++) {
			if(listHistoricalSites.get(i).getName().toUpperCase().contains(string.toUpperCase())) {
				historicalSites.add(listHistoricalSites.get(i));
			}
		}
		return historicalSites;
	}
	
	public Dynasty findDynasty(String string) {
		for(int i = 0; i<listDynasties.size(); i++) {
			if(listDynasties.get(i).getName().equals(string)) {
				return listDynasties.get(i);
			}
		}
		return null;
	}
	
	public ObservableList<Dynasty> searchDynasty(String string) {
		ObservableList<Dynasty> dynasties = FXCollections.observableArrayList();
		for(int i = 0; i<listDynasties.size(); i++) {
			if(listDynasties.get(i).getName().toUpperCase().contains(string.toUpperCase())) {
				dynasties.add(listDynasties.get(i));
			}
		}
		return dynasties;
	}
	
	public Event findEvent(String string) {
		for(int i = 0; i<listEvents.size(); i++) {
			if(listEvents.get(i).getName().equals(string)) {
				return listEvents.get(i);
			}
		}
		return null;
	}
	
	public ObservableList<Event> searchEvent(String string) {
		ObservableList<Event> events = FXCollections.observableArrayList();
		for(int i = 0; i<listEvents.size(); i++) {
			if(listEvents.get(i).getName().toUpperCase().contains(string.toUpperCase())) {
				events.add(listEvents.get(i));
			}
		}
		return events;
	}
	
	public Festival findFestival(String string) {
		for(int i = 0; i<listFestivals.size(); i++) {
			if(listFestivals.get(i).getName().equals(string)) {
				return listFestivals.get(i);
			}
		}
		return null;
	}
	
	public ObservableList<Festival> searchFestival(String string) {
		ObservableList<Festival> festivals = FXCollections.observableArrayList();
		for(int i = 0; i<listFestivals.size(); i++) {
			if(listFestivals.get(i).getName().toUpperCase().contains(string.toUpperCase())) {
				festivals.add(listFestivals.get(i));
			}
		}
		return festivals;
	}
	
	public void mergeData() {
		for(int i=0; i<listDynasties.size();i++) {
			listEntities.add(listDynasties.get(i));
		}
//		System.out.println(listDynasties.size());
		for(int i=0; i<listHistoricalSites.size();i++) {
			listEntities.add(listHistoricalSites.get(i));
		}
//		System.out.println(listHistoricalSites.size());
		for(int i=0; i<listEvents.size();i++) {
			listEntities.add(listEvents.get(i));
		}
//		System.out.println(listEvents.size());
		for(int i=0; i<listHistoricalFigures.size();i++) {
			listEntities.add(listHistoricalFigures.get(i));
		}
//		System.out.println(listHistoricalFigures.size());
		for(int i=0; i<listFestivals.size();i++) {
			listEntities.add(listFestivals.get(i));
		}
//		System.out.println(listFestivals.size());
		Collections.sort(listEntities, new Comparator<Entity>() {
			@Override
			public int compare(Entity e1, Entity e2) {
				return e1.getId()>e2.getId() ? 1 : -1;
			}
		});
//		for(int i=0; i<listEntities.size();i++) {
//			System.out.println(listEntities.get(i).getId());
//		}
	}
	
	/**
	 * <ul>
	 * <li>1 là triều đại</li>
	 * <li>2 là sự kiện</li>
	 * <li>3 là lễ hội</li>
	 * <li>4 là nhân vật</li>
	 * <li>5 là di tích</li>
	 * </ul>
	 */
	
	public void getData() {
		//đọc file json
		parseTDLSList();
		parseSKLSList();
		parseLHVHList();
		parseNVLSList();
		parseDTLSList();
	}
	
	public void parseTDLSList() {
		List<Dynasty> listDynasties = new RSDynasty().getDataFromFile();
		this.listDynasties.addAll(listDynasties);
	}
	
	public void parseSKLSList() {
		List<Event> listEvents = new RSEvent().getDataFromFile();
		this.listEvents.addAll(listEvents);
	}
	
	public void parseLHVHList() {
		List<Festival> listFestivals = new RSFestival().getDataFromFile();
		this.listFestivals.addAll(listFestivals);
	}
	
	public void parseNVLSList() {
		List<HistoricalFigure> listHistoricalFigures = new RSHistoricalFigure().getDataFromFile();
		this.listHistoricalFigures.addAll(listHistoricalFigures);
	}
	
	public void parseDTLSList() {
		List<HistoricalSite> listHistoricalSites = new RSHistoricalSite().getDataFromFile();
		this.listHistoricalSites.addAll(listHistoricalSites);
	}
}











