package br.convidas.manager;

import java.util.List;

import br.convidas.banco.EventoDao;
import br.convidas.classes.Evento;

public class ManagerEvento {
	
	private static EventoDao cd;
	
	public static Boolean update(Evento evento) {
		return Boolean.valueOf(getEventoDao().updateEvento(evento));
	}

	public static Boolean create(Evento evento) {
		return Boolean.valueOf(getEventoDao().insereEvento(evento));
	}

	public static Boolean delete(Evento evento) {
		return Boolean.valueOf(getEventoDao().deleteEvento(evento.getId()));
	}
	
	
	public static List<Evento> getEventos() {
		return getEventoDao().getEventos();
	}
	
	private static EventoDao getEventoDao(){
		if(cd == null){
			cd = new EventoDao();
		}
		return cd;
	}
	
}
