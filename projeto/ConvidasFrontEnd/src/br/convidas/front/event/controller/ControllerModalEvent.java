package br.convidas.front.event.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import br.convidas.classes.Cidade;
import br.convidas.classes.Evento;
import br.convidas.front.contact.cities.TelaCities;
import br.convidas.manager.ManagerEvento;
import br.convidas.tools.log.LogTools;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerModalEvent {
	
	private static final String STYLE_ERROR = "-fx-border-color:red; -fx-border-radius:4";
	
	@FXML private Label labelTitle;
	@FXML private TextField textName;
	@FXML private TextField textCidade;
	@FXML private DatePicker textInitialDate;
	@FXML private DatePicker textFinalDate;
	@FXML private TextArea textDescription;
	@FXML private TextArea textLocal;
	
	private Stage stage;
	private Boolean newEvent;
	private Evento event;
	private ControllerEvent controllerEvent;
	private Cidade cidadeSelect;
	
	public void actionName(){
		textName.setStyle("");
	}
	
	public void openCities(){
		try{
			TelaCities tc = new TelaCities();
			tc.setControllerModalEvent(this);
			tc.start(new Stage());
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void save(){
		if(!validateFiedls()){
			return;
		}
		if(event == null){
			event = new Evento();
		}
		event.setName(textName.getText());
		event.setDescription(textDescription.getText());
		event.setLocale(textLocal.getText());
		event.setCity(cidadeSelect);
		event.setInitialDate(getDate(textInitialDate));
		event.setFinalDate(getDate(textFinalDate));
		if(newEvent){
			if(ManagerEvento.create(event)){
				stage.close();
				controllerEvent.messageSucessEvent("Evento cadastrado com sucesso!");
			}else{
				Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
				dialogoInfo.setTitle("Erro!");
				dialogoInfo.setHeaderText("Erro ao cadastrar pessoa física!");
				dialogoInfo.showAndWait();
			}
		}else{
			if(ManagerEvento.update(event)){
				stage.close();
				controllerEvent.messageSucessEvent("Evento alterado com sucesso!");
			}else{
				Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
				dialogoInfo.setTitle("Erro!");
				dialogoInfo.setHeaderText("Erro ao alterar evento");
				dialogoInfo.showAndWait();
			}
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
	
	private Boolean validateFiedls(){
		Boolean valid = true;
		if(textName.getText().isEmpty()){
			textName.setStyle(STYLE_ERROR);
			valid = false;
		}
		Date initial = getDate(textInitialDate);
		Date finalDate = getDate(textFinalDate);
		
		if(initial != null && finalDate != null){
			if(initial.after(finalDate)){
				valid = false;
				Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
				dialogoInfo.setTitle("Erro!");
				dialogoInfo.setHeaderText("A data final deve ser depois da data inicial");
				dialogoInfo.showAndWait();
			}
		}
		
		
		return valid;
	}
	
	private void populateEvent(Evento event){
		textName.setText(event.getName());
		textDescription.setText(event.getDescription());
		textLocal.setText(event.getLocale());
		if(event.getInitialDate() != null){
			textInitialDate.setValue(getLocalDate(event.getInitialDate()));
		}
		if(event.getFinalDate() != null){
			textFinalDate.setValue(getLocalDate(event.getFinalDate()));
		}
		if(event.getCity() != null){
			textCidade.setText(event.getCity().getName());
		}
		cidadeSelect = event.getCity();
	}
	
	private LocalDate getLocalDate(Date data){
		if(data == null){
			return null;
		}
		Instant instant = Instant.ofEpochMilli(data.getTime());
	    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
	}
	
	public Cidade getCidadeSelect() {
		return cidadeSelect;
	}

	public void setCidadeSelect(Cidade cidadeSelect) {
		this.cidadeSelect = cidadeSelect;
		textCidade.setText(cidadeSelect.getName());
	}
	
	public ControllerEvent getControllerEvent() {
		return controllerEvent;
	}

	public void setControllerEvent(ControllerEvent controllerEvent) {
		this.controllerEvent = controllerEvent;
	}

	public Boolean getNewEvent() {
		return newEvent;
	}

	public void setNewEvent(Boolean newEvent) {
		this.newEvent = newEvent;
		if(newEvent){
			labelTitle.setText("Cadastrar Evento");
		}else{
			labelTitle.setText("Alterar Evento");
		}
	}

	public Evento getEvent() {
		return event;
	}

	public void setEvent(Evento event) {
		this.event = event;
		if(event != null){
			populateEvent(event);
		}
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
}
