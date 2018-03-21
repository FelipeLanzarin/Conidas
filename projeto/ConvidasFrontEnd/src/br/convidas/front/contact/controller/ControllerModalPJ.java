package br.convidas.front.contact.controller;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import br.convidas.classes.Cidade;
import br.convidas.classes.PessoaJuridica;
import br.convidas.front.contact.cities.TelaCities;
import br.convidas.manager.ManagerPJ;
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

public class ControllerModalPJ implements Initializable{
	
	private static final String STYLE_ERROR = "-fx-border-color:red; -fx-border-radius:4";
	
	@FXML private TextField textName;
	@FXML private TextField textEmail;
	@FXML private TextField textCnpj;
	@FXML private ToggleGroup groupN;
	@FXML private RadioButton radioNYes;
	@FXML private RadioButton radioNNot;
	@FXML private TextField textRua;
	@FXML private TextField textNumero;
	@FXML private TextField textBairro;
	@FXML private TextField textCep;
	@FXML private TextField textCidade;
	@FXML private TextField textTelephone;
	@FXML private TextField textResponsavel;
	@FXML private TextArea textHistorico;
	@FXML private TextArea textContrib;
	@FXML private Label labelTitle;
	
	private Stage stage;
	private ControllerContactsRelationManager contacts;
	private Boolean newPJ;
	private PessoaJuridica pessoa;
	private Cidade cidadeSelect;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void save(){
		
		if(!validateFiedls()){
			return;
		}
		if(pessoa == null){
			pessoa = new PessoaJuridica();
		}
		pessoa.setName(textName.getText());
		pessoa.setCnpj(textCnpj.getText());
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
		pessoa.setResponsavel(textResponsavel.getText());
		pessoa.setCidade(cidadeSelect);
		pessoa.setContribuicao(textContrib.getText());
		
		if(newPJ){
			pessoa.setCreationDate(new Date());
			if(ManagerPJ.create(pessoa)){
				stage.close();
				contacts.messageSucessPJ("Pessoa cadastrada com sucesso!");
			}else{
				Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
				dialogoInfo.setTitle("Erro!");
				dialogoInfo.setHeaderText("Erro ao cadastrar pessoa física!");
				dialogoInfo.showAndWait();
			}
		}else{
			if(ManagerPJ.update(pessoa)){
				stage.close();
				contacts.messageSucessPJ("Pessoa alterada com sucesso!");
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
//		if(textCnpj.getText().isEmpty()){
//			textCnpj.setStyle(STYLE_ERROR);
//			valid = false;
//		}
//		if(textEmail.getText().isEmpty()){
//			textEmail.setStyle(STYLE_ERROR);
//			valid = false;
//		}
		
		if(textResponsavel.getText().isEmpty()){
			textResponsavel.setStyle(STYLE_ERROR);
			valid = false;
		}
		return valid;
	}
	
	private void populatePessoa(PessoaJuridica pessoa){
		textName.setText(pessoa.getName());
		textCnpj.setText(pessoa.getCnpj());
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
		textResponsavel.setText(pessoa.getResponsavel());
		textContrib.setText(pessoa.getContribuicao());
		if(pessoa.getCidade() != null){
			textCidade.setText(pessoa.getCidade().getName());
		}
	}
	
	public void actionName(){
		textName.setStyle("");
	}
	
	public void actionCnpj(){
		textCnpj.setStyle("");
	}
	
	public void actionEmail(){
		textEmail.setStyle("");
	}
	
	public void actionResponsavel(){
		textResponsavel.setStyle("");
	}
	public void openCities(){
		try {
			TelaCities tc = new TelaCities();
			tc.setControllerModalPJ(this);
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
	public ControllerContactsRelationManager getContacts() {
		return contacts;
	}
	public void setContacts(ControllerContactsRelationManager contacts) {
		this.contacts = contacts;
	}
	public Boolean getNewPF() {
		return newPJ;
	}
	public void setNewPF(Boolean newPJ) {
		this.newPJ = newPJ;
		if(newPJ){
			labelTitle.setText("Cadastrar "+ labelTitle.getText());
		}else{
			labelTitle.setText("Alterar "+ labelTitle.getText());
		}
	}

	public PessoaJuridica getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaJuridica pessoa) {
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
