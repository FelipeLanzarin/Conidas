package br.convidas.banco;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.convidas.classes.PessoaFisica;
import br.convidas.tools.log.LogTools;


public class PessoaFisicaDao {
	
	public boolean inserePessoaFisica (PessoaFisica pessoa){
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		try{
			em.getTransaction().begin();
			em.persist(pessoa);
			em.getTransaction().commit();
		}catch (Exception e) {
			LogTools.logError("erro ao inserir pessoa fisica no banco: "+ e.toString());
			em.getTransaction().rollback();
			return false;
		}finally{
			em.close();
		}
		
		return true;
	}
	public boolean inserePessoaFisicas (List<PessoaFisica> pessoas){
		EntityManager em = EntityManagerUtil.getEntityManager();
	
		try{
			em.getTransaction().begin();
			for (PessoaFisica pessoa : pessoas) {
				em.persist(pessoa);
			}
			em.getTransaction().commit();
		}catch (Exception e) {
			LogTools.logError("erro ao inserir pessoa fisica no banco: "+ e.toString());
			em.getTransaction().rollback();
			return false;
		}finally{
			em.close();
		}
		
		return true;
	}
	
	public boolean updatePessoaFisica(PessoaFisica pessoa){
		EntityManager em = EntityManagerUtil.getEntityManager();
		try{
			em.getTransaction().begin();
			em.merge(pessoa);
			em.getTransaction().commit();
		}catch (Exception e) {
			LogTools.logError("erro ao alterar pessoa fisica no banco: "+ e.toString());
			em.getTransaction().rollback();
			return false;
		}finally{
			em.close();
		}
		return true;
	}
	
	public boolean deletePessoaFisica(Integer id){
		EntityManager em = EntityManagerUtil.getEntityManager();
		try{
			PessoaFisica pessoa = getPessoaFisica(id);
			if(pessoa != null){
				em.getTransaction().begin();
				pessoa = em.merge(pessoa);
				em.remove(pessoa);
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
	
	public PessoaFisica getPessoaFisica(Integer id){
		EntityManager em = EntityManagerUtil.getEntityManager();
		PessoaFisica pessoa = null;
		try{
			pessoa = em.find(PessoaFisica.class, id);
		}catch (Exception e) {
			LogTools.logError("erro ao obter pessoa fisica no banco: "+ e.toString());
		}finally{
			em.close();
		}
		
		return pessoa;
	}
	
	public List<PessoaFisica> getPessoaFisicas (){
		EntityManager em = EntityManagerUtil.getEntityManager();
		List<PessoaFisica> pessoas = null;
		try{
			Query query = em.createQuery("FROM PessoaFisica ORDER BY name");
			pessoas = query.getResultList();
		}catch (Exception e) {
			LogTools.logError("erro ao obter pessoas fisicas no banco: "+ e.toString());
		}finally{
			em.close();
		}
		return pessoas;
	}
	
	public List<PessoaFisica> getPessoaFisicasOthers (String querySQL){
		EntityManager em = EntityManagerUtil.getEntityManager();
		List<PessoaFisica> pessoas = null;
		try{
			Query query = em.createNativeQuery(querySQL, PessoaFisica.class);
			pessoas = query.getResultList();
		}catch (Exception e) {
			LogTools.logError("erro ao obter pessoas fisicas no banco: "+ e.toString());
		}finally{
			em.close();
		}
		return pessoas;
	}
	
	public List<PessoaFisica> getPessoaFisicas (String param,String param2){
		EntityManager em = EntityManagerUtil.getEntityManager();
		List<PessoaFisica> pessoas = null;
		try{
			Query query = em.createQuery("FROM PessoaFisica where name like :name or name like :name2 ORDER BY name");
			query.setParameter("name", param+"%");
			query.setParameter("name2", param2+"%");
			pessoas = query.getResultList();
		}catch (Exception e) {
			LogTools.logError("erro ao obter pessoas fisicas no banco: "+ e.toString());
		}finally{
			em.close();
		}
		return pessoas;
	}
	
	public List<PessoaFisica> getPessoaFisicasByRelation (String param){
		EntityManager em = EntityManagerUtil.getEntityManager();
		List<PessoaFisica> pessoas = null;
		try{
			Query query = em.createQuery("FROM PessoaFisica where relacao = :relation ORDER BY name");
			query.setParameter("relation", param);
			pessoas = query.getResultList();
		}catch (Exception e) {
			LogTools.logError("erro ao obter pessoas fisicas no banco: "+ e.toString());
		}finally{
			em.close();
		}
		return pessoas;
	}
	public List<PessoaFisica> getPessoaFisicasByNewsletter (boolean newsleter){
		EntityManager em = EntityManagerUtil.getEntityManager();
		List<PessoaFisica> pessoas = null;
		try{
			Query query = em.createQuery("FROM PessoaFisica where newsletter = :newsletter ORDER BY name");
			query.setParameter("newsletter", newsleter);
			pessoas = query.getResultList();
		}catch (Exception e) {
			LogTools.logError("erro ao obter pessoas fisicas no banco: "+ e.toString());
		}finally{
			em.close();
		}
		return pessoas;
	}

}
