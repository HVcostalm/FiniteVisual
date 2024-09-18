package model;

public class Estado {
	private String nome;
    private boolean isFinal;
    
	public Estado(String nome, boolean isFinal) {
		super();
		this.nome = nome;
		this.isFinal = isFinal;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isFinal() {
		return isFinal;
	}
	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	@Override
	public String toString() {
		return "Estado [nome=" + nome + ", isFinal=" + isFinal + "]";
	}
    
    
}
