package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import screen.controller.MainScreenController;
import variable.Data;

public class Main extends Application {
	
	private Data data = new Data();
	
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/info/MainScreen.fxml"));
		data.getData();
		data.mergeData();
		MainScreenController controller = new MainScreenController(stage,data);
		loader.setController(controller);
		Parent root = loader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/screen/info/MainScreen.css").toExternalForm());
		stage.setTitle("Màn hình chính");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
//		GetData.run();
		launch(args);
	}

}