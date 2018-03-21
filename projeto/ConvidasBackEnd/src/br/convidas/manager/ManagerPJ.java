package br.convidas.manager;

import java.util.List;

import br.convidas.banco.PessoaJuridicaDao;
import br.convidas.classes.Evento;
import br.convidas.classes.PessoaFisica;
import br.convidas.classes.PessoaJuridica;

public class ManagerPJ {
	
	private static PessoaJuridicaDao pjd;
	
	public static Boolean update(PessoaJuridica pessoa) {
		return Boolean.valueOf(getPessoaJuridicaDao().updatePessoaJuridica(pessoa));
	}

	public static Boolean create(PessoaJuridica pessoa) {
		return Boolean.valueOf(getPessoaJuridicaDao().inserePessoaJuridica(pessoa));
	}

	public static Boolean delete(PessoaJuridica pessoa) {
		return Boolean.valueOf(getPessoaJuridicaDao().deletePessoaJuridica(pessoa.getId()));
	}
	
	public static List<PessoaJuridica> getPessoaJuridicas(String param, String param2) {
		return getPessoaJuridicaDao().getPessoaJuridicas(param, param2);
	}
	public static List<PessoaJuridica> getPessoaJuridicasOthers (){
		String querySQL = "SELECT * FROM pessoa_juridica WHERE name ~ E'^[^a-zA-Z].*';";
		return getPessoaJuridicaDao().getPessoaJuridicasOthers(querySQL);
	}
	
	public static List<PessoaJuridica> getPessoaJuridicas() {
		return getPessoaJuridicaDao().getPessoaJuridicas();
	}
	
	/**
	 * Responsavel por trazer as pessoas que nao participam do envento enviado por parametro
	 * @param event
	 * @return
	 */
	public static List<PessoaJuridica> getPessoaJuridicasForParticipation(Evento event) {
		return getPessoaJuridicaDao().getPessoaJuridicas();
	}
	
	private static PessoaJuridicaDao getPessoaJuridicaDao(){
		if(pjd == null){
			pjd = new PessoaJuridicaDao();
		}
		return pjd;
	}
	
}
