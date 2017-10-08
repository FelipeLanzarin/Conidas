package br.convidas.manager;

import java.util.List;

import br.convidas.banco.ParticipacaoDao;
import br.convidas.classes.Evento;
import br.convidas.classes.Participacao;
import br.convidas.classes.PessoaFisica;
import br.convidas.classes.PessoaJuridica;

public class ManagerParticipacao {
	
	private static ParticipacaoDao cd;
	
	public static Boolean update(Participacao participacao) {
		return Boolean.valueOf(getParticipacaoDao().updateParticipacao(participacao));
	}

	public static Boolean create(Participacao participacao) {
		return Boolean.valueOf(getParticipacaoDao().insereParticipacao(participacao));
	}

	public static Boolean delete(Participacao participacao) {
		return Boolean.valueOf(getParticipacaoDao().deleteParticipacao(participacao.getId()));
	}
	
	public static List<Participacao> getParticipacaos() {
		return getParticipacaoDao().getParticipacoes();
	}
	
	public static List<Participacao> getParticipacaoOfEvent(Evento evento) {
		return getParticipacaoDao().getParticipacaoOfEvent(evento.getId());
	}
	
	public static List<Participacao> getParticipacaoOfPJ(PessoaJuridica pj) {
		return getParticipacaoDao().getParticipacaoOfPJ(pj.getId());
	}
	
	public static List<Participacao> getParticipacaoOfPF(PessoaFisica pf) {
		return getParticipacaoDao().getParticipacaoOfPF(pf.getId());
	}
	
	private static ParticipacaoDao getParticipacaoDao(){
		if(cd == null){
			cd = new ParticipacaoDao();
		}
		return cd;
	}
	
}
