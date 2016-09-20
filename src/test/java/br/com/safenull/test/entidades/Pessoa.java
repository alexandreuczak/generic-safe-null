package br.com.safenull.test.entidades;
import java.util.List;

import br.com.safenull.test.entidades.extras.Job;

public class Pessoa extends Entidade{

	private int id;
	private String descricao;
	private Endereco endereco;
	private List<Email>email;
	private Telefone telefone;

	private Job job;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public List<Email> getEmail() {
		return email;
	}
	public void setEmail(List<Email> email) {
		this.email = email;
	}
	public Telefone getTelefone() {
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
