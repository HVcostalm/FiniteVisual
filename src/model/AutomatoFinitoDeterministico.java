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

    public boolean verificaCadeiaComCaminho(String cadeia, List<String> caminho) {
        Estado estadoAtual = this.estadoInicial;
        caminho.add(estadoAtual.getNome());

        for (char simbolo : cadeia.toCharArray()) {
            boolean transicaoEncontrada = false;

            for (Transicao transicao : transicoes) {
                if (transicao.getEstadoOrigem().equals(estadoAtual) && transicao.getSimbolo() == simbolo) {
                    estadoAtual = transicao.getEstadoDestino();
                    caminho.add(estadoAtual.getNome());
                    transicaoEncontrada = true;
                    break;
                }
            }

            if (!transicaoEncontrada) {
                return false;
            }
        }

        return estadoAtual.isFinal();
    }

    private Estado proximoEstado(Estado estadoOrigem, char simbolo) {
        for (Transicao transicao : transicoes) {
            if (transicao.getEstadoOrigem().equals(estadoOrigem) && transicao.getSimbolo() == simbolo) {
                return transicao.getEstadoDestino();
            }
        }
        return null;
    }

    public void exibirAutomato() {
        System.out.println("Autômato: " + this.nome);
        System.out.println("Estados: ");
        for (Estado estado : this.estados) {
            System.out.print(estado.getNome() + (estado.isFinal() ? " (final)" : "") + "\n");
        }
        System.out.println("\nEstado Inicial: " + this.estadoInicial.getNome());
        System.out.println("Transições:");
        for (Transicao transicao : this.transicoes) {
            System.out.println(transicao.getEstadoOrigem().getNome() + " --" + transicao.getSimbolo() + "--> "
                    + transicao.getEstadoDestino().getNome());
        }
        System.out.println("------------------------------\n");

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
