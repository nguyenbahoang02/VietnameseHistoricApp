package screen.controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import screen.info.RelatedInfoLabel;
import variable.Data;
import variable.Event;
import variable.HistoricalFigure;
import variable.HistoricalSite;


public class InfoScreenController implements Initializable{
	
	private Data data;
	private int dataType;
	private String string;
	private int id;
	
	@FXML
    private Label name;
	
    @FXML
    private HBox dateOfBirth;

    @FXML
    private HBox dateOfDeath;

    @FXML
    private HBox role;

    @FXML
    private HBox festival;

    @FXML
    private HBox historicalSites;

    @FXML
    private HBox historicalFigures;
    
    @FXML
    private HBox events;

    @FXML
    private HBox time;
    
    @FXML
    private HBox homeTown;
    
    @FXML
    private HBox otherName;
    
    @FXML
    private HBox location;
    
    @FXML
    private HBox countryName;

    @FXML
    private Text desText;

    @FXML
    private HBox description;

    @FXML
    private HBox succession;

    @FXML
    private HBox tienNhiem;
	
    @FXML
    private VBox infoBox;

    
	public InfoScreenController(Data data,int dataType, String string, int id) {
		this.data = data;
		this.dataType = dataType;
		this.string = string;
		this.id=id;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		name.setText(string);
		/*
		 * 1 là triều đại
		 * 2 là sự kiện
		 * 3 là lễ hội
		 * 4 là nhân vật
		 * 5 là di tích
		 */
		switch (dataType) {
		case 1:
			//không cần thiết 
			break;
		case 2:
			Event event = (Event) data.getListEntities().get(id);
			if(event==null) {
				addLabelToHbox(time, "Không rõ");
				addLabelToHbox(historicalFigures, "Không rõ");
			}else {
				if(string.length()>=40) {
					name.setStyle("-fx-font-size: 20pt; -fx-alignment: CENTER;");
					if(string.length()>=60) {
						name.setStyle("-fx-font-size: 16pt; -fx-alignment: CENTER;");
					}
				}
				if(event.getTime().length()==0) {
					addLabelToHbox(time, "Không rõ");
				}else addLabelToHbox(time, event.getTime());
				int hfLength=0;
				int hBoxCount=0;
				HBox hBox[] = new HBox[10];
				boolean[] addedHbox = new boolean[10];
				for(int i = 0; i<10; i++) {
					addedHbox[i]=false;
				}
				if(event.getHistoricalFiguresId().size()==0) {
					addLabelToHbox(historicalFigures, "Không rõ");
				}
				else 
				for(int i = 0;i<event.getHistoricalFiguresId().size();i++) {
					hfLength+=data.getListEntities().get(event.getHistoricalFiguresId().get(i)).getName().length();
					if((hfLength>120&&hBoxCount==0)||(hfLength>140&&hBoxCount>0)) {
						hfLength=data.getListEntities().get(event.getHistoricalFiguresId().get(i)).getName().length();
						hBox[hBoxCount+1] = new HBox();
						hBox[hBoxCount+1].setPrefHeight(historicalFigures.getPrefHeight());
						if(addedHbox[hBoxCount+1]==false) {
							infoBox.getChildren().add(2+hBoxCount,hBox[hBoxCount+1]);
							addedHbox[hBoxCount]=true;
							hBoxCount++;
						}
					}
					if(hBoxCount==0) {
						if(i==event.getHistoricalFiguresId().size()-1) {
							addLabelToHbox(historicalFigures, data.getListEntities().get(event.getHistoricalFiguresId().get(i)).getName(),4, event.getHistoricalFiguresId().get(i));
						}
						else addLabelToHbox(historicalFigures, data.getListEntities().get(event.getHistoricalFiguresId().get(i)).getName() + ",", 4, event.getHistoricalFiguresId().get(i));		
					}else {
						if(i==event.getHistoricalFiguresId().size()-1) {
							addLabelToHbox(hBox[hBoxCount], data.getListEntities().get(event.getHistoricalFiguresId().get(i)).getName(),4, event.getHistoricalFiguresId().get(i));
						}
						else addLabelToHbox(hBox[hBoxCount], data.getListEntities().get(event.getHistoricalFiguresId().get(i)).getName() + ",", 4, event.getHistoricalFiguresId().get(i));		
					}
				}
			}
			break;
		case 3:
			//không cần thiết
			break;
		case 4:
			HistoricalFigure historicalFigure = (HistoricalFigure) data.getListEntities().get(id);
			if(historicalFigure==null) {
				addLabelToHbox(dateOfBirth, "Không rõ");
				addLabelToHbox(dateOfDeath, "Không rõ");
				addLabelToHbox(otherName, "Không rõ");
				addLabelToHbox(homeTown, "Không rõ");
				addLabelToHbox(historicalSites, "Không rõ");
				addLabelToHbox(festival, "Không rõ");
				addLabelToHbox(role, "Không rõ");
			}else {
				if(historicalFigure.getDateOfBirth()==null) {
					addLabelToHbox(dateOfBirth, "Không rõ");
				}else addLabelToHbox(dateOfBirth, historicalFigure.getDateOfBirth());
				if(historicalFigure.getDateOfDeath()==null) {
					addLabelToHbox(dateOfDeath, "Không rõ");
				}else addLabelToHbox(dateOfDeath, historicalFigure.getDateOfDeath());
				if(historicalFigure.getOtherName()==null) {
					addLabelToHbox(otherName, "Không rõ");
				}else addLabelToHbox(otherName, historicalFigure.getOtherName());
				if(historicalFigure.getHomeTown()==null) {
					addLabelToHbox(homeTown, "Không rõ");
				}else addLabelToHbox(homeTown, historicalFigure.getHomeTown());
				if(historicalFigure.getRole()==null) {
					addLabelToHbox(role, "Không rõ");
				}else addLabelToHbox(role, historicalFigure.getRole());
				int hfLength=0;
				int hBoxCount=0;
				HBox hBox[] = new HBox[10];
				boolean[] addedHbox = new boolean[10];
				for(int i = 0; i<10; i++) {
					addedHbox[i]=false;
				}
				if(historicalFigure.getHistoricalSitesId().size()==0) {
					addLabelToHbox(historicalSites, "Không rõ");
				}
				else 
				for(int i = 0;i<historicalFigure.getHistoricalSitesId().size();i++) {	
					hfLength+=data.getListEntities().get(historicalFigure.getHistoricalSitesId().get(i)).getName().length();
					if((hfLength>120&&hBoxCount==0)||(hfLength>140&&hBoxCount>0)) {
						hfLength=data.getListEntities().get(historicalFigure.getHistoricalSitesId().get(i)).getName().length();
						hBox[hBoxCount+1] = new HBox();
						hBox[hBoxCount+1].setPrefHeight(historicalSites.getPrefHeight());
						if(addedHbox[hBoxCount+1]==false) {
							infoBox.getChildren().add(5+hBoxCount,hBox[hBoxCount+1]);
							addedHbox[hBoxCount]=true;
							hBoxCount++;
						}
					}
					if(hBoxCount==0) {
						if(i==historicalFigure.getHistoricalSitesId().size()-1) {
							addLabelToHbox(historicalSites, data.getListEntities().get(historicalFigure.getHistoricalSitesId().get(i)).getName(),5, historicalFigure.getHistoricalSitesId().get(i));
						}
						else addLabelToHbox(historicalSites, data.getListEntities().get(historicalFigure.getHistoricalSitesId().get(i)).getName() + ",", 5, historicalFigure.getHistoricalSitesId().get(i));		
					}else {
						if(i==historicalFigure.getHistoricalSitesId().size()-1) {
							addLabelToHbox(hBox[hBoxCount], data.getListEntities().get(historicalFigure.getHistoricalSitesId().get(i)).getName(),5, historicalFigure.getHistoricalSitesId().get(i));
						}
						else addLabelToHbox(hBox[hBoxCount], data.getListEntities().get(historicalFigure.getHistoricalSitesId().get(i)).getName() + ",", 5, historicalFigure.getHistoricalSitesId().get(i));		
					}
				}
				int additionalHBox=hBoxCount;
				hfLength=0;
				hBoxCount=0;
				hBox= new HBox[10];
				addedHbox = new boolean[10];
				for(int i = 0; i<10; i++) {
					addedHbox[i]=false;
				}
				if(historicalFigure.getEventsId().size()==0) {
					addLabelToHbox(events, "Không rõ");
				}
				else 
				for(int i = 0;i<historicalFigure.getEventsId().size();i++) {	
					hfLength+=data.getListEntities().get(historicalFigure.getEventsId().get(i)).getName().length();
					if((hfLength>120&&hBoxCount==0)||(hfLength>140&&hBoxCount>0)) {
						hfLength=data.getListEntities().get(historicalFigure.getEventsId().get(i)).getName().length();
						hBox[hBoxCount+1] = new HBox();
						hBox[hBoxCount+1].setPrefHeight(historicalSites.getPrefHeight());
						if(addedHbox[hBoxCount+1]==false) {
							infoBox.getChildren().add(6+hBoxCount+additionalHBox,hBox[hBoxCount+1]);
							addedHbox[hBoxCount]=true;
							hBoxCount++;
						}
					}
					if(hBoxCount==0) {
						if(i==historicalFigure.getEventsId().size()-1) {
							addLabelToHbox(events, data.getListEntities().get(historicalFigure.getEventsId().get(i)).getName(),2, historicalFigure.getEventsId().get(i));
						}
						else addLabelToHbox(events, data.getListEntities().get(historicalFigure.getEventsId().get(i)).getName() + ",", 2, historicalFigure.getEventsId().get(i));		
					}else {
						if(i==historicalFigure.getHistoricalSitesId().size()-1) {
							addLabelToHbox(hBox[hBoxCount], data.getListEntities().get(historicalFigure.getEventsId().get(i)).getName(),2, historicalFigure.getEventsId().get(i));
						}
						else addLabelToHbox(hBox[hBoxCount], data.getListEntities().get(historicalFigure.getEventsId().get(i)).getName() + ",", 2, historicalFigure.getEventsId().get(i));		
					}
				}
			}
			break;
		case 5:
			HistoricalSite historicalSite = (HistoricalSite) data.getListEntities().get(id);
			if(historicalSite==null) {
				addLabelToHbox(location, "Không rõ");
				addLabelToHbox(historicalFigures, "Không rõ");
			}else {
				if(historicalSite.getLocation().length()==0) {
					addLabelToHbox(location, "Không rõ");
				}else addLabelToHbox(location, historicalSite.getLocation());
				int hfLength=0;
				int hBoxCount=0;
				HBox hBox[] = new HBox[10];
				boolean[] addedHbox = new boolean[10];
				for(int i = 0; i<10; i++) {
					addedHbox[i]=false;
				}
				if(historicalSite.getHistoricalFiguresId().size()==0) {
					addLabelToHbox(historicalFigures, "Không rõ");
				}
				else 
				for(int i = 0;i<historicalSite.getHistoricalFiguresId().size();i++) {	
					hfLength+=data.getListEntities().get(historicalSite.getHistoricalFiguresId().get(i)).getName().length();
					if((hfLength>120&&hBoxCount==0)||(hfLength>140&&hBoxCount>0)) {
						hfLength=data.getListEntities().get(historicalSite.getHistoricalFiguresId().get(i)).getName().length();
						hBox[hBoxCount+1] = new HBox();
						hBox[hBoxCount+1].setPrefHeight(historicalFigures.getPrefHeight());
						if(addedHbox[hBoxCount+1]==false) {
							infoBox.getChildren().add(2+ hBoxCount,hBox[hBoxCount+1]);
							addedHbox[hBoxCount]=true;
							hBoxCount++;
						}
					}
					if(hBoxCount==0) {
						if(i==historicalSite.getHistoricalFiguresId().size()-1) {
							addLabelToHbox(historicalFigures, data.getListEntities().get(historicalSite.getHistoricalFiguresId().get(i)).getName(),4, historicalSite.getHistoricalFiguresId().get(i));
						}
						else addLabelToHbox(historicalFigures, data.getListEntities().get(historicalSite.getHistoricalFiguresId().get(i)).getName() + ",", 4, historicalSite.getHistoricalFiguresId().get(i));		
					}else {
						if(i==historicalSite.getHistoricalFiguresId().size()-1) {
							addLabelToHbox(hBox[hBoxCount], data.getListEntities().get(historicalSite.getHistoricalFiguresId().get(i)).getName(),4, historicalSite.getHistoricalFiguresId().get(i));
						}
						else addLabelToHbox(hBox[hBoxCount], data.getListEntities().get(historicalSite.getHistoricalFiguresId().get(i)).getName() + ",", 4, historicalSite.getHistoricalFiguresId().get(i));		
					}
				}
			}
			break;
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
		hBox.getChildren().add(new RelatedInfoLabel(string, hBox.getPrefHeight(), dataType, data, id));
	}	
}
