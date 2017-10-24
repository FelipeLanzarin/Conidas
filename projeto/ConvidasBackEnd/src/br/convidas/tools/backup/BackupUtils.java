package br.convidas.tools.backup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.convidas.manager.ManagerCidade;
import br.convidas.manager.ManagerEvento;
import br.convidas.manager.ManagerOcorrencyPF;
import br.convidas.manager.ManagerOcorrencyPJ;
import br.convidas.manager.ManagerPF;
import br.convidas.manager.ManagerPJ;
import br.convidas.manager.ManagerParticipacao;
import br.convidas.tools.csv.CSVTools;
import br.convidas.tools.log.LogTools;

public class BackupUtils {
	
	private String pathRaiz = "backup/";
	private CSVTools csv;
	
	public void startBackup(){
		SimpleDateFormat sdfY = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdfM = new SimpleDateFormat("MM");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date data = new Date();
		csv = new CSVTools();
		String path = pathRaiz+sdfY.format(data) +"/"+sdfM.format(data)+"/"+sdf.format(data)+"/";
		File files = new File(path);
		if (!files.exists()) {
			if (files.mkdirs()) {
				System.out.println("create folder " + files.getAbsolutePath());
				realizeAllBackup(files);
			} else {
				LogTools.logError("Erro ao criar diretorio para relizar backup");
			}
		}else{
			System.out.println("diretorio já existe");
			System.out.println("create folder " + files.getAbsolutePath());
			realizeAllBackup(files);
		}
	}
	
	private void realizeAllBackup(File pathFiles){
		backupPF(pathFiles);
		backupPJ(pathFiles);
		backupCities(pathFiles);
		backupOPF(pathFiles);
		backupOPJ(pathFiles);
		backupEvents(pathFiles);
		backupEvents(pathFiles);
		backupParticipations(pathFiles);
	}
	
	private void backupPF(File pathFiles){
		try {
			FileWriter filesName;
			filesName = new FileWriter(pathFiles.getAbsolutePath()+"/PessoaFisica.bk");
			csv.writeBackupPF(filesName, ManagerPF.getPessoasFisicas());
			filesName.close();
		} catch (IOException e) {
			LogTools.logError("Erro em backupPF");
			LogTools.logError(e);
		}
		
		
	}
	
	private void backupPJ(File pathFiles){
		try {
			FileWriter filesName;
			filesName = new FileWriter(pathFiles.getAbsolutePath()+"/PessoaJuridica.bk");
			csv.writeBackupPJ(filesName, ManagerPJ.getPessoaJuridicas());
			filesName.close();
		} catch (IOException e) {
			LogTools.logError("Erro em backupPF");
			LogTools.logError(e);
		}
	}
	
	private void backupCities(File pathFiles){
		try {
			FileWriter filesName;
			filesName = new FileWriter(pathFiles.getAbsolutePath()+"/Cidade.bk");
			csv.writeBackupCities(filesName, ManagerCidade.getCidades());
			filesName.close();
		} catch (IOException e) {
			LogTools.logError("Erro em backupPF");
			LogTools.logError(e);
		}
	}
	
	private void backupOPF(File pathFiles){
		try {
			FileWriter filesName;
			filesName = new FileWriter(pathFiles.getAbsolutePath()+"/OcorrenciaPF.bk");
			csv.writeBackupOPF(filesName, ManagerOcorrencyPF.getOcorrencyPF());
			filesName.close();
		} catch (IOException e) {
			LogTools.logError("Erro em backupPF");
			LogTools.logError(e);
		}
	}
	
	private void backupOPJ(File pathFiles){
		try {
			FileWriter filesName;
			filesName = new FileWriter(pathFiles.getAbsolutePath()+"/OcorrenciaPJ.bk");
			csv.writeBackupOPJ(filesName, ManagerOcorrencyPJ.getOcorrencyPJ());
			filesName.close();
		} catch (IOException e) {
			LogTools.logError("Erro em backupPF");
			LogTools.logError(e);
		}
	}
	
	private void backupEvents(File pathFiles){
		try {
			FileWriter filesName;
			filesName = new FileWriter(pathFiles.getAbsolutePath()+"/Evento.bk");
			csv.writeBackupEvents(filesName, ManagerEvento.getEventos());
			filesName.close();
		} catch (IOException e) {
			LogTools.logError("Erro em backupPF");
			LogTools.logError(e);
		}
	}
	
	private void backupParticipations(File pathFiles){
		try {
			FileWriter filesName;
			filesName = new FileWriter(pathFiles.getAbsolutePath()+"/Participacao.bk");
			csv.writeBackupParticipations(filesName, ManagerParticipacao.getParticipacaos());
			filesName.close();
		} catch (IOException e) {
			LogTools.logError("Erro em backupPF");
			LogTools.logError(e);
		}
	}
	
}
