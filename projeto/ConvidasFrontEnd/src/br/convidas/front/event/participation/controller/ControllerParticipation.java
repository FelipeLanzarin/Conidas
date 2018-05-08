package br.convidas.front.event.participation.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.convidas.classes.Evento;
import br.convidas.classes.PessoaFisica;
import br.convidas.classes.PessoaJuridica;
import br.convidas.front.contact.TelaConsultContacts;
import br.convidas.front.event.handlers.mouse.ButtonDeleteMouseClickedParticipation;
import br.convidas.front.event.participation.TelaSelectPessoaForParticipation;
import br.convidas.manager.ManagerParticipacao;
import br.convidas.tools.log.LogTools;
import fx.tools.button.ButtonEventUtils;
import fx.tools.mouse.MouseEnventControler;
import fx.tools.table.FXTable;
import fx.tools.utils.FXUtilsControl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerParticipation implements Initializable{
	
//	private static final String STYLE_BUTTON="-fx-background-color:  #ffffff; -fx-border-color:  #ddd; -fx-border-radius: 4; -fx-background-radius: 4";
	private static String IMAGE_LIXEIRA	= "/front/images/manager/lixeira.png";
	private static String ENGRENAGEM	= "/front/images/manager/engrenagem.png";
	private static String BORDER = "-fx-border-color: #ddd;";
	private static String GRAY_BACKGROUND = "-fx-background-color: #f5f5f5;";
	private static final Double HEIGT_PANE = 469D;
	
	@FXML private AnchorPane paneListPF;
	@FXML private AnchorPane paneListPJ;
	@FXML private TextField textNamePF;
	@FXML private TextField textNamePJ;
	
	private Stage stage;
	private Evento event;
	private List<PessoaFisica> listParticipationPF;
	private List<PessoaJuridica> listParticipationPJ;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
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
		updateTablePF(listPFSelect);
	}
	
	public void filterByNamePJ(){
		String name = textNamePJ.getText();
		List<PessoaJuridica> listPJSelect = new ArrayList<>();
		for (PessoaJuridica pessoa : listParticipationPJ) {
			if(pessoa.getName().contains(name)){
				listPJSelect.add(pessoa);
			}
		}
		updateTablePJ(listPJSelect);
	}
	
	public void insertParticipationPF(PessoaFisica pf){
		listParticipationPF.add(pf);
		updateTablePF(listParticipationPF);
	}
	
	public void insertParticipationPJ(PessoaJuridica pj){
		listParticipationPJ.add(pj);
		updateTablePJ(listParticipationPJ);
	}
	
	public void removeParticipationPF(PessoaFisica pf){
		listParticipationPF.remove(pf);
		updateTablePF(listParticipationPF);
	}
	
	public void removeParticipationPJ(PessoaJuridica pj){
		listParticipationPJ.remove(pj);
		updateTablePJ(listParticipationPJ);
	}
	
	
	public void addParticipation(){
		try{
			TelaConsultContacts tcc = new TelaConsultContacts();
			tcc.setEvento(event);
			tcc.setIsRelationConsult(false);
//			tcc.setControllerParticipation(this);
			tcc.start(new Stage());
//			TelaSelectPessoaForParticipation tsp = new TelaSelectPessoaForParticipation();
//			tsp.setEvent(event);
//			tsp.setControllerParticipation(this);
//			tsp.start(new Stage());
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public FXTable updateTablePF(List<PessoaFisica> list){
		if(list == null){
			return null;
		}
		paneListPF.getChildren().clear();
		if(list.isEmpty()){
			return null;
		}
		FXTable table = new FXTable();
		table.setPaneTable(paneListPF);
		table.setSizeRowns(36.0);
		table.setLayoutXInit(0.0);
		table.setLayoutYInit(0.0);
		table.setStyle(BORDER);
		table.setSizeFont(14.0);
		table.setFont("SansSerif Bold");
		Image image = new Image(ENGRENAGEM);
		ImageView img = new ImageView();
		img.setImage(image);
		Double[] sizeColumns = {350.0, 160.0,  40.0};
		Object[] header = 	{"Nome", "CPF", img};
		table.setSizeColumns(sizeColumns);
		table.addRown(header, null);
		table.setFont("SansSerif");
		table.setStyle(BORDER+GRAY_BACKGROUND);
		for (PessoaFisica pessoa : list) {
			Object[] line = populateRownPF(pessoa);
			table.addRown(line, null);
		}
		if(paneListPF.getPrefHeight() < HEIGT_PANE){
			paneListPF.setPrefHeight(HEIGT_PANE);
		}
		return table;
	}
	
	public Object[] populateRownPF(PessoaFisica pessoa){
		if(pessoa == null){
			return null;
		}
		Object[] array = new Object[3];
		array[0] = pessoa.getName();
		array[1] = pessoa.getCpf();
		
		Image image = new Image(IMAGE_LIXEIRA);
		ImageView img = new ImageView();
		img.setImage(image);
		Button seeContact= new Button(null, img);
		seeContact.setLayoutX(10.0);
		seeContact.setLayoutY(6.0);
		seeContact.setPrefHeight(20.0);
		seeContact.setMinHeight(20.0);
		seeContact.setPrefWidth(20.0);
		seeContact.setMinWidth(20.0);
		ButtonDeleteMouseClickedParticipation bec = new ButtonDeleteMouseClickedParticipation();
		bec.setPessoaFisica(pessoa);
		bec.setControllerParticipation(this);
		bec.setEvento(event);
		seeContact.setOnMouseClicked(MouseEnventControler.getMouseCliked(bec));
		ButtonEventUtils.setEvents(seeContact);
		
		Button[] buttons = {seeContact};
		array[2] = buttons;
		return array;
	}
	
	
	public FXTable updateTablePJ(List<PessoaJuridica> list){
		if(list == null){
			return null;
		}
		paneListPJ.getChildren().clear();
		if(list.isEmpty()){
			return null;
		}
		FXTable table = new FXTable();
		table.setPaneTable(paneListPJ);
		table.setSizeRowns(36.0);
		table.setLayoutXInit(0.0);
		table.setLayoutYInit(0.0);
		table.setStyle(BORDER);
		table.setSizeFont(14.0);
		table.setFont("SansSerif Bold");
		Image image = new Image(ENGRENAGEM);
		ImageView img = new ImageView();
		img.setImage(image);
		Double[] sizeColumns = {350.0, 160.0, 40.0};
		Object[] header = 	{"Nome", "CNPJ", img};
		table.setSizeColumns(sizeColumns);
		table.addRown(header, null);
		table.setFont("SansSerif");
		table.setStyle(BORDER+GRAY_BACKGROUND);
		for (PessoaJuridica pessoa : list) {
			Object[] line = populateRownPJ(pessoa);
			table.addRown(line, null);
		}
		if(paneListPJ.getPrefHeight() < HEIGT_PANE){
			paneListPJ.setPrefHeight(HEIGT_PANE);
		}
		return table;
	}
	
	public Object[] populateRownPJ(PessoaJuridica pessoa){
		if(pessoa == null){
			return null;
		}
		Object[] array = new Object[3];
		array[0] = pessoa.getName();
		array[1] = pessoa.getCnpj();
		
		Image image = new Image(IMAGE_LIXEIRA);
		ImageView img = new ImageView();
		img.setImage(image);
		Button seeContact= new Button(null, img);
		seeContact.setLayoutX(10.0);
		seeContact.setLayoutY(6.0);
		seeContact.setPrefHeight(20.0);
		seeContact.setMinHeight(20.0);
		seeContact.setPrefWidth(20.0);
		seeContact.setMinWidth(20.0);
		ButtonDeleteMouseClickedParticipation bec = new ButtonDeleteMouseClickedParticipation();
		bec.setPessoaJuridica(pessoa);
		bec.setControllerParticipation(this);
		bec.setEvento(event);
		seeContact.setOnMouseClicked(MouseEnventControler.getMouseCliked(bec));
		ButtonEventUtils.setEvents(seeContact);
		
		Button[] buttons = {seeContact};
		array[2] = buttons;
		return array;
	}
	
	public Evento getEvent() {
		return event;
	}

	public void setEvent(Evento event) {
		this.event = event;
		listParticipationPF = ManagerParticipacao.getParticipacaoPFOfEvent(event);
		listParticipationPJ = ManagerParticipacao.getParticipacaoPJOfEvent(event);
		updateTablePF(listParticipationPF);
		updateTablePJ(listParticipationPJ);
	}
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
		FXUtilsControl.setScene(stage.getScene());
	}
	
}
