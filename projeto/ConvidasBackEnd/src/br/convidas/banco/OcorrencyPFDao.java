package br.convidas.banco;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.convidas.classes.OcorrencyPF;
import br.convidas.classes.PessoaFisica;
import br.convidas.tools.log.LogTools;


public class OcorrencyPFDao {
	
	public boolean insereOcorrencyPF (OcorrencyPF ocorrencyPF){
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		try{
			em.getTransaction().begin();
			em.persist(ocorrencyPF);
			em.getTransaction().commit();
		}catch (Exception e) {
			LogTools.logError("erro ao inserir ocorrencyPF no banco: "+ e.toString());
			em.getTransaction().rollback();
			return false;
		}finally{
			em.close();
		}
		
		return true;
	}
	public boolean insereOcorrencyPF(List<OcorrencyPF> ocorrencyPFs){
		EntityManager em = EntityManagerUtil.getEntityManager();
	
		try{
			em.getTransaction().begin();
			for (OcorrencyPF ocorrencyPF : ocorrencyPFs) {
				em.persist(ocorrencyPF);
			}
			em.getTransaction().commit();
		}catch (Exception e) {
			LogTools.logError("erro ao inserir ocorrencyPFs no banco: "+ e.toString());
			em.getTransaction().rollback();
			return false;
		}finally{
			em.close();
		}
		
		return true;
	}
	
	public boolean updateOcorrencyPF(OcorrencyPF ocorrencyPF){
		EntityManager em = EntityManagerUtil.getEntityManager();
		try{
			em.getTransaction().begin();
			em.merge(ocorrencyPF);
			em.getTransaction().commit();
		}catch (Exception e) {
			LogTools.logError("erro ao alterar ocorrencyPF no banco: "+ e.toString());
			em.getTransaction().rollback();
			return false;
		}finally{
			em.close();
		}
		return true;
	}
	
	public boolean deleteOcorrencyPF(Integer id){
		EntityManager em = EntityManagerUtil.getEntityManager();
		try{
			OcorrencyPF ocorrencyPF = getOcorrencyPF(id);
			if(ocorrencyPF != null){
				em.getTransaction().begin();
				ocorrencyPF = em.merge(ocorrencyPF);
				em.remove(ocorrencyPF);
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
	
	public OcorrencyPF getOcorrencyPF(Integer id){
		EntityManager em = EntityManagerUtil.getEntityManager();
		OcorrencyPF ocorrencyPF = null;
		try{
			ocorrencyPF = em.find(OcorrencyPF.class, id);
		}catch (Exception e) {
			LogTools.logError("erro ao obter ocorrencyPF no banco: "+ e.toString());
		}finally{
			em.close();
		}
		
		return ocorrencyPF;
	}
	
	public List<OcorrencyPF> getOcorrencyPFs(){
		EntityManager em = EntityManagerUtil.getEntityManager();
		List<OcorrencyPF> ocorrencyPF = null;
		try{
			Query query = em.createQuery("FROM OcorrencyPF ORDER BY date");
			ocorrencyPF = query.getResultList();
		}catch (Exception e) {
			LogTools.logError("erro ao obter OcorrencyPF no banco: "+ e.toString());
		}finally{
			em.close();
		}
		return ocorrencyPF;
	}
	
	public List<OcorrencyPF> getOcorrencyPFsByPessoa(PessoaFisica pessoa, String querySQL ){
		EntityManager em = EntityManagerUtil.getEntityManager();
		List<OcorrencyPF> ocorrencyPF = null;
		try{
			Query query = em.createNativeQuery(querySQL, OcorrencyPF.class);
			query.setParameter(1, pessoa.getId());
			ocorrencyPF = query.getResultList();
		}catch (Exception e) {
			LogTools.logError("erro ao obter OcorrencyPF no banco: "+ e.toString());
		}finally{
			em.close();
		}
		return ocorrencyPF;
	}
	
}
