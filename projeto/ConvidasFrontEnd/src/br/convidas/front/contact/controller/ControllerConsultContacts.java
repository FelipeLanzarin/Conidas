package br.convidas.front.contact.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.convidas.classes.Evento;
import br.convidas.classes.Participacao;
import br.convidas.classes.PessoaFisica;
import br.convidas.classes.PessoaJuridica;
import br.convidas.front.contact.TelaConsultModalPF;
import br.convidas.front.contact.TelaConsultModalPJ;
import br.convidas.front.event.participation.controller.ControllerParticipation;
import br.convidas.manager.ManagerPF;
import br.convidas.manager.ManagerPJ;
import br.convidas.manager.ManagerParticipacao;
import br.convidas.tools.log.LogTools;
import br.convidas.utils.ConvidasUtils;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerConsultContacts implements Initializable{
	
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
	@FXML private Button buttonPf;
	@FXML private Button buttonPj;
	@FXML private TextField textCpf;
	@FXML private TextField textPFName;
	@FXML private TextField textCnpj;
	@FXML private TextField textPJName;
	@FXML private Pane paneSelect;
	@FXML private Text a;
	@FXML private Pane paneAlphabet;
	@FXML private Label title;
	private Stage stage;
	private List<PessoaFisica> list;
	private List<PessoaJuridica> listPj; 
	private Text lastText;
	private ControllerParticipation controllerParticipation;
	private Evento evento;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showAphabet();
	}
	
	private void showAphabet(){
		Double newLayoutX = a.getLayoutX();
		for ( int i = 0; i<26 ;i++) {
			Text b = new Text();
			b.setText(""+ConvidasUtils.alphabetUpperCase[i]);
			b.setFont(a.getFont());
			b.setCursor(a.getCursor());
			b.setId(""+i);
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
			b.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					filterByLetther(b);
				}
			});
			paneAlphabet.getChildren().add(b);
		}
		Text outro = new Text();
		outro.setText("Outros");
		outro.setFont(a.getFont());
		outro.setCursor(a.getCursor());
		outro.setLayoutX(newLayoutX);
		outro.setFill(a.getFill());
		outro.setId("outro");
		outro.setLayoutY(a.getLayoutY());
		outro.setVisible(true);
		outro.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				filterByOther(outro);
			}
		});
		paneAlphabet.getChildren().add(outro);
		a.setVisible(false);
	}
	
	public void filterByOther(Text text){
		if(text.getId().equals(lastText.getId())){
			return;
		}
		if(controllerParticipation != null){
			list = ManagerParticipacao.getPossibleParticipacaoPFOfEventOther(evento);
			listPj = ManagerParticipacao.getPossibleParticipacaoPJOfEventOther(evento);
		}else{
			list = ManagerPF.getPessoaFisicasOthers();
			listPj = ManagerPJ.getPessoaJuridicasOthers();
		}
		text.setFill(Color.GREEN);
		lastText.setFill(a.getFill());
		updateTable(list);
		updateTablePj(listPj);
		lastText = text;
	}
	
	public void filterByLetther(Text text){
		if(text.getId().equals(lastText.getId())){
			return;
		}
		int indice = Integer.parseInt(text.getId());
		if(controllerParticipation != null){
			
			list = ManagerParticipacao.getPossibleParticipacaoPFOfEvent(evento, ConvidasUtils.alphabetLowerCase[indice], ConvidasUtils.alphabetUpperCase[indice]);
			listPj = ManagerParticipacao.getPossibleParticipacaoPJOfEvent(evento, ConvidasUtils.alphabetLowerCase[indice], ConvidasUtils.alphabetUpperCase[indice]);
		}else{
			list = ManagerPF.getPessoasFisicas(ConvidasUtils.alphabetLowerCase[indice], ConvidasUtils.alphabetUpperCase[indice]);
			listPj = ManagerPJ.getPessoaJuridicas(ConvidasUtils.alphabetLowerCase[indice], ConvidasUtils.alphabetUpperCase[indice]);
		}
		text.setFill(Color.GREEN);
		lastText.setFill(a.getFill());
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
		table.setVisible(pf);
		tablePJ.setVisible(!pf);
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
	
	public void updateTablePj(List<PessoaJuridica> list){
		try {
			if(list== null){
				list = ManagerPJ.getPessoaJuridicas();
			}
			tablePJ.setItems(FXCollections.observableArrayList(list));
		}catch (Exception e) {
			LogTools.logError(e);
		}

	}
	
	public void createTable(){
		try {
			if(controllerParticipation != null){
				list = ManagerParticipacao.getPossibleParticipacaoPFOfEvent(evento, "A", "a");
				listPj = ManagerParticipacao.getPossibleParticipacaoPJOfEvent(evento, "A", "a");
			}else{
				list = ManagerPF.getPessoasFisicas("A", "a");
				listPj = ManagerPJ.getPessoaJuridicas("A", "a");
			}
			columnOne.setCellValueFactory(new PropertyValueFactory<>("name"));
			columnTwo.setCellValueFactory(new PropertyValueFactory<>("cpf"));
			columnThree.setCellValueFactory(new PropertyValueFactory<>("email"));
			columnFour.setCellValueFactory(new PropertyValueFactory<>("relacao"));
			columnFive.setCellValueFactory(new PropertyValueFactory<>("telefone"));
			columnSix.setCellValueFactory(new PropertyValueFactory<>("newsletter"));
			updateTable(list);
			columnOnePj.setCellValueFactory(new PropertyValueFactory<>("name"));
			columnTwoPj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
			columnThreePj.setCellValueFactory(new PropertyValueFactory<>("email"));
			columnFourPj.setCellValueFactory(new PropertyValueFactory<>("responsavel"));
			columnFivePj.setCellValueFactory(new PropertyValueFactory<>("telefone"));
			columnSixPj.setCellValueFactory(new PropertyValueFactory<>("newsletter"));
			updateTablePj(listPj);
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	@FXML
	public void clickTable(MouseEvent mouseEvent){
		if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
			if(mouseEvent.getClickCount() == 2){
				PessoaFisica pessoa = table.getSelectionModel().getSelectedItem();
				if(controllerParticipation != null){
					Participacao participation = addParticipationPF(pessoa);
					if(participation != null){
						updateParticipationPF(participation);
						Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
						dialogoInfo.setTitle("Sucesso");
						dialogoInfo.setHeaderText("Participação adicionada com sucesso!");
						dialogoInfo.showAndWait();
					}else{
						Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
						dialogoInfo.setTitle("Erro");
						dialogoInfo.setHeaderText("Ocorreu um erro no processamento!");
						dialogoInfo.showAndWait();
					}
				}else{
					try {
						TelaConsultModalPF tme = new TelaConsultModalPF();
						tme.setPessoaFisica(pessoa);
						tme.start(new Stage());
					}catch (Exception e) {
						LogTools.logError(e);
					}
				}
			}
		}
	}
	
	public void updateParticipationPF(Participacao pf){
		list.remove(pf.getPessoaFisica());
		updateTable(list);
		controllerParticipation.insertParticipationPF(pf.getPessoaFisica());
	}
	
	private Participacao addParticipationPF(PessoaFisica pessoaFisica){
		Participacao participation = new Participacao();
		participation.setEvento(evento);
		participation.setPessoaFisica(pessoaFisica);
		
		if(ManagerParticipacao.create(participation)){
			return participation;
		}
		return null;
	}
	
	private Participacao addParticipationPJ(PessoaJuridica pessoaJuridica){
		Participacao participation = new Participacao();
		participation.setEvento(evento);
		participation.setPessoaJuridica(pessoaJuridica);
		
		if(ManagerParticipacao.create(participation)){
			return participation;
		}
		return null;
	}
	
	@FXML
	public void clickTablePj(MouseEvent mouseEvent){
		if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
			if(mouseEvent.getClickCount() == 2){
				PessoaJuridica pessoa = tablePJ.getSelectionModel().getSelectedItem();
				if(controllerParticipation != null){
					Participacao participation = addParticipationPJ(pessoa);
					if(participation != null){
						updateParticipationPJ(participation);
						Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
						dialogoInfo.setTitle("Sucesso");
						dialogoInfo.setHeaderText("Participação adicionada com sucesso!");
						dialogoInfo.showAndWait();
					}else{
						Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
						dialogoInfo.setTitle("Erro");
						dialogoInfo.setHeaderText("Ocorreu um erro no processamento!");
						dialogoInfo.showAndWait();
					}
				}else{
					try {
						TelaConsultModalPJ tme = new TelaConsultModalPJ();
						tme.setPessoaJuridica(pessoa);
						tme.start(new Stage());
					} catch (Exception e) {
						LogTools.logError(e);
					}
				}
			}
		}
		
	}
	
	public void updateParticipationPJ(Participacao pj){
		listPj.remove(pj.getPessoaJuridica());
		updateTablePj(listPj);
		controllerParticipation.insertParticipationPJ(pj.getPessoaJuridica());
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
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public ControllerParticipation getControllerParticipation() {
		return controllerParticipation;
	}

	public void setControllerParticipation(ControllerParticipation controllerParticipation) {
		this.controllerParticipation = controllerParticipation;
		if(controllerParticipation != null){
			title.setText("Selecione com dois cliques sobre o participante ");
		}
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
