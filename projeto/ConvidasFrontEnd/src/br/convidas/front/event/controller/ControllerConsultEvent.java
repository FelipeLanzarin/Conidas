package br.convidas.front.event.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import br.convidas.classes.Evento;
import br.convidas.front.event.TelaEventDetail;
import br.convidas.manager.ManagerEvento;
import br.convidas.tools.log.LogTools;
import br.convidas.utils.ConvidasUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ControllerConsultEvent implements Initializable{
	
	@FXML private TableView<Evento> table;
	@FXML private TableColumn<Evento, String> columnOne;
	@FXML private TableColumn<Evento, String> columnTwo;
	@FXML private TableColumn<Evento, String> columnThree;
	@FXML private TableColumn<Evento, String> columnFour;
	@FXML private TableColumn<Evento, String> columnFive;
	@FXML private TextField textName;
	@FXML private DatePicker textDate;
	private Stage stage;
	private List<Evento> list;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		createTable();
	}
	
	public void updateTable(List<Evento> list){
		try {
			if(list== null){
				list = ManagerEvento.getEventos();
			}
			table.setItems(FXCollections.observableArrayList(list));
		}catch (Exception e) {
			LogTools.logError(e);
		}

	}
	
	public void messageSucess(String message){
		Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
		dialogoInfo.setTitle("Sucesso");
		dialogoInfo.setHeaderText(message);
		dialogoInfo.showAndWait();
		updateTable(null);
	}
	
	public void createTable(){
		try {
			list = ManagerEvento.getEventos();
			columnOne.setCellValueFactory(new PropertyValueFactory<>("name"));
			columnTwo.setCellValueFactory(new PropertyValueFactory<>("locale"));
			columnThree.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Evento,String>, ObservableValue<String>>(){
				@Override
				public ObservableValue<String> call(CellDataFeatures<Evento, String> evento) {
					return new SimpleStringProperty(ConvidasUtils.getStringByDate(evento.getValue().getInitialDate()));
				}
			});
			columnFour.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Evento,String>, ObservableValue<String>>(){
				@Override
				public ObservableValue<String> call(CellDataFeatures<Evento, String> evento) {
					return new SimpleStringProperty(ConvidasUtils.getStringByDate(evento.getValue().getFinalDate()));
				}
			});
			
			columnFive.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Evento,String>, ObservableValue<String>>(){
				@Override
				public ObservableValue<String> call(CellDataFeatures<Evento, String> evento) {
					String city = "";
					if(evento.getValue().getCity() != null){
						city = evento.getValue().getCity().getName();
					}
					return new SimpleStringProperty(city);
				}
			});
			
			updateTable(list);
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void clickTable(){
		try {
			Evento evento = table.getSelectionModel().getSelectedItem();
			if(evento != null){
				TelaEventDetail tme = new TelaEventDetail();
				tme.setEvent(evento);
				tme.start(new Stage());
			}
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	
	public void filterByName(){
		String name = textName.getText().toLowerCase();
		List<Evento> listEventoSelect = new ArrayList<>();
		for (Evento evento : list) {
			String nameLower = evento.getName().toLowerCase();
			if(nameLower.contains(name)){
				listEventoSelect.add(evento);
			}
		}
		updateTable(listEventoSelect);
	}
	
	public void filterByDate(){
		Date data = getDate(textDate);
		if(data != null){
			String dataSelect = sdf.format(data);
			List<Evento> listEventoSelect = new ArrayList<>();
			for (Evento evento : list) {
				if(evento.getInitialDate() != null){
					String initialDate = sdf.format(evento.getInitialDate());
					if(dataSelect.equals(initialDate)){
						listEventoSelect.add(evento);
					}
				}
			}
			updateTable(listEventoSelect);
		}else{
			updateTable(null);
		}
	}
	
	private Date getDate(DatePicker datePicker){
		LocalDate ld = datePicker.getValue();
		if(ld == null){
			return null;
		}
		Calendar c =  Calendar.getInstance();
		c.set(ld.getYear(), ld.getMonthValue()-1, ld.getDayOfMonth());
		Date date = c.getTime();
		return date;
	}
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	
}
