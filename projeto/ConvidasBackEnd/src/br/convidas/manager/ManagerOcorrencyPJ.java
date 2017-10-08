package br.convidas.manager;

import java.util.List;

import br.convidas.banco.OcorrencyPJDao;
import br.convidas.classes.OcorrencyPJ;
import br.convidas.classes.PessoaJuridica;

public class ManagerOcorrencyPJ {
	
	private static OcorrencyPJDao ocorrencyPJDao;
	
	public static Boolean update(OcorrencyPJ ocorrencyPJ) {
		return Boolean.valueOf(getOcorrencyPJDao().updateOcorrencyPJ(ocorrencyPJ));
	}

	public static Boolean create(OcorrencyPJ ocorrencyPJ) {
		return Boolean.valueOf(getOcorrencyPJDao().insereOcorrencyPJ(ocorrencyPJ));
	}

	public static Boolean delete(OcorrencyPJ ocorrencyPJ) {
		return Boolean.valueOf(getOcorrencyPJDao().deleteOcorrencyPJ(ocorrencyPJ.getId()));
	}
	
	public static List<OcorrencyPJ> getOcorrencyPJ() {
		return getOcorrencyPJDao().getOcorrencyPJs();
	}
	
	public static List<OcorrencyPJ> getOcorrencyByPJ(PessoaJuridica pj){
		String sql = "select * from ocorrency_pj o "
				+ "where o.pj_id = ? "
				+ "order by date";
		return getOcorrencyPJDao().getOcorrencyPJsByPessoa(pj, sql);
				
	}
	
	private static OcorrencyPJDao getOcorrencyPJDao(){
		if(ocorrencyPJDao == null){
			ocorrencyPJDao = new OcorrencyPJDao();
		}
		return ocorrencyPJDao;
	}
	
}
