package br.convidas.front.cities.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.convidas.classes.Cidade;
import br.convidas.front.cities.controller.ControllerCities;
import br.convidas.manager.ManagerCidade;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerModalCity implements Initializable{
	
	private static final String STYLE_ERROR = "-fx-border-color:red; -fx-border-radius:4";

	@FXML private Label labelTitle;
	@FXML private TextField textName;
	@FXML private TextField textPais;
	@FXML private ComboBox<String> comboStates;
	
	private Stage stage;
	private ControllerCities cities;
	private Boolean newCity;
	private Cidade cidade;
	private List<String> states;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	public void save(){
		
		if(!validateFiedls()){
			return;
		}
		if(cidade == null){
			cidade = new Cidade();
		}
		cidade.setName(textName.getText());
		cidade.setUf(getState());
		cidade.setPais(textPais.getText());
		if(newCity){
			if(ManagerCidade.create(cidade)){
				stage.close();
				cities.messageSucess("Cidade cadastrada com sucesso!");
			}else{
				Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
				dialogoInfo.setTitle("Erro!");
				dialogoInfo.setHeaderText("Erro ao cadastrar Cidade!");
				dialogoInfo.showAndWait();
			}
		}else{
			if(ManagerCidade.update(cidade)){
				stage.close();
				cities.messageSucess("Cidade alterada com sucesso!");
			}else{
				Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
				dialogoInfo.setTitle("Erro!");
				dialogoInfo.setHeaderText("Erro ao alterar Cidade!");
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
		if(textPais.getText().isEmpty()){
			textPais.setStyle(STYLE_ERROR);
			valid = false;
		}

		return valid;
	}
	
	private void populateCity(Cidade cidade){
		textName.setText(cidade.getName());
		textPais.setText(cidade.getPais());
		comboStates.setValue(getState(cidade.getUf()));
	}
	
	private String getState(){
		String state = comboStates.getSelectionModel().getSelectedItem();
		if(state != null){
		String states[] = state.split(" - ");
			if(states.length > 1){
				return states[1];
			}
		}
		return "";
	}
	
	private String getState(String stateCity){
		
		for (String string : states) {
			String state[] = string.split(" - ");
			if(state.length > 1){
				if(stateCity.equalsIgnoreCase(state[1])){
					return string;
				}
			}
		}
		return "";
	}
	
	public void actionName(){
		textName.setStyle("");
	}
	
	public void actionPais(){
		textPais.setStyle("");
	}
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Boolean getNewCity() {
		return newCity;
	}

	public void setNewCity(Boolean newCity) {
		this.newCity = newCity;
		if(newCity){
			labelTitle.setText("Cadastrar "+ labelTitle.getText());
		}else{
			labelTitle.setText("Alterar "+ labelTitle.getText());
		}
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
		if(cidade != null){
			populateCity(cidade);
		}
	}

	public List<String> getStates() {
		return states;
	}

	public void setStates(List<String> states) {
		this.states = states;
	}

	public ControllerCities getCities() {
		return cities;
	}

	public void setCities(ControllerCities cities) {
		this.cities = cities;
		states = new ArrayList<>();
		states.add("Sem estado");
		states.add("Acre - AC");
		states.add("Alagoas - AL");
		states.add("Amapá - AP");
		states.add("Amazonas - AM");
		states.add("Bahia - BA");
		states.add("Ceará - CE");
		states.add("Distrito Federal - DF");
		states.add("Espírito Santo - ES");
		states.add("Goiás - GO");
		states.add("Maranhão - MA");
		states.add("Mato Grosso - MT");
		states.add("Mato Grosso do Sul - MS");
		states.add("Minas Gerais - MG");
		states.add("Pará - PA");
		states.add("Paraíba - PB");
		states.add("Paraná - PR");
		states.add("Pernambuco - PE");
		states.add("Piauí - PI");
		states.add("Rio de Janeiro - RJ");
		states.add("Rio Grande do Norte - RN");
		states.add("Rio Grande do Sul - RS");
		states.add("Rondônia - RO");
		states.add("Roraima - RR");
		states.add("Santa Catarina - SC");
		states.add("São Paulo - SP");
		states.add("Sergipe - SE");
		states.add("Tocantins - TO");
		
		comboStates.getItems().addAll(states);
		
	}
	
}
