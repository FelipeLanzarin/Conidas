package br.convidas.banco;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.convidas.classes.Participacao;
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
	
	public boolean updateParticipacao(Participacao participacao){
		EntityManager em = EntityManagerUtil.getEntityManager();
		try{
			em.getTransaction().begin();
			em.merge(participacao);
			em.getTransaction().commit();
		}catch (Exception e) {
			LogTools.logError("erro ao alterar participacao no banco: "+ e.toString());
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
	
	public List<Participacao> getParticipacaoOfEvent (Integer id){
		EntityManager em = EntityManagerUtil.getEntityManager();
		List<Participacao> participacao = null;
		try{
			String queryString = "FROM Participacao p WHERE p.pj_id = :id ORDER BY name";
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
}
