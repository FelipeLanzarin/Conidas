package br.convidas.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="participacao")
public class Participacao {
	
	@Id
    @SequenceGenerator(name="SEQ", sequenceName="GEN_PART_ID", allocationSize=1)
    @GeneratedValue(generator="SEQ",strategy= GenerationType.SEQUENCE)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="pj_id", referencedColumnName="id")
	private PessoaJuridica pessoaJuridica;
	@ManyToOne
	@JoinColumn(name="pf_id", referencedColumnName="id")
	private PessoaFisica pessoaFisica;
	@ManyToOne
	@JoinColumn(name="event_id", referencedColumnName="id")
	private Evento evento;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}
	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}
	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}
	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
