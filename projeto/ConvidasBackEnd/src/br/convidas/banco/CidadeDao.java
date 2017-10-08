package br.convidas.banco;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.convidas.classes.Cidade;
import br.convidas.classes.PessoaFisica;
import br.convidas.tools.log.LogTools;


public class CidadeDao {
	
	public boolean insereCidade (Cidade cidade){
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		try{
			em.getTransaction().begin();
			em.persist(cidade);
			em.getTransaction().commit();
		}catch (Exception e) {
			LogTools.logError("erro ao inserir cidade no banco: "+ e.toString());
			em.getTransaction().rollback();
			return false;
		}finally{
			em.close();
		}
		
		return true;
	}
	
	public boolean updateCidade(Cidade cidade){
		EntityManager em = EntityManagerUtil.getEntityManager();
		try{
			em.getTransaction().begin();
			em.merge(cidade);
			em.getTransaction().commit();
		}catch (Exception e) {
			LogTools.logError("erro ao alterar cidade no banco: "+ e.toString());
			em.getTransaction().rollback();
			return false;
		}finally{
			em.close();
		}
		return true;
	}
	
	public boolean deleteCidade(Integer id){
		EntityManager em = EntityManagerUtil.getEntityManager();
		try{
			Cidade cidade = getCidade(id);
			if(cidade != null){
				em.getTransaction().begin();
				cidade = em.merge(cidade);
				em.remove(cidade);
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
	
	public Cidade getCidade(Integer id){
		EntityManager em = EntityManagerUtil.getEntityManager();
		Cidade cidade = null;
		try{
			cidade = em.find(Cidade.class, id);
		}catch (Exception e) {
			LogTools.logError("erro ao obter cidade no banco: "+ e.toString());
		}finally{
			em.close();
		}
		
		return cidade;
	}
	
	public List<Cidade> getCidades (){
		EntityManager em = EntityManagerUtil.getEntityManager();
		List<Cidade> cidade = null;
		try{
			Query query = em.createQuery("FROM Cidade ORDER BY name");
			cidade = query.getResultList();
		}catch (Exception e) {
			LogTools.logError("erro ao obter cidades no banco: "+ e.toString());
		}finally{
			em.close();
		}
		return cidade;
	}

}
