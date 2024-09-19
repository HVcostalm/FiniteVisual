package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import model.AutomatoFinitoDeterministico;
import model.Estado;
import model.Transicao;

public class Main {
	public static void main(String[] args) {
		int opcao;
		Scanner scan = new Scanner(System.in);
		List<AutomatoFinitoDeterministico> automatos = new ArrayList<>();
		
		// Exemplo simples
        Estado q0 = new Estado("q0", false);
        Estado q1 = new Estado("q1", true);
        
        List<Estado> estados = Arrays.asList(q0, q1);
        List<Transicao> transicoes = Arrays.asList(new Transicao(q0, 'a', q1), new Transicao(q1, 'b', q0));
        List<Character> alfabeto = Arrays.asList('a', 'b');
        
        AutomatoFinitoDeterministico automatoFinito = new AutomatoFinitoDeterministico("teste1", estados, q0, transicoes, alfabeto);
        automatos.add(automatoFinito);
		
		do {
			System.out.println("O que gostaria de fazer:\n1- Criar novo automato\n2- Ver histórico"
					+ "\n3- Sair");
			opcao = scan.nextInt();
			scan.nextLine();
			switch(opcao){
			case 1:
				System.out.println("\nIndo para tela de criacao de automato...");
				automatos.add(criarAutomato(scan));
				break;
			case 2:
				System.out.println("\nIndo para tela do historico de automato...");
				verHistoricoAutomato(automatos,scan);
				break;
			case 3:
				System.out.println("\nDesligando simulador...");
				break;
			default:
				System.out.println("Opção inexistente");
				break;
				
			}
			
	
		}while(opcao!=3);
		
		scan.close();
	}
	
	public static void verHistoricoAutomato(List<AutomatoFinitoDeterministico> automatos, Scanner scan) {
		String resposta;
		
		if(automatos.isEmpty()) {
			System.out.println("Nenhum automato criado");
			System.out.println("Deseja criar um automato? [s/n]");
			resposta = scan.nextLine();
			if(resposta.equalsIgnoreCase("s"))
				automatos.add(criarAutomato(scan));
			else
				System.out.println("Voltando para tela principal...");
		}
		else {
			do {
				System.out.println("Automato criados:\n");
				System.out.println(automatos + "\n");
				System.out.println("Digite o nome do automato caso queira verificar a cadeia, "
						+ "se não responda 'n' ");
				resposta = scan.nextLine();
				
				for(AutomatoFinitoDeterministico automato: automatos) {
					if(automato.getNome().equalsIgnoreCase(resposta))
					{
						System.out.println("Indo para a tela de verificacao...");
						verificarCadeia(automato, scan);
					}
				}
				if(resposta.equalsIgnoreCase("n")) {
					System.out.println("Voltando para tela principal...");
				}
				else
					System.out.println("Resposta invalida!!!\nDigite novamente\n");
			}while(!resposta.equalsIgnoreCase("n"));
			
		}
		
	}
	
	public static AutomatoFinitoDeterministico criarAutomato(Scanner scanner) {
		AutomatoFinitoDeterministico automatoFinito = new AutomatoFinitoDeterministico();
		String nomeAutomato, resposta;
		Estado estadoInicial = null;
		List<Estado> estados = new ArrayList<>();
		List<Character> alfabeto = new ArrayList<>();
		List<Transicao> transicoes = new ArrayList<>();
	        
		// Criação dos estados
		estados = criarEstados(scanner);

		// Definição do estado inicial
		estadoInicial = definirEstadoInicial(estados,scanner);

		// Criação do alfabeto
		alfabeto = criarAlfabeto(scanner);

		// Criação das transições
		transicoes = criarTransicoes(estados, alfabeto, scanner);
		
		// Definição do nome do automato finito
		System.out.println("Defina o nome deste automato: ");
		nomeAutomato = scanner.nextLine();
		
		// Criação do autômato
		automatoFinito.setTransicoes(transicoes);
		automatoFinito.setAlfabeto(alfabeto);
		automatoFinito.setEstados(estados);
		automatoFinito.setEstadoInicial(estadoInicial);
		automatoFinito.setNome(nomeAutomato);
		System.out.println("Automato criado com sucesso!!!");

		// Verificação de cadeia
		System.out.println("\nDeseja vericar a cadeia do automato criado? [s/n]");
		System.out.println("Obs: Se a sua resposta for nao irá voltar para a tela inicial\n");
		resposta = scanner.nextLine();
		if(resposta.equalsIgnoreCase("s"))
			verificarCadeia(automatoFinito, scanner);
		else
			System.out.println("Voltando para tela principal...");
		return automatoFinito;
	}
	
	public static List<Estado> criarEstados(Scanner scanner) {
		List<Estado> estados = new ArrayList<>();
		int numeroEstados, contadorEstados;
		String nomeEstado, resposta;
		boolean isFinal;
		boolean estadoFinalExiste = false;
		
		// Criação dos estados
		do {
			System.out.println("Quantos estados o autômato terá?");
			numeroEstados = scanner.nextInt();
			scanner.nextLine();
			for (contadorEstados = 0; contadorEstados < numeroEstados; contadorEstados++) {
				System.out.println("Digite o nome do estado " + (contadorEstados + 1) + ":");
				nomeEstado = scanner.nextLine();
				System.out.println("Esse estado é final? (s/n):");
				resposta = scanner.nextLine();
				isFinal = resposta.equalsIgnoreCase("s");
				estados.add(new Estado(nomeEstado, isFinal));
			}
			for (Estado estado : estados) {
				if (estado.isFinal() == true) {
					estadoFinalExiste = true;
					break;
				}
			}
			if (estadoFinalExiste == false) {
				System.out.println("\nNão existe automato final. Crie novamente os estados\n");
				estados.removeAll(estados);
			} else
				System.out.println("Estados criados");
		} while (!estadoFinalExiste);

		return estados;
	}
	
	public static Estado definirEstadoInicial(List<Estado> estados, Scanner scanner) {
		Estado estadoInicial = null;
		String nomeEstadoInicial;
		boolean existeEstadoInicial = false;
		
		// Definição do estado inicial
		do {
			System.out.println("\nVeja abaixo os estados que você criou e escolha o estado inicial:");
			System.out.println(estados);
			System.out.println("Digite o nome do estado inicial:");
			nomeEstadoInicial = scanner.nextLine();
			for (Estado estado : estados) {
				if (estado.getNome().equals(nomeEstadoInicial)) {
					estadoInicial = estado;
					existeEstadoInicial = true;
					break;
				}
			}

			if (estadoInicial == null) {
				System.out.println("Estado inicial inválido. Escolha um existente...");
			}
		}while(!existeEstadoInicial);
		
		
		return estadoInicial;
	}
	
	public static List<Character> criarAlfabeto(Scanner scanner){
		List<Character> alfabetos = new ArrayList<>();
		String alfabeto;

		// Criação do alfabeto
		System.out.println("Digite os símbolos do alfabeto, separados por espaço:");
		alfabeto = scanner.nextLine();
		for (char simboloAlfabeto : alfabeto.toCharArray()) {
			if (simboloAlfabeto != ' ') {
				alfabetos.add(simboloAlfabeto);
			}
		}
		
		return alfabetos;
	}
	
	public static List<Transicao> criarTransicoes(List<Estado> estados, List<Character> alfabetos, Scanner scanner){
		List<Transicao> transicoes = new ArrayList<>();
		int numeroTransicoes, contadorTransicoes;
		String alfabeto, nomeEstadoOrigem, nomeEstadoDestino;
		char simbolo;
		Estado estadoOrigem = null;
		Estado estadoDestino = null;
		
		// Criação das transições
		System.out.println("Quantas transições o autômato terá?");
		numeroTransicoes = scanner.nextInt();
		scanner.nextLine(); // Consumir linha

		for (contadorTransicoes = 0; contadorTransicoes < numeroTransicoes; contadorTransicoes++) {
			System.out.println(estados);
			System.out.println("\nDigite o estado origem da transição " + (contadorTransicoes + 1) + ":");
			nomeEstadoOrigem = scanner.nextLine();
			for (Estado estado : estados) {
				if (estado.getNome().equals(nomeEstadoOrigem)) {
					estadoOrigem = estado;
					break;
				}
			}

			if (estadoOrigem == null) {
				System.out.println("Estado atual inválido.");
				contadorTransicoes--;
				continue;
			}
			
			System.out.println("Digite o símbolo da transição:");
			simbolo = scanner.nextLine().charAt(0);

			if (!alfabetos.contains(simbolo)) {
				System.out.println("Símbolo inválido.");
				contadorTransicoes--;
				continue;
			}

			System.out.println("Digite o estado destino:");
			nomeEstadoDestino = scanner.nextLine();
			for (Estado estado : estados) {
				if (estado.getNome().equals(nomeEstadoDestino)) {
					estadoDestino = estado;
					break;
				}
			}

			if (estadoDestino == null) {
				System.out.println("Estado destino inválido.");
				contadorTransicoes--;
				continue;
			}

			transicoes.add(new Transicao(estadoOrigem, simbolo, estadoDestino));
			estadoOrigem=null;
			estadoDestino=null;
		}
		
		return transicoes;
	}

	public static void verificarCadeia(AutomatoFinitoDeterministico automatoFinito, Scanner scanner) {
		boolean aceita, continua=false;
		String respostaContinuar, cadeia;
		
		do {
			System.out.println("Digite a cadeia para verificar:");
			cadeia = scanner.nextLine();
			aceita = automatoFinito.verificaCadeia(cadeia);
			if (aceita) {
				System.out.println("Cadeia aceita!");
			} else {
				System.out.println("Cadeia rejeitada!");
			}
			
			System.out.println("Deseja continuar verificando cadeias? [s/n]");
			respostaContinuar = scanner.nextLine();
			if(respostaContinuar.equalsIgnoreCase("n"))
				continua=true;
			else
				System.out.println("Voltando para tela principal...");
			
		}while(!continua);
		
	}
}
