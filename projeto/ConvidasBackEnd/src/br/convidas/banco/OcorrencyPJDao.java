package br.convidas.banco;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.convidas.classes.OcorrencyPF;
import br.convidas.classes.OcorrencyPJ;
import br.convidas.classes.PessoaFisica;
import br.convidas.classes.PessoaJuridica;
import br.convidas.tools.log.LogTools;


public class OcorrencyPJDao {
	
	public boolean insereOcorrencyPJ (OcorrencyPJ ocorrencyPJ){
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		try{
			em.getTransaction().begin();
			em.persist(ocorrencyPJ);
			em.getTransaction().commit();
		}catch (Exception e) {
			LogTools.logError("erro ao inserir ocorrencyPJ no banco: "+ e.toString());
			em.getTransaction().rollback();
			return false;
		}finally{
			em.close();
		}
		
		return true;
	}
	public boolean insereOcorrencyPJ(List<OcorrencyPJ> ocorrencyPJs){
		EntityManager em = EntityManagerUtil.getEntityManager();
	
		try{
			em.getTransaction().begin();
			for (OcorrencyPJ ocorrencyPJ : ocorrencyPJs) {
				em.persist(ocorrencyPJ);
			}
			em.getTransaction().commit();
		}catch (Exception e) {
			LogTools.logError("erro ao inserir ocorrencyPJs no banco: "+ e.toString());
			em.getTransaction().rollback();
			return false;
		}finally{
			em.close();
		}
		
		return true;
	}
	
	public boolean updateOcorrencyPJ(OcorrencyPJ ocorrencyPJ){
		EntityManager em = EntityManagerUtil.getEntityManager();
		try{
			em.getTransaction().begin();
			em.merge(ocorrencyPJ);
			em.getTransaction().commit();
		}catch (Exception e) {
			LogTools.logError("erro ao alterar ocorrencyPJ no banco: "+ e.toString());
			em.getTransaction().rollback();
			return false;
		}finally{
			em.close();
		}
		return true;
	}
	
	public boolean deleteOcorrencyPJ(Integer id){
		EntityManager em = EntityManagerUtil.getEntityManager();
		try{
			OcorrencyPJ ocorrencyPJ = getOcorrencyPJ(id);
			if(ocorrencyPJ != null){
				em.getTransaction().begin();
				ocorrencyPJ = em.merge(ocorrencyPJ);
				em.remove(ocorrencyPJ);
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
	
	public OcorrencyPJ getOcorrencyPJ(Integer id){
		EntityManager em = EntityManagerUtil.getEntityManager();
		OcorrencyPJ ocorrencyPJ = null;
		try{
			ocorrencyPJ = em.find(OcorrencyPJ.class, id);
		}catch (Exception e) {
			LogTools.logError("erro ao obter ocorrencyPJ no banco: "+ e.toString());
		}finally{
			em.close();
		}
		
		return ocorrencyPJ;
	}
	
	public List<OcorrencyPJ> getOcorrencyPJs(){
		EntityManager em = EntityManagerUtil.getEntityManager();
		List<OcorrencyPJ> ocorrencyPJ = null;
		try{
			Query query = em.createQuery("FROM OcorrencyPJ ORDER BY date");
			ocorrencyPJ = query.getResultList();
		}catch (Exception e) {
			LogTools.logError("erro ao obter OcorrencyPJs no banco: ");
			LogTools.logError(e);
		}finally{
			em.close();
		}
		return ocorrencyPJ;
	}
	
	public List<OcorrencyPJ> getOcorrencyPJsByPessoa(PessoaJuridica pessoa, String querySQL ){
		EntityManager em = EntityManagerUtil.getEntityManager();
		List<OcorrencyPJ> ocorrencyPJ = null;
		try{
			Query query = em.createNativeQuery(querySQL, OcorrencyPJ.class);
			query.setParameter(1, pessoa.getId());
			ocorrencyPJ = query.getResultList();
		}catch (Exception e) {
			LogTools.logError("erro ao obter OcorrencyPJ no banco: ");
			LogTools.logError(e);
		}finally{
			em.close();
		}
		return ocorrencyPJ;
	}
	
}
