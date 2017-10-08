package br.convidas.front.contact.handlers.mouse;

import br.convidas.classes.Cidade;
import br.convidas.front.cities.controller.ControllerCities;
import br.convidas.manager.ManagerCidade;
import br.convidas.tools.log.LogTools;
import fx.tools.action.EventAction;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ButtonDeleteMouseClickedCity implements EventAction{
	
	private Cidade cidade;
	private ControllerCities telaCities;
	
	@Override
	public void action() {
		try {
			final ButtonType btnSim = new ButtonType("Sim");
			final ButtonType btnNao = new ButtonType("Não");
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "Você deseja excluir a cidade "+ cidade.getName()+"?", btnSim, btnNao);
			alert.showAndWait();

			if (alert.getResult() == btnSim) {
				if(ManagerCidade.delete(cidade)){
					Alert dialog = new Alert(Alert.AlertType.INFORMATION);
					dialog.setTitle("Sucesso!");
					dialog.setHeaderText("Cidade excluída com sucesso");
					dialog.showAndWait();
					telaCities.updateTablePF(null);
				}else{
					Alert dialog = new Alert(Alert.AlertType.ERROR);
					dialog.setTitle("Erro!");
					dialog.setHeaderText("Cidade já possui uma pessoa!");
					dialog.showAndWait();
				}
			}
		} catch (Exception e) {
			LogTools.logError(e);
		}
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public ControllerCities getTelaCities() {
		return telaCities;
	}

	public void setTelaCities(ControllerCities telaCities) {
		this.telaCities = telaCities;
	}


}
