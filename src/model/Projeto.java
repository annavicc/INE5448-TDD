package model;

import java.util.HashSet;
import java.util.Set;

public class Projeto {
	
	private String nome;
	private long id;
	private Set<Ocorrencia> ocorrencias;
	
	public Projeto(long id, String nome) {
		this.nome = nome;
		this.id = id;
		this.ocorrencias = new HashSet<Ocorrencia>();
	}
	
	public String getNome() {
		return nome;
	}
	
	public long getId() {
		return id;
	}
	
	public Set<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}
	
	public void adicionarOcorrencia(Ocorrencia ocorrencia) {
		ocorrencias.add(ocorrencia);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Projeto) {
			Projeto projeto = (Projeto) obj;
			return this.id == projeto.getId();
		}
		return super.equals(obj);
	}
	

}
