package br.convidas.banco;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.convidas.classes.Evento;
import br.convidas.tools.log.LogTools;

public class EventoDao {
	
	public boolean insereEvento (Evento evento){
		EntityManager em = EntityManagerUtil.getEntityManager();
		
		try{
			em.getTransaction().begin();
			em.persist(evento);
			em.getTransaction().commit();
		}catch (Exception e) {
			LogTools.logError("erro ao inserir evento no banco: "+ e.toString());
			em.getTransaction().rollback();
			return false;
		}finally{
			em.close();
		}
		
		return true;
	}
	
	public boolean updateEvento(Evento evento){
		EntityManager em = EntityManagerUtil.getEntityManager();
		try{
			em.getTransaction().begin();
			em.merge(evento);
			em.getTransaction().commit();
		}catch (Exception e) {
			LogTools.logError("erro ao alterar evento no banco: "+ e.toString());
			em.getTransaction().rollback();
			return false;
		}finally{
			em.close();
		}
		return true;
	}
	
	public boolean deleteEvento(Integer id){
		EntityManager em = EntityManagerUtil.getEntityManager();
		try{
			Evento evento = getEvento(id);
			if(evento != null){
				em.getTransaction().begin();
				evento = em.merge(evento);
				em.remove(evento);
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
	
	public Evento getEvento(Integer id){
		EntityManager em = EntityManagerUtil.getEntityManager();
		Evento evento = null;
		try{
			evento = em.find(Evento.class, id);
		}catch (Exception e) {
			LogTools.logError("erro ao obter evento no banco: "+ e.toString());
		}finally{
			em.close();
		}
		
		return evento;
	}
	
	public List<Evento> getEventos (){
		EntityManager em = EntityManagerUtil.getEntityManager();
		List<Evento> evento = null;
		try{
			Query query = em.createQuery("FROM Evento ORDER BY name");
			evento = query.getResultList();
		}catch (Exception e) {
			LogTools.logError("erro ao obter eventos no banco: "+ e.toString());
		}finally{
			em.close();
		}
		return evento;
	}
}
