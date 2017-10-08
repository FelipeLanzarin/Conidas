package br.convidas.manager;

import java.util.List;

import br.convidas.banco.PessoaJuridicaDao;
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
	
	public static List<PessoaJuridica> getPessoaJuridicas() {
		return getPessoaJuridicaDao().getPessoaJuridicas();
	}
	
	private static PessoaJuridicaDao getPessoaJuridicaDao(){
		if(pjd == null){
			pjd = new PessoaJuridicaDao();
		}
		return pjd;
	}
	
}
