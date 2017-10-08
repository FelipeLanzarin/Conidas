package br.convidas.manager;

import java.util.List;

import br.convidas.banco.OcorrencyPFDao;
import br.convidas.classes.OcorrencyPF;
import br.convidas.classes.PessoaFisica;

public class ManagerOcorrencyPF {
	
	private static OcorrencyPFDao ocorrencyPF;
	
	public static Boolean update(OcorrencyPF ocorrencyPF) {
		return Boolean.valueOf(getOcorrencyPFDao().updateOcorrencyPF(ocorrencyPF));
	}

	public static Boolean create(OcorrencyPF ocorrencyPF) {
		return Boolean.valueOf(getOcorrencyPFDao().insereOcorrencyPF(ocorrencyPF));
	}

	public static Boolean delete(OcorrencyPF ocorrencyPF) {
		return Boolean.valueOf(getOcorrencyPFDao().deleteOcorrencyPF(ocorrencyPF.getId()));
	}
	
	public static List<OcorrencyPF> getOcorrencyPF() {
		return getOcorrencyPFDao().getOcorrencyPFs();
	}
	
	public static List<OcorrencyPF> getOcorrencyByPF(PessoaFisica pf){
		String sql = "select * from ocorrency_pf o "
				+ "where o.pf_id = ? "
				+ "order by date";
		return getOcorrencyPFDao().getOcorrencyPFsByPessoa(pf, sql);
				
	}
	
	private static OcorrencyPFDao getOcorrencyPFDao(){
		if(ocorrencyPF == null){
			ocorrencyPF = new OcorrencyPFDao();
		}
		return ocorrencyPF;
	}
	
}
