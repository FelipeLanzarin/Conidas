package br.convidas.front.contact.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.convidas.classes.PessoaFisica;
import br.convidas.classes.PessoaJuridica;
import br.convidas.front.contact.TelaModalPF;
import br.convidas.front.contact.TelaModalPJ;
import br.convidas.front.contact.handlers.mouse.ButtonDeleteMouseClickedPF;
import br.convidas.front.contact.handlers.mouse.ButtonDeleteMouseClickedPJ;
import br.convidas.front.contact.handlers.mouse.ButtonEditMouseClickedPF;
import br.convidas.front.contact.handlers.mouse.ButtonEditMouseClickedPJ;
import br.convidas.manager.ManagerPF;
import br.convidas.manager.ManagerPJ;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControllerContacts implements Initializable{
	
	private static final String STYLE_BUTTON="-fx-background-color:  #ffffff; -fx-border-color:  #ddd; -fx-border-radius: 4; -fx-background-radius: 4";
	private static String IMAGE_PENCIL	= "/front/images/manager/pencil.png";
	private static String ENGRENAGEM	= "/front/images/manager/engrenagem.png";
	private static String IMAGE_LIXEIRA	= "/front/images/manager/lixeira.png";
	private static String BORDER = "-fx-border-color: #ddd;";
	private static String GRAY_BACKGROUND = "-fx-background-color: #f5f5f5;";
	private static String BUTTON_DELETE_STYLE = "-fx-background-color: #d9534f; "
											  + "-fx-border-color: #d43f3a; "
											  + "-fx-background-radius: 3; "
											  + "-fx-border-radius:3;";
	private static String BUTTON_EDIT_STYLE = "-fx-background-color: #fff; "
											+ "-fx-border-color: #8c8c8c; "
											+ "-fx-background-radius: 3; "
											+ "-fx-border-radius:3;";
	private static final Double HEIGT_PANE = 485D;
	
	@FXML private Pane paneSelect;
	@FXML private Pane paneList;
	@FXML private Pane paneListPF;
	@FXML private Pane paneListPJ;
	@FXML private Button buttonPf;
	@FXML private Button buttonPj;
	@FXML private Button buttonNewPF;
	@FXML private Button buttonNewPJ;
	@FXML private TextField textCpf;
	@FXML private TextField textPFName;
	@FXML private TextField textCnpj;
	@FXML private TextField textPJName;
	
	private Stage stage;
	private Boolean pageSelect = true;
	private List<PessoaFisica> listPF;
	private List<PessoaJuridica> listPJ;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		updateTablePF(null);
		updateTablePJ(null);
	}
	
	public void clickButtonPF(){
		selectPF();
	}
	
	public void clickButtonPJ(){
		selectPJ();
	}
	
	public void clickButtonNewPF(){
		try {
			TelaModalPF tmpf = new TelaModalPF();
			tmpf.setNewPf(true);
			tmpf.setControllerContacts(this);
			tmpf.start(new Stage());
		} catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	
	public void clickButtonNewPJ(){
		try {
			TelaModalPJ tmpJ = new TelaModalPJ();
			tmpJ.setNewPf(true);
			tmpJ.setControllerContacts(this);
			tmpJ.start(new Stage());
		} catch (Exception e) {
			LogTools.logError(e);
		}
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
		buttonNewPF.setVisible(pf);
		buttonNewPJ.setVisible(!pf);
		paneListPF.setVisible(pf);
		paneListPJ.setVisible(!pf);
		pageSelect  = pf;
		if(pageSelect){
			updatePaneWithListPf();
		}else{
			updatePaneWithListPJ();
		}
	}
	
	public void messageSucess(String message){
		Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
		dialogoInfo.setTitle("Sucesso");
		dialogoInfo.setHeaderText(message);
		dialogoInfo.showAndWait();
		updateTablePF(null);
	}
	
	public void messageSucessPJ(String message){
		Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
		dialogoInfo.setTitle("Sucesso");
		dialogoInfo.setHeaderText(message);
		dialogoInfo.showAndWait();
		updateTablePJ(null);
	}
	
	public void filterByCPF(){
		String name = textCpf.getText();
		List<PessoaFisica> listPFSelect = new ArrayList<>();
		for (PessoaFisica pessoa : listPF) {
			if(pessoa.getCpf().contains(name)){
				listPFSelect.add(pessoa);
			}
		}
		updateTablePF(listPFSelect);
	}
	
	public void filterByNamePJ(){
		String name = textPJName.getText().toLowerCase();
		List<PessoaJuridica> listPFSelect = new ArrayList<>();
		for (PessoaJuridica pessoa : listPJ) {
			String nameP = pessoa.getName().toLowerCase();
			if(pessoa.getName().contains(name)){
				listPFSelect.add(pessoa);
			}
		}
		updateTablePJ(listPFSelect);
	}
	
	public void filterByCNPJ(){
		String name = textCnpj.getText();
		List<PessoaJuridica> listPFSelect = new ArrayList<>();
		for (PessoaJuridica pessoa : listPJ) {
			if(pessoa.getCnpj().contains(name)){
				listPFSelect.add(pessoa);
			}
		}
		updateTablePJ(listPFSelect);
	}
	
	public void filterByNamePF(){
		String name = textPFName.getText();
		List<PessoaFisica> listPFSelect = new ArrayList<>();
		for (PessoaFisica pessoa : listPF) {
			if(pessoa.getName().contains(name)){
				listPFSelect.add(pessoa);
			}
		}
		updateTablePF(listPFSelect);
	}
	
	private void updatePaneWithListPf(){
		paneList.setPrefHeight(HEIGT_PANE);
		if(paneList.getPrefHeight() < paneListPF.getPrefHeight()+100){
			paneList.setPrefHeight(paneListPF.getPrefHeight()+100);
		}
	}
	
	private void updatePaneWithListPJ(){
		paneList.setPrefHeight(HEIGT_PANE);
		if(paneList.getPrefHeight() < paneListPJ.getPrefHeight()+100){
			paneList.setPrefHeight(paneListPJ.getPrefHeight()+100);
		}
	}
	
	public FXTable updateTablePF(List<PessoaFisica> list){
		if(list == null){
			list = ManagerPF.getPessoasFisicas();
			listPF = list;
		}
		paneListPF.getChildren().clear();
		if(list.isEmpty()){
			return null;
		}
		FXTable table = new FXTable();
		table.setPaneTable(paneListPF);
		table.setSizeRowns(36.0);
		table.setLayoutXInit(22.0);
		table.setLayoutYInit(24.0);
		table.setStyle(BORDER);
		table.setSizeFont(14.0);
		table.setFont("SansSerif Bold");
		Image image = new Image(ENGRENAGEM);
		ImageView img = new ImageView();
		img.setImage(image);
		Double[] sizeColumns = {200.0, 120.0, 300.0, 150.0, 150.0, 90.0, 100.0};
		Object[] header = 	{"Nome", "CPF", "E-mail", "Relação", "Telefone", "Newsletter", img};
		table.setSizeColumns(sizeColumns);
		table.addRown(header, null);
		table.setFont("SansSerif");
		table.setStyle(BORDER+GRAY_BACKGROUND);
		for (PessoaFisica pessoa : list) {
			Object[] line = populateRownPF(pessoa);
			table.addRown(line, null);
		}
		if(pageSelect){
			updatePaneWithListPf();
		}
		return table;
	}
	
	
	public Object[] populateRownPF(PessoaFisica pessoa){
		if(pessoa == null){
			return null;
		}
		Object[] array = new Object[7];
		array[0] = pessoa.getName();
		array[1] = pessoa.getCpf();
		array[2] = pessoa.getEmail();
		array[3] = pessoa.getRelacao();
		array[4] = pessoa.getTelefone();
		
		String newsletter = "Não";
		if(pessoa.getNewsletter()){
			newsletter = "Sim";
		}
		array[5] = newsletter;
		
		Image image = new Image(IMAGE_PENCIL);
		ImageView img = new ImageView();
		img.setImage(image);
		Button edit= new Button(null, img);
		edit.setLayoutX(23.0);
		edit.setLayoutY(6.0);
		edit.setPrefHeight(23.0);
		edit.setMinHeight(23.0);
		edit.setPrefWidth(23.0);
		edit.setMinWidth(23.0);
		edit.setStyle(BUTTON_EDIT_STYLE);
		ButtonEditMouseClickedPF bec = new ButtonEditMouseClickedPF();
		bec.setPessoaFisica(pessoa);
		bec.setTelaContact(this);
		edit.setOnMouseClicked(MouseEnventControler.getMouseCliked(bec));
		ButtonEventUtils.setEvents(edit);
		
		image = new Image(IMAGE_LIXEIRA);
		img = new ImageView();
		img.setImage(image);
		Button delete= new Button(null, img);
		delete.setLayoutX(53.0);
		delete.setLayoutY(6.0);
		delete.setMinHeight(22.0);
		delete.setPrefHeight(22.0);
		delete.setMinWidth(23.0);
		delete.setPrefWidth(23.0);
		delete.setStyle(BUTTON_DELETE_STYLE);
		ButtonDeleteMouseClickedPF bdc = new ButtonDeleteMouseClickedPF();
		bdc.setPessoaFisica(pessoa);
		bdc.setTelaContact(this);
		delete.setOnMouseClicked(MouseEnventControler.getMouseCliked(bdc));
		ButtonEventUtils.setEvents(delete);
		
		Button[] buttons = {delete, edit};
		array[6] = buttons;
		return array;
	}
	
	public FXTable updateTablePJ(List<PessoaJuridica> list){
		if(list == null){
			list = ManagerPJ.getPessoaJuridicas();
			listPJ = list;
		}
		paneListPJ.getChildren().clear();
		if(list.isEmpty()){
			return null;
		}
		FXTable table = new FXTable();
		table.setPaneTable(paneListPJ);
		table.setSizeRowns(36.0);
		table.setLayoutXInit(22.0);
		table.setLayoutYInit(24.0);
		table.setStyle(BORDER);
		table.setSizeFont(14.0);
		table.setFont("SansSerif Bold");
		Image image = new Image(ENGRENAGEM);
		ImageView img = new ImageView();
		img.setImage(image);
		Double[] sizeColumns = {200.0, 120.0, 200.0, 250.0, 150.0, 90.0, 100.0};
		Object[] header = 	{"Nome", "CNPJ", "E-mail", "Responsável", "Telefone", "Newsletter", img};
		table.setSizeColumns(sizeColumns);
		table.addRown(header, null);
		table.setFont("SansSerif");
		table.setStyle(BORDER+GRAY_BACKGROUND);
		for (PessoaJuridica pessoa : list) {
			Object[] line = populateRownPJ(pessoa);
			table.addRown(line, null);
		}
		if(!pageSelect){
			updatePaneWithListPJ();
		}
		return table;
	}
	
	public Object[] populateRownPJ(PessoaJuridica pessoa){
		if(pessoa == null){
			return null;
		}
		Object[] array = new Object[7];
		array[0] = pessoa.getName();
		array[1] = pessoa.getCnpj();
		array[2] = pessoa.getEmail();
		array[3] = pessoa.getResponsavel();
		array[4] = pessoa.getTelefone();
		
		String newsletter = "Não";
		if(pessoa.getNewsletter()){
			newsletter = "Sim";
		}
		array[5] = newsletter;
		
		Image image = new Image(IMAGE_PENCIL);
		ImageView img = new ImageView();
		img.setImage(image);
		Button edit= new Button(null, img);
		edit.setLayoutX(23.0);
		edit.setLayoutY(6.0);
		edit.setPrefHeight(23.0);
		edit.setMinHeight(23.0);
		edit.setPrefWidth(23.0);
		edit.setMinWidth(23.0);
		edit.setStyle(BUTTON_EDIT_STYLE);
		ButtonEditMouseClickedPJ bec = new ButtonEditMouseClickedPJ();
		bec.setPessoaJuridica(pessoa);
		bec.setTelaContact(this);
		edit.setOnMouseClicked(MouseEnventControler.getMouseCliked(bec));
		ButtonEventUtils.setEvents(edit);
		
		image = new Image(IMAGE_LIXEIRA);
		img = new ImageView();
		img.setImage(image);
		Button delete= new Button(null, img);
		delete.setLayoutX(53.0);
		delete.setLayoutY(6.0);
		delete.setMinHeight(22.0);
		delete.setPrefHeight(22.0);
		delete.setMinWidth(23.0);
		delete.setPrefWidth(23.0);
		delete.setStyle(BUTTON_DELETE_STYLE);
		ButtonDeleteMouseClickedPJ bdc = new ButtonDeleteMouseClickedPJ();
		bdc.setPessoaJuridica(pessoa);
		bdc.setTelaContact(this);
		delete.setOnMouseClicked(MouseEnventControler.getMouseCliked(bdc));
		ButtonEventUtils.setEvents(delete);
		
		Button[] buttons = {delete, edit};
		array[6] = buttons;
		return array;
	}
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
		FXUtilsControl.setScene(stage.getScene());
	}

	public List<PessoaFisica> getListPF() {
		return listPF;
	}

	public void setListPF(List<PessoaFisica> listPF) {
		this.listPF = listPF;
	}

	public List<PessoaJuridica> getListPJ() {
		return listPJ;
	}

	public void setListPJ(List<PessoaJuridica> listPJ) {
		this.listPJ = listPJ;
	}
	
}
