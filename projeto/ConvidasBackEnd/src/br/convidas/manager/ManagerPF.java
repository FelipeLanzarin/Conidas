package br.convidas.manager;

import java.util.List;

import br.convidas.banco.PessoaFisicaDao;
import br.convidas.classes.PessoaFisica;

public class ManagerPF {
	
	private static PessoaFisicaDao pfd;
	
	public static Boolean update(PessoaFisica pessoa) {
		return Boolean.valueOf(getPessoaFisicaDao().updatePessoaFisica(pessoa));
	}

	public static Boolean create(PessoaFisica pessoa) {
		return Boolean.valueOf(getPessoaFisicaDao().inserePessoaFisica(pessoa));
	}

	public static Boolean delete(PessoaFisica pessoa) {
		return Boolean.valueOf(getPessoaFisicaDao().deletePessoaFisica(pessoa.getId()));
	}
	
	public static List<PessoaFisica> getPessoasFisicas() {
		return getPessoaFisicaDao().getPessoaFisicas();
	}
	
	private static PessoaFisicaDao getPessoaFisicaDao(){
		if(pfd == null){
			pfd = new PessoaFisicaDao();
		}
		return pfd;
	}
	
}
