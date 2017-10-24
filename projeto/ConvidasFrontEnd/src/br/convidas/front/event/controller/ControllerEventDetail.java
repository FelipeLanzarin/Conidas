package br.convidas.front.event.controller;

import java.text.SimpleDateFormat;

import br.convidas.classes.Evento;
import br.convidas.front.event.participation.TelaParticipation;
import br.convidas.tools.log.LogTools;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerEventDetail{

	@FXML private TextField textName;
	@FXML private TextField textCidade;
	@FXML private TextField textDateInitial;
	@FXML private TextField textDateFinal;
	@FXML private TextArea textLocale;
	@FXML private TextArea textDescription;
	
	private Evento event;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public void populateEvent(Evento event){
		textName.setText(event.getName());
		if(event.getCity() != null){
			textCidade.setText(event.getCity().getName());
		}
		if(event.getInitialDate() != null){
			textDateInitial.setText(sdf.format(event.getInitialDate()));
		}
		if(event.getFinalDate() != null){
			textDateFinal.setText(sdf.format(event.getFinalDate()));
		}
		textLocale.setText(event.getLocale());
		textDescription.setText(event.getDescription());
	}
	
	public void openParticipations(){
		try{
			TelaParticipation tp = new TelaParticipation();
			tp.setEvent(event);
			tp.start(new Stage());
		}catch (Exception e) {
			LogTools.logError(e);
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
	

}
