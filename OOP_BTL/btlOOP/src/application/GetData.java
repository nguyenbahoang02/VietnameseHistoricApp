package application;

import java.util.ArrayList;
import java.util.List;

public class GetData {

	public static void run() {
		resolve.RSHistoricalFigure resolveHistoricalFigure = new resolve.RSHistoricalFigure();
		List<variable.HistoricalFigure> listHistoricalFigure = new ArrayList<>();
		listHistoricalFigure.addAll(resolveHistoricalFigure.getDataFromHTML());
		resolveHistoricalFigure.writeDatatoFileJSON(listHistoricalFigure);
		
		System.out.println("=======================");
		resolve.RSEvent resolveEvent = new resolve.RSEvent();
		List<variable.Event> listEvents = new ArrayList<>();
		listEvents.addAll(resolveEvent.getDataFromHTML());
		resolveEvent.writeDatatoFileJSON(listEvents);
		
		System.out.println("=======================");
		resolve.RSHistoricalSite resolveHistoricalSite = new resolve.RSHistoricalSite();
		List<variable.HistoricalSite> listHistoricalSites = new ArrayList<>();
		listHistoricalSites.addAll(resolveHistoricalSite.getDataFromHTML());
		resolveHistoricalSite.writeDatatoFileJSON(listHistoricalSites);
		
		System.out.println("=======================");
		resolve.RSFestival resolveFestival = new resolve.RSFestival();
		List<variable.Festival> listFestivals = new ArrayList<>();
		listFestivals.addAll(resolveFestival.getDataFromHTML());
		resolveFestival.writeDatatoFileJSON(listFestivals);
		
		System.out.println("=======================");
		resolve.RSDynasty resolveDynasty = new resolve.RSDynasty();
		List<variable.Dynasty> listDynastys = new ArrayList<>();
		listDynastys.addAll(resolveDynasty.getDataFromHTML());
		resolveDynasty.writeDatatoFileJSON(listDynastys);
	}
}
