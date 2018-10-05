package model;

public class Ocorrencia {

	public enum Tipo { TAREFA, BUG, MELHORIA };
	public enum Estado { ABERTA, FINALIZADA };
	public enum Prioridade { ALTA, MEDIA, BAIXA };
	
	private String resumo;
	private long id;
	private Tipo tipo;
	private Estado estado;
	private Prioridade prioridade;
	private Funcionario funcionarioResponsavel;
	
	
	public Ocorrencia(long id, String resumo,
				Tipo tipo, Prioridade prioridade,
				Funcionario funcionarioResponsavel) {
		this.id = id;
		this.resumo = resumo;
		this.tipo = tipo;
		this.prioridade = prioridade;
		this.funcionarioResponsavel = funcionarioResponsavel;
		this.estado = Estado.ABERTA;
		if (funcionarioResponsavel == null) {
			throw new IllegalStateException("Funcionário responsável inválido");
		}
		funcionarioResponsavel.atribuiOcorrencia();
	}
	
	public long getId() {
		return id;
	}
	
	public String getResumo() {
		return resumo;
	}
	
	public Tipo getTipo() {
		return tipo;
	}

	public Prioridade getPrioridade() {
		return prioridade;
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	public Funcionario getFuncionarioResponsavel() {
		return funcionarioResponsavel;
	}
	
	public void setPrioridade(Prioridade prioridade) {
		if (this.estado == Estado.ABERTA) {
			this.prioridade = prioridade;
		} else {
			throw new IllegalStateException("Estado da ocorrência deve ser ABERTO");
		}
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
		if (funcionarioResponsavel == null) {
			throw new IllegalArgumentException("Funcionário inválido");
		}
		if (this.estado == Estado.ABERTA) {
			this.funcionarioResponsavel = funcionarioResponsavel;
		} else {
			throw new IllegalStateException("Estado da ocorrência deve ser ABERTO");
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Ocorrencia) {
			Ocorrencia ocorrencia = (Ocorrencia) obj;
			return this.id == ocorrencia.getId();
		}
		return super.equals(obj);
	}
	
	
	
}
