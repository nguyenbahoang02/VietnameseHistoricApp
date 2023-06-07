package screen.info;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import screen.controller.InfoScreenController;
import variable.Data;

public class RelatedInfoLabel extends Label {
	/**
	 * <ul>
	 * <li>1 là triều đại</li>
	 * <li>2 là sự kiện</li>
	 * <li>3 là lễ hội</li>
	 * <li>4 là nhân vật</li>
	 * <li>5 là di tích</li>
	 * </ul>
	 */
	public RelatedInfoLabel(String name, double labelHeight, int dataType, Data data,int id) {
		setPrefHeight(labelHeight);
		setText(name);
		setPadding(new Insets(0, 0, 0, 4));
		setStyle("-fx-alignment: CENTER_LEFT");
		setCursor(Cursor.HAND);
		if(name.charAt(name.length()-1)==',') {
			name = name.substring(0, name.length()-1);
		}
		InfoScreenController infoScreenController = new InfoScreenController(data, dataType, name,id);
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				switch (dataType) {
				case 1: 
					Stage stage1 = new Stage();
					FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/screen/info/DynastyInfoScreen.fxml"));
					loader1.setController(infoScreenController);
					Parent root1;
					try {
						root1 = loader1.load();
						Scene scene = new Scene(root1);
						stage1.setTitle("Triều đại lịch sử");
						stage1.setResizable(false);
						stage1.setScene(scene);
						stage1.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 2: 
					Stage stage2 = new Stage();
					FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/screen/info/EventInfoScreen.fxml"));
					loader2.setController(infoScreenController);
					Parent root2;
					try {
						root2 = loader2.load();
						Scene scene = new Scene(root2);
						stage2.setTitle("Sự kiện lịch sử");
						stage2.setResizable(false);
						stage2.setScene(scene);
						stage2.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 3: 
					Stage stage3 = new Stage();
					FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/screen/info/FestivalInfoScreen.fxml"));
					loader3.setController(infoScreenController);
					Parent root3;
					try {
						root3 = loader3.load();
						Scene scene = new Scene(root3);
						stage3.setTitle("Lễ hội văn hóa");
						stage3.setResizable(false);
						stage3.setScene(scene);
						stage3.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 4: 
					Stage stage4 = new Stage();
					FXMLLoader loader4 = new FXMLLoader(getClass().getResource("/screen/info/HistoricalFigureInfoScreen.fxml"));
					loader4.setController(infoScreenController);
					Parent root4;
					try {
						root4 = loader4.load();
						Scene scene = new Scene(root4);
						stage4.setTitle("Nhân vật lịch sử");
						stage4.setResizable(false);
						stage4.setScene(scene);
						stage4.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 5: 
					Stage stage5 = new Stage();
					FXMLLoader loader5 = new FXMLLoader(getClass().getResource("/screen/info/HistoricalSiteInfoScreen.fxml"));
					loader5.setController(infoScreenController);
					Parent root5;
					try {
						root5 = loader5.load();
						Scene scene = new Scene(root5);
						stage5.setTitle("Di tích lịch sử");
						stage5.setResizable(false);
						stage5.setScene(scene);
						stage5.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		});
		
	}
	
	public RelatedInfoLabel(String name, double labelHeight) {
		setPrefHeight(labelHeight);
		setText(name);
		setPadding(new Insets(0, 0, 0, 4));
		setStyle("-fx-alignment: CENTER_LEFT");
		setMinHeight(USE_PREF_SIZE);
		setWrapText(true);
	}
}
