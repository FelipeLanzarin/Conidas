package br.convidas.front.event.participation.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.convidas.classes.Evento;
import br.convidas.classes.Participacao;
import br.convidas.classes.PessoaFisica;
import br.convidas.classes.PessoaJuridica;
import br.convidas.front.contact.TelaConsultContacts;
import br.convidas.manager.ManagerParticipacao;
import br.convidas.tools.log.LogTools;
import fx.tools.utils.FXUtilsControl;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerParticipationRelationManager implements Initializable{
	
	@FXML private TableView<PessoaFisica> table;
	@FXML private TableColumn<PessoaFisica, String> columnOne;
	@FXML private TableColumn<PessoaFisica, String> columnTwo;
	@FXML private TextField textNamePF;
	@FXML private TextField textNamePJ;
	@FXML private TableView<PessoaJuridica> tablePJ;
	@FXML private TableColumn<PessoaJuridica, String> columnOnePj;
	@FXML private TableColumn<PessoaJuridica, String> columnTwoPj;
	@FXML private Button buttonDelete;
	@FXML private Button buttonDeletePJ;
	
	private Stage stage;
	private Evento event;
	private List<PessoaFisica> listParticipationPF;
	private List<PessoaJuridica> listParticipationPJ;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void filterByNamePF(){
		String name = textNamePF.getText().toLowerCase();
		List<PessoaFisica> listPFSelect = new ArrayList<>();
		for (PessoaFisica pessoa : listParticipationPF) {
			String namep = pessoa.getName().toLowerCase();
			if(namep.contains(name)){
				listPFSelect.add(pessoa);
			}
		}
		updateTable(listPFSelect);
	}
	
	public void filterByNamePJ(){
		String name = textNamePJ.getText();
		List<PessoaJuridica> listPJSelect = new ArrayList<>();
		for (PessoaJuridica pessoa : listParticipationPJ) {
			if(pessoa.getName().contains(name)){
				listPJSelect.add(pessoa);
			}
		}
		updateTablePj(listPJSelect);
	}
	
	public void insertParticipationPF(PessoaFisica pf){
		listParticipationPF.add(pf);
		updateTable(listParticipationPF);
	}
	
	public void insertParticipationPJ(PessoaJuridica pj){
		listParticipationPJ.add(pj);
		updateTablePj(listParticipationPJ);
	}
	
	public void removeParticipationPF(PessoaFisica pf){
		listParticipationPF.remove(pf);
		updateTable(listParticipationPF);
	}
	
	public void removeParticipationPJ(PessoaJuridica pj){
		listParticipationPJ.remove(pj);
		updateTablePj(listParticipationPJ);
	}
	public void clickTable(){
		try {
			PessoaFisica pessoa = table.getSelectionModel().getSelectedItem();
			if(pessoa != null){
				buttonDelete.setDisable(false);
			}
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	public void clickTablePJ(){
		try {
			PessoaJuridica pessoa = tablePJ.getSelectionModel().getSelectedItem();
			if(pessoa != null){
				buttonDeletePJ.setDisable(false);
			}
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	
	public void createTable(){
		try {
			listParticipationPF = ManagerParticipacao.getParticipacaoPFOfEvent(event);
			listParticipationPJ = ManagerParticipacao.getParticipacaoPJOfEvent(event);
			columnOne.setCellValueFactory(new PropertyValueFactory<>("name"));
			columnTwo.setCellValueFactory(new PropertyValueFactory<>("cpf"));
			updateTable(listParticipationPF);
			columnOnePj.setCellValueFactory(new PropertyValueFactory<>("name"));
			columnTwoPj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
			updateTablePj(listParticipationPJ);
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void updateTable(List<PessoaFisica> list){
		try {
			disableButtons(true);
			table.setItems(FXCollections.observableArrayList(list));
		}catch (Exception e) {
			LogTools.logError(e);
		}

	}
	
	public void updateTablePj(List<PessoaJuridica> list){
		try {
			disableButtons(true);
			tablePJ.setItems(FXCollections.observableArrayList(list));
		}catch (Exception e) {
			LogTools.logError(e);
		}

	}
	
	private void disableButtons(Boolean disable){
		buttonDeletePJ.setDisable(disable);
		buttonDelete.setDisable(disable);
	}	
	
	public void addParticipation(){
		try{
			TelaConsultContacts tcc = new TelaConsultContacts();
			tcc.setEvento(event);
			tcc.setIsRelationConsult(false);
			tcc.setControllerParticipation(this);
			tcc.start(new Stage());
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void clickButtonDelete(){
		try {
			PessoaFisica pessoa = table.getSelectionModel().getSelectedItem();
			if(pessoa == null){
				return;
			}
			final ButtonType btnSim = new ButtonType("Sim");
			final ButtonType btnNao = new ButtonType("Não");
			String name = pessoa.getName();
			Alert alert = new Alert(AlertType.CONFIRMATION, "Você deseja remover o participante "+ name+" deste evento?", btnSim, btnNao);
			alert.showAndWait();

			if (alert.getResult() == btnSim) {
				Participacao participation = ManagerParticipacao.getParticipacaoOfPFAndEvent(pessoa, event);
				
				
				if(participation != null && ManagerParticipacao.delete(participation)){
					Alert dialog = new Alert(Alert.AlertType.INFORMATION);
					dialog.setTitle("Sucesso!");
					dialog.setHeaderText("Participante removido com sucesso");
					dialog.showAndWait();
					removeParticipationPF(pessoa);
				}else{
					Alert dialog = new Alert(Alert.AlertType.ERROR);
					dialog.setTitle("Erro!");
					dialog.setHeaderText("Erro ao remover participante!");
					dialog.showAndWait();
				}
			}
		} catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void clickButtonDeletePJ(){
		try {
			PessoaJuridica pessoa = tablePJ.getSelectionModel().getSelectedItem();
			if(pessoa == null){
				return;
			}
			final ButtonType btnSim = new ButtonType("Sim");
			final ButtonType btnNao = new ButtonType("Não");
			String name = pessoa.getName();
			Alert alert = new Alert(AlertType.CONFIRMATION, "Você deseja remover o participante "+ name+" deste evento?", btnSim, btnNao);
			alert.showAndWait();

			if (alert.getResult() == btnSim) {
				Participacao participation = ManagerParticipacao.getParticipacaoOfPJAndEvent(pessoa, event);
				
				
				if(participation != null && ManagerParticipacao.delete(participation)){
					Alert dialog = new Alert(Alert.AlertType.INFORMATION);
					dialog.setTitle("Sucesso!");
					dialog.setHeaderText("Participante removido com sucesso");
					dialog.showAndWait();
					removeParticipationPJ(pessoa);
				}else{
					Alert dialog = new Alert(Alert.AlertType.ERROR);
					dialog.setTitle("Erro!");
					dialog.setHeaderText("Erro ao remover participante!");
					dialog.showAndWait();
				}
			}
		} catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	
	public Evento getEvent() {
		return event;
	}

	public void setEvent(Evento event) {
		this.event = event;
		listParticipationPF = ManagerParticipacao.getParticipacaoPFOfEvent(event);
		listParticipationPJ = ManagerParticipacao.getParticipacaoPJOfEvent(event);
		updateTable(listParticipationPF);
		updateTablePj(listParticipationPJ);
	}
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
		FXUtilsControl.setScene(stage.getScene());
	}
	
}
