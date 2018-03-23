package br.convidas.manager;

import java.util.List;

import br.convidas.banco.ParticipacaoDao;
import br.convidas.classes.Evento;
import br.convidas.classes.Participacao;
import br.convidas.classes.PessoaFisica;
import br.convidas.classes.PessoaJuridica;

public class ManagerParticipacao {
	
	private static final String QUERY_PJ_EVENT ="select * from pessoa_juridica pj1"
			+ " where  pj1.id not in("
			+ "	select pj.id from pessoa_juridica pj, participacao"
			+ "	where pj.id = participacao.pj_id"
			+ "	and participacao.event_id = :id"
			+ ") order by name";
	private static final String QUERY_PJ_EVENT_OTHER ="select * from pessoa_juridica pj1"
			+ " where  name ~ E'^[^a-zA-Z].*'"
			+ " and pj1.id not in("
			+ "	select pj.id from pessoa_juridica pj, participacao"
			+ "	where pj.id = participacao.pj_id"
			+ "	and participacao.event_id = :id"
			+ ") order by name";
	private static final String QUERY_PJ_EVENT_LIKE ="select * from pessoa_juridica pj1"
			+ " where  (pj1.name like :param or pj1.name like :param2) "
			+ " and pj1.id not in("
			+ "	select pj.id from pessoa_juridica pj, participacao"
			+ "	where pj.id = participacao.pj_id"
			+ "	and participacao.event_id = :id"
			+ ") order by name";
	private static final String QUERY_PF_EVENT ="select * from pessoa_fisica pf1"
			+ " where  pf1.id not in("
			+ "	select pf.id from pessoa_fisica pf, participacao"
			+ "	where pf.id = participacao.pf_id"
			+ "	and participacao.event_id = :id"
			+ ") order by name";
	private static final String QUERY_PF_EVENT_OTHER ="select * from pessoa_fisica pf1"
			+ " where name ~ E'^[^a-zA-Z].*'"
			+ " and pf1.id not in("
			+ "	select pf.id from pessoa_fisica pf, participacao"
			+ "	where pf.id = participacao.pf_id"
			+ "	and participacao.event_id = :id"
			+ ") order by name";
	private static final String QUERY_PF_EVENT_LIKE ="select * from pessoa_fisica pf1"
			+ " where (pf1.name like :param or pf1.name like :param2) "
			+ " and   pf1.id not in("
			+ "	select pf.id from pessoa_fisica pf, participacao"
			+ "	where pf.id = participacao.pf_id"
			+ "	and participacao.event_id = :id"
			+ ") order by name";
	private static final String QUERY_PART_PJ_EVENT ="select pj.* from pessoa_juridica pj, participacao"
			+ "	where pj.id = participacao.pj_id"
			+ "	and participacao.event_id = :id"
			+ " order by name";	
	private static final String QUERY_PART_PF_EVENT ="select pf.* from pessoa_fisica pf, participacao"
			+ "	where pf.id = participacao.pf_id"
			+ "	and participacao.event_id = :id order by name";	
	
	private static final String QUERY_PART_PF_EVENT_UNIQUE ="select p.* from participacao p"
			+ "	where :id = p.pf_id"
			+ "	and p.event_id = :eventId";	
	private static final String QUERY_PART_PJ_EVENT_UNIQUE ="select p.* from participacao p"
			+ "	where :id = p.pj_id"
			+ "	and p.event_id = :eventId";	

	
	private static ParticipacaoDao cd;

	public static Boolean create(Participacao participacao) {
		return Boolean.valueOf(getParticipacaoDao().insereParticipacao(participacao));
	}

	public static Boolean delete(Participacao participacao) {
		return Boolean.valueOf(getParticipacaoDao().deleteParticipacao(participacao.getId()));
	}
	
	public static List<Participacao> getParticipacaos() {
		return getParticipacaoDao().getParticipacoes();
	}
	
	/**
	 * Metodo responsavel por retornar as pessoas que nao participam do evento
	 * @param evento
	 * @return
	 */
	public static List<PessoaJuridica> getPossibleParticipacaoPJOfEvent(Evento evento) {
		return getParticipacaoDao().getPossibleParticipacaoPJOfEvent(evento.getId(), QUERY_PJ_EVENT, null, null);
	}
	
	public static List<PessoaJuridica> getPossibleParticipacaoPJOfEvent(Evento evento, String param, String param2) {
		return getParticipacaoDao().getPossibleParticipacaoPJOfEvent(evento.getId(), QUERY_PJ_EVENT_LIKE, param, param2);
	}
	
	public static List<PessoaJuridica> getPossibleParticipacaoPJOfEventOther(Evento evento) {
		return getParticipacaoDao().getPossibleParticipacaoPJOfEvent(evento.getId(), QUERY_PJ_EVENT_OTHER, null, null);
	}
	
	/**
	 * Metodo responsavel por retornar as pessoas que nao participam do evento
	 * @param evento
	 * @return
	 */
	public static List<PessoaFisica> getPossibleParticipacaoPFOfEvent(Evento evento) {
		return getParticipacaoDao().getPossibleParticipacaoPFOfEvent(evento.getId(), QUERY_PF_EVENT, null, null);
	}
	
	public static List<PessoaFisica> getPossibleParticipacaoPFOfEvent(Evento evento, String param, String param2) {
		return getParticipacaoDao().getPossibleParticipacaoPFOfEvent(evento.getId(), QUERY_PF_EVENT_LIKE, param, param2);
	}
	
	public static List<PessoaFisica> getPossibleParticipacaoPFOfEventOther(Evento evento) {
		return getParticipacaoDao().getPossibleParticipacaoPFOfEvent(evento.getId(), QUERY_PF_EVENT_OTHER, null, null);
	}
	
	/**
	 * Metodo responsavel por retornar as pessoas que participam do evento
	 * @param evento
	 * @return
	 */
	public static List<PessoaJuridica> getParticipacaoPJOfEvent(Evento evento) {
		return getParticipacaoDao().getPossibleParticipacaoPJOfEvent(evento.getId(), QUERY_PART_PJ_EVENT, null, null);
	}
	
	/**
	 * Metodo responsavel por retornar as pessoas que  participam do evento
	 * @param evento
	 * @return
	 */
	public static List<PessoaFisica> getParticipacaoPFOfEvent(Evento evento) {
		return getParticipacaoDao().getPossibleParticipacaoPFOfEvent(evento.getId(), QUERY_PART_PF_EVENT, null, null);
	}
	
	/**
	 * Metodo responsavel por retornar as participacoes de uma pessoa juridica
	 * @param pj
	 * @return
	 */
	public static List<Participacao> getParticipacaoOfPJ(PessoaJuridica pj) {
		return getParticipacaoDao().getParticipacaoOfPJ(pj.getId());
	}
	
	/**
	 * Metodo responsavel por retornar as participacoes de uma pessoa fisica
	 * @param pf
	 * @return
	 */
	public static List<Participacao> getParticipacaoOfPF(PessoaFisica pf) {
		return getParticipacaoDao().getParticipacaoOfPF(pf.getId());
	}
	
	/**
	 * Metodo responsavel por retornar a participacoe de uma pessoa fisica em um determinado evento
	 * @param pf
	 * @return
	 */
	public static Participacao getParticipacaoOfPFAndEvent(PessoaFisica pf, Evento event) {
		return getParticipacaoDao().getParticipacaoOfPFAndEvent(pf.getId(), event.getId(), QUERY_PART_PF_EVENT_UNIQUE);
	}
	

	/**
	 * Metodo responsavel por retornar a participacoe de uma pessoa juridica em um determinado evento
	 * @param pf
	 * @return
	 */
	public static Participacao getParticipacaoOfPJAndEvent(PessoaJuridica pj, Evento event) {
		return getParticipacaoDao().getParticipacaoOfPFAndEvent(pj.getId(), event.getId(), QUERY_PART_PJ_EVENT_UNIQUE);
	}
	
	private static ParticipacaoDao getParticipacaoDao(){
		if(cd == null){
			cd = new ParticipacaoDao();
		}
		return cd;
	}
	
}
