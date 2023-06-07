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

public class RSEvent implements IResolveData<variable.Event> {
	// kiểm tra từng nhân vật lịch sử có tồn tại trong đoạn text không ?
	private static ArrayList<String> getHistoricalFigures(ArrayList<String> listHistoricalFigures,
			ArrayList<String> historicalFigures, String text) {
		for (String i : listHistoricalFigures) {
			if (text.toUpperCase().contains(i) && !historicalFigures.contains(Config.nameFormat(i)))
				historicalFigures.add(Config.nameFormat(i));
		}

		return historicalFigures;
	}

	// lấy dữ liệu nhân vật liên quan từ Wiki
	private static ArrayList<String> getHistoricalFiguresToWiki(ArrayList<String> listHistoricalFigures,
			ArrayList<String> historicalFigures, String nameEvent, Element i) {
		try {
			for (Element j : i.children()) {
				if (j.outerHtml().contains("</a>")) {
					int commaIndex = nameEvent.length();
					for (int k = 0; k < nameEvent.length(); k++)
						if (nameEvent.charAt(k) == ',')
							commaIndex = k;
					if (listHistoricalFigures.contains(j.html().toUpperCase().replace("KHỞI NGHĨA", "").trim())) {
						historicalFigures.add(Config.nameFormat(j.html()).replace("Khởi Nghĩa", ""));
					}
					if (j.html().toUpperCase().equals(nameEvent.toUpperCase())) {
						Document connectWiki = Jsoup.connect("https://vi.wikipedia.org" + j.attr("href")).get();
						Elements elementsConnectWiki = connectWiki.select(".infobox > tbody > tr");
						for (Element k : elementsConnectWiki) {
							if (k.text().equals("Chỉ huy và lãnh đạo")) {
								historicalFigures = getHistoricalFigures(listHistoricalFigures, historicalFigures,
										k.nextElementSibling().text());
							}
						}
					}
				}
			}
		} catch (IOException e) {

		}
		return historicalFigures;
	}

	// lấy dữ liệu nhân vật liên quan từ người kể sử
	private static ArrayList<String> getHistoricalFiguresToNKS(ArrayList<String> listHistoricalFigures,
			ArrayList<String> historicalFigures, String nameEvent) {
		try {
			int commaIndex = nameEvent.length();
			for (int j = 0; j < nameEvent.length(); j++)
				if (nameEvent.charAt(j) == ',')
					commaIndex = j;
			String link = "https://www.google.com.vn/search?q=" + nameEvent.substring(0, commaIndex).trim()
					+ " nguoikesu";
			Elements elementsNKS = Jsoup.connect(link).get().select(".yuRUbf > a");
			for (Element element : elementsNKS) {
				if (element.attr("href").contains("https://nguoikesu.com/nhan-vat")) {
					for (Element elementChild : element.children()) {
						if (elementChild.outerHtml().contains("</h3>")) {
							historicalFigures.add(elementChild.text().replace("- Người Kể Sử", "")
									.replace("- NguoiKeSu.com", "").trim());
						}
					}
				}
				if (element.attr("href").contains("https://nguoikesu.com/dong-lich-su")) {
					String text = Jsoup.connect(element.attr("href")).get().select("#jm-maincontent").text();
					historicalFigures = getHistoricalFigures(listHistoricalFigures, historicalFigures, text);
				}
			}
		} catch (IOException e) {

		}
		return historicalFigures;
	}

	// lấy Date bằng cách phân tích html
	private static String getDateFromHtml(String html) {
		if (html.contains("</b>")) {
			int indexEnd;
			int indexStart = 999;
			String dateToHtml = html;
			for (int i = 0; i < html.length(); i++) {
				if (html.charAt(i) == '<' && html.charAt(i + 1) == 'b')
					indexStart = i + 3;
				if (i > indexStart && html.charAt(i) == '<' && html.charAt(i + 1) == '/') {
					indexEnd = i;
					dateToHtml = html.substring(indexStart, indexEnd);
					break;
				}
			}
			return dateToHtml.trim();
		}
		return null;
	}

	// lấy tên Event bằng cách phân tích html
	private static String getEventFromHtml(String html) {
		if (html.contains("</a>")) {
			if (html.contains("</b>")) {
				html = html.replace(getDateFromHtml(html), "");
				html = html.replace("<b>", "");
				html = html.replace("</b>", "");
			}
			while (html.contains("<a")) {
				int indexStart = 999;
				int indexEnd;
				for (int i = 0; i < html.length(); i++) {
					if (html.charAt(i) == '<' && html.charAt(i + 1) == 'a') {
						indexStart = i;
					}
					if (i > indexStart && html.charAt(i) == '>') {
						indexEnd = i + 1;
						String subString = html.substring(indexStart, indexEnd);
						html = html.replace(subString, "");
						indexStart = 999;
					}
				}
			}
			return html.replace("</a>", "").trim();
		}
		return null;
	}

	@Override
	public List<variable.Event> getDataFromHTML() {
		List<variable.Event> listEvents = new ArrayList<>();
		ArrayList<String> listHistoricalFigures = new ArrayList<>();
		resolve.RSHistoricalFigure resolveHistoricalFigure = new resolve.RSHistoricalFigure();
		List<variable.HistoricalFigure> historicalFiguresFromFile = resolveHistoricalFigure.getDataFromFile();
		for (variable.HistoricalFigure i : historicalFiguresFromFile) {
			listHistoricalFigures.add(i.getName().toUpperCase());
		}
		String path = "https://vi.wikipedia.org";

		String url = "https://vi.wikipedia.org/wiki/Ni%C3%AAn_bi%E1%BB%83u_l%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam";
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements data1 = doc.select(".mw-parser-output > p");
		Elements data2 = doc.select(".mw-parser-output > p+dl > dd");

		int count = 0;
//		 xử lí data1

		for (Element i : data1) {
			ArrayList<String> historicalFigures = new ArrayList<>();
			ArrayList<Integer> historicalFiguresId = new ArrayList<>();
			String date;
			String nameEvent;
			if (i.html().contains("</a>")) {
				date = "năm " + getDateFromHtml(i.html());
				nameEvent = getEventFromHtml(i.html());

				// lấy nhân vật lịch sử liên quan từ event

				historicalFigures = getHistoricalFiguresToWiki(listHistoricalFigures, historicalFigures, nameEvent, i);

				if (historicalFigures.size() == 0) {
					historicalFigures = getHistoricalFiguresToNKS(listHistoricalFigures, historicalFigures, nameEvent);
				}
				variable.Event event = new variable.Event();
				for (variable.HistoricalFigure getDataFromHTML : historicalFiguresFromFile) {
					for (String historicalFigureString : historicalFigures) {
						if (getDataFromHTML.getName().toUpperCase()
								.equals(historicalFigureString.toUpperCase())) {
							historicalFiguresId.add(getDataFromHTML.getId());
							getDataFromHTML.addEventId(event.getId());
						}
					}
				}
				event.setTime(date);
				event.setName(nameEvent);
				event.setHistoricalFiguresId(historicalFiguresId);
				listEvents.add(event);
				System.out.println(event);
			}
		}

		// xử lí data2
		for (Element i : data2) {
			ArrayList<String> historicalFigures = new ArrayList<>();
			ArrayList<Integer> historicalFiguresId = new ArrayList<>();
			String date;
			String nameEvent;

			Element parentOfData2 = i.parent().previousElementSibling();
			String yearData = parentOfData2.html();

			if (getDateFromHtml(i.html()) != null)
				date = getDateFromHtml(i.html()) + " năm " + getDateFromHtml(yearData);
			else
				date = "năm " + getDateFromHtml(yearData);
			nameEvent = getEventFromHtml(i.html());
			// lấy nhân vật liên quan

			// lấy nhân vật lịch sử liên quan từ evnet

			historicalFigures = getHistoricalFiguresToWiki(listHistoricalFigures, historicalFigures, nameEvent, i);

			if (historicalFigures.size() == 0) {
				historicalFigures = getHistoricalFiguresToNKS(listHistoricalFigures, historicalFigures, nameEvent);
			}
			variable.Event event = new variable.Event();
			for (variable.HistoricalFigure historicalFigureFromFile : historicalFiguresFromFile) {
				for (String historicalFigureString : historicalFigures) {
					if (historicalFigureFromFile.getName().toUpperCase().equals(historicalFigureString.toUpperCase())) {
						historicalFiguresId.add(historicalFigureFromFile.getId());
						historicalFigureFromFile.addEventId(event.getId());
					}
				}
			}
			event.setTime(date);
			event.setName(nameEvent);
			event.setHistoricalFiguresId(historicalFiguresId);
			listEvents.add(event);
			System.out.println(event);
		}
		resolveHistoricalFigure.writeDatatoFileJSON(historicalFiguresFromFile);
		return listEvents;
	}

	@Override
	public List<variable.Event> getDataFromFile() {
		try {
			Gson gson = new Gson();
			Reader reader = Files.newBufferedReader(Paths.get(Config.PATH_FILE + "events.json"));
			List<variable.Event> listEvents = new Gson().fromJson(reader, new TypeToken<List<variable.Event>>() {
			}.getType());
			reader.close();
			return listEvents;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	};

	@Override
	public void writeDatatoFileJSON(List<variable.Event> data) {
		try {
			FileWriter fw = new FileWriter(Config.PATH_FILE + "events.json");
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
			e.printStackTrace();
		}
	};

}
