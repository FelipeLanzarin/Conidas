package br.convidas.tools.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import br.convidas.classes.Cidade;
import br.convidas.classes.Evento;
import br.convidas.classes.OcorrencyPF;
import br.convidas.classes.OcorrencyPJ;
import br.convidas.classes.Participacao;
import br.convidas.classes.PessoaFisica;
import br.convidas.classes.PessoaJuridica;
import br.convidas.tools.log.LogTools;

public class CSVTools {
	
	private static final String SP = ";#SP#;";
	private static final String QUEBRA_LINHA= " ;#QB#; ";
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	public void writeBackupPF(FileWriter writer, List<PessoaFisica> list){
		try {
			writer.write("ID"+SP);
			writer.write("NOME"+SP);
			writer.write("CPF"+SP);
			writer.write("EMAIL"+SP);
			writer.write("RELACAO"+SP);
			writer.write("RUA"+SP);
			writer.write("NUMERO"+SP);
			writer.write("BAIRRO"+SP);
			writer.write("CEP"+SP);
			writer.write("NEWSLETTER"+SP);
			writer.write("CREATION_DATE"+SP);
			writer.write("TELEFONE"+SP);
			writer.write("HISTORICO"+SP);
			writer.write("CIDADE"+SP);
	
			for (PessoaFisica pessoa : list) {
				writer.write(10);
				writer.write(pessoa.getId()+""+SP);
				writer.write(pessoa.getName() + ""+SP);
				writer.write(pessoa.getCpf()+""+SP);
				writer.write(pessoa.getEmail()+""+SP);
				writer.write(pessoa.getRelacao()+""+SP);
				writer.write(pessoa.getRua()+""+SP);
				writer.write(pessoa.getNumero()+""+SP);
				writer.write(pessoa.getBairro()+""+SP);
				writer.write(pessoa.getCep() + ""+SP);
				writer.write(pessoa.getNewsletter()+""+SP);
				String date = "";
				if(pessoa.getCreationDate() != null){
					date = sdf.format(pessoa.getCreationDate());
				}
				writer.write(date+""+SP);
				writer.write(pessoa.getTelefone()+""+SP);
				writer.write(convertWord(pessoa.getHistorico())+""+SP);
				if(pessoa.getCidade() != null){
					writer.write(pessoa.getCidade().getId()+""+SP);
				}else{
					writer.write(" "+SP);
				}
				
			}
		} catch (IOException e) {
			LogTools.logError(e);
		}
	}
	
	public void writeBackupPJ(FileWriter writer, List<PessoaJuridica> list){
		try {
			writer.write("ID");
			writer.write("NOME"+SP);
			writer.write("RESPONSAVEL"+SP);
			writer.write("CNPJ"+SP);
			writer.write("EMAIL"+SP);
			writer.write("CONTRIBUICAO"+SP);
			writer.write("RUA"+SP);
			writer.write("NUMERO"+SP);
			writer.write("BAIRRO"+SP);
			writer.write("CEP"+SP);
			writer.write("NEWSLETTER"+SP);
			writer.write("CREATION_DATE"+SP);
			writer.write("TELEFONE"+SP);
			writer.write("HISTORICO"+SP);
			writer.write("CIDADE"+SP);
	
			for (PessoaJuridica pessoa : list) {
				writer.write(10);
				writer.write(pessoa.getId()+""+SP);
				writer.write(pessoa.getName() + ""+SP);
				writer.write(pessoa.getResponsavel()+""+SP);
				writer.write(pessoa.getCnpj()+""+SP);
				writer.write(pessoa.getEmail()+""+SP);
				writer.write(pessoa.getContribuicao()+""+SP);
				writer.write(pessoa.getRua()+""+SP);
				writer.write(pessoa.getNumero()+""+SP);
				writer.write(pessoa.getBairro()+""+SP);
				writer.write(pessoa.getCep() + ""+SP);
				writer.write(pessoa.getNewsletter()+""+SP);
				String date = "";
				if(pessoa.getCreationDate() != null){
					date = sdf.format(pessoa.getCreationDate());
				}
				writer.write(date+""+SP);
				writer.write(pessoa.getTelefone()+""+SP);
				writer.write(convertWord(pessoa.getHistorico())+""+SP);
				if(pessoa.getCidade() != null){
					writer.write(pessoa.getCidade().getId()+""+SP);
				}else{
					writer.write(" "+SP);
				}
				
			}
		} catch (IOException e) {
			LogTools.logError(e);
		}
	}
	
	public void writeBackupCities(FileWriter writer, List<Cidade> list){
		try {
			writer.write("ID");
			writer.write("NOME"+SP);
			writer.write("UF"+SP);
			writer.write("PAIS"+SP);
	
			for (Cidade cidade : list) {
				writer.write(10);
				writer.write(cidade.getId()+""+SP);
				writer.write(cidade.getName() + ""+SP);
				writer.write(cidade.getUf()+""+SP);
				writer.write(cidade.getPais()+""+SP);
			}
		} catch (IOException e) {
			LogTools.logError(e);
		}
	}
	
	public void writeBackupOPF(FileWriter writer, List<OcorrencyPF> list){
		try {
			writer.write("ID");
			writer.write("DATA"+SP);
			writer.write("DESCRICAO"+SP);
			writer.write("PF"+SP);
	
			for (OcorrencyPF ocorrency : list) {
				writer.write(10);
				writer.write(ocorrency.getId()+""+SP);
				String date = "";
				if(ocorrency.getDate() != null){
					date = sdf.format(ocorrency.getDate());
				}
				writer.write(date + ""+SP);
				writer.write(convertWord(ocorrency.getDescription()) + ""+SP);
				if(ocorrency.getPessoFisica() != null){
					writer.write(ocorrency.getPessoFisica().getId()+""+SP);
				}else{
					writer.write(" "+SP);
				}
			}
		} catch (IOException e) {
			LogTools.logError(e);
		}
	}
	
	public void writeBackupOPJ(FileWriter writer, List<OcorrencyPJ> list){
		try {
			writer.write("ID");
			writer.write("DATA"+SP);
			writer.write("DESCRICAO"+SP);
			writer.write("PJ"+SP);
	
			for (OcorrencyPJ ocorrency : list) {
				writer.write(10);
				writer.write(ocorrency.getId()+""+SP);
				String date = "";
				if(ocorrency.getDate() != null){
					date = sdf.format(ocorrency.getDate());
				}
				writer.write(date + ""+SP);
				writer.write(convertWord(ocorrency.getDescription()) + ""+SP);
				if(ocorrency.getPessoaJuridica() != null){
					writer.write(ocorrency.getPessoaJuridica().getId()+""+SP);
				}else{
					writer.write(" "+SP);
				}
			}
		} catch (IOException e) {
			LogTools.logError(e);
		}
	}
	
	public void writeBackupEvents(FileWriter writer, List<Evento> list){
		try {
			writer.write("ID");
			writer.write("NOME"+SP);
			writer.write("DESCRICAO"+SP);
			writer.write("LOCAL"+SP);
			writer.write("DATA_INICIAL"+SP);
			writer.write("DATA_FINAL"+SP);
			writer.write("CIDADE"+SP);
	
			for (Evento evento : list) {
				writer.write(10);
				writer.write(evento.getId()+""+SP);
				writer.write(evento.getName() + ""+SP);
				writer.write(convertWord(evento.getDescription()) + ""+SP);
				writer.write(convertWord(evento.getLocale()) + ""+SP);
				String initialDate = "";
				if(evento.getInitialDate() != null){
					initialDate = sdf.format(evento.getInitialDate());
				}
				writer.write(initialDate + ""+SP);
				String finalDate = "";
				if(evento.getFinalDate() != null){
					finalDate = sdf.format(evento.getFinalDate());
				}
				writer.write(finalDate + ""+SP);
				if(evento.getCity() != null){
					writer.write(evento.getCity().getId()+""+SP);
				}else{
					writer.write(" "+SP);
				}
			}
		} catch (IOException e) {
			LogTools.logError(e);
		}
	}
	
	public void writeBackupParticipations(FileWriter writer, List<Participacao> list){
		try {
			writer.write("ID");
			writer.write("PJ"+SP);
			writer.write("PF"+SP);
			writer.write("EVENTO"+SP);
	
			for (Participacao part : list) {
				writer.write(10);
				writer.write(part.getId()+""+SP);
				if(part.getPessoaJuridica() != null){
					writer.write(part.getPessoaJuridica().getId()+""+SP);
				}else{
					writer.write(" "+SP);
				}
				if(part.getPessoaFisica() != null){
					writer.write(part.getPessoaFisica().getId()+""+SP);
				}else{
					writer.write(" "+SP);
				}
				if(part.getEvento() != null){
					writer.write(part.getEvento().getId()+""+SP);
				}else{
					writer.write(" "+SP);
				}
			}
		} catch (IOException e) {
			LogTools.logError(e);
		}
	}
	
	private String convertWord(String word){
		if(word == null){
			return "";
		}
		word = word.replace("\n", QUEBRA_LINHA);
		return word;
	}
	
}
