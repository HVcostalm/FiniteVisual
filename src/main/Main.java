package main;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import model.AutomatoFinitoDeterministico;
import model.Estado;
import model.Transicao;
import model.Alfabeto;

public class Main {
	public static void main(String[] args) {
		int opcao;
		Scanner scan = new Scanner(System.in);
		
		do {
			System.out.println("O que gostaria de fazer:\n1- Criar novo automato\n2- Ver histórico"
					+ "\n3- Simular Automato\n4- Sair");
			opcao = scan.nextInt();
			switch(opcao){
			case 1:
				System.out.println("Testar");
				break;
			case 2:
				System.out.println("Sem essa função");
				break;
			case 3:
				System.out.println("Sem essa função");
				break;
			case 4:
				System.out.println("Desligando simulador...");
				break;
			default:
				System.out.println("Opção inexistente");
				break;
				
			}
			
	
		}while(opcao!=4);
		
		/*
		Estado q0 = new Estado("q0", false);
        Estado q1 = new Estado("q1", true);
        
        Alfabeto simboloA = new Alfabeto("a");
        Alfabeto simboloB = new Alfabeto("b");
        
        List<Estado> estados = Arrays.asList(q0, q1);
        List<Transicao> transicoes = Arrays.asList(new Transicao(q0, 'a', q1), new Transicao(q1, 'b', q0));
        List<Alfabeto> alfabeto = Arrays.asList(simboloA, simboloB);
        
        AutomatoFinitoDeterministico dfa = new AutomatoFinitoDeterministico("teste 1",estados, q0, transicoes, alfabeto);
        
        System.out.println(dfa.verificaCadeia("ab")); // false
        System.out.println(dfa.verificaCadeia("a")); // true
        */
		
		scan.close();
	}
	
	public void criarAutomato() {
		AutomatoFinitoDeterministico dfa = new AutomatoFinitoDeterministico();
		
	}
}
