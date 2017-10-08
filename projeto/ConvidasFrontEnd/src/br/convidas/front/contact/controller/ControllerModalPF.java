package br.convidas.front.contact.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.convidas.classes.Cidade;
import br.convidas.classes.PessoaFisica;
import br.convidas.front.contact.cities.TelaCities;
import br.convidas.manager.ManagerPF;
import br.convidas.tools.log.LogTools;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ControllerModalPF implements Initializable{
	
	private static final String STYLE_ERROR = "-fx-border-color:red; -fx-border-radius:4";
	
	@FXML private TextField textName;
	@FXML private TextField textEmail;
	@FXML private TextField textCpf;
	@FXML private ToggleGroup groupN;
	@FXML private RadioButton radioNYes;
	@FXML private RadioButton radioNNot;
	@FXML private TextField textRua;
	@FXML private TextField textNumero;
	@FXML private TextField textBairro;
	@FXML private TextField textCep;
	@FXML private TextField textCidade;
	@FXML private TextField textTelephone;
	@FXML private TextArea textHistorico;
	@FXML private RadioButton radioBols;
	@FXML private RadioButton radioPad;
	@FXML private RadioButton radioCol;
	@FXML private RadioButton radioAmg;
	@FXML private ToggleGroup groupR;
	@FXML private Label labelTitle;
	
	private Stage stage;
	private ControllerContacts contacts;
	private Boolean newPF;
	private PessoaFisica pessoa;
	private Cidade cidadeSelect;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void save(){
		
		if(!validateFiedls()){
			return;
		}
		if(pessoa == null){
			pessoa = new PessoaFisica();
		}
		pessoa.setName(textName.getText());
		pessoa.setCpf(textCpf.getText());
		pessoa.setEmail(textEmail.getText());
		RadioButton radioSelect = (RadioButton) groupN.getSelectedToggle();
		if("true".equals(radioSelect.getId())){
			pessoa.setNewsletter(true);
		}else{
			pessoa.setNewsletter(false);
		}
		pessoa.setRua(textRua.getText());
		pessoa.setNumero(textNumero.getText());
		pessoa.setBairro(textBairro.getText());
		pessoa.setCep(textCep.getText());
		pessoa.setTelefone(textTelephone.getText());
		pessoa.setHistorico(textHistorico.getText());
		pessoa.setCidade(cidadeSelect);
		radioSelect = (RadioButton) groupR.getSelectedToggle();
		switch (radioSelect.getId()) {
		case "bolsista":
			pessoa.setRelacao("Bolsista");
			break;
		case "padrinho":
			pessoa.setRelacao("Padrinho");
			break;
		case "colaborador":
			pessoa.setRelacao("Colaborador");
			break;
		case "amigo":
			pessoa.setRelacao("Amigo");
			break;

		}
		
		if(newPF){
			if(ManagerPF.create(pessoa)){
				stage.close();
				contacts.messageSucess("Pessoa cadastrada com sucesso!");
			}else{
				Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
				dialogoInfo.setTitle("Erro!");
				dialogoInfo.setHeaderText("Erro ao cadastrar pessoa física!");
				dialogoInfo.showAndWait();
			}
		}else{
			if(ManagerPF.update(pessoa)){
				stage.close();
				contacts.messageSucess("Pessoa alterada com sucesso!");
			}else{
				Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
				dialogoInfo.setTitle("Erro!");
				dialogoInfo.setHeaderText("Erro ao alterar pessoa física!");
				dialogoInfo.showAndWait();
			}
		}
	}
	
	private Boolean validateFiedls(){
		
		Boolean valid = true;
		if(textName.getText().isEmpty()){
			textName.setStyle(STYLE_ERROR);
			valid = false;
		}
//		if(textCpf.getText().isEmpty()){
//			textCpf.setStyle(STYLE_ERROR);
//			valid = false;
//		}
//		if(textEmail.getText().isEmpty()){
//			textEmail.setStyle(STYLE_ERROR);
//			valid = false;
//		}
		return valid;
	}
	
	private void populatePessoa(PessoaFisica pessoa){
		textName.setText(pessoa.getName());
		textCpf.setText(pessoa.getCpf());
		textEmail.setText(pessoa.getEmail());
		if(pessoa.getNewsletter()){
			radioNYes.setSelected(true);
		}else{
			radioNNot.setSelected(true);
		}
		textRua.setText(pessoa.getRua());
		textNumero.setText(pessoa.getNumero());
		textBairro.setText(pessoa.getBairro());
		textCep.setText(pessoa.getCep());
		textTelephone.setText(pessoa.getTelefone());
		textHistorico.setText(pessoa.getHistorico());
		if(pessoa.getCidade() != null){
			textCidade.setText(pessoa.getCidade().getName());
		}
		String relacao = pessoa.getRelacao();
		switch (relacao) {
			case "Bolsista":
				radioBols.setSelected(true);
				break;
			case "Padrinho":
				radioPad.setSelected(true);
				break;
			case "Colaborador":
				radioCol.setSelected(true);
				break;
			case "Amigo":
				radioAmg.setSelected(true);
				break;

		}
	}
	
	public void actionName(){
		textName.setStyle("");
	}
	
	public void actionCpf(){
		textCpf.setStyle("");
	}
	
	public void actionEmail(){
		textEmail.setStyle("");
	}
	
	public void openCities(){
		try {
			TelaCities tc = new TelaCities();
			tc.setControllerModalPF(this);
			tc.start(new Stage());
		} catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public ControllerContacts getContacts() {
		return contacts;
	}
	public void setContacts(ControllerContacts contacts) {
		this.contacts = contacts;
	}
	public Boolean getNewPF() {
		return newPF;
	}
	public void setNewPF(Boolean newPF) {
		this.newPF = newPF;
		if(newPF){
			labelTitle.setText("Cadastrar "+ labelTitle.getText());
		}else{
			labelTitle.setText("Alterar "+ labelTitle.getText());
		}
	}
	public PessoaFisica getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaFisica pessoa) {
		this.pessoa = pessoa;
		if(pessoa != null){
			populatePessoa(pessoa);
		}
	}

	public Cidade getCidadeSelect() {
		return cidadeSelect;
	}

	public void setCidadeSelect(Cidade cidadeSelect) {
		this.cidadeSelect = cidadeSelect;
		textCidade.setText(cidadeSelect.getName());
	}
	
}
