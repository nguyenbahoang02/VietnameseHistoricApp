package resolve;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RSHistoricalFigure implements IResolveData<variable.HistoricalFigure> {
	private static String[] getDateOfBirthAndDateOfDeath(String des) {
		String REGEX_DATE_FROM_DES_1 = "^[0-9 -?thángnăm–]{0,100}$";
		String REGEX_DATE_FROM_DES_2 = "^sinh năm+[ 0-9]{0,5}$";
		String REGEX_DATE_FROM_DES_3 = "^chữ Hán:+.+[,;]+[0-9,? -thángnăm]{0,100}$";
		String[] DateOfBirthAndDateOfDeath = new String[2];
		int indexStart = 9999;
		int indexEnd = -1;
		for (int i = 0; i < des.length(); i++) {
			if (des.charAt(i) == '(')
				indexStart = i + 1;
			else if (des.charAt(i) == ')') {
				indexEnd = i;
				break;
			}
		}
		if (indexEnd != -1) {
			String subDes = des.substring(indexStart, indexEnd);
			Pattern pattern1 = Pattern.compile(REGEX_DATE_FROM_DES_1);
			Pattern pattern2 = Pattern.compile(REGEX_DATE_FROM_DES_2);
			Pattern pattern3 = Pattern.compile(REGEX_DATE_FROM_DES_3);
			Matcher matcher1 = pattern1.matcher(subDes);
			Matcher matcher2 = pattern2.matcher(subDes);
			Matcher matcher3 = pattern3.matcher(subDes);
			if (indexEnd != -1) {
				if (matcher1.matches()) {
					if (subDes.contains(","))
						return subDes.split(",");
					else if (subDes.contains("–"))
						return subDes.split("–");
					else if (subDes.contains("-") && subDes.split("-").length >= 2)
						return subDes.split("-");
					else {
						String[] date = new String[2];
						date[0] = subDes;
						date[1] = null;
					}
				} else if (matcher2.matches()) {
					String[] date = new String[2];
					date[0] = subDes.replace("sinh năm ", "");
					date[1] = null;
					return date;
				} else if (matcher3.matches()) {
					String REGEX_DATE = "^[0-9 -?thángnăm,–TCNhoặcsinh]{0,200}$";
					Pattern patternDate = Pattern.compile(REGEX_DATE);
					Matcher matcherDate = patternDate.matcher(subDes);
					while (!matcherDate.matches()) {
						int index = 999;
						for (int i = 0; i < subDes.length(); i++) {
							if (subDes.charAt(i) == ',' || subDes.charAt(i) == ';') {
								index = i + 2;
								break;
							}
						}
						if (index != 999)
							subDes = subDes.replace(subDes.substring(0, index), "");
						matcherDate = patternDate.matcher(subDes);
					}
					if (subDes.contains("–"))
						return subDes.split("–");
					else if (subDes.contains("-"))
						return subDes.split("-");
					else {
						String[] date = new String[2];
						date[0] = subDes;
						date[1] = null;
					}
				}
			}
		}
		return null;
	}

	@Override
	public List<variable.HistoricalFigure> getDataFromHTML() {
		List<variable.HistoricalFigure> listHistoricalFigures = new ArrayList<>();
		String urlSource = "https://nguoikesu.com";
		String url = "https://nguoikesu.com/nhan-vat";
		while (!url.equals("")) {
			try {

				Document docHistoricalFigureHome = Jsoup.connect(url).get();
				Elements eListHistoricalFigures = docHistoricalFigureHome
						.select(".item-content > .page-header > h2 > a ");
				for (Element eHistoricalFigure : eListHistoricalFigures) {
					String name = null;
					String homeTown = null;
					String dateOfBirth = null;
					String dateOfDeath = null;
					String otherName = null;
					String dynasty = null;
					List<Integer> historicalSites = new ArrayList<>();
					List<Integer> events = new ArrayList<>();
					String role = null;
					name = eHistoricalFigure.text();
					Element eDesHistoricalFigure = eHistoricalFigure.parent().parent().parent();
					String[] date = new String[2];
					date = getDateOfBirthAndDateOfDeath(eDesHistoricalFigure.text());
					if (date != null) {
						if (date[0] != null) {
							if (!date[0].contains("?"))
								dateOfBirth = date[0].trim();
						}
						if (date[1] != null) {
							if (!date[1].contains("?"))
								dateOfDeath = date[1].trim();
						}
					}
					Document docHistoricalFigureInformation = Jsoup.connect(urlSource + eHistoricalFigure.attr("href"))
							.get();
					Elements eHistoricalFigureAllInformations = docHistoricalFigureInformation
							.select("table.infobox > tbody > tr");
					for (Element eHistoricalFigureInformation : eHistoricalFigureAllInformations) {
						String textHistoricalFigureInformation = eHistoricalFigureInformation.text();
						if (!textHistoricalFigureInformation.contains("?")
								&& !textHistoricalFigureInformation.contains("không rõ")) {
							if (textHistoricalFigureInformation.contains("Sinh")) {
								if (dateOfBirth == null)
									dateOfBirth = textHistoricalFigureInformation.replace("Sinh ", "");
							}
							if (textHistoricalFigureInformation.contains("Mất")) {
								if (dateOfDeath == null)
									dateOfDeath = textHistoricalFigureInformation.replace("Mất ", "");
							}
							if (textHistoricalFigureInformation.contains("Tên đầy đủ")
									|| textHistoricalFigureInformation.contains("Tên khác")) {
								otherName = textHistoricalFigureInformation.replace("Tên đầy đủ ", "")
										.replace("Tên khác ", "");
							}
							if (textHistoricalFigureInformation.contains("Quê quán")) {
								homeTown = textHistoricalFigureInformation.replace("Quê quán ", "");
							}
							if (textHistoricalFigureInformation.contains("Nghề nghiệp")) {
								role = textHistoricalFigureInformation.replace("Nghề nghiệp ", "");
							}
							if (textHistoricalFigureInformation.contains("Chức vụ ")) {
								if (role == null)
									textHistoricalFigureInformation.replace("Chức vụ ", "");
							}
							if (eHistoricalFigureInformation.previousElementSibling() != null
									&& eHistoricalFigureInformation.previousElementSibling().text().equals("Chức vụ")) {
								if (role == null)
									role = textHistoricalFigureInformation;
							}
							if (textHistoricalFigureInformation.contains("Triều đại")) {
								dynasty = textHistoricalFigureInformation.replace("Triều đại ", "");
							}
						}
					}
					variable.HistoricalFigure historicalFigure = new variable.HistoricalFigure(name, homeTown,
							dateOfBirth, dateOfDeath, otherName, dynasty, historicalSites, events, role);
					listHistoricalFigures.add(historicalFigure);
					System.out.println(historicalFigure);
				}
				Element eNextPage = docHistoricalFigureHome
						.selectFirst(".page-item > a[aria-label=\"Đi tới tiếp tục trang\"]");
				if (eNextPage != null)
					url = urlSource + eNextPage.attr("href");
				else
					url = "";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return listHistoricalFigures;
	}

	@Override
	public List<variable.HistoricalFigure> getDataFromFile() {
		try {
			Gson gson = new Gson();
			Reader reader = Files.newBufferedReader(Paths.get(Config.PATH_FILE + "historicalFigures.json"));
			List<variable.HistoricalFigure> listHistoricalFigures = new Gson().fromJson(reader,
					new TypeToken<List<variable.HistoricalFigure>>() {
					}.getType());
			for (variable.HistoricalFigure historicalFigure : listHistoricalFigures) {
				if(historicalFigure.getEventsId()==null) {
					historicalFigure.setEventsId(new ArrayList<Integer>());
				}
				if(historicalFigure.getHistoricalSitesId()==null) {
					historicalFigure.setHistoricalSitesId(new ArrayList<Integer>());
				}
			}
			reader.close();
			return listHistoricalFigures;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public void writeDatatoFileJSON(List<variable.HistoricalFigure> data) {
		try {
			FileWriter fw = new FileWriter(Config.PATH_FILE + "historicalFigures.json");
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
	}
}
