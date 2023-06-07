package screen.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import screen.info.RelatedInfoLabel;
import variable.Data;
import variable.Dynasty;
import variable.Event;
import variable.Festival;
import variable.HistoricalFigure;
import variable.HistoricalSite;

public class MainScreenController implements Initializable{
	
	private Stage stage;
	private Data data = new Data();
	
	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	
	public Stage getStage() {
		return stage;
	}
	
    @FXML
    private Label searchResult;
    
	@FXML
	private Label DTLS_Name;

	@FXML
	private Label LHVH_Name;

	@FXML
	private Label NVLS_Name;

	@FXML
	private Label SKLS_Name;

	@FXML
	private Label TDLS_Name;

	@FXML
	private HBox hBox_DTLS_HistoricalFigures;

	@FXML
	private HBox hBox_DTLS_Location;

	@FXML
	private HBox hBox_LHVH_Events;

	@FXML
	private HBox hBox_LHVH_HistoricalFigures;

	@FXML
	private HBox hBox_LHVH_Location;

	@FXML
	private HBox hBox_LHVH_Time;

	@FXML
	private HBox hBox_LHVH_HistoricalSites;

	@FXML
	private HBox hBox_NVLS_Date;

	@FXML
	private HBox hBox_NVLS_Description;
	
	@FXML
	private HBox hBox_NVLS_Dynasty;

	@FXML
	private HBox hBox_NVLS_Events;

	@FXML
	private HBox hBox_NVLS_HistoricalSites;

	@FXML
	private HBox hBox_NVLS_HomeTown;

	@FXML
	private HBox hBox_SKLS_Date;

	@FXML
	private HBox hBox_SKLS_HistoricalFigures;

	@FXML
	private HBox hBox_TDLS_CountryName;
	
	@FXML
	private HBox hBox_TDLS_Description;

	@FXML
	private HBox hBox_TDLS_Events;

	@FXML
	private HBox hBox_TDLS_HistoricalFigures;

	@FXML
	private HBox hBox_TDLS_KeTuc;

	@FXML
	private HBox hBox_TDLS_TienNhiem;

	@FXML
	private HBox hBox_TDLS_Time;

	@FXML
	private VBox vBox_DTLS_Info;

	@FXML
	private VBox vBox_LHVH_Info;

	@FXML
	private VBox vBox_NVLS_Info;

	@FXML
	private VBox vBox_SKLS_Info;

	@FXML
	private VBox vBox_TDLS_Info;
	
    @FXML
    private ScrollPane scrollPane_DTLS;

    @FXML
    private ScrollPane scrollPane_LHVH;

    @FXML
    private ScrollPane scrollPane_NVLS;

    @FXML
    private ScrollPane scrollPane_SKLS;

    @FXML
    private ScrollPane scrollPane_TDLS;

	@FXML
    private TableView<Dynasty> tableTDLS;
	
    @FXML
    private TableColumn<Dynasty, Integer> indexColTDLS;

    @FXML
    private TableColumn<Dynasty, String> nameColTDLS;
 
    @FXML 
    private TableView<Festival> tableLHVH;
    
    @FXML 
    private TableColumn<Festival, Integer> indexColLHVH;
    
    @FXML 
    private TableColumn<Festival, String> nameColLHVH;
    
    @FXML 
    private TableView<HistoricalFigure> tableNVLS;
    
    @FXML 
    private TableColumn<HistoricalFigure, Integer> indexColNVLS;
    
    @FXML 
    private TableColumn<HistoricalFigure, String> nameColNVLS;
    
    @FXML 
    private TableView<HistoricalSite> tableDTLS;
    
    @FXML 
    private TableColumn<HistoricalSite, Integer> indexColDTLS;
    
    @FXML 
    private TableColumn<HistoricalSite, String> nameColDTLS;
    
    @FXML 
    private TableView<Event> tableSKLS;
    
    @FXML 
    private TableColumn<Event, Integer> indexColSKLS;
    
    @FXML 
    private TableColumn<Event, String> nameColSKLS;
     
    @FXML
    private TextField searchField;
    
    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabPane_DTLS;

    @FXML
    private Tab tabPane_TDLS;
   
    @FXML
    private Text TDLS_Description;
    
    @FXML
    private Text NVLS_Description;
    
	public MainScreenController(Stage stage,Data data) {
		this.stage = stage;
		this.data = data;
	}

	@FXML
    void search(ActionEvent event) throws IOException {
//		tabPane.getSelectionModel().select(tabPane_DTLS);
//		tableDTLS.getSelectionModel().select(0);
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//set Data
		//khởi tạo, set bảng bên phải chưa xuất hiện
		//stage.setFullScreen(true);
		searchResult.setVisible(false);
		scrollPane_TDLS.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane_TDLS.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane_NVLS.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane_NVLS.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane_DTLS.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane_DTLS.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane_LHVH.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane_LHVH.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane_SKLS.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane_SKLS.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane_TDLS.setVisible(false);
		scrollPane_NVLS.setVisible(false);
		scrollPane_DTLS.setVisible(false);
		scrollPane_LHVH.setVisible(false);
		scrollPane_SKLS.setVisible(false);
		//set dữ liệu cho cột rồi thêm vào bảng 
		indexColTDLS.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(tableTDLS.getItems().indexOf(cellData.getValue()) + 1));
		nameColTDLS.setCellValueFactory(new PropertyValueFactory<Dynasty, String>("name"));
		nameColTDLS.setStyle("-fx-font-size: 14pt;");
		tableTDLS.setItems(data.getListDynasties());
		//set tính năng khi chọn 1 đối tượng thì bảng bên phải sẽ thay đổi
		tableTDLS.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Dynasty>() {
			@Override
			public void changed(ObservableValue<? extends Dynasty> arg0, Dynasty arg1, Dynasty clickedObject) {
				if(clickedObject!=null) {
					scrollPane_TDLS.setVisible(true);
					TDLS_Name.setText(clickedObject.getName());
					resetVbox(vBox_TDLS_Info);
					resetHbox(hBox_TDLS_CountryName);
					resetHbox(hBox_TDLS_Events);
					resetHbox(hBox_TDLS_HistoricalFigures);
					resetHbox(hBox_TDLS_KeTuc);
					resetHbox(hBox_TDLS_TienNhiem);
					resetHbox(hBox_TDLS_Time);
					if(clickedObject.getTime()!=null) {
						addLabelToHbox(hBox_TDLS_Time, clickedObject.getTime());
					}else addLabelToHbox(hBox_TDLS_Time, "Không rõ");
					if(clickedObject.getCountryName()!=null) {
						addLabelToHbox(hBox_TDLS_CountryName, clickedObject.getCountryName());
					}else addLabelToHbox(hBox_TDLS_CountryName, "Không rõ");
					if(clickedObject.getDescription()!=null) {
						TDLS_Description.setText(clickedObject.getDescription());
					}else TDLS_Description.setText("Không rõ");
					if(clickedObject.getKeTuc()!=null) {
						addLabelToHbox(hBox_TDLS_KeTuc, clickedObject.getKeTuc());
					}else addLabelToHbox(hBox_TDLS_KeTuc, "Không rõ");
					if(clickedObject.getTienNhiem()!=null) {
						addLabelToHbox(hBox_TDLS_TienNhiem, clickedObject.getTienNhiem());
					}else addLabelToHbox(hBox_TDLS_TienNhiem, "Không rõ");
					int hfLength=0;
					int hBoxCount=0;
					int additionalHbox=0;
					HBox hBox[] = new HBox[10];
					boolean[] addedHbox = new boolean[10];
					for(int i = 0; i<10; i++) {
						addedHbox[i]=false;
					}
					if(clickedObject.getHistoricalFiguresId().size()==0) {
						addLabelToHbox(hBox_TDLS_HistoricalFigures, "Không rõ");
					}
					else 
					for(int i = 0;i<clickedObject.getHistoricalFiguresId().size();i++) {
						hfLength+=data.getListEntities().get(clickedObject.getHistoricalFiguresId().get(i)).getName().length();
						if((hfLength>45&&hBoxCount==0)||(hfLength>60&&hBoxCount>0)) {
							hfLength=0;
							hBox[hBoxCount+1] = new HBox();
							hBox[hBoxCount+1].setPrefHeight(hBox_TDLS_HistoricalFigures.getPrefHeight());
							if(addedHbox[hBoxCount+1]==false) {
								vBox_TDLS_Info.getChildren().add(5+hBoxCount, hBox[hBoxCount+1]);
								addedHbox[hBoxCount]=true;
								hBoxCount++;
							}
						}
						if(hBoxCount==0) {
							if(i==clickedObject.getHistoricalFiguresId().size()-1) {
								addLabelToHbox(hBox_TDLS_HistoricalFigures, data.getListEntities().get(clickedObject.getHistoricalFiguresId().get(i)).getName(),4, clickedObject.getHistoricalFiguresId().get(i));
							}
							else addLabelToHbox(hBox_TDLS_HistoricalFigures, data.getListEntities().get(clickedObject.getHistoricalFiguresId().get(i)).getName() + ",", 4, clickedObject.getHistoricalFiguresId().get(i));		
						}else {
							if(i==clickedObject.getHistoricalFiguresId().size()-1) {
								addLabelToHbox(hBox[hBoxCount], data.getListEntities().get(clickedObject.getHistoricalFiguresId().get(i)).getName(),4, clickedObject.getHistoricalFiguresId().get(i));
							}
							else addLabelToHbox(hBox[hBoxCount], data.getListEntities().get(clickedObject.getHistoricalFiguresId().get(i)).getName() + ",", 4, clickedObject.getHistoricalFiguresId().get(i));		
						}
					}
					additionalHbox=hBoxCount;
					hfLength=0;
					hBoxCount=0;
					hBox = new HBox[10];
					addedHbox = new boolean[10];
					for(int i = 0; i<10; i++) {
						addedHbox[i]=false;
					}
					if(clickedObject.getEventsId().size()==0) {
						addLabelToHbox(hBox_TDLS_Events, "Không rõ");
					}
					else 
					for(int i = 0;i<clickedObject.getEventsId().size();i++) {
						hfLength+=data.getListEntities().get(clickedObject.getEventsId().get(i)).getName().length();
						if((hfLength>60&&hBoxCount==0)||(hfLength>80&&hBoxCount>0)) {
							hfLength=data.getListEntities().get(clickedObject.getEventsId().get(i)).getName().length();
							hBox[hBoxCount+1] = new HBox();
							hBox[hBoxCount+1].setPrefHeight(hBox_TDLS_Events.getPrefHeight());
							if(addedHbox[hBoxCount+1]==false) {
								vBox_TDLS_Info.getChildren().add(6+hBoxCount+additionalHbox, hBox[hBoxCount+1]);
								addedHbox[hBoxCount]=true;
								hBoxCount++;
							}
						}
						if(hBoxCount==0) {
							if(i==clickedObject.getEventsId().size()-1) {
								addLabelToHbox(hBox_TDLS_Events, data.getListEntities().get(clickedObject.getEventsId().get(i)).getName(), 2, clickedObject.getEventsId().get(i));
							}
							else addLabelToHbox(hBox_TDLS_Events, data.getListEntities().get(clickedObject.getEventsId().get(i)).getName() + ",", 2, clickedObject.getEventsId().get(i));		
						}else {
							if(i==clickedObject.getEventsId().size()-1) {
								addLabelToHbox(hBox[hBoxCount], data.getListEntities().get(clickedObject.getEventsId().get(i)).getName(), 2, clickedObject.getEventsId().get(i));
							}
							else addLabelToHbox(hBox[hBoxCount], data.getListEntities().get(clickedObject.getEventsId().get(i)).getName() + ",", 2, clickedObject.getEventsId().get(i));		
						}
					}
				}
			}
		});
		//set dữ liệu cho cột rồi thêm vào bảng 
		indexColLHVH.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(tableLHVH.getItems().indexOf(cellData.getValue()) + 1));
		nameColLHVH.setCellValueFactory(new PropertyValueFactory<Festival, String>("name"));
		nameColLHVH.setStyle("-fx-font-size: 16pt;");
		tableLHVH.setItems(data.getListFestivals());
		//set tính năng khi chọn 1 đối tượng thì bảng bên phải sẽ thay đổi
		tableLHVH.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Festival>() {
			@Override
			public void changed(ObservableValue<? extends Festival> arg0, Festival arg1, Festival clickedObject) {
				if(clickedObject!=null) {
					scrollPane_LHVH.setVisible(true);
					LHVH_Name.setText(clickedObject.getName());
					resetVbox(vBox_LHVH_Info);
					resetHbox(hBox_LHVH_Events);
					resetHbox(hBox_LHVH_HistoricalFigures);
					resetHbox(hBox_LHVH_HistoricalSites);
					resetHbox(hBox_LHVH_Location);
					resetHbox(hBox_LHVH_Time);
					if(clickedObject.getTime().length()!=0) {
						addLabelToHbox(hBox_LHVH_Time, clickedObject.getTime());
					}else addLabelToHbox(hBox_LHVH_Time, "Không rõ");
					if(clickedObject.getLocation().length()!=0) {
						addLabelToHbox(hBox_LHVH_Location, clickedObject.getLocation());
					}else addLabelToHbox(hBox_LHVH_Location, "Không rõ");
					int hfLength=0;
					int hBoxCount=0;
					HBox hBox[] = new HBox[10];
					boolean[] addedHbox = new boolean[10];
					for(int i = 0; i<10; i++) {
						addedHbox[i]=false;
					}
					if(clickedObject.getHistoricalFiguresId().size()==0) {
						addLabelToHbox(hBox_LHVH_HistoricalFigures, "Không rõ");
					}
					else 
					for(int i = 0;i<clickedObject.getHistoricalFiguresId().size();i++) {
						hfLength+=data.getListEntities().get(clickedObject.getHistoricalFiguresId().get(i)).getName().length();
						if((hfLength>45&&hBoxCount==0)||(hfLength>60&&hBoxCount>0)) {
							hfLength=0;
							hBox[hBoxCount+1] = new HBox();
							hBox[hBoxCount+1].setPrefHeight(hBox_LHVH_HistoricalFigures.getPrefHeight());
							if(addedHbox[hBoxCount+1]==false) {
								vBox_LHVH_Info.getChildren().add(4, hBox[hBoxCount+1]);
								addedHbox[hBoxCount]=true;
								hBoxCount++;
							}
						}
						if(hBoxCount==0) {
							if(i==clickedObject.getHistoricalFiguresId().size()-1) {
								addLabelToHbox(hBox_LHVH_HistoricalFigures, data.getListEntities().get(clickedObject.getHistoricalFiguresId().get(i)).getName(),4, clickedObject.getHistoricalFiguresId().get(i));
							}
							else addLabelToHbox(hBox_LHVH_HistoricalFigures, data.getListEntities().get(clickedObject.getHistoricalFiguresId().get(i)).getName() + ",", 4, clickedObject.getHistoricalFiguresId().get(i));		
						}else {
							if(i==clickedObject.getHistoricalFiguresId().size()-1) {
								addLabelToHbox(hBox[hBoxCount], data.getListEntities().get(clickedObject.getHistoricalFiguresId().get(i)).getName(),4, clickedObject.getHistoricalFiguresId().get(i));
							}
							else addLabelToHbox(hBox[hBoxCount], data.getListEntities().get(clickedObject.getHistoricalFiguresId().get(i)).getName() + ",", 4, clickedObject.getHistoricalFiguresId().get(i));		
						}
					}
					hfLength=0;
					hBoxCount=0;
					hBox = new HBox[10];
					addedHbox = new boolean[10];
					for(int i = 0; i<10; i++) {
						addedHbox[i]=false;
					}
					if(clickedObject.getHistoricalSites().size()==0) {
						addLabelToHbox(hBox_LHVH_HistoricalSites, "Không rõ");
					}
					else 
					for(int i = 0;i<clickedObject.getHistoricalSites().size();i++) {
						hfLength+=clickedObject.getHistoricalSites().get(i).length();
						if((hfLength>70&&hBoxCount==0)||(hfLength>60&&hBoxCount>0)) {
							hfLength=0;
							hBox[hBoxCount+1] = new HBox();
							hBox[hBoxCount+1].setPrefHeight(hBox_LHVH_HistoricalSites.getPrefHeight());
							if(addedHbox[hBoxCount+1]==false) {
								vBox_LHVH_Info.getChildren().add(3, hBox[hBoxCount+1]);
								addedHbox[hBoxCount]=true;
								hBoxCount++;
							}
						}
						if(hBoxCount==0) {
							if(i==clickedObject.getHistoricalSites().size()-1) {
								addLabelToHbox(hBox_LHVH_HistoricalSites, clickedObject.getHistoricalSites().get(i));
							}
							else addLabelToHbox(hBox_LHVH_HistoricalSites, clickedObject.getHistoricalSites().get(i) + ",");		
						}else {
							if(i==clickedObject.getHistoricalSites().size()-1) {
								addLabelToHbox(hBox[hBoxCount], clickedObject.getHistoricalSites().get(i));
							}
							else addLabelToHbox(hBox[hBoxCount], clickedObject.getHistoricalSites().get(i) + ",");		
						}
					}
					hfLength=0;
					hBoxCount=0;
					hBox = new HBox[10];
					addedHbox = new boolean[10];
					for(int i = 0; i<10; i++) {
						addedHbox[i]=false;
					}
					if(clickedObject.getEvents().size()==0) {
						addLabelToHbox(hBox_LHVH_Events, "Không rõ");
					}
					else 
					for(int i = 0;i<clickedObject.getEvents().size();i++) {
						hfLength+=clickedObject.getEvents().get(i).length();
						if((hfLength>70&&hBoxCount==0)||(hfLength>60&&hBoxCount>0)) {
							hfLength=0;
							hBox[hBoxCount+1] = new HBox();
							hBox[hBoxCount+1].setPrefHeight(hBox_LHVH_Events.getPrefHeight());
							if(addedHbox[hBoxCount+1]==false) {
								vBox_LHVH_Info.getChildren().add(5, hBox[hBoxCount+1]);
								addedHbox[hBoxCount]=true;
								hBoxCount++;
							}
						}
						if(hBoxCount==0) {
							if(i==clickedObject.getEvents().size()-1) {
								addLabelToHbox(hBox_LHVH_Events, clickedObject.getEvents().get(i));
							}
							else addLabelToHbox(hBox_LHVH_Events, clickedObject.getEvents().get(i) + ",");		
						}else {
							if(i==clickedObject.getEvents().size()-1) {
								addLabelToHbox(hBox[hBoxCount], clickedObject.getEvents().get(i));
							}
							else addLabelToHbox(hBox[hBoxCount], clickedObject.getEvents().get(i) + ",");		
						}
					}
					
				}
			}
			
		});
		
		//set dữ liệu cho cột rồi thêm vào bảng 
		indexColSKLS.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(tableSKLS.getItems().indexOf(cellData.getValue()) + 1));
		nameColSKLS.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
		nameColSKLS.setStyle("-fx-font-size: 12pt;");
		tableSKLS.setItems(data.getListEvents());
		//set tính năng khi chọn 1 đối tượng thì bảng bên phải sẽ thay đổi
		tableSKLS.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {
			@Override
			public void changed(ObservableValue<? extends Event> arg0, Event arg1, Event clickedObject) {
				if(clickedObject!=null) {
					scrollPane_SKLS.setVisible(true);
					SKLS_Name.setText(clickedObject.getName());
					if(SKLS_Name.getText().toString().length()>10) {
						if(SKLS_Name.getText().toString().length()>20) {
							if(SKLS_Name.getText().toString().length()>30) {
								if(SKLS_Name.getText().toString().length()>40) {
									if(SKLS_Name.getText().toString().length()>50) {
										SKLS_Name.setStyle("-fx-font-size: 16pt; -fx-alignment: CENTER;");
									}else SKLS_Name.setStyle("-fx-font-size: 18pt; -fx-alignment: CENTER;");
								}else SKLS_Name.setStyle("-fx-font-size: 22pt; -fx-alignment: CENTER;");
							}else SKLS_Name.setStyle("-fx-font-size: 24pt; -fx-alignment: CENTER;");
						}else SKLS_Name.setStyle("-fx-font-size: 26pt; -fx-alignment: CENTER;");
					}else SKLS_Name.setStyle("-fx-font-size: 30pt; -fx-alignment: CENTER;");
					resetVbox(vBox_SKLS_Info);
					resetHbox(hBox_SKLS_Date);
					resetHbox(hBox_SKLS_HistoricalFigures);
					if(clickedObject.getTime().length()!=0) {
						addLabelToHbox(hBox_SKLS_Date, clickedObject.getTime());
					}else addLabelToHbox(hBox_SKLS_Date, "Không rõ");
					int hfLength=0;
					int hBoxCount=0;
					HBox hBox[] = new HBox[10];
					boolean[] addedHbox = new boolean[10];
					for(int i = 0; i<10; i++) {
						addedHbox[i]=false;
					}
					if(clickedObject.getHistoricalFiguresId().size()==0) {
						addLabelToHbox(hBox_SKLS_HistoricalFigures, "Không rõ");
					}
					else 
					for(int i = 0;i<clickedObject.getHistoricalFiguresId().size();i++) {
						hfLength+=data.getListEntities().get(clickedObject.getHistoricalFiguresId().get(i)).getName().length();
						if((hfLength>45&&hBoxCount==0)||(hfLength>60&&hBoxCount>0)) {
							hfLength=0;
							hBox[hBoxCount+1] = new HBox();
							hBox[hBoxCount+1].setPrefHeight(hBox_SKLS_HistoricalFigures.getPrefHeight());
							if(addedHbox[hBoxCount+1]==false) {
								vBox_SKLS_Info.getChildren().add(hBox[hBoxCount+1]);
								addedHbox[hBoxCount]=true;
								hBoxCount++;
							}
						}
						if(hBoxCount==0) {
							if(i==clickedObject.getHistoricalFiguresId().size()-1) {
								addLabelToHbox(hBox_SKLS_HistoricalFigures, data.getListEntities().get(clickedObject.getHistoricalFiguresId().get(i)).getName(),4, clickedObject.getHistoricalFiguresId().get(i));
							}
							else addLabelToHbox(hBox_SKLS_HistoricalFigures, data.getListEntities().get(clickedObject.getHistoricalFiguresId().get(i)).getName() + ",", 4, clickedObject.getHistoricalFiguresId().get(i));		
						}else {
							if(i==clickedObject.getHistoricalFiguresId().size()-1) {
								addLabelToHbox(hBox[hBoxCount], data.getListEntities().get(clickedObject.getHistoricalFiguresId().get(i)).getName(),4, clickedObject.getHistoricalFiguresId().get(i));
							}
							else addLabelToHbox(hBox[hBoxCount], data.getListEntities().get(clickedObject.getHistoricalFiguresId().get(i)).getName() + ",", 4, clickedObject.getHistoricalFiguresId().get(i));		
						}
					}
				}
			}
		});
		
		//set dữ liệu cho cột rồi thêm vào bảng 
		indexColDTLS.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(tableDTLS.getItems().indexOf(cellData.getValue()) + 1));
		nameColDTLS.setCellValueFactory(new PropertyValueFactory<HistoricalSite, String>("name"));
		nameColDTLS.setStyle("-fx-font-size: 16pt;");
		tableDTLS.setItems(data.getListHistoricalSites());
		//set tính năng khi chọn 1 đối tượng thì bảng bên phải sẽ thay đổi
		tableDTLS.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HistoricalSite>() {
			@Override
			public void changed(ObservableValue<? extends HistoricalSite> arg0, HistoricalSite arg1, HistoricalSite clickedObject) {
				if(clickedObject!=null) {
					scrollPane_DTLS.setVisible(true);
					DTLS_Name.setText(clickedObject.getName());
					if(DTLS_Name.getText().toString().length()>10) {
						if(DTLS_Name.getText().toString().length()>20) {
							if(DTLS_Name.getText().toString().length()>30) {
								if(DTLS_Name.getText().toString().length()>40) {
									if(DTLS_Name.getText().toString().length()>50) {
										DTLS_Name.setStyle("-fx-font-size: 16pt; -fx-alignment: CENTER;");
									}else DTLS_Name.setStyle("-fx-font-size: 18pt; -fx-alignment: CENTER;");
								}else DTLS_Name.setStyle("-fx-font-size: 22pt; -fx-alignment: CENTER;");
							}else DTLS_Name.setStyle("-fx-font-size: 24pt; -fx-alignment: CENTER;");
						}else DTLS_Name.setStyle("-fx-font-size: 26pt; -fx-alignment: CENTER;");
					}else DTLS_Name.setStyle("-fx-font-size: 30pt; -fx-alignment: CENTER;");
					resetVbox(vBox_DTLS_Info);
					resetHbox(hBox_DTLS_HistoricalFigures);
					resetHbox(hBox_DTLS_Location);
					if(clickedObject.getLocation().length()!=0) {
						addLabelToHbox(hBox_DTLS_Location, clickedObject.getLocation());
					}else addLabelToHbox(hBox_DTLS_Location,"Không rõ");
					int hfLength=0;
					int hBoxCount=0;
					HBox hBox[] = new HBox[10];
					boolean[] addedHbox = new boolean[10];
					for(int i = 0; i<10; i++) {
						addedHbox[i]=false;
					}
					if(clickedObject.getHistoricalFiguresId().size()==0) {
						addLabelToHbox(hBox_DTLS_HistoricalFigures, "Không rõ");
					}
					else for(int i = 0;i<clickedObject.getHistoricalFiguresId().size();i++) {
						hfLength+= data.getListEntities().get(clickedObject.getHistoricalFiguresId().get(i)).getName().length();
						if((hfLength>45&&hBoxCount==0)||(hfLength>60&&hBoxCount>0)) {
							hfLength=0;
							hBox[hBoxCount+1] = new HBox();
							hBox[hBoxCount+1].setPrefHeight(hBox_DTLS_HistoricalFigures.getPrefHeight());
							if(addedHbox[hBoxCount+1]==false) {
								vBox_DTLS_Info.getChildren().add(hBox[hBoxCount+1]);
								addedHbox[hBoxCount]=true;
								hBoxCount++;
							}
						}
						if(hBoxCount==0) {
							if(i==clickedObject.getHistoricalFiguresId().size()-1) {
								addLabelToHbox(hBox_DTLS_HistoricalFigures, data.getListEntities().get(clickedObject.getHistoricalFiguresId().get(i)).getName(),4, clickedObject.getHistoricalFiguresId().get(i));
							}
							else addLabelToHbox(hBox_DTLS_HistoricalFigures, data.getListEntities().get(clickedObject.getHistoricalFiguresId().get(i)).getName() + ",", 4, clickedObject.getHistoricalFiguresId().get(i));		
						}else {
							if(i==clickedObject.getHistoricalFiguresId().size()-1) {
								addLabelToHbox(hBox[hBoxCount], data.getListEntities().get(clickedObject.getHistoricalFiguresId().get(i)).getName(),4, clickedObject.getHistoricalFiguresId().get(i));
							}
							else addLabelToHbox(hBox[hBoxCount], data.getListEntities().get(clickedObject.getHistoricalFiguresId().get(i)).getName() + ",", 4, clickedObject.getHistoricalFiguresId().get(i));		
						}
					}
				}
			}
		});	
		
		//set dữ liệu cho cột rồi thêm vào bảng 
		indexColNVLS.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(tableNVLS.getItems().indexOf(cellData.getValue()) + 1));
		nameColNVLS.setCellValueFactory(new PropertyValueFactory<HistoricalFigure, String>("name"));
		nameColNVLS.setStyle("-fx-font-size: 16pt;");
		tableNVLS.setItems(data.getListHistoricalFigures());
		//set tính năng khi chọn 1 đối tượng thì bảng bên phải sẽ thay đổi
		tableNVLS.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HistoricalFigure>() {
			@Override
			public void changed(ObservableValue<? extends HistoricalFigure> arg0, HistoricalFigure arg1, HistoricalFigure clickedObject) {
					if(clickedObject!=null) {
						scrollPane_NVLS.setVisible(true);
						NVLS_Name.setText(clickedObject.getName());
						resetVbox(vBox_NVLS_Info);
						resetHbox(hBox_NVLS_Date);
						resetHbox(hBox_NVLS_Dynasty);
						resetHbox(hBox_NVLS_Events);
						resetHbox(hBox_NVLS_HistoricalSites);
						resetHbox(hBox_NVLS_HomeTown);
						if(clickedObject.getDateOfBirth()!=null) {
							addLabelToHbox(hBox_NVLS_Date, clickedObject.getDateOfBirth());
						}else addLabelToHbox(hBox_NVLS_Date,"Không rõ");
						if(clickedObject.getHomeTown()!=null) {
							addLabelToHbox(hBox_NVLS_HomeTown, clickedObject.getHomeTown());
						}else addLabelToHbox(hBox_NVLS_HomeTown, "Không rõ");
						if(clickedObject.getDynasty()!=null) {
							addLabelToHbox(hBox_NVLS_Dynasty, clickedObject.getDynasty());
						}else addLabelToHbox(hBox_NVLS_Dynasty, "Không rõ");
						if(clickedObject.getRole()!=null) {
							hBox_NVLS_Description.setVisible(true);
							NVLS_Description.setText(clickedObject.getRole());
						}else hBox_NVLS_Description.setVisible(false);
						int hfLength=0;
						int hBoxCount=0;
						int additionalHbox=0;
						HBox hBox[] = new HBox[10];
						boolean[] addedHbox = new boolean[10];
						for(int i = 0; i<10; i++) {
							addedHbox[i]=false;
						}
						if(clickedObject.getHistoricalSitesId().size()==0) {
							addLabelToHbox(hBox_NVLS_HistoricalSites, "Không rõ");
						}
						else 
						for(int i = 0;i<clickedObject.getHistoricalSitesId().size();i++) {
							hfLength+=data.getListEntities().get(clickedObject.getHistoricalSitesId().get(i)).getName().length();
							if((hfLength>45&&hBoxCount==0)||(hfLength>60&&hBoxCount>0)) {
								hfLength=0;
								hBox[hBoxCount+1] = new HBox();
								hBox[hBoxCount+1].setPrefHeight(hBox_NVLS_HistoricalSites.getPrefHeight());
								if(addedHbox[hBoxCount+1]==false) {
									vBox_NVLS_Info.getChildren().add(4+hBoxCount, hBox[hBoxCount+1]);
									addedHbox[hBoxCount]=true;
									hBoxCount++;
								}
							}
							if(hBoxCount==0) {
								if(i==clickedObject.getHistoricalSitesId().size()-1) {
									addLabelToHbox(hBox_NVLS_HistoricalSites, data.getListEntities().get(clickedObject.getHistoricalSitesId().get(i)).getName(),5, clickedObject.getHistoricalSitesId().get(i));
								}
								else addLabelToHbox(hBox_NVLS_HistoricalSites, data.getListEntities().get(clickedObject.getHistoricalSitesId().get(i)).getName() + ",", 5, clickedObject.getHistoricalSitesId().get(i));		
							}else {
								if(i==clickedObject.getHistoricalSitesId().size()-1) {
									addLabelToHbox(hBox[hBoxCount], data.getListEntities().get(clickedObject.getHistoricalSitesId().get(i)).getName(),5, clickedObject.getHistoricalSitesId().get(i));
								}
								else addLabelToHbox(hBox[hBoxCount], data.getListEntities().get(clickedObject.getHistoricalSitesId().get(i)).getName() + ",", 5, clickedObject.getHistoricalSitesId().get(i));		
							}
						}
						additionalHbox=hBoxCount;
						hfLength=0;
						hBoxCount=0;
						hBox = new HBox[10];
						addedHbox = new boolean[10];
						for(int i = 0; i<10; i++) {
							addedHbox[i]=false;
						}
						if(clickedObject.getEventsId().size()==0) {
							addLabelToHbox(hBox_NVLS_Events, "Không rõ");
						}
						else 
						for(int i = 0;i<clickedObject.getEventsId().size();i++) {
							hfLength+=data.getListEntities().get(clickedObject.getEventsId().get(i)).getName().length();
							if((hfLength>60&&hBoxCount==0)||(hfLength>80&&hBoxCount>0)) {
								hfLength=data.getListEntities().get(clickedObject.getEventsId().get(i)).getName().length();
								hBox[hBoxCount+1] = new HBox();
								hBox[hBoxCount+1].setPrefHeight(hBox_NVLS_Events.getPrefHeight());
								if(addedHbox[hBoxCount+1]==false) {
									vBox_NVLS_Info.getChildren().add(5+hBoxCount+additionalHbox, hBox[hBoxCount+1]);
									addedHbox[hBoxCount]=true;
									hBoxCount++;
								}
							}
							if(hBoxCount==0) {
								if(i==clickedObject.getEventsId().size()-1) {
									addLabelToHbox(hBox_NVLS_Events, data.getListEntities().get(clickedObject.getEventsId().get(i)).getName(), 2, clickedObject.getEventsId().get(i));
								}
								else addLabelToHbox(hBox_NVLS_Events, data.getListEntities().get(clickedObject.getEventsId().get(i)).getName() + ",", 2, clickedObject.getEventsId().get(i));		
							}else {
								if(i==clickedObject.getEventsId().size()-1) {
									addLabelToHbox(hBox[hBoxCount], data.getListEntities().get(clickedObject.getEventsId().get(i)).getName(), 2, clickedObject.getEventsId().get(i));
								}
								else addLabelToHbox(hBox[hBoxCount], data.getListEntities().get(clickedObject.getEventsId().get(i)).getName() + ",", 2, clickedObject.getEventsId().get(i));		
							}
						}
					}
			}
		});		
		
		searchField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String text) {
				if(text.length()==0) {
					tableDTLS.setItems(data.getListHistoricalSites());
					tableNVLS.setItems(data.getListHistoricalFigures());
					tableSKLS.setItems(data.getListEvents());
					tableLHVH.setItems(data.getListFestivals());
					tableTDLS.setItems(data.getListDynasties());
				}
				else {
					if(text.charAt(0) != ' ') {
						tableDTLS.setItems(data.searchHistoricalSite(text));
						tableLHVH.setItems(data.searchFestival(text));
						tableNVLS.setItems(data.searchHistoricalFigure(text));
						tableSKLS.setItems(data.searchEvent(text));
						tableTDLS.setItems(data.searchDynasty(text));
					}else searchField.setText(""); 
				}
			}
			
		});
		
	}
	
	public void resetHbox(HBox hBox) {
		while(hBox.getChildren().size()>1) {
			hBox.getChildren().remove(1);
		}
	}
	
	public void addLabelToHbox(HBox hBox, String string) {
		hBox.getChildren().add(new RelatedInfoLabel(string, hBox.getPrefHeight()));
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
	
	public void addLabelToHbox(HBox hBox, String string, int dataType, int id) {
		hBox.getChildren().add(new RelatedInfoLabel(string, hBox.getPrefHeight(), dataType, data,id));
	}
	
	public void resetVbox(VBox vBox) {
		for (int i = 0; i < vBox.getChildren().size(); i++) {
			if (vBox.getChildren().get(i).getId()==null) {
				vBox.getChildren().remove(i);
				i=0;
			}
		}
	}
	
	
}









