package br.convidas.front.event.handlers.mouse;

import br.convidas.classes.Evento;
import br.convidas.classes.Participacao;
import br.convidas.classes.PessoaFisica;
import br.convidas.classes.PessoaJuridica;
import br.convidas.front.event.participation.controller.ControllerParticipation;
import br.convidas.manager.ManagerParticipacao;
import br.convidas.tools.log.LogTools;
import fx.tools.action.EventAction;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ButtonDeleteMouseClickedParticipation implements EventAction{
	
	private Evento evento;
	private PessoaFisica pessoaFisica;
	private PessoaJuridica pessoaJuridica;
	private ControllerParticipation controllerParticipation;
	
	@Override
	public void action() {
		try {
			final ButtonType btnSim = new ButtonType("Sim");
			final ButtonType btnNao = new ButtonType("Não");
			String name = "";
			Boolean pf = true;
			if(pessoaFisica!= null){
				name = pessoaFisica.getName();
			}else{
				name = pessoaJuridica.getName();
				pf = false;
			}
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "Você deseja remover o participante "+ name+" deste evento?", btnSim, btnNao);
			alert.showAndWait();

			if (alert.getResult() == btnSim) {
				Participacao participation = null;
				if(pf){
					participation = ManagerParticipacao.getParticipacaoOfPFAndEvent(pessoaFisica, evento);
				}else{
					participation = ManagerParticipacao.getParticipacaoOfPJAndEvent(pessoaJuridica, evento);
				}
				
				if(participation != null && ManagerParticipacao.delete(participation)){
					Alert dialog = new Alert(Alert.AlertType.INFORMATION);
					dialog.setTitle("Sucesso!");
					dialog.setHeaderText("Participante removido com sucesso");
					dialog.showAndWait();
					if(pf){
						controllerParticipation.removeParticipationPF(pessoaFisica);
					}else{
						controllerParticipation.removeParticipationPJ(pessoaJuridica);
					}
				}else{
					Alert dialog = new Alert(Alert.AlertType.ERROR);
					dialog.setTitle("Erro!");
					dialog.setHeaderText("Erro ao remover participante!");
					dialog.showAndWait();
				}
			}
		} catch (Exception e) {
			LogTools.logError(e);
		}
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	public ControllerParticipation getControllerParticipation() {
		return controllerParticipation;
	}

	public void setControllerParticipation(ControllerParticipation controllerParticipation) {
		this.controllerParticipation = controllerParticipation;
	}
	
}
