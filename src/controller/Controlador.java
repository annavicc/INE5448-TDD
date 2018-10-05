package controller;

import java.util.HashMap;

import model.Funcionario;
import model.Ocorrencia;
import model.Ocorrencia.Estado;
import model.Ocorrencia.Prioridade;
import model.Ocorrencia.Tipo;
import model.Projeto;

public class Controlador {

	private static long totalFuncionarios = 0;
	private static long totalProjetos= 0;
	private static long totalOcorrencias = 0;

	private HashMap<Long, Funcionario> funcionarios;
	private HashMap<Long, Projeto> projetos;
	private HashMap<Long, Ocorrencia> ocorrencias;
	
	public Controlador(){
		this.funcionarios = new HashMap<Long, Funcionario>();
		this.projetos = new HashMap<Long, Projeto>();
		this.ocorrencias = new HashMap<Long, Ocorrencia>();
		zerarContagem();
	}
	
	public HashMap<Long, Funcionario> getFuncionarios() {
		return funcionarios;
	}
	
	public HashMap<Long, Projeto> getProjetos() {
		return projetos;
	}
	
	public HashMap<Long, Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}
	
	public Funcionario cadastrarFuncionario(String nomeFuncionario) {
		Funcionario funcionario = new Funcionario(++totalFuncionarios, nomeFuncionario);
		funcionarios.put(totalFuncionarios, funcionario);
		return funcionario;
	}
	
	public Projeto cadastrarProjeto(String nomeProjeto) {
		Projeto projeto = new Projeto(++totalProjetos, nomeProjeto);
		projetos.put(totalProjetos, projeto);
		return projeto;
	}
	
	public Ocorrencia cadastrarOcorrencia(String resumo, Tipo tipo,
									Prioridade prioridade,
									long idProjeto,
									long idFuncionario) {
		if (!existeFuncionario(idFuncionario)) {
			throw new IllegalArgumentException("Funcionário não existe");
		}
		if (!existeProjeto(idProjeto)) {
			throw new IllegalArgumentException("Id do projeto não existe");	
		}
		Funcionario funcionario = funcionarios.get(idFuncionario);
		if (funcionario.possuiMaximoDeOcorrencias()) {
			throw new IllegalStateException("Funcionário já possui limite de 10 ocorrências");
		}
		Ocorrencia ocorrencia = new Ocorrencia(++totalOcorrencias,
												resumo, tipo,
												prioridade, funcionario);
		projetos.get(idProjeto).adicionarOcorrencia(ocorrencia);
		ocorrencias.put(totalOcorrencias, ocorrencia);
		return ocorrencia;
	}
	
	public void finalizarOcorrencia(long idOcorrencia) {
		if (!existeOcorrencia(idOcorrencia)) {
			throw new IllegalArgumentException("Ocorrência não existe");
		}
		Ocorrencia ocorrencia = ocorrencias.get(idOcorrencia);
		ocorrencia.setEstado(Estado.FINALIZADA);
		ocorrencia.getFuncionarioResponsavel().eliminaOcorrencia();
	}
	
	public boolean existeFuncionario(long id) {
		return funcionarios.containsKey(id);
	}
	
	public boolean existeProjeto(long id) {
		return projetos.get(id) != null;
	}
	
	public boolean existeOcorrencia(long id) {
		return ocorrencias.get(id) != null;
	}

	private void zerarContagem() {
		totalFuncionarios = 0;
		totalProjetos = 0;
		totalOcorrencias = 0;
	}

}
