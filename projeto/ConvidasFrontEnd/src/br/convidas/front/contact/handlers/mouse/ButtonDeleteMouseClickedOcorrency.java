package br.convidas.front.contact.handlers.mouse;

import java.text.SimpleDateFormat;

import br.convidas.classes.OcorrencyPF;
import br.convidas.classes.OcorrencyPJ;
import br.convidas.front.contact.ocorrency.controller.ControllerOcorrencys;
import br.convidas.manager.ManagerOcorrencyPF;
import br.convidas.manager.ManagerOcorrencyPJ;
import br.convidas.tools.log.LogTools;
import fx.tools.action.EventAction;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ButtonDeleteMouseClickedOcorrency implements EventAction{
	
	private OcorrencyPF ocorrency;
	private OcorrencyPJ ocorrencyPJ;
	private ControllerOcorrencys telaOcorrencys;
	
	@Override
	public void action() {
		try {
			final ButtonType btnSim = new ButtonType("Sim");
			final ButtonType btnNao = new ButtonType("Não");

			SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy");
			String date = "";
			if(ocorrency != null){
				if(ocorrency.getDate() == null){
					date="";
				}else{
					date = sdFormat.format(ocorrency.getDate());
				}
			}else{
				if(ocorrencyPJ.getDate() == null){
					date="";
				}else{
					date = sdFormat.format(ocorrencyPJ.getDate());
				}
			}
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "Você deseja excluir a ocorrencia do dia "+date+"?", btnSim, btnNao);
			alert.setTitle("Alerta!");
			alert.getButtonTypes().setAll(btnSim, btnNao);
			alert.showAndWait();

			if (alert.getResult() == btnSim) {
				if(ocorrency != null){
					if(ManagerOcorrencyPF.delete(ocorrency)){
						Alert dialog = new Alert(Alert.AlertType.INFORMATION);
						dialog.setTitle("Sucesso!");
						dialog.setHeaderText("Ocorrência excluída com sucesso");
						dialog.showAndWait();
						telaOcorrencys.updateTablePF(null);
					}else{
						Alert dialog = new Alert(Alert.AlertType.ERROR);
						dialog.setTitle("Erro!");
						dialog.setHeaderText("Erro ao excluir ocorrencia!");
						dialog.showAndWait();
					}
				}else{
					if(ManagerOcorrencyPJ.delete(ocorrencyPJ)){
						Alert dialog = new Alert(Alert.AlertType.INFORMATION);
						dialog.setTitle("Sucesso!");
						dialog.setHeaderText("Ocorrência excluída com sucesso");
						dialog.showAndWait();
						telaOcorrencys.updateTablePJ(null);
					}else{
						Alert dialog = new Alert(Alert.AlertType.ERROR);
						dialog.setTitle("Erro!");
						dialog.setHeaderText("Erro ao excluir ocorrencia!");
						dialog.showAndWait();
					}
				}
			}
		} catch (Exception e) {
			LogTools.logError(e);
		}
	}

	public OcorrencyPF getOcorrency() {
		return ocorrency;
	}

	public void setOcorrency(OcorrencyPF ocorrency) {
		this.ocorrency = ocorrency;
	}

	public ControllerOcorrencys getTelaOcorrencys() {
		return telaOcorrencys;
	}

	public void setTelaOcorrencys(ControllerOcorrencys telaOcorrencys) {
		this.telaOcorrencys = telaOcorrencys;
	}

	public OcorrencyPJ getOcorrencyPJ() {
		return ocorrencyPJ;
	}

	public void setOcorrencyPJ(OcorrencyPJ ocorrencyPJ) {
		this.ocorrencyPJ = ocorrencyPJ;
	}
	

}
