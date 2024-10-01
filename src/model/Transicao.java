package model;

public class Transicao {
	private Estado estadoOrigem;
    private char simbolo;
    private Estado estadoDestino;
    
	public Transicao(Estado estadoAtual, char simbolo, Estado estadoDestino) {
		super();
		this.estadoOrigem = estadoAtual;
		this.simbolo = simbolo;
		this.estadoDestino = estadoDestino;
	}
	
	public Estado getEstadoOrigem() {
		return estadoOrigem;
	}
	public void setEstadoOrigem(Estado estadoOrigem) {
		this.estadoOrigem = estadoOrigem;
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

	@Override
	public String toString() {
		return this.estadoOrigem.getNome() + " --" + simbolo + "--> " + this.estadoDestino.getNome();
	}
    
	
	
}
