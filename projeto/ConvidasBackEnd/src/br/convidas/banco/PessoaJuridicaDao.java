package br.convidas.banco;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.convidas.classes.PessoaJuridica;
import br.convidas.tools.log.LogTools;


public class PessoaJuridicaDao {
	
	public boolean inserePessoaJuridica (PessoaJuridica pessoa){
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		try{
			em.getTransaction().begin();
			em.persist(pessoa);
			em.getTransaction().commit();
		}catch (Exception e) {
			LogTools.logError("erro ao inserir pessoa juridica no banco: "+ e.toString());
			LogTools.logError(e);
			em.getTransaction().rollback();
			return false;
		}finally{
			em.close();
		}
		
		return true;
	}
	public boolean inserePessoaJuridica (List<PessoaJuridica> pessoas){
		EntityManager em = EntityManagerUtil.getEntityManager();
	
		try{
			em.getTransaction().begin();
			for (PessoaJuridica pessoa : pessoas) {
				em.persist(pessoa);
			}
			em.getTransaction().commit();
		}catch (Exception e) {
			LogTools.logError("erro ao inserir pessoa juridica no banco: "+ e.toString());
			em.getTransaction().rollback();
			return false;
		}finally{
			em.close();
		}
		
		return true;
	}
	
	public boolean updatePessoaJuridica(PessoaJuridica pessoa){
		EntityManager em = EntityManagerUtil.getEntityManager();
		try{
			em.getTransaction().begin();
			em.merge(pessoa);
			em.getTransaction().commit();
		}catch (Exception e) {
			LogTools.logError("erro ao alterar pessoa juridica no banco: "+ e.toString());
			em.getTransaction().rollback();
			return false;
		}finally{
			em.close();
		}
		return true;
	}
	
	public boolean deletePessoaJuridica(Integer id){
		EntityManager em = EntityManagerUtil.getEntityManager();
		try{
			PessoaJuridica pessoa = getPessoaJuridica(id);
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
	
	public PessoaJuridica getPessoaJuridica(Integer id){
		EntityManager em = EntityManagerUtil.getEntityManager();
		PessoaJuridica pessoa = null;
		try{
			pessoa = em.find(PessoaJuridica.class, id);
		}catch (Exception e) {
			LogTools.logError("erro ao obter Pessoa Juridica no banco: "+ e.toString());
		}finally{
			em.close();
		}
		
		return pessoa;
	}
	
	public List<PessoaJuridica> getPessoaJuridicas (){
		EntityManager em = EntityManagerUtil.getEntityManager();
		List<PessoaJuridica> pessoas = null;
		try{
			Query query = em.createQuery("FROM PessoaJuridica ORDER BY name");
			pessoas = query.getResultList();
		}catch (Exception e) {
			LogTools.logError("erro ao obter pessoas fisicas no banco: "+ e.toString());
		}finally{
			em.close();
		}
		return pessoas;
	}

}
