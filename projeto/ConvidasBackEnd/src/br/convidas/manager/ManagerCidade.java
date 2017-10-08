package br.convidas.manager;

import java.util.List;

import br.convidas.banco.CidadeDao;
import br.convidas.classes.Cidade;

public class ManagerCidade {
	
	private static CidadeDao cd;
	
	public static Boolean update(Cidade cidade) {
		return Boolean.valueOf(getCidadeDao().updateCidade(cidade));
	}

	public static Boolean create(Cidade cidade) {
		return Boolean.valueOf(getCidadeDao().insereCidade(cidade));
	}

	public static Boolean delete(Cidade cidade) {
		return Boolean.valueOf(getCidadeDao().deleteCidade(cidade.getId()));
	}
	
	
	public static List<Cidade> getCidades() {
		return getCidadeDao().getCidades();
	}
	
	private static CidadeDao getCidadeDao(){
		if(cd == null){
			cd = new CidadeDao();
		}
		return cd;
	}
	
}
