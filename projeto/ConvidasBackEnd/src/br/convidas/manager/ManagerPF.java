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
	
	public static List<PessoaFisica> getPessoasFisicas(String param, String param2) {
		return getPessoaFisicaDao().getPessoaFisicas(param, param2);
	}
	
	public static List<PessoaFisica> getPessoasFisicasByRelation(String param) {
		return getPessoaFisicaDao().getPessoaFisicasByRelation(param);
	}
	
	public static List<PessoaFisica> getPessoaFisicasByNewsletter(boolean param) {
		return getPessoaFisicaDao().getPessoaFisicasByNewsletter(param);
	}
	
	public static List<PessoaFisica> getPessoaFisicasOthers (){
		String querySQL = "SELECT * FROM pessoa_fisica WHERE name ~ E'^[^a-zA-Z].*';";
		return getPessoaFisicaDao().getPessoaFisicasOthers(querySQL);
	}
	
	private static PessoaFisicaDao getPessoaFisicaDao(){
		if(pfd == null){
			pfd = new PessoaFisicaDao();
		}
		return pfd;
	}
	
}
