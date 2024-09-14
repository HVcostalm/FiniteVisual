package model;

public class Transicao {
	private Estado estadoAtual;
    private char simbolo;
    private Estado estadoDestino;
    
	public Transicao(Estado estadoAtual, char simbolo, Estado estadoDestino) {
		super();
		this.estadoAtual = estadoAtual;
		this.simbolo = simbolo;
		this.estadoDestino = estadoDestino;
	}
	
	public Estado getEstadoAtual() {
		return estadoAtual;
	}
	public void setEstadoAtual(Estado estadoAtual) {
		this.estadoAtual = estadoAtual;
	}
	public char getSimbolo() {
		return simbolo;
	}
	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}
	public Estado getEstadoDestino() {
		return estadoDestino;
	}
	public void setEstadoDestino(Estado estadoDestino) {
		this.estadoDestino = estadoDestino;
	}
    
}
