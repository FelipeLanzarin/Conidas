package br.convidas.front.contact.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.convidas.classes.PessoaFisica;
import br.convidas.classes.PessoaJuridica;
import br.convidas.front.contact.TelaModalPF;
import br.convidas.front.contact.TelaModalPJ;
import br.convidas.manager.ManagerPF;
import br.convidas.manager.ManagerPJ;
import br.convidas.tools.log.LogTools;
import br.convidas.utils.ConvidasUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ControllerContactsRelationManager implements Initializable{
	
	private static final String STYLE_BUTTON="-fx-background-color:  #ffffff; -fx-border-color:  #ddd; -fx-border-radius: 4; -fx-background-radius: 4";
	
	@FXML private TableView<PessoaFisica> table;
	@FXML private TableColumn<PessoaFisica, String> columnOne;
	@FXML private TableColumn<PessoaFisica, String> columnTwo;
	@FXML private TableColumn<PessoaFisica, String> columnThree;
	@FXML private TableColumn<PessoaFisica, String> columnFour;
	@FXML private TableColumn<PessoaFisica, String> columnFive;
	@FXML private TableColumn<PessoaFisica, String> columnSix;
	@FXML private TableView<PessoaJuridica> tablePJ;
	@FXML private TableColumn<PessoaJuridica, String> columnOnePj;
	@FXML private TableColumn<PessoaJuridica, String> columnTwoPj;
	@FXML private TableColumn<PessoaJuridica, String> columnThreePj;
	@FXML private TableColumn<PessoaJuridica, String> columnFourPj;
	@FXML private TableColumn<PessoaJuridica, String> columnFivePj;
	@FXML private TableColumn<PessoaJuridica, String> columnSixPj;
	@FXML private Button buttonEdit;
	@FXML private Button buttonDelete;
	@FXML private Button buttonPf;
	@FXML private Button buttonPj;
	@FXML private Text a;
	@FXML private TextField textCpf;
	@FXML private TextField textPFName;
	@FXML private TextField textCnpj;
	@FXML private TextField textPJName;
	@FXML private Pane paneSelect;
	@FXML private Pane paneAlphabet;
	private Stage stage;
	private List<PessoaFisica> list;
	private List<PessoaJuridica> listPj; 
	private Boolean pageSelect = true;
	private Text lastText;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showAphabet();
		createTable();
	}
	
	private void showAphabet(){
		Double newLayoutX = a.getLayoutX();
		for ( int i = 0; i<26 ;i++) {
			Text b = new Text();
			b.setText(""+ConvidasUtils.alphabetUpperCase[i]);
			b.setFont(a.getFont());
			b.setCursor(a.getCursor());
			if(i == 0){
				b.setLayoutX(a.getLayoutX());
				b.setFill(Color.GREEN);
				lastText=b;
			}else{
				b.setLayoutX(newLayoutX);
				b.setFill(a.getFill());
			}
			if(i==8){
				newLayoutX+=20;
			}else{
				newLayoutX+=30;
			}
			b.setLayoutY(a.getLayoutY());
			b.setVisible(true);
			b.setId(""+i);
			b.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					filterByLetther(b,false);
				}
			});
			paneAlphabet.getChildren().add(b);
		}
		Text outro = new Text();
		outro.setId("outro");
		outro.setText("Outros");
		outro.setFont(a.getFont());
		outro.setCursor(a.getCursor());
		outro.setLayoutX(newLayoutX);
		outro.setFill(a.getFill());
		outro.setLayoutY(a.getLayoutY());
		outro.setVisible(true);
		outro.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				filterByOther(outro, false);
			}
		});
		paneAlphabet.getChildren().add(outro);
		a.setVisible(false);
	}
	
	public void filterByOther(Text text, Boolean needUpdate){
		if(text.getId().equals(lastText.getId()) && !needUpdate){
			return;
		}
		list = ManagerPF.getPessoaFisicasOthers();
		listPj = ManagerPJ.getPessoaJuridicasOthers();
		lastText.setFill(a.getFill());
		text.setFill(Color.GREEN);
		updateTable(list);
		updateTablePj(listPj);
		lastText = text;
	}
	
	public void filterByLetther(Text text, Boolean needUpdate){
		if(text.getId().equals(lastText.getId()) && !needUpdate){
			return;
		}
		int indice = Integer.parseInt(text.getId());
		list = ManagerPF.getPessoasFisicas(ConvidasUtils.alphabetLowerCase[indice], ConvidasUtils.alphabetUpperCase[indice]);
		listPj = ManagerPJ.getPessoaJuridicas(ConvidasUtils.alphabetLowerCase[indice], ConvidasUtils.alphabetUpperCase[indice]);
		lastText.setFill(a.getFill());
		text.setFill(Color.GREEN);
		updateTable(list);
		updateTablePj(listPj);
		lastText = text;
	}
	
	public void clickButtonPF(){
		selectPF();
	}
	
	public void clickButtonPJ(){
		selectPJ();
	}
	
	private void selectPJ(){
		buttonPf.setStyle("");
		buttonPj.setStyle(STYLE_BUTTON);
		paneSelect.setLayoutX(buttonPj.getLayoutX()+1);
		visiblePane(false);
	}
	
	private void selectPF(){
		buttonPj.setStyle("");
		buttonPf.setStyle(STYLE_BUTTON);
		paneSelect.setLayoutX(buttonPf.getLayoutX()+1);
		visiblePane(true);
		
	}
	
	private void visiblePane(Boolean pf){
		textPJName.setVisible(!pf);
		textCnpj.setVisible(!pf);
		textCpf.setVisible(pf);
		textPFName.setVisible(pf);
		pageSelect = pf;
		table.setVisible(pf);
		tablePJ.setVisible(!pf);
	}
	
	public void messageSucess(String message){
		Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
		dialogoInfo.setTitle("Sucesso");
		dialogoInfo.setHeaderText(message);
		dialogoInfo.showAndWait();
		if(lastText.getId().equals("outro")){
			filterByOther(lastText, true);
		}else{
			filterByLetther(lastText, true);
		}
//		updateTable(null);
	}
	
	public void messageSucessPJ(String message){
		Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
		dialogoInfo.setTitle("Sucesso");
		dialogoInfo.setHeaderText(message);
		dialogoInfo.showAndWait();
		if(lastText.getId().equals("outro")){
			filterByOther(lastText, true);
		}else{
			filterByLetther(lastText, true);
		}
	}
	
	public void updateTable(List<PessoaFisica> list){
		try {
			if(list== null){
				list = ManagerPF.getPessoasFisicas();
			}
			disableButtons(true);
			table.setItems(FXCollections.observableArrayList(list));
		}catch (Exception e) {
			LogTools.logError(e);
		}

	}
	
	public void updateTablePj(List<PessoaJuridica> list){
		try {
			if(list== null){
				list = ManagerPJ.getPessoaJuridicas();
			}
			disableButtons(true);
			tablePJ.setItems(FXCollections.observableArrayList(list));
		}catch (Exception e) {
			LogTools.logError(e);
		}

	}
	
	public void createTable(){
		try {
			list = ManagerPF.getPessoasFisicas("A", "a");
			columnOne.setCellValueFactory(new PropertyValueFactory<>("name"));
			columnTwo.setCellValueFactory(new PropertyValueFactory<>("cpf"));
			columnThree.setCellValueFactory(new PropertyValueFactory<>("email"));
			columnFour.setCellValueFactory(new PropertyValueFactory<>("relacao"));
			columnFive.setCellValueFactory(new PropertyValueFactory<>("telefone"));
			columnSix.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PessoaFisica,String>, ObservableValue<String>>(){
				@Override
				public ObservableValue<String> call(CellDataFeatures<PessoaFisica, String> pf) {
					String b = "Não";
					if(pf.getValue().getNewsletter()){
						b = "Sim";
					}
					return new SimpleStringProperty(b);
				}
			});
			updateTable(list);
			listPj = ManagerPJ.getPessoaJuridicas("A", "a");
			columnOnePj.setCellValueFactory(new PropertyValueFactory<>("name"));
			columnTwoPj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
			columnThreePj.setCellValueFactory(new PropertyValueFactory<>("email"));
			columnFourPj.setCellValueFactory(new PropertyValueFactory<>("responsavel"));
			columnFivePj.setCellValueFactory(new PropertyValueFactory<>("telefone"));
			columnSixPj.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PessoaJuridica,String>, ObservableValue<String>>(){
				@Override
				public ObservableValue<String> call(CellDataFeatures<PessoaJuridica, String> pj) {
					String b = "Não";
					if(pj.getValue().getNewsletter()){
						b = "Sim";
					}
					return new SimpleStringProperty(b);
				}
			});
			updateTablePj(listPj);
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void clickButtonNew(){
		try{
			if(pageSelect){
				TelaModalPF tmpf = new TelaModalPF();
				tmpf.setNewPf(true);
				tmpf.setControllerContacts(this);
				tmpf.start(new Stage());
			}else{
				TelaModalPJ tmpJ = new TelaModalPJ();
				tmpJ.setNewPf(true);
				tmpJ.setControllerContacts(this);
				tmpJ.start(new Stage());
			}
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void clickButtonEdit(){
		try{
//			Cidade city = table.getSelectionModel().getSelectedItem();
//			TelaModalCity tmc = new TelaModalCity();
//			tmc.setControllerCities(this);
//			tmc.setCidade(city);
//			tmc.setNewCity(false);
//			tmc.start(new Stage());
			try{
				if(pageSelect){
					PessoaFisica pf = table.getSelectionModel().getSelectedItem();
					if(pf != null){
						TelaModalPF tmpf = new TelaModalPF();
						tmpf.setNewPf(false);
						tmpf.setPessoaFisica(pf);
						tmpf.setControllerContacts(this);
						tmpf.start(new Stage());
					}
				}else{
					PessoaJuridica pj = tablePJ.getSelectionModel().getSelectedItem();
					if(pj != null){
						TelaModalPJ tmpJ = new TelaModalPJ();
						tmpJ.setNewPf(false);
						tmpJ.setPessoaJuridica(pj);
						tmpJ.setControllerContacts(this);
						tmpJ.start(new Stage());
					}
				}
			}catch (Exception e) {
				LogTools.logError(e);
			}
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void clickButtonDelete(){
		try {
			if(pageSelect){
				PessoaFisica pessoa = table.getSelectionModel().getSelectedItem();
				if(pessoa == null){
					return;
				}
				final ButtonType btnSim = new ButtonType("Sim");
				final ButtonType btnNao = new ButtonType("Não");
				
				Alert alert = new Alert(AlertType.CONFIRMATION, "Você deseja excluir a Pessoa "+ pessoa.getName()+"?", btnSim, btnNao);
				alert.showAndWait();
	
				if (alert.getResult() == btnSim) {
					try{
						if(ManagerPF.delete(pessoa)){
							Alert dialog = new Alert(Alert.AlertType.INFORMATION);
							dialog.setTitle("Sucesso!");
							dialog.setHeaderText("Pessoa excluída com sucesso");
							dialog.showAndWait();
							if(lastText.getId().equals("outro")){
								filterByOther(lastText, true);
							}else{
								filterByLetther(lastText, true);
							}
						}else{
							Alert dialog = new Alert(Alert.AlertType.ERROR);
							dialog.setTitle("Erro!");
							dialog.setHeaderText("Pessoa já possui uma Ocorrência ou já tem participação em um evento!");
							dialog.showAndWait();
						}
					}catch (Exception me) {
						Alert dialog = new Alert(Alert.AlertType.ERROR);
						dialog.setTitle("Erro!");
						dialog.setHeaderText("Erro ao excluir!");
						dialog.showAndWait();
					}
				}
			}else{
				PessoaJuridica pessoa = tablePJ.getSelectionModel().getSelectedItem();
				if(pessoa == null){
					return;
				}
				final ButtonType btnSim = new ButtonType("Sim");
				final ButtonType btnNao = new ButtonType("Não");
				
				Alert alert = new Alert(AlertType.CONFIRMATION, "Você deseja excluir a Pessoa "+ pessoa.getName()+"?", btnSim, btnNao);
				alert.showAndWait();
	
				if (alert.getResult() == btnSim) {
					try{
						if(ManagerPJ.delete(pessoa)){
							Alert dialog = new Alert(Alert.AlertType.INFORMATION);
							dialog.setTitle("Sucesso!");
							dialog.setHeaderText("Pessoa excluída com sucesso");
							dialog.showAndWait();
							if(lastText.getId().equals("outro")){
								filterByOther(lastText, true);
							}else{
								filterByLetther(lastText, true);
							}
						}else{
							Alert dialog = new Alert(Alert.AlertType.ERROR);
							dialog.setTitle("Erro!");
							dialog.setHeaderText("Pessoa já possui uma Ocorrência ou já tem participação em um evento!");
							dialog.showAndWait();
						}
					}catch (Exception me) {
						Alert dialog = new Alert(Alert.AlertType.ERROR);
						dialog.setTitle("Erro!");
						dialog.setHeaderText("Erro ao excluir!");
						dialog.showAndWait();
					}
				}
			}
		} catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void clickTable(){
		try {
			PessoaFisica pessoa = table.getSelectionModel().getSelectedItem();
			if(pessoa != null){
				disableButtons(false);
			}
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void clickTablePj(){
		try {
			PessoaJuridica pessoa = tablePJ.getSelectionModel().getSelectedItem();
			if(pessoa != null){
				disableButtons(false);
			}
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	public void filterByCPF(){
		String name = textCpf.getText();
		List<PessoaFisica> listPFSelect = new ArrayList<>();
		for (PessoaFisica pessoa : list) {
			if(pessoa.getCpf().contains(name)){
				listPFSelect.add(pessoa);
			}
		}
		updateTable(listPFSelect);
	}
	
	public void filterByNamePJ(){
		String name = textPJName.getText().toLowerCase();
		List<PessoaJuridica> listPFSelect = new ArrayList<>();
		for (PessoaJuridica pessoa : listPj) {
			String nameP = pessoa.getName().toLowerCase();
			if(nameP.contains(name)){
				listPFSelect.add(pessoa);
			}
		}
		updateTablePj(listPFSelect);
	}
	
	public void filterByCNPJ(){
		String name = textCnpj.getText();
		List<PessoaJuridica> listPFSelect = new ArrayList<>();
		for (PessoaJuridica pessoa : listPj) {
			if(pessoa.getCnpj().contains(name)){
				listPFSelect.add(pessoa);
			}
		}
		updateTablePj(listPFSelect);
	}
	
	public void filterByNamePF(){
		String name = textPFName.getText().toLowerCase();
		List<PessoaFisica> listPFSelect = new ArrayList<>();
		for (PessoaFisica pessoa : list) {
			String nameP = pessoa.getName().toLowerCase();
			if(nameP.contains(name)){
				listPFSelect.add(pessoa);
			}
		}
		updateTable(listPFSelect);
	}
	
	private void disableButtons(Boolean disable){
		buttonEdit.setDisable(disable);
		buttonDelete.setDisable(disable);
	}	
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
