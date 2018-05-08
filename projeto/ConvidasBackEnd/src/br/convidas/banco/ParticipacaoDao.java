package br.convidas.banco;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.convidas.classes.Participacao;
import br.convidas.classes.PessoaFisica;
import br.convidas.classes.PessoaJuridica;
import br.convidas.tools.log.LogTools;

public class ParticipacaoDao {
	
	public boolean insereParticipacao (Participacao participacao){
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		try{
			em.getTransaction().begin();
			em.persist(participacao);
			em.getTransaction().commit();
		}catch (Exception e) {
			LogTools.logError("erro ao inserir participacao no banco: "+ e.toString());
			em.getTransaction().rollback();
			return false;
		}finally{
			em.close();
		}
		
		return true;
	}
	
	public boolean deleteParticipacao(Integer id){
		EntityManager em = EntityManagerUtil.getEntityManager();
		try{
			Participacao participacao = getParticipacao(id);
			if(participacao != null){
				em.getTransaction().begin();
				participacao = em.merge(participacao);
				em.remove(participacao);
				em.getTransaction().commit();
			}
		}catch (Exception e) {
			LogTools.logError("erro ao excluir : "+ e.toString());
			try{
				em.getTransaction().rollback();
			}catch (Exception ex) {
				LogTools.logError("erro ao  dar roolback: "+ e.toString());
			}
			return false;
		}finally{
			em.close();
		}
		return true;
	}
	
	public Participacao getParticipacao(Integer id){
		EntityManager em = EntityManagerUtil.getEntityManager();
		Participacao participacao = null;
		try{
			participacao = em.find(Participacao.class, id);
		}catch (Exception e) {
			LogTools.logError("erro ao obter participacoes no banco: "+ e.toString());
		}finally{
			em.close();
		}
		
		return participacao;
	}
	
	public List<Participacao> getParticipacoes (){
		EntityManager em = EntityManagerUtil.getEntityManager();
		List<Participacao> participacao = null;
		try{
			Query query = em.createQuery("FROM Participacao");
			participacao = query.getResultList();
		}catch (Exception e) {
			LogTools.logError("erro ao obter participacaos no banco: "+ e.toString());
		}finally{
			em.close();
		}
		return participacao;
	}
	
	public List<Participacao> getParticipacaoOfPF (Integer id){
		EntityManager em = EntityManagerUtil.getEntityManager();
		List<Participacao> participacao = null;
		try{
			String queryString = "FROM Participacao p WHERE p.pessoaFisica = :id ORDER BY name";
			Query query = em.createQuery(queryString);
			query.setParameter("id",id);
			participacao = query.getResultList();
		}catch (Exception e) {
			LogTools.logError("erro ao obter participacaos no banco: "+ e.toString());
		}finally{
			em.close();
		}
		return participacao;
	}
	
	public List<Participacao> getParticipacaoOfPJ (Integer id){
		EntityManager em = EntityManagerUtil.getEntityManager();
		List<Participacao> participacao = null;
		try{
			String queryString = "FROM Participacao p WHERE p.pessoaJuridica = :id ORDER BY name";
			Query query = em.createQuery(queryString);
			query.setParameter("id",id);
			participacao = query.getResultList();
		}catch (Exception e) {
			LogTools.logError("erro ao obter participacoes no banco: "+ e.toString());
		}finally{
			em.close();
		}
		return participacao;
	}	
	
	public List<PessoaJuridica> getPossibleParticipacaoPJOfEvent (Integer id, String querySQL, String param, String param2){
		EntityManager em = EntityManagerUtil.getEntityManager();
		List<PessoaJuridica> pessoas = null;
		try{
			Query query = em.createNativeQuery(querySQL, PessoaJuridica.class);
			query.setParameter("id",id);
			if(param != null && param2 != null){
				query.setParameter("param", param+"%");
				query.setParameter("param2", param2+"%");
			}
			pessoas = query.getResultList();
		}catch (Exception e) {
			LogTools.logError("erro ao obter participacoes no banco: "+ e.toString());
		}finally{
			em.close();
		}
		return pessoas;
	}
	
	public List<PessoaFisica> getPossibleParticipacaoPFOfEvent (Integer id, String querySQL,String param, String param2){
		EntityManager em = EntityManagerUtil.getEntityManager();
		List<PessoaFisica> pessoas = null;
		try{
			Query query = em.createNativeQuery(querySQL, PessoaFisica.class);
			query.setParameter("id",id);
			if(param != null && param2 != null){
				query.setParameter("param", param+"%");
				query.setParameter("param2", param2+"%");
			}
			pessoas = query.getResultList();
		}catch (Exception e) {
			LogTools.logError(e);
		}finally{
			em.close();
		}
		return pessoas;
	}
	
	public List<PessoaJuridica> getParticipacaoPJOfEvent (Integer id, String querySQL){
		EntityManager em = EntityManagerUtil.getEntityManager();
		List<PessoaJuridica> pessoas = null;
		try{
			Query query = em.createNativeQuery(querySQL, PessoaJuridica.class);
			query.setParameter("id",id);
			pessoas = query.getResultList();
		}catch (Exception e) {
			LogTools.logError("erro ao obter participacoes no banco: "+ e.toString());
		}finally{
			em.close();
		}
		return pessoas;
	}
	
	public List<PessoaFisica> getParticipacaoPFOfEvent (Integer id, String querySQL){
		EntityManager em = EntityManagerUtil.getEntityManager();
		List<PessoaFisica> pessoas = null;
		try{
			Query query = em.createNativeQuery(querySQL, PessoaFisica.class);
			query.setParameter("id",id);
			pessoas = query.getResultList();
		}catch (Exception e) {
			LogTools.logError(e);
		}finally{
			em.close();
		}
		return pessoas;
	}
	
	public Participacao getParticipacaoOfPFAndEvent (Integer id, Integer eventId, String querySQL){
		EntityManager em = EntityManagerUtil.getEntityManager();
		List<Participacao> participacoes = null;
		try{
			Query query = em.createNativeQuery(querySQL, Participacao.class);
			query.setParameter("id",id);
			query.setParameter("eventId",eventId);
			participacoes = query.getResultList();
			if(participacoes != null && !participacoes.isEmpty()){
				return participacoes.get(0);
			}
		}catch (Exception e) {
			LogTools.logError(e);
		}finally{
			em.close();
		}
		return null;
	}
	
	public Participacao getParticipacaoPJOfEventAndEvent (Integer id, Integer eventId, String querySQL){
		EntityManager em = EntityManagerUtil.getEntityManager();
		List<Participacao> participacoes = null;
		try{
			Query query = em.createNativeQuery(querySQL, Participacao.class);
			query.setParameter("id",id);
			query.setParameter("eventId",eventId);
			participacoes = query.getResultList();
			if(participacoes != null && !participacoes.isEmpty()){
				return participacoes.get(0);
			}
		}catch (Exception e) {
			LogTools.logError("erro ao obter participacoes no banco: "+ e.toString());
		}finally{
			em.close();
		}
		return null;
	}
	
	
}
