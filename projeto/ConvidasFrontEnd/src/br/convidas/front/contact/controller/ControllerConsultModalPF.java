package br.convidas.front.contact.controller;

import br.convidas.classes.PessoaFisica;
import br.convidas.front.contact.ocorrency.TelaOcorrencys;
import br.convidas.tools.log.LogTools;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerConsultModalPF{
	
	@FXML private TextField textEmail;
	@FXML private TextField textCpf;
	@FXML private TextField textRua;
	@FXML private TextField textNumero;
	@FXML private TextField textBairro;
	@FXML private TextField textCep;
	@FXML private TextField textCidade;
	@FXML private TextField textTelephone;
	@FXML private TextField textNewsletter;
	@FXML private TextField textRelacao;
	@FXML private TextArea textHistorico;
	@FXML private Label labelName;
	
	private PessoaFisica pessoa;
	
	public void close(){
		
	}
	
	public void clickOcorrencys(){
		try{
			TelaOcorrencys to = new TelaOcorrencys();
			to.setPessoaFisica(pessoa);
			to.start(new Stage());
		}catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	private void populatePessoa(PessoaFisica pessoa){
		labelName.setText(pessoa.getName());
		textCpf.setText(pessoa.getCpf());
		textEmail.setText(pessoa.getEmail());
		textRua.setText(pessoa.getRua());
		textNumero.setText(pessoa.getNumero());
		textBairro.setText(pessoa.getBairro());
		textCep.setText(pessoa.getCep());
		textTelephone.setText(pessoa.getTelefone());
		textHistorico.setText(pessoa.getHistorico());
		if(pessoa.getCidade() != null){
			textCidade.setText(pessoa.getCidade().getName());
		}
		textRelacao.setText(pessoa.getRelacao());
		String newsletter = "Não";
		if(pessoa.getNewsletter()){
			newsletter = "Sim";
		}
		textNewsletter.setText(newsletter);
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
}
