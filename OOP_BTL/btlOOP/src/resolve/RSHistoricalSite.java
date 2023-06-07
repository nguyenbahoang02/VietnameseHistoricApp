package resolve;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RSHistoricalSite implements IResolveData<variable.HistoricalSite> {
	private static List<variable.HistoricalSite> getHistoricalSitesFromDsvh(List<String> listHistoricalFigures) {
		List<variable.HistoricalSite> listHistoricalSites = new ArrayList<>();
		resolve.RSHistoricalFigure resolveHistoricalFigure = new resolve.RSHistoricalFigure();
		List<variable.HistoricalFigure> listHistoricalFiguresFromFile = resolveHistoricalFigure.getDataFromFile();
		String url = "http://dsvh.gov.vn/danh-muc-di-tich-quoc-gia-dac-biet-1752";
		Document connect;
		try {
			connect = Jsoup.connect(url).timeout(0).get();
			Elements elementParent = connect.select("tr");
			for (Element i : elementParent) {
				if (i.children().get(1).text().contains("DTLS")) {
					String nameHistoricalSite = i.children().get(1).text();
					String locationHistoricalSite = i.children().get(3).text();
					List<String> historicalFigures = new ArrayList<>();
					List<Integer> listHistoricalFiguresId = new ArrayList<>();
					variable.HistoricalSite historicalSite = new variable.HistoricalSite();
					String href = i.children().get(1).firstElementChild().firstElementChild().attr("href");
					if (href != "") {
						String linkToGetHistoricalFigures = "http://dsvh.gov.vn" + href.replace("http:/", "");
						Element textToGetHistoricalFigures = Jsoup.connect(linkToGetHistoricalFigures).timeout(0).get()
								.selectFirst(".page-content");
						if (textToGetHistoricalFigures != null) {
							for (String historicalFigure : listHistoricalFigures) {
								if (textToGetHistoricalFigures.text().toUpperCase().contains(historicalFigure))
									historicalFigures.add(Config.nameFormat(historicalFigure));
							}
						}
					}
					boolean checkExist = false;
					for (variable.HistoricalSite checkHistoricalSite : listHistoricalSites) {
						if (nameHistoricalSite.toUpperCase().contains(checkHistoricalSite.getName().toUpperCase())) {
							checkExist = true;
							break;
						}
					}
					if (checkExist == false) {
						for (variable.HistoricalFigure historicalFigureFromFile : listHistoricalFiguresFromFile) {
							for (String historicalFigure : historicalFigures) {
								if (historicalFigureFromFile.getName().toUpperCase()
										.equals(historicalFigure.toUpperCase())) {
									listHistoricalFiguresId.add(historicalFigureFromFile.getId());
									historicalFigureFromFile.addHistoricalSiteId(historicalSite.getId());
								}
							}
						}
						historicalSite.setName(nameHistoricalSite);
						historicalSite.setLocation(locationHistoricalSite);
						historicalSite.setHistoricalFiguresId(listHistoricalFiguresId);
						listHistoricalSites.add(historicalSite);
					}

				}
			}
			;
		} catch (IOException e) {
			e.printStackTrace();
		}
		resolveHistoricalFigure.writeDatatoFileJSON(listHistoricalFiguresFromFile);
		return listHistoricalSites;
	}

	private static List<variable.HistoricalSite> getHistoricalSitesFromNKS(List<String> listHistoricalFigures) {
		List<variable.HistoricalSite> listHistoricalSites = new ArrayList<>();
		resolve.RSHistoricalFigure resolveHistoricalFigure = new resolve.RSHistoricalFigure();
		List<variable.HistoricalFigure> listHistoricalFiguresFromFile = resolveHistoricalFigure.getDataFromFile();
		String url = "https://nguoikesu.com/di-tich-lich-su";
		try {
			do {
				Document connect = Jsoup.connect(url).timeout(0).get();
				Elements eHistoricalSites = connect.select(".list-group-item.list-group-item-action > h3 > a");
				for (Element eHistoricalSite : eHistoricalSites) {
					String nameHistoricalSite = eHistoricalSite.text();
					String locationHistoricalSite = null;
					String linkHistoricalSite = "https://nguoikesu.com" + eHistoricalSite.attr("href");
					Document connectHistoricalSite = Jsoup.connect(linkHistoricalSite).timeout(0).get();
					Elements eLocationHistoricalSites = connectHistoricalSite.select(".infobox > table > tbody > tr");
					for (Element eLocationHistoricalSite : eLocationHistoricalSites) {
						if (eLocationHistoricalSite.text().contains("Vị trí ")
								|| eLocationHistoricalSite.text().contains("Địa chỉ ")
								|| eLocationHistoricalSite.text().contains("Địa điểm ")
								|| eLocationHistoricalSite.text().contains("Khu vực")) {
							locationHistoricalSite = eLocationHistoricalSite.text().replace("Vị trí ", "")
									.replace("Địa chỉ ", "").replace("Địa điểm ", "").replace("Khu vực", "").trim();
						}
					}
					List<String> historicalFigures = new ArrayList<>();
					List<Integer> listHistoricalFiguresId = new ArrayList<>();
					for (Element historicalFigure : connectHistoricalSite
							.select("#list-ref-characters > .list-references > li > a")) {
						historicalFigures.add(historicalFigure.text());
					}
					if (locationHistoricalSite != null) {
						variable.HistoricalSite historicalSite = new variable.HistoricalSite();
						for (variable.HistoricalFigure historicalFigureFromFile : listHistoricalFiguresFromFile) {
							for (String historicalFigure : historicalFigures) {
								if (historicalFigureFromFile.getName().toUpperCase()
										.equals(historicalFigure.toUpperCase())) {
									listHistoricalFiguresId.add(historicalFigureFromFile.getId());
									historicalFigureFromFile.addHistoricalSiteId(historicalSite.getId());
								}
							}
						}
						historicalSite.setLocation(locationHistoricalSite);
						historicalSite.setName(nameHistoricalSite);
						historicalSite.setHistoricalFiguresId(listHistoricalFiguresId);
						listHistoricalSites.add(historicalSite);
					}
				}
				Element linkNextPage = connect.selectFirst(".page-item > a[aria-label=\"Đi tới tiếp tục trang\"]");
				if (linkNextPage != null) {
					url = "https://nguoikesu.com" + linkNextPage.attr("href");
				} else
					url = null;
			} while (url != null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		resolveHistoricalFigure.writeDatatoFileJSON(listHistoricalFiguresFromFile);
		return listHistoricalSites;
	}

	@Override
	public List<variable.HistoricalSite> getDataFromHTML() {
		List<variable.HistoricalSite> listHistoricalSites = new ArrayList<>();
		List<variable.HistoricalFigure> listHistoricalFiguresFromFile = new resolve.RSHistoricalFigure().getDataFromFile();
		List<String> listHistoricalFigures = new ArrayList<>();
		for (variable.HistoricalFigure i : listHistoricalFiguresFromFile)
			listHistoricalFigures.add(i.getName().toUpperCase() + " ");
		listHistoricalSites.addAll(getHistoricalSitesFromNKS(listHistoricalFigures));
		listHistoricalSites.addAll(getHistoricalSitesFromDsvh(listHistoricalFigures));
		return listHistoricalSites;
	}

	@Override
	public List<variable.HistoricalSite> getDataFromFile() {
		try {
			Gson gson = new Gson();
			Reader reader = Files.newBufferedReader(Paths.get(Config.PATH_FILE + "historicalSites.json"));
			List<variable.HistoricalSite> listHistoricalSites = new Gson().fromJson(reader,
					new TypeToken<List<variable.HistoricalSite>>() {
					}.getType());
			reader.close();
			return listHistoricalSites;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public void writeDatatoFileJSON(List<variable.HistoricalSite> data) {
		// TODO Auto-generated method stub
		try {
			FileWriter fw = new FileWriter(Config.PATH_FILE + "historicalSites.json");
			fw.write("[\n");
			for (int i = 0; i < data.size(); i++) {
				fw.write(data.get(i).toString());
				if (i != data.size() - 1)
					fw.write(",\n");
				else
					fw.write("\n");
			}
			fw.write("\n]");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
