package br.convidas.front.contact.ocorrency.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import br.convidas.classes.OcorrencyPF;
import br.convidas.classes.OcorrencyPJ;
import br.convidas.classes.PessoaFisica;
import br.convidas.classes.PessoaJuridica;
import br.convidas.manager.ManagerOcorrencyPF;
import br.convidas.manager.ManagerOcorrencyPJ;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ControllerModalOcorrency implements Initializable{
	
	private static final String STYLE_ERROR = "-fx-border-color:red; -fx-border-radius:4";
	
	@FXML private DatePicker textDateOcorrency;
	@FXML private TextArea textDescription;
	@FXML private Label labelTitle;
	
	private Stage stage;
	private ControllerOcorrencys controllerOcorrencys;
	private Boolean isPF;
	private Boolean isPJ;
	private PessoaFisica pessoaFisica;
	private PessoaJuridica pessoaJuridica;
	private OcorrencyPF ocorrencyPF;
	private OcorrencyPJ ocorrencyPJ;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void save(){
		if(isPF){
			saveOcorrencyPF();
		}else {
			saveOcorrencyPJ();
		}
	}
	
	public void saveOcorrencyPF(){
		
		if(!validateFiedls()){
			return;
		}
		Boolean newPF = false;
		if(ocorrencyPF == null){
			newPF=true;
			ocorrencyPF = new OcorrencyPF();
			ocorrencyPF.setPessoFisica(pessoaFisica);
		}
		ocorrencyPF.setDate(getDate());
		ocorrencyPF.setDescription(textDescription.getText());
		
		if(newPF){
			if(ManagerOcorrencyPF.create(ocorrencyPF)){
				stage.close();
				controllerOcorrencys.messageSucess("Ocorrência cadastrada com sucesso!");
			}else{
				Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
				dialogoInfo.setTitle("Erro!");
				dialogoInfo.setHeaderText("Erro ao cadastrar ocorrência!");
				dialogoInfo.showAndWait();
			}
		}else{
			if(ManagerOcorrencyPF.update(ocorrencyPF)){
				stage.close();
				controllerOcorrencys.messageSucess("Ocorrência alterada com sucesso!");
			}else{
				Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
				dialogoInfo.setTitle("Erro!");
				dialogoInfo.setHeaderText("Erro ao alterar ocorrência!");
				dialogoInfo.showAndWait();
			}
		}
	}
	
	public void saveOcorrencyPJ(){
		
		if(!validateFiedls()){
			return;
		}
		Boolean newPJ = false;
		if(ocorrencyPJ == null){
			newPJ = true;
			ocorrencyPJ = new OcorrencyPJ();
			ocorrencyPJ.setPessoaJuridica(pessoaJuridica);
		}
		ocorrencyPJ.setDate(getDate());
		ocorrencyPJ.setDescription(textDescription.getText());
		
		if(newPJ){
			if(ManagerOcorrencyPJ.create(ocorrencyPJ)){
				stage.close();
				controllerOcorrencys.messageSucessPJ("Ocorrência cadastrada com sucesso!");
			}else{
				Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
				dialogoInfo.setTitle("Erro!");
				dialogoInfo.setHeaderText("Erro ao cadastrar ocorrência!");
				dialogoInfo.showAndWait();
			}
		}else{
			if(ManagerOcorrencyPJ.update(ocorrencyPJ)){
				stage.close();
				controllerOcorrencys.messageSucessPJ("Ocorrência alterada com sucesso!");
			}else{
				Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
				dialogoInfo.setTitle("Erro!");
				dialogoInfo.setHeaderText("Erro ao alterar ocorrência!");
				dialogoInfo.showAndWait();
			}
		}
	}
	
	private Date getDate(){
		LocalDate ld = textDateOcorrency.getValue();
		Calendar c =  Calendar.getInstance();
		c.set(ld.getYear(), ld.getMonthValue()-1, ld.getDayOfMonth());
		Date date = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println(sdf.format(date));
		return date;
	}
	
	private Boolean validateFiedls(){
		
		Boolean valid = true;
		if(textDateOcorrency.getValue() == null){
			textDateOcorrency.setStyle(STYLE_ERROR);
			valid = false;
		}
		if(textDescription.getText().isEmpty()){
			textDescription.setStyle(STYLE_ERROR);
			valid = false;
		}
		return valid;
	}
	
	private void populateOcorrencyPF(OcorrencyPF ocorrencyPF){
		textDescription.setText(ocorrencyPF.getDescription());
		Instant instant = Instant.ofEpochMilli(ocorrencyPF.getDate().getTime());
	    LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
		textDateOcorrency.setValue(localDate);
	}
	
	private void populateOcorrencyPJ(OcorrencyPJ ocorrencyPJ){
		textDescription.setText(ocorrencyPJ.getDescription());
		Instant instant = Instant.ofEpochMilli(ocorrencyPJ.getDate().getTime());
	    LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
		textDateOcorrency.setValue(localDate);
	}
		
	
	public void actionDescription(){
		textDescription.setStyle("");
	}
	
	public void actionDate(){
		textDateOcorrency.setStyle("");
	}	
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public Boolean getIsPF() {
		return isPF;
	}

	public void setIsPF(Boolean isPF) {
		this.isPF = isPF;
		if(isPF){
			if(ocorrencyPF != null){
				labelTitle.setText("Alterar ocorrencia de " + ocorrencyPF.getPessoFisica().getName());
			}else{
				labelTitle.setText("Cadastrar ocorrencia para " + pessoaFisica.getName());
			}
		}
	}

	public Boolean getIsPJ() {
		return isPJ;
	}

	public void setIsPJ(Boolean isPJ) {
		this.isPJ = isPJ;
		if(isPJ){
			if(ocorrencyPJ != null){
				labelTitle.setText("Alterar ocorrencia de " + ocorrencyPJ.getPessoaJuridica().getName());
			}else{
				labelTitle.setText("Cadastrar ocorrencia para " + pessoaJuridica.getName());
			}
		}
	}

	public ControllerOcorrencys getControllerOcorrencys() {
		return controllerOcorrencys;
	}

	public void setControllerOcorrencys(ControllerOcorrencys controllerOcorrencys) {
		this.controllerOcorrencys = controllerOcorrencys;
	}


	public OcorrencyPF getOcorrencyPF() {
		return ocorrencyPF;
	}

	public void setOcorrencyPF(OcorrencyPF ocorrencyPF) {
		this.ocorrencyPF = ocorrencyPF;
		if(ocorrencyPF != null){
			populateOcorrencyPF(ocorrencyPF);
		}
	}

	public OcorrencyPJ getOcorrencyPJ() {
		return ocorrencyPJ;
	}

	public void setOcorrencyPJ(OcorrencyPJ ocorrencyPJ) {
		this.ocorrencyPJ = ocorrencyPJ;
		if(ocorrencyPJ != null){
			populateOcorrencyPJ(ocorrencyPJ);
		}
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}
	
	
}
