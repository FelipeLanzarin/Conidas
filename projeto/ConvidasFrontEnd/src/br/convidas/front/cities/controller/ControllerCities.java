package br.convidas.front.cities.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.convidas.classes.Cidade;
import br.convidas.front.cities.handlers.mouse.ButtonDeleteMouseClickedCity;
import br.convidas.front.cities.handlers.mouse.ButtonEditCityMouseClicked;
import br.convidas.front.contact.cities.TelaModalCity;
import br.convidas.front.contact.controller.ControllerModalPF;
import br.convidas.front.contact.controller.ControllerModalPJ;
import br.convidas.manager.ManagerCidade;
import br.convidas.tools.log.LogTools;
import fx.tools.button.ButtonEventUtils;
import fx.tools.mouse.MouseEnventControler;
import fx.tools.table.FXTable;
import fx.tools.utils.FXUtilsControl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerCities implements Initializable{
	
	private static String IMAGE_PENCIL	= "/front/images/manager/pencil.png";
	private static String IMAGE_LIXEIRA	= "/front/images/manager/lixeira.png";
	private static String ENGRENAGEM	= "/front/images/manager/engrenagem.png";
	private static String BORDER = "-fx-border-color: #ddd;";
	private static String GRAY_BACKGROUND = "-fx-background-color: #f5f5f5;";
	private static String BUTTON_EDIT_STYLE = "-fx-background-color: #fff; "
											+ "-fx-border-color: #8c8c8c; "
											+ "-fx-background-radius: 3; "
											+ "-fx-border-radius:3;";
	private static String BUTTON_DELETE_STYLE = "-fx-background-color: #d9534f; "
			  + "-fx-border-color: #d43f3a; "
			  + "-fx-background-radius: 3; "
			  + "-fx-border-radius:3;";
	
	@FXML private AnchorPane paneList;
	@FXML private TextField textNameFilter;
	
	
	
	private Stage stage;
	private List<Cidade> listCidades;
	private ControllerModalPJ controllerModalPJ;
	private ControllerModalPF controllerModalPF;
	
	public void filterCities(){
		String city = textNameFilter.getText();
		List<Cidade> listCidadesSelects = new ArrayList<>();
		for (Cidade cidade : listCidades) {
			if(cidade.getName().contains(city)){
				listCidadesSelects.add(cidade);
			}
		}
		updateTablePF(listCidadesSelects);
	}
	
	public FXTable updateTablePF(List<Cidade> listCidades){
		if(listCidades== null){
			listCidades = ManagerCidade.getCidades();
			this.listCidades = listCidades;
		}
		FXTable table = new FXTable();
		paneList.getChildren().clear();
		table.setPaneTable(paneList);
		table.setSizeRowns(36.0);
		table.setLayoutXInit(0.0);
		table.setLayoutYInit(0.0);
		table.setStyle(BORDER);
		table.setSizeFont(14.0);
		table.setFont("SansSerif Bold");
		Image image = new Image(ENGRENAGEM);
		ImageView img = new ImageView();
		img.setImage(image);
		Double[] sizeColumns = {225.0, 50.0, 105.0, 75.0};
		Object[] header = 	{"Nome", "UF", "Pais", img};
		table.setSizeColumns(sizeColumns);
		table.addRown(header, null);
		table.setFont("SansSerif");
		table.setStyle(BORDER+GRAY_BACKGROUND);
		for (Cidade cidade : listCidades) {
			Object[] line = populateRownPF(cidade);
			table.addRown(line, null);
		}
		return table;
	}
	
	public void newCity(){
		try {
			TelaModalCity tmc = new TelaModalCity();
			tmc.setNewCity(true);
			tmc.setControllerCities(this);
			tmc.start(new Stage());
		} catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void messageSucess(String message){
		Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
		dialogoInfo.setTitle("Sucesso");
		dialogoInfo.setHeaderText(message);
		dialogoInfo.showAndWait();
		updateTablePF(null);
	}
	
	public Object[] populateRownPF(Cidade cidade){
		if(cidade == null){
			return null;
		}
		Object[] array = new Object[4];
		array[0] = cidade.getName();
		array[1] = cidade.getUf();
		array[2] = cidade.getPais();
		
		Image image = new Image(IMAGE_PENCIL);
		ImageView img = new ImageView();
		img.setImage(image);
		Button edit= new Button(null, img);
		edit.setLayoutX(13.0);
		edit.setLayoutY(6.0);
		edit.setPrefHeight(23.0);
		edit.setMinHeight(23.0);
		edit.setPrefWidth(23.0);
		edit.setMinWidth(23.0);
		edit.setStyle(BUTTON_EDIT_STYLE);
		ButtonEditCityMouseClicked  bec = new ButtonEditCityMouseClicked();
		bec.setControllerCities(this);
		bec.setCidade(cidade);
		bec.setControllerCities(this);
		edit.setOnMouseClicked(MouseEnventControler.getMouseCliked(bec));
		ButtonEventUtils.setEvents(edit);
		
		image = new Image(IMAGE_LIXEIRA);
		img = new ImageView();
		img.setImage(image);
		Button delete= new Button(null, img);
		delete.setLayoutX(40.0);
		delete.setLayoutY(6.0);
		delete.setMinHeight(22.0);
		delete.setPrefHeight(22.0);
		delete.setMinWidth(23.0);
		delete.setPrefWidth(23.0);
		delete.setStyle(BUTTON_DELETE_STYLE);
		ButtonDeleteMouseClickedCity bdc = new ButtonDeleteMouseClickedCity();
		bdc.setCidade(cidade);
		bdc.setTelaCities(this);
		delete.setOnMouseClicked(MouseEnventControler.getMouseCliked(bdc));
		ButtonEventUtils.setEvents(delete);
	
		
		Button[] buttons = {delete, edit};
		array[3] = buttons;
		return array;
	}
	
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
		FXUtilsControl.setScene(stage.getScene());
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
