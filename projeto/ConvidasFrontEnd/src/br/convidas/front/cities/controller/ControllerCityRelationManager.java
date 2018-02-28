package br.convidas.front.cities.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.convidas.classes.Cidade;
import br.convidas.front.contact.cities.TelaModalCity;
import br.convidas.front.contact.controller.ControllerModalPF;
import br.convidas.front.contact.controller.ControllerModalPJ;
import br.convidas.manager.ManagerCidade;
import br.convidas.tools.log.LogTools;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerCityRelationManager implements Initializable{
	
	@FXML private TextField textNameFilter;
	@FXML private TableView<Cidade> table;
	@FXML private TableColumn<Cidade, String> columnOne;
	@FXML private TableColumn<Cidade, String> columnTwo;
	@FXML private TableColumn<Cidade, String> columnThree;
	@FXML private Button buttonEdit;
	@FXML private Button buttonDelete;
	private Stage stage;
	private List<Cidade> list;
	private ControllerModalPJ controllerModalPJ;
	private ControllerModalPF controllerModalPF;
	
	
	
	public void filterCities(){
		String city = textNameFilter.getText().toLowerCase();
		List<Cidade> listCidadesSelects = new ArrayList<>();
		for (Cidade cidade : list) {
			String name = cidade.getName().toLowerCase();
			if(name.contains(city)){
				listCidadesSelects.add(cidade);
			}
		}
		updateTable(listCidadesSelects);
	}
	
	public void clickButtonNew(){
		try{
			TelaModalCity tmc = new TelaModalCity();
			tmc.setControllerCities(this);
			tmc.setNewCity(true);
			tmc.start(new Stage());
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void clickButtonEdit(){
		try{
			Cidade city = table.getSelectionModel().getSelectedItem();
			TelaModalCity tmc = new TelaModalCity();
			tmc.setControllerCities(this);
			tmc.setCidade(city);
			tmc.setNewCity(false);
			tmc.start(new Stage());
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void clickButtonDelete(){
		try {
			Cidade city = table.getSelectionModel().getSelectedItem();
			if(city == null){
				return;
			}
			final ButtonType btnSim = new ButtonType("Sim");
			final ButtonType btnNao = new ButtonType("Não");
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "Você deseja excluir a Cidade "+ city.getName()+"?", btnSim, btnNao);
			alert.showAndWait();

			if (alert.getResult() == btnSim) {
				try{
					if(ManagerCidade.delete(city)){
						Alert dialog = new Alert(Alert.AlertType.INFORMATION);
						dialog.setTitle("Sucesso!");
						dialog.setHeaderText("Cidade excluída com sucesso");
						dialog.showAndWait();
						updateTable(null);
					}else{
						Alert dialog = new Alert(Alert.AlertType.ERROR);
						dialog.setTitle("Erro!");
						dialog.setHeaderText("Erro inesperado!");
						dialog.showAndWait();
					}
				}catch (Exception me) {
					Alert dialog = new Alert(Alert.AlertType.ERROR);
					dialog.setTitle("Erro!");
					dialog.setHeaderText("Erro ao excluir!");
					dialog.showAndWait();
				}
			}
		} catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void clickTable(){
		try {
			Cidade city = table.getSelectionModel().getSelectedItem();
			if(city != null){
				disableButtons(false);
			}
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	private void disableButtons(Boolean disable){
		buttonEdit.setDisable(disable);
		buttonDelete.setDisable(disable);
	}	
	public void messageSucess(String message){
		Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
		dialogoInfo.setTitle("Sucesso");
		dialogoInfo.setHeaderText(message);
		dialogoInfo.showAndWait();
		updateTable(null);
	}
	
	public void updateTable(List<Cidade> listCitys){
		try {
			if(listCitys== null){
				listCitys = ManagerCidade.getCidades();
			}
			disableButtons(true);
			table.setItems(FXCollections.observableArrayList(listCitys));
		}catch (Exception e) {
			LogTools.logError(e);
		}

	}
	
	public void createTable(){
		try {
			list = ManagerCidade.getCidades();
			columnOne.setCellValueFactory(new PropertyValueFactory<>("name"));
			columnTwo.setCellValueFactory(new PropertyValueFactory<>("uf"));
			columnThree.setCellValueFactory(new PropertyValueFactory<>("pais"));
			updateTable(list);
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public ControllerModalPJ getControllerModalPJ() {
		return controllerModalPJ;
	}

	public void setControllerModalPJ(ControllerModalPJ controllerModalPJ) {
		this.controllerModalPJ = controllerModalPJ;
	}

	public ControllerModalPF getControllerModalPF() {
		return controllerModalPF;
	}

	public void setControllerModalPF(ControllerModalPF controllerModalPF) {
		this.controllerModalPF = controllerModalPF;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
