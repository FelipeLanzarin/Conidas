package br.convidas.front.contact.handlers.mouse;

import br.convidas.classes.OcorrencyPF;
import br.convidas.classes.OcorrencyPJ;
import br.convidas.front.contact.ocorrency.TelaModalOcorrency;
import br.convidas.front.contact.ocorrency.controller.ControllerOcorrencys;
import br.convidas.tools.log.LogTools;
import fx.tools.action.EventAction;
import javafx.stage.Stage;

public class ButtonEditMouseClickedOcorrency implements EventAction{
	
	private OcorrencyPF ocorrencyPF;
	private OcorrencyPJ ocorrencyPJ;
	private ControllerOcorrencys telaOcorrency;
	private Boolean isPF;
	private Boolean isPJ;
	
	@Override
	public void action() {
		try {
			TelaModalOcorrency tme = new TelaModalOcorrency();
			tme.setOcorrencyPF(ocorrencyPF);
			tme.setOcorrencyPJ(ocorrencyPJ);
//			tme.setControllerOcorrencys(telaOcorrency);
			tme.setIsPF(isPF);
			tme.setIsPJ(isPJ);
			tme.start(new Stage());
		} catch (Exception e) {
			LogTools.logError(e);
		}
	}

	public OcorrencyPF getOcorrencyPF() {
		return ocorrencyPF;
	}

	public void setOcorrencyPF(OcorrencyPF ocorrencyPF) {
		this.ocorrencyPF = ocorrencyPF;
		setIsPF(true);
		setIsPJ(false);
	}

	public ControllerOcorrencys getTelaOcorrency() {
		return telaOcorrency;
	}

	public void setTelaOcorrency(ControllerOcorrencys telaOcorrency) {
		this.telaOcorrency = telaOcorrency;
	}

	public OcorrencyPJ getOcorrencyPJ() {
		return ocorrencyPJ;
	}

	public void setOcorrencyPJ(OcorrencyPJ ocorrencyPJ) {
		this.ocorrencyPJ = ocorrencyPJ;
		setIsPF(false);
		setIsPJ(true);
	}

	public Boolean getIsPF() {
		return isPF;
	}

	public void setIsPF(Boolean isPF) {
		this.isPF = isPF;
	}

	public Boolean getIsPJ() {
		return isPJ;
	}

	public void setIsPJ(Boolean isPJ) {
		this.isPJ = isPJ;
	}

}
