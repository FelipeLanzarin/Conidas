package br.convidas.classes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="pessoa_fisica")
public class PessoaFisica{
	
	@Id
    @SequenceGenerator(name="SEQ", sequenceName="GEN_PF_ID", allocationSize=1)
    @GeneratedValue(generator="SEQ",strategy= GenerationType.SEQUENCE)
	private Integer id;
	private String cpf;
	private String name;
	private String email;
	private String relacao;
	private String rua;
	private String numero;
	private String bairro;
	private String cep;
	private Boolean newsletter;
	@Column(name="creation_date")
	private Date creationDate;
	@ManyToOne
	@JoinColumn(name="cidade_id", referencedColumnName="id")
	private Cidade cidade;
	private String telefone;
	@Column(length=9999)
	private String historico;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public Boolean getNewsletter() {
		return newsletter;
	}
	public void setNewsletter(Boolean newsletter) {
		this.newsletter = newsletter;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getHistorico() {
		return historico;
	}
	public void setHistorico(String historico) {
		this.historico = historico;
	}	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRelacao() {
		return relacao;
	}
	public void setRelacao(String relacao) {
		this.relacao = relacao;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	@Override
	public String toString() {
		return "PessoaFisica [id=" + id + ", cpf=" + cpf + ", name=" + name + ", email=" + email + ", relacao="
				+ relacao + ", rua=" + rua + ", numero=" + numero + ", bairro=" + bairro + ", cep=" + cep
				+ ", newsletter=" + newsletter + ", telefone=" + telefone + ", historico=" + historico + "]";
	}
	
	
}
