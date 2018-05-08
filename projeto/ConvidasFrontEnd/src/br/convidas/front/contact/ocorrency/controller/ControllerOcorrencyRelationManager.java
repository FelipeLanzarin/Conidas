package br.convidas.front.contact.ocorrency.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import br.convidas.classes.OcorrencyPF;
import br.convidas.classes.OcorrencyPJ;
import br.convidas.classes.PessoaFisica;
import br.convidas.classes.PessoaJuridica;
import br.convidas.front.contact.ocorrency.TelaModalOcorrency;
import br.convidas.manager.ManagerOcorrencyPF;
import br.convidas.manager.ManagerOcorrencyPJ;
import br.convidas.tools.log.LogTools;
import br.convidas.utils.ConvidasUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ControllerOcorrencyRelationManager implements Initializable{
	
	@FXML private TableView<OcorrencyPF> table;
	@FXML private TableColumn<OcorrencyPF, String> columnOne;
	@FXML private TableColumn<OcorrencyPF, String> columnTwo;
	@FXML private TableView<OcorrencyPJ> table2;
	@FXML private TableColumn<OcorrencyPJ, String> columnOne2;
	@FXML private TableColumn<OcorrencyPJ, String> columnTwo2;
	@FXML private Button buttonEdit;
	@FXML private Button buttonDelete;
	@FXML private DatePicker textDataInitial;
	@FXML private DatePicker textDateFinal;
	@FXML private Label labelName;
	
	private Stage stage;
	private PessoaFisica pessoaFisica;
	private PessoaJuridica pessoaJuridica;
	private List<OcorrencyPF> ocorrencyPFs;
	private List<OcorrencyPJ> ocorrencyPJs;
	
	
	public void clickButtonNew(){
		try {
			TelaModalOcorrency tmpf = new TelaModalOcorrency();
			Boolean isPf = null;
			Boolean isPj = null;
			if(pessoaFisica != null){
				isPf = true;
				isPj = false;
			}else{
				isPj = true;
				isPf = false;
			}
			
			tmpf.setIsPF(isPf);
			tmpf.setIsPJ(isPj);
			tmpf.setControllerOcorrencys(this);
			tmpf.setPessoaFisica(pessoaFisica);
			tmpf.setPessoaJuridica(pessoaJuridica);
			tmpf.start(new Stage());
		} catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void clickButtonEdit(){
		try{
			Boolean isPf = null;
			Boolean isPj = null;
			TelaModalOcorrency tme = new TelaModalOcorrency();
			if(pessoaFisica != null){
				OcorrencyPF ocorrency = table.getSelectionModel().getSelectedItem();
				tme.setOcorrencyPF(ocorrency);
				isPf = true;
				isPj = false;
			}else{
				OcorrencyPJ ocorrency = table2.getSelectionModel().getSelectedItem();
				tme.setOcorrencyPJ(ocorrency);
				isPj = true;
				isPf = false;
			}
			tme.setControllerOcorrencys(this);
			tme.setIsPF(isPf);
			tme.setIsPJ(isPj);
			tme.start(new Stage());
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void clickButtonDelete(){
		try {
			OcorrencyPF ocorrency = null;
			OcorrencyPJ ocorrencyPj = null;
			if(pessoaFisica != null){
				ocorrency = table.getSelectionModel().getSelectedItem();
			}else{
				ocorrencyPj = table2.getSelectionModel().getSelectedItem();
			}
			final ButtonType btnSim = new ButtonType("Sim");
			final ButtonType btnNao = new ButtonType("Não");

			SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy");
			String date = "";
			if(ocorrency != null){
				if(ocorrency.getDate() == null){
					date="";
				}else{
					date = sdFormat.format(ocorrency.getDate());
				}
			}else{
				if(ocorrencyPj.getDate() == null){
					date="";
				}else{
					date = sdFormat.format(ocorrencyPj.getDate());
				}
			}
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "Você deseja excluir a ocorrencia do dia "+date+"?", btnSim, btnNao);
			alert.setTitle("Alerta!");
			alert.getButtonTypes().setAll(btnSim, btnNao);
			alert.showAndWait();

			if (alert.getResult() == btnSim) {
				if(ocorrency != null){
					if(ManagerOcorrencyPF.delete(ocorrency)){
						Alert dialog = new Alert(Alert.AlertType.INFORMATION);
						dialog.setTitle("Sucesso!");
						dialog.setHeaderText("Ocorrência excluída com sucesso");
						dialog.showAndWait();
						updateTable(null);
					}else{
						Alert dialog = new Alert(Alert.AlertType.ERROR);
						dialog.setTitle("Erro!");
						dialog.setHeaderText("Erro ao excluir ocorrencia!");
						dialog.showAndWait();
					}
				}else{
					if(ManagerOcorrencyPJ.delete(ocorrencyPj)){
						Alert dialog = new Alert(Alert.AlertType.INFORMATION);
						dialog.setTitle("Sucesso!");
						dialog.setHeaderText("Ocorrência excluída com sucesso");
						dialog.showAndWait();
						updateTablePJ(null);
					}else{
						Alert dialog = new Alert(Alert.AlertType.ERROR);
						dialog.setTitle("Erro!");
						dialog.setHeaderText("Erro ao excluir ocorrencia!");
						dialog.showAndWait();
					}
				}
			}
		} catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void createTable(List<OcorrencyPF> list){
		try {

			columnOne.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<OcorrencyPF,String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<OcorrencyPF, String> param) {
					return new SimpleStringProperty(ConvidasUtils.getStringByDate(param.getValue().getDate()));
				}
			});
			columnTwo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<OcorrencyPF,String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<OcorrencyPF, String> param) {
					String column = param.getValue().getDescription();
					if(column != null){
						String text[] =  column.split("\n");
						column = text[0];
					}
					return new SimpleStringProperty(column);
				}
			});
			updateTable(list);
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void createTablePj(List<OcorrencyPJ> list){
		try {
			columnOne2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<OcorrencyPJ,String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<OcorrencyPJ, String> param) {
					return new SimpleStringProperty(ConvidasUtils.getStringByDate(param.getValue().getDate()));
				}
			});
			columnTwo2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<OcorrencyPJ,String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<OcorrencyPJ, String> param) {
					String column = param.getValue().getDescription();
					if(column != null){
						String text[] =  column.split("\n");
						column = text[0];
					}
					return new SimpleStringProperty(column);
				}
			});
			
			updateTablePJ(list);
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void clickTable(){
		try {
			OcorrencyPF ocorrency = table.getSelectionModel().getSelectedItem();
			if(ocorrency != null){
				disableButtons(false);
			}
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void clickTablePj(){
		try {
			OcorrencyPJ ocorrency = table2.getSelectionModel().getSelectedItem();
			if(ocorrency != null){
				disableButtons(false);
			}
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void clickBuscar(){
		Date dateFinal = null;
		Date dateInicial = null;
		LocalDate ld = null;
		Calendar c = null;
		if(textDataInitial.getValue() == null){
			Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
			dialogoInfo.setTitle("Erro");
			dialogoInfo.setHeaderText("Digite uma data inicial!");
			dialogoInfo.showAndWait();
			return;
		}else{
			ld = textDataInitial.getValue();
			c =  Calendar.getInstance();
			c.set(ld.getYear(), ld.getMonthValue()-1, ld.getDayOfMonth()-1);
			dateInicial = c.getTime();
		}
		if(textDateFinal.getValue() == null){
			Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
			dialogoInfo.setTitle("Erro");
			dialogoInfo.setHeaderText("Digite uma data final!");
			dialogoInfo.showAndWait();
			return;
		}else{
			ld = textDateFinal.getValue();
			c =  Calendar.getInstance();
			c.set(ld.getYear(), ld.getMonthValue()-1, ld.getDayOfMonth());
			dateFinal = c.getTime();
		}
		
		if(ocorrencyPFs != null){
			List<OcorrencyPF> ocorrencys = new ArrayList<>();
			for (OcorrencyPF ocorrency : ocorrencyPFs) {
				if(ocorrency.getDate() != null){
					if(ocorrency.getDate().after(dateInicial) && ocorrency.getDate().before(dateFinal)){
						ocorrencys.add(ocorrency);
					}
				}
			}
			updateTable(ocorrencys);
		}else if(ocorrencyPJs != null){
			List<OcorrencyPJ> ocorrencys = new ArrayList<>();
			for (OcorrencyPJ ocorrency : ocorrencyPJs) {
				if(ocorrency.getDate() != null){
					if(ocorrency.getDate().after(dateInicial) && ocorrency.getDate().before(dateFinal)){
						ocorrencys.add(ocorrency);
					}
				}
			}
			updateTablePJ(ocorrencys);
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
	
	public void messageSucessPJ(String message){
		Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
		dialogoInfo.setTitle("Sucesso");
		dialogoInfo.setHeaderText(message);
		dialogoInfo.showAndWait();
		updateTablePJ(null);
	}
	
	public void updateTable(List<OcorrencyPF> list){
		try {
			if(list == null){
				list = ManagerOcorrencyPF.getOcorrencyByPF(pessoaFisica);
			}
			disableButtons(true);
			table.setItems(FXCollections.observableArrayList(list));
		}catch (Exception e) {
			LogTools.logError(e);
		}

	}
	
	public void updateTablePJ(List<OcorrencyPJ> list){
		try {
			if(list == null){
				list = ManagerOcorrencyPJ.getOcorrencyByPJ(pessoaJuridica);
			}
			disableButtons(true);
			table2.setItems(FXCollections.observableArrayList(list));
		}catch (Exception e) {
			LogTools.logError(e);
		}

	}
	
	private void setVisibleTables(Boolean visible){
		table.setVisible(visible);
		table2.setVisible(!visible);
	}
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
		if(pessoaFisica != null){
			labelName.setText("Ocorrências de "+pessoaFisica.getName());
			setVisibleTables(true);
			setOcorrencyPFs(ManagerOcorrencyPF.getOcorrencyByPF(pessoaFisica));
		}
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
		if(pessoaJuridica != null){
			labelName.setText("Ocorrências de "+pessoaJuridica.getName());
			setVisibleTables(false);
			setOcorrencyPJs(ManagerOcorrencyPJ.getOcorrencyByPJ(pessoaJuridica));
		}
	}

	public List<OcorrencyPF> getOcorrencyPFs() {
		return ocorrencyPFs;
	}

	public void setOcorrencyPFs(List<OcorrencyPF> ocorrencyPFs) {
		this.ocorrencyPFs = ocorrencyPFs;
		createTable(ocorrencyPFs);
	}

	public List<OcorrencyPJ> getOcorrencyPJs() {
		return ocorrencyPJs;
	}

	public void setOcorrencyPJs(List<OcorrencyPJ> ocorrencyPJs) {
		this.ocorrencyPJs = ocorrencyPJs;
		createTablePj(ocorrencyPJs);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
