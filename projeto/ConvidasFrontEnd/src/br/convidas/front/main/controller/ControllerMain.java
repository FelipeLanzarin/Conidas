package br.convidas.front.main.controller;

import br.convidas.front.cities.TelaCities;
import br.convidas.front.contact.TelaConsultContacts;
import br.convidas.front.contact.TelaContacts;
import br.convidas.front.event.TelaEvent;
import br.convidas.tools.log.LogTools;
import javafx.stage.Stage;

public class ControllerMain {
	
	
	public void clickButtonContact(){
		try {
			TelaContacts tc = new TelaContacts();
			tc.start(new Stage());
		} catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void clickButtonConsult(){
		try {
			TelaConsultContacts tc = new TelaConsultContacts();
			tc.start(new Stage());
		} catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void clickButtonEvents(){
		try {
			TelaEvent te = new TelaEvent();
			te.start(new Stage());
		} catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void clickButtonCities(){
		try {
			TelaCities tc = new TelaCities();
			tc.start(new Stage());
		} catch (Exception e) {
			LogTools.logError(e);
		}
	}
	
	public void clickButtonConsultEvents(){
		
	}
}
