package resolve;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RSFestival implements IResolveData<variable.Festival> {
//	Tìm nhân vật lịch sử trong file nhân vật
	private static void timNhanVat(String s, variable.Festival fes) throws IOException {
		List<variable.HistoricalFigure> listHistoricalFiguresFromFile = new resolve.RSHistoricalFigure().getDataFromFile();
		for (variable.HistoricalFigure historicalFigureFromFile : listHistoricalFiguresFromFile) {
			if (s.toUpperCase().contains(historicalFigureFromFile.getName().toUpperCase()) == true)
				fes.addHistoricalFiguresId(historicalFigureFromFile.getId());
		}
	}

	private static void getInfo(variable.Festival fes, int s, Document doc_Info) throws IOException {
//		doc_Info là trang thông tin lễ hội rồi select từ nó nhá. VD: Elements table = doc_Info.select(".infobox ")
//		Chỉ lấy di tích,nhân vật,sự kiện theo trường hợp (nếu có)
//		s(situation) = 1 là TH1 chỉ có xem thêm
		List<variable.HistoricalFigure> listHistoricalFiguresFromFile = new resolve.RSHistoricalFigure().getDataFromFile();
		if (s == 1) {
			Elements thong_tin = doc_Info.select("#mw-content-text > div.mw-parser-output > h2");
			Elements xem_them = null;
			for (int i = 0; i < thong_tin.size(); i++) {
//					Chọn thẻ ul có thông tin
				if (thong_tin.get(i).text().contains("Xem thêm")
						&& thong_tin.get(i).nextElementSibling().text().contains("Chú thích") == false)
					xem_them = thong_tin.get(i).nextElementSibling().select("li > a");
			}

			if (xem_them != null)
				for (int i = 0; i < xem_them.size(); i++) {
//				Sự liện lịch sử liên quan
					if (xem_them.get(i).text().contains("Trận") == true && xem_them.get(i).childNodeSize() == 1) {
//					System.out.println(xem_them.get(i).text());
						fes.addEvents(xem_them.get(i).text());

//					Di tích lịch sử
					} else if (xem_them.get(i).text().contains("Đền") == true
							|| xem_them.get(i).text().contains("Phủ") == true
							|| xem_them.get(i).text().contains("Miếu") == true
							|| xem_them.get(i).text().contains("Ấp") == true
							|| xem_them.get(i).text().contains("Di tích") == true) {
						fes.addHistoricalSites(xem_them.get(i).text());

					} else {

//				Nhân vật lịch sử
						String text = xem_them.get(i).text();
						boolean check = true;
						for (int j = 0; j < text.length(); j++) {
							if (text.charAt(j) == ' ' && (text.charAt(j + 1) < 'A' || text.charAt(j + 1) > 'Z'))
								check = false;
						}

						if (check == true)
							timNhanVat(text, fes);

					}

				}
		} else
//		s(situation) = 2 là TH2 chỉ có infobox
		if (s == 2) {
			Elements table = doc_Info.select("#mw-content-text > .mw-parser-output > .infobox >tbody >tr");
			Elements thong_tin = null;

//			Địa danh
			for (int i = 0; i < table.size(); i++) {
				if (table.get(i).text().contains("Đền") || table.get(i).text().contains("Chùa")) {
					fes.addHistoricalSites(table.get(i).text().split(",")[0]);
					break;
				}
			}

			for (int i = 0; i < table.size(); i++) {

//				NVLS
				if (!table.get(i).select("td > b").isEmpty()) {
					for (variable.HistoricalFigure historicalFigureFromFile : listHistoricalFiguresFromFile) {
						if (historicalFigureFromFile.getName().toUpperCase()
								.equals(table.get(i).select("td>b").text().toUpperCase().trim()))
							fes.addHistoricalFiguresId(historicalFigureFromFile.getId());
					}
				}

				// SKLS
				if (table.get(i).firstElementChild().text().contains("Công tích") == true) {
					int t = table.get(i).lastElementChild().text().indexOf("chống");
					fes.addEvents(table.get(i).lastElementChild().text().substring(t));
//					System.out.println(table.get(i).lastElementChild().text().substring(t));
				}
			}

		} else
//		s(situation) = 3 là TH3 có cả 2
		if (s == 3) {
//            NVLS
			Elements paragram = doc_Info.select("#mw-content-text > div.mw-parser-output > p");

			for (Element element : paragram) {
				Elements a = element.select("a");

				for (Element element2 : a) {
					char firstLetter = element2.text().charAt(0);
					if (firstLetter >= 'A' && firstLetter <= 'Z')
						timNhanVat(element2.text(), fes);
				}

				if (element.nextElementSibling().tagName() != "p")
					break;

			}

			// Di tích liên quan
			if (doc_Info.select(" th > a > span").text().indexOf("Di tích đặc biệt") >= 0) { // xử lý trường hợp trong
																								// bảng

				fes.addHistoricalSites(doc_Info.select(
						"#mw-content-text > div.mw-parser-output > table:nth-child(1) > tbody > tr:nth-child(1) > th")
						.text());
			}

			Elements Xemthem = doc_Info.select("#mw-content-text > div.mw-parser-output > ul:nth-child(36)"); // xử lý
																												// trường
																												// hợp
																												// trong
																												// xem
																												// thêm

			try {
				for (Element e : Xemthem) {
					fes.addHistoricalSites(e.child(1).text());
				}
			} catch (Exception e) {
			}

			Elements Xemthem2 = doc_Info.select("#mw-content-text > div.mw-parser-output > ul:nth-child(95) > li > a");
			try {
				for (Element e : Xemthem2) {
					fes.addHistoricalSites(e.text());
				}
			} catch (Exception e) {
			}

			Elements Xemthem3 = doc_Info.select("#mw-content-text > div.mw-parser-output > ul:nth-child(88)");
			try {
				for (Element e : Xemthem3) {
					for (variable.HistoricalFigure historicalFigureFromFile : listHistoricalFiguresFromFile) {
						if (historicalFigureFromFile.getName().toUpperCase()
								.equals(e.child(3).child(0).text().toUpperCase().trim()))
							fes.addHistoricalFiguresId(historicalFigureFromFile.getId()); // Nguyễn Trãi
					}
					fes.addHistoricalSites(e.child(0).child(0).text() + "\n" + e.child(1).child(0).text()); // Đền Kiếp
																											// Bạc
				}
			} catch (Exception e) {
			}

			Elements Lienketngoai = doc_Info
					.select("#mw-content-text > div.mw-parser-output > ul:nth-child(54) > li:nth-child(1) > a");
			try {
				for (Element e : Lienketngoai) {
					fes.addHistoricalSites(e.text());
				}
			} catch (Exception e) {
			}
		} else
			return;
	}

	@Override
	public List<variable.Festival> getDataFromHTML() {
		Document doc_List, doc_Info;
		List<variable.Festival> listFestival = new ArrayList<variable.Festival>();

		// Lấy danh sách tên lễ hội
		String link_List = "https://vi.wikipedia.org/wiki/C%C3%A1c_ng%C3%A0y_l%E1%BB%85_%E1%BB%9F_Vi%E1%BB%87t_Nam?fbclid=IwAR24JMV9wI9C6B6iKs3QDtCfnL5uv-w4gs-kKXAOospS95YWRM3n780zV1I";
		try {
			doc_List = Jsoup.connect(link_List).get();
			Elements List = doc_List.select(".wikitable").get(1).select("tr");
			for (int i = 1; i < List.size(); i++) {
				variable.Festival Lehoi = new variable.Festival();
				String lehoi_Link = "https://vi.wikipedia.org";
				for (int j = 0; j < 3; j++) {
					if (j == 0)
						Lehoi.setTime(List.get(i).child(j).text().trim());
					else if (j == 1) {
						Lehoi.setName(List.get(i).child(j).firstElementChild().text().trim());
						lehoi_Link += List.get(i).child(j).firstElementChild().attr("href");
					} else
						Lehoi.setLocation(List.get(i).child(j).text().trim());
				}
				int s = 0; // situation = 0
				doc_Info = Jsoup.connect(lehoi_Link).get();
				if (doc_Info.text().indexOf("Xem thêm") >= 0)
					s += 1;
				if (!doc_Info.select(".infobox ").isEmpty())
					s += 2;
				getInfo(Lehoi, s, doc_Info);

//				Thêm lễ hội
				listFestival.add(Lehoi);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listFestival;
	}

	@Override
	public List<variable.Festival> getDataFromFile() {
		try {
			Gson gson = new Gson();
			Reader reader = Files.newBufferedReader(Paths.get(Config.PATH_FILE + "festivals.json"));
			List<variable.Festival> listFestivals = new Gson().fromJson(reader,
					new TypeToken<List<variable.Festival>>() {
					}.getType());
			reader.close();
			return listFestivals;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public void writeDatatoFileJSON(List<variable.Festival> data) {
		try {
			FileWriter fw = new FileWriter(Config.PATH_FILE + "festivals.json");
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
