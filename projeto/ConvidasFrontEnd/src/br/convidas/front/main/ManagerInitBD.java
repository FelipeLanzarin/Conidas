package br.convidas.front.main;

import br.convidas.banco.EntityManagerUtil;

public class ManagerInitBD extends Thread{

	private Main main;
	
	@Override
	public void run() {
		if(EntityManagerUtil.initEntityManager()){
			main.sucessConectBD();
		}else{
			main.failedConectBD();
		}
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}
	

}
