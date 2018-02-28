package br.convidas.front.contact.cities.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.convidas.classes.Cidade;
import br.convidas.front.contact.controller.ControllerModalPF;
import br.convidas.front.contact.controller.ControllerModalPJ;
import br.convidas.front.contact.handlers.mouse.ButtonSelectCityMouseClicked;
import br.convidas.front.event.controller.ControllerModalEvent;
import br.convidas.manager.ManagerCidade;
import fx.tools.button.ButtonEventUtils;
import fx.tools.mouse.MouseEnventControler;
import fx.tools.table.FXTable;
import fx.tools.utils.FXUtilsControl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerCities implements Initializable{
	
	private static String IMAGE_PENCIL	= "/front/images/manager/pencil.png";
	private static String ENGRENAGEM	= "/front/images/manager/engrenagem.png";
	private static String BORDER = "-fx-border-color: #ddd;";
	private static String GRAY_BACKGROUND = "-fx-background-color: #f5f5f5;";
	private static String BUTTON_EDIT_STYLE = "-fx-background-color: #fff; "
											+ "-fx-border-color: #8c8c8c; "
											+ "-fx-background-radius: 3; "
											+ "-fx-border-radius:3;";
	
	@FXML private AnchorPane paneList;
	@FXML private TextField textNameFilter;
	
	
	
	private Stage stage;
	private List<Cidade> listCidades;
	private ControllerModalPJ controllerModalPJ;
	private ControllerModalPF controllerModalPF;
	private ControllerModalEvent controllerModalEvent;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listCidades = ManagerCidade.getCidades();
	}	
	
	
	public void filterCities(){
		String city = textNameFilter.getText().toLowerCase();
		List<Cidade> listCidadesSelects = new ArrayList<>();
		for (Cidade cidade : listCidades) {
			String name = cidade.getName().toLowerCase();
			if(name.contains(city)){
				listCidadesSelects.add(cidade);
			}
		}
		updateTablePF(listCidadesSelects);
	}
	
	public FXTable updateTablePF(List<Cidade> listCidades){
		if(listCidades== null){
			listCidades = this.listCidades;
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
		Double[] sizeColumns = {250.0, 50.0, 105.0, 50.0};
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
		Button select= new Button(null, img);
		select.setLayoutX(13.0);
		select.setLayoutY(6.0);
		select.setPrefHeight(23.0);
		select.setMinHeight(23.0);
		select.setPrefWidth(23.0);
		select.setMinWidth(23.0);
		select.setStyle(BUTTON_EDIT_STYLE);
		ButtonSelectCityMouseClicked bec = new ButtonSelectCityMouseClicked();
		bec.setCidade(cidade);
		bec.setControllerModalPF(controllerModalPF);
		bec.setControllerModalPJ(controllerModalPJ);
		bec.setControllerModalEvent(controllerModalEvent);
		bec.setControllerCities(this);
		select.setOnMouseClicked(MouseEnventControler.getMouseCliked(bec));
		ButtonEventUtils.setEvents(select);
	
		
		Button[] buttons = {select};
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


	public ControllerModalEvent getControllerModalEvent() {
		return controllerModalEvent;
	}

	public void setControllerModalEvent(ControllerModalEvent controllerModalEvent) {
		this.controllerModalEvent = controllerModalEvent;
	}
	
}
