package br.convidas.front.contact.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.convidas.classes.PessoaFisica;
import br.convidas.front.contact.TelaConsultModalPF;
import br.convidas.manager.ManagerPF;
import br.convidas.tools.log.LogTools;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerTeste implements Initializable{
	
	@FXML private TableView<PessoaFisica> table;
	@FXML private TableColumn<PessoaFisica, String> columnOne;
	@FXML private TableColumn<PessoaFisica, String> columnTwo;
	@FXML private TableColumn<PessoaFisica, String> columnThree;
	@FXML private TableColumn<PessoaFisica, String> columnFour;
	@FXML private TableColumn<PessoaFisica, String> columnFive;
	@FXML private TableColumn<PessoaFisica, String> columnSix;
	private Stage stage;
	private List<PessoaFisica> list;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		createTable();
	}
	
	public void updateTable(List<PessoaFisica> list){
		try {
			if(list== null){
				list = ManagerPF.getPessoasFisicas();
			}
			table.setItems(FXCollections.observableArrayList(list));
		}catch (Exception e) {
			LogTools.logError(e);
		}

	}
	
	public void createTable(){
		try {
			System.out.println("buscando pessoas");
			list = ManagerPF.getPessoasFisicas();
			System.out.println("fim");
			columnOne.setCellValueFactory(new PropertyValueFactory<>("name"));
			columnTwo.setCellValueFactory(new PropertyValueFactory<>("cpf"));
			columnThree.setCellValueFactory(new PropertyValueFactory<>("email"));
			columnFour.setCellValueFactory(new PropertyValueFactory<>("relacao"));
			columnFive.setCellValueFactory(new PropertyValueFactory<>("telefone"));
			columnSix.setCellValueFactory(new PropertyValueFactory<>("newsletter"));
			
			System.out.println("aa");
			updateTable(list);
			System.out.println("fim2");
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	@FXML
	public void clickTable(MouseEvent mouseEvent){
		if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
			if(mouseEvent.getClickCount() == 2){
				try {
					PessoaFisica pessoa = table.getSelectionModel().getSelectedItem();
					TelaConsultModalPF tme = new TelaConsultModalPF();
					tme.setPessoaFisica(pessoa);
					tme.start(new Stage());
				}catch (Exception e) {
					LogTools.logError(e);
				}
			}
		}
	}
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
}
