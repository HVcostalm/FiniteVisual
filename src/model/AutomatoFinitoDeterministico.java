package model;

import java.util.List;

public class AutomatoFinitoDeterministico {
	private String nome;
	private List<Estado> estados;
    private Estado estadoInicial;
    private List<Transicao> transicoes;
    private List<Character> alfabeto;
    
    public AutomatoFinitoDeterministico(String nome, List<Estado> estados, Estado estadoInicial, List<Transicao> transicoes, List<Character> alfabeto) {
        this.nome = nome;
    	this.estados = estados;
        this.estadoInicial = estadoInicial;
        this.transicoes = transicoes;
        this.alfabeto = alfabeto;
    }
    
    public AutomatoFinitoDeterministico() {
    	
    }
    
    public boolean verificaCadeia(String cadeia) {
        Estado estadoAtual = estadoInicial;
        for (char simbolo : cadeia.toCharArray()) {
            estadoAtual = proximoEstado(estadoAtual, simbolo);
            if (estadoAtual == null) return false;
        }
        return estadoAtual.isFinal();
    }

    private Estado proximoEstado(Estado estadoAtual, char simbolo) {
        for (Transicao transicao : transicoes) {
            if (transicao.getEstadoAtual().equals(estadoAtual) && transicao.getSimbolo() == simbolo) {
                return transicao.getEstadoDestino();
            }
        }
        return null;
    }
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Estado> getEstados() {
		return estados;
	}
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	public Estado getEstadoInicial() {
		return estadoInicial;
	}
	public void setEstadoInicial(Estado estadoInicial) {
		this.estadoInicial = estadoInicial;
	}
	public List<Transicao> getTransicoes() {
		return transicoes;
	}
	public void setTransicoes(List<Transicao> transicoes) {
		this.transicoes = transicoes;
	}
	public List<Character> getAlfabeto() {
		return alfabeto;
	}
	public void setAlfabeto(List<Character> alfabeto) {
		this.alfabeto = alfabeto;
	}

    
    
	
}
