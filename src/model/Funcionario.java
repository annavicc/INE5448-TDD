package model;

public class Funcionario {
	
	private final int LIMITE_OCORRENCIAS_FUNCIONARIO = 10;

	private long id;
	private String nome;
	private int ocorrenciasAtribuidas;
	
	public Funcionario(long id, String nome) {
		this.id = id;
		this.nome = nome;
		this.ocorrenciasAtribuidas = 0;
	}
	
	public String getNome() {
		return nome;
	}
	
	public long getId() {
		return id;
	}
	
	
	public boolean possuiMaximoDeOcorrencias() {
		return ocorrenciasAtribuidas >= LIMITE_OCORRENCIAS_FUNCIONARIO;
	}
	
	public int getQuantidadeDeOcorrencias() {
		return ocorrenciasAtribuidas;
	}
	
	public void atribuiOcorrencia() {
		ocorrenciasAtribuidas++;
	}
	
	public void eliminaOcorrencia() {
		if (ocorrenciasAtribuidas > 0) {
			ocorrenciasAtribuidas--;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Funcionario) {
			Funcionario funcionario = (Funcionario)obj;
			return this.id == funcionario.getId();
		}
		return super.equals(obj);
	}
	

}
