package resolve;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.VariableElement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import variable.Dynasty;

public class RSDynasty implements IResolveData<Dynasty> {

	// Lấy dữ liệu cho từng triều đại
	private static void getInfo(variable.Dynasty TrieuDai, Elements table) throws IOException {
		int i = 0;
		resolve.RSEvent resolveEvent = new resolve.RSEvent();
		List<variable.Event> listEventsFromFile = resolveEvent.getDataFromFile();
		resolve.RSHistoricalFigure resolveHistoricalFigure = new resolve.RSHistoricalFigure();
		List<variable.HistoricalFigure> listHistoricalFiguresFromFile = resolveHistoricalFigure.getDataFromFile();
		// Lặp qua table để lấy thông tin
		for (Element element : table) {
			// i=0 <=> Lấy tên nước
			if (i == 0) {
				TrieuDai.setCountryName(element.text());
				i++;
			}
			// i=1 <=> Lấy năm
			if (element.firstElementChild().childrenSize() == 0 && i == 1) {
				TrieuDai.setTime(element.text());
				i++;
			}

			// NVLS và SKLS đều có class="mergedrow"
			if (element.hasClass("mergedrow")) {
				if (element.text().isEmpty())
					i++;
				else {
					// i=3 <=> Lấy NVLS
					if (i == 3 && element.text() != "") {
						try {
							String error = element.select("th").text().split("•")[1]; // Check lỗi đặc biệt
							String NVLS = element.select("td").text();
							// Loại bỏ thông tin không cần thiết: (đầu tiên),(cuối cùng).In thử sẽ thấy (mũi
							// tên lên trên)
							try {
								int index = NVLS.indexOf(" (");
								for (variable.HistoricalFigure historicalFigure : listHistoricalFiguresFromFile) {
									if (historicalFigure.getName().toUpperCase()
											.equals(NVLS.substring(0, index).toUpperCase())) {
										TrieuDai.addHistoricalFiguresId(historicalFigure.getId());
									}
								}
							} catch (Exception e) {
								// TODO: handle exception
								for (variable.HistoricalFigure historicalFigure : listHistoricalFiguresFromFile) {
									if (historicalFigure.getName().toUpperCase().equals(NVLS.toUpperCase())) {
										TrieuDai.addHistoricalFiguresId(historicalFigure.getId());
									}
								}
							}
						} catch (Exception e) {
							// Xử lí khi gặp lỗi đặc biệt
							i--;
							continue;
						}
					}

					// i=3 <=> Lấy SKLS
					if (i == 4 && element.text() != "") {
						for (variable.Event event : listEventsFromFile) {
							if (event.getTime().contains(element.select("td").text()))
								TrieuDai.addEventsId(event.getId());

						}

					}
				}

			}
			// Lấy đủ thông tin thì thoát vòng lặp
			if (i == 4 && element.firstElementChild().tagName() == "th"
					&& element.firstElementChild().childrenSize() == 0) {
				break;
			}

		}

	}

	@Override
	public List<variable.Dynasty> getDataFromHTML() {
		Document doc_List, doc_Info;
		Elements List = null;
		List<variable.Dynasty> listDynasty = new ArrayList<variable.Dynasty>(); // Danh sách obj triều đại
		resolve.RSHistoricalFigure resolveHistoricalFigure = new resolve.RSHistoricalFigure();
		List<variable.HistoricalFigure> listHistoricalFiguresFromFile = resolveHistoricalFigure.getDataFromFile();
		// Lấy danh sách tên triều đại
		String link_List = "https://vi.wikipedia.org/wiki/Tri%E1%BB%81u_%C4%91%E1%BA%A1i";
		try {
			doc_List = Jsoup.connect(link_List).get();
			List = doc_List.selectFirst((".mw-parser-output ul")).select("li");
		} catch (IOException e1) {
			e1.printStackTrace();
		}


		String link_Info = "https://vi.wikipedia.org/wiki/";
		// Dùng for lặp qua danh sách tên triều đại và thử tìm trên wiki
		for (Element element : List) {

			try {
				// Nối tên triều đại vào wiki để tìm
				doc_Info = Jsoup.connect(link_Info + element.text()).get();
				// Select bảng thông tin chứa thông tin cần lấy
				Elements table = doc_Info.select(".infobox tbody tr");
				// Seclect nội dung chứa đoạn mô tả
				Elements content = doc_Info.select(".mw-parser-output>p");
				Element des;
				// Kiểm tra nếu có chữ thì mới lấy
				if (content.get(0).hasText())
					des = content.get(0);
				else
					des = content.get(1); // In thử phần mô tả

				variable.Dynasty trieuDai = new variable.Dynasty(); // Tạo biến triều đại
				trieuDai.setName(element.text()); // Set tên triều đại
				trieuDai.setDescription(des.text()); // Set mô tả
//				System.out.println(trieuDai.getDescription());
				getInfo(trieuDai, table); // Lấy các thông tin còn lại
				listDynasty.add(trieuDai); // Thêm vào danh sách

			} catch (Exception e) {
				// Trường hợp không có trên wiki
				// Lấy thông tin ở đường link bên dưới
				Elements info = null;
				try {
					doc_Info = Jsoup.connect("http://www.informatik.uni-leipzig.de/~duc/sach/dvsktt/dvsktt01.html")
							.get();
					info = doc_Info.select("p"); // Nội dung page
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				// Khởi tạo biến và lấy thông tin theo page

				// Do page cố định nên cần xác định triều đại rồi lấy "thủ công"

				variable.Dynasty trieuDai = new variable.Dynasty();
				trieuDai.setName(element.text());

				// Thời vua Hùng(index=0)
				if (element.elementSiblingIndex() == 0) {
					String des = info.get(25).text(); // Mô tả ở thẻ <p>...</p> thứ 25
					trieuDai.setDescription(des);
					trieuDai.setTime(des.substring(des.lastIndexOf("[") + 1, des.lastIndexOf("]"))); // Tách thời gian
																										// từ phần mô tả
					Elements NVLS = info.select(".f6"); // NVLS là 3 thẻ <span class="f6">...</span>
					for (int i = 0; i < 3; i++) {
						for (variable.HistoricalFigure historicalFigure : listHistoricalFiguresFromFile) {
							if (historicalFigure.getName().toUpperCase()
									.equals(NVLS.get(i).text().trim().toUpperCase())) {
								trieuDai.addHistoricalFiguresId(historicalFigure.getId());
							}
						}
					}

				}
				// Thời vua Hùng(index=1) Lấy tương tự như trên
				if (element.elementSiblingIndex() == 1) {
					String des = info.last().text();
					trieuDai.setDescription(des);
					trieuDai.setTime(des.substring(des.lastIndexOf("[") + 1, des.lastIndexOf("]")));
					Element NVLS = info.select(".f6").last();
					for (variable.HistoricalFigure historicalFigure : listHistoricalFiguresFromFile) {
						if (historicalFigure.getName().toUpperCase().equals(NVLS.text().trim().toUpperCase())) {
							trieuDai.addHistoricalFiguresId(historicalFigure.getId());
						}
					}

				}

				listDynasty.add(trieuDai); // Thêm vào danh sách sau khi lấy thông tin
			}

		}

		// Lặp qua danh sách obj triều đại để set kế tục và triều đại
		// với i>1 trieuDai[i] là kế tục của trieuDai[i-1] và trieuDai[i-1] là tiền
		// nhiệm của trieuDai[i]
		for (int i = 1; i < listDynasty.size(); i++) {
			try {
				listDynasty.get(i - 1).setKeTuc(listDynasty.get(i).getName());
				listDynasty.get(i).setTienNhiem(listDynasty.get(i - 1).getName());
			} catch (Exception e) {
				continue;
			}
		}

		return listDynasty;
	}

	@Override
	public List<variable.Dynasty> getDataFromFile() {
		try {
			Gson gson = new Gson();
			Reader reader = Files.newBufferedReader(Paths.get(Config.PATH_FILE + "dynasties.json"));
			List<variable.Dynasty> listDynasties = new Gson().fromJson(reader, new TypeToken<List<variable.Dynasty>>() {
			}.getType());
			reader.close();
			return listDynasties;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public void writeDatatoFileJSON(List<variable.Dynasty> data) {
		try {
			FileWriter fw = new FileWriter(Config.PATH_FILE + "dynasties.json");
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