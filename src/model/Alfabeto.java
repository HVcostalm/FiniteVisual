package model;

import java.util.List;

public class Alfabeto {
	private List<String> simbolos;
	private String simbolo;
	
	public Alfabeto(String simbolo) {
		super();
		this.simbolo = simbolo;
	}

	public List<String> getSimbolos() {
		return simbolos;
	}

	public void setSimbolos(List<String> simbolos) {
		this.simbolos = simbolos;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}
	
	
	
}
