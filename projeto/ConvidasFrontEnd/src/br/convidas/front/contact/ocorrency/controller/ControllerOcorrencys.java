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
import br.convidas.front.contact.handlers.mouse.ButtonDeleteMouseClickedOcorrency;
import br.convidas.front.contact.handlers.mouse.ButtonEditMouseClickedOcorrency;
import br.convidas.front.contact.ocorrency.TelaModalOcorrency;
import br.convidas.manager.ManagerOcorrencyPF;
import br.convidas.manager.ManagerOcorrencyPJ;
import br.convidas.tools.log.LogTools;
import fx.tools.button.ButtonEventUtils;
import fx.tools.mouse.MouseEnventControler;
import fx.tools.table.FXTable;
import fx.tools.utils.FXUtilsControl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControllerOcorrencys implements Initializable{
	
//	private static final String STYLE_BUTTON="-fx-background-color:  #ffffff; -fx-border-color:  #ddd; -fx-border-radius: 4; -fx-background-radius: 4";
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
	
	@FXML private Pane paneList;
	@FXML private DatePicker textDataInitial;
	@FXML private DatePicker textDateFinal;
	@FXML private Label labelName;
	
	private Stage stage;
	private PessoaFisica pessoaFisica;
	private PessoaJuridica pessoaJuridica;
	private List<OcorrencyPF> ocorrencyPFs;
	private List<OcorrencyPJ> ocorrencyPJs;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		updateTablePF(null);
//		updateTablePJ(null);
	}
	
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
			c.set(ld.getYear(), ld.getMonthValue()-1, ld.getDayOfMonth()+1);
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
			updateTablePF(ocorrencys);
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

	
	public FXTable updateTablePF(List<OcorrencyPF> list){
		if(list == null){
			list = ManagerOcorrencyPF.getOcorrencyByPF(pessoaFisica);
		}
		paneList.getChildren().clear();
		if(list == null || list.isEmpty()){
			return null;
		}
		FXTable table = new FXTable();
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
		Double[] sizeColumns = {150.0, 335.0, 100.0};
		Object[] header = 	{"Data", "Descrição", img};
		table.setSizeColumns(sizeColumns);
		table.addRown(header, null);
		table.setFont("SansSerif");
		table.setStyle(BORDER+GRAY_BACKGROUND);
		for (OcorrencyPF  ocorrencyPF : list) {
			Object[] line = populateRownPF(ocorrencyPF);
			table.addRown(line, null);
		}
		return table;
	}
	
	
	public Object[] populateRownPF(OcorrencyPF ocorrencyPF){
		if(ocorrencyPF == null){
			return null;
		}
		Object[] array = new Object[3];
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(ocorrencyPF.getDate() == null){
			array[0] = "";
		}else{
			array[0] = sdf.format(ocorrencyPF.getDate());
		}
		array[1] = ocorrencyPF.getDescription();
		
	
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
		ButtonEditMouseClickedOcorrency bec = new ButtonEditMouseClickedOcorrency();
		bec.setOcorrencyPF(ocorrencyPF);
		bec.setTelaOcorrency(this);
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
		ButtonDeleteMouseClickedOcorrency bdc = new ButtonDeleteMouseClickedOcorrency();
		bdc.setOcorrency(ocorrencyPF);
		bdc.setTelaOcorrencys(this);
		delete.setOnMouseClicked(MouseEnventControler.getMouseCliked(bdc));
		ButtonEventUtils.setEvents(delete);
		
		Button[] buttons = {delete, edit};
		array[2] = buttons;
		return array;
	}
	
	public FXTable updateTablePJ(List<OcorrencyPJ> list){
		if(list == null){
			list = ManagerOcorrencyPJ.getOcorrencyByPJ(pessoaJuridica);
		}
		paneList.getChildren().clear();
		if(list == null || list.isEmpty()){
			return null;
		}
		FXTable table = new FXTable();
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
		Double[] sizeColumns = {150.0, 335.0, 100.0};
		Object[] header = 	{"Data", "Descrição", img};
		table.setSizeColumns(sizeColumns);
		table.addRown(header, null);
		table.setFont("SansSerif");
		table.setStyle(BORDER+GRAY_BACKGROUND);
		for (OcorrencyPJ  ocorrencyPJ : list) {
			Object[] line = populateRownPJ(ocorrencyPJ);
			table.addRown(line, null);
		}
		return table;
	}


	public Object[] populateRownPJ(OcorrencyPJ ocorrencyPJ){
		if(ocorrencyPJ == null){
			return null;
		}
		Object[] array = new Object[3];
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(ocorrencyPJ.getDate() == null){
			array[0] = "";
		}else{
			array[0] = sdf.format(ocorrencyPJ.getDate());
		}
		array[1] = ocorrencyPJ.getDescription();
		
	
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
		ButtonEditMouseClickedOcorrency bec = new ButtonEditMouseClickedOcorrency();
		bec.setOcorrencyPJ(ocorrencyPJ);
		bec.setTelaOcorrency(this);
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
		ButtonDeleteMouseClickedOcorrency bdc = new ButtonDeleteMouseClickedOcorrency();
		bdc.setOcorrencyPJ(ocorrencyPJ);
		bdc.setTelaOcorrencys(this);
		delete.setOnMouseClicked(MouseEnventControler.getMouseCliked(bdc));
		ButtonEventUtils.setEvents(delete);
		
		Button[] buttons = {delete, edit};
		array[2] = buttons;
		return array;
	}
	
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
		FXUtilsControl.setScene(stage.getScene());
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
		if(pessoaFisica != null){
			labelName.setText("Ocorrências de "+pessoaFisica.getName());
			setOcorrencyPFs(ManagerOcorrencyPF.getOcorrencyByPF(pessoaFisica));
		}
	}

	public List<OcorrencyPF> getOcorrencyPFs() {
		return ocorrencyPFs;
	}

	public void setOcorrencyPFs(List<OcorrencyPF> ocorrencyPFs) {
		this.ocorrencyPFs = ocorrencyPFs;
		updateTablePF(ocorrencyPFs);
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
		if(pessoaJuridica != null){
			labelName.setText("Ocorrências de "+pessoaJuridica.getName());
			setOcorrencyPJs(ManagerOcorrencyPJ.getOcorrencyByPJ(pessoaJuridica));
		}
	}

	public List<OcorrencyPJ> getOcorrencyPJs() {
		return ocorrencyPJs;
	}

	public void setOcorrencyPJs(List<OcorrencyPJ> ocorrencyPJs) {
		this.ocorrencyPJs = ocorrencyPJs;
		updateTablePJ(ocorrencyPJs);
	}
	
}
