package view;
import model.Estado;
import model.Transicao;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DiagramaEstados extends JPanel {
    private List<Estado> estados;
    private List<Transicao> transicoes;

    public DiagramaEstados(List<Estado> estados, List<Transicao> transicoes) {
        this.estados = estados;
        this.transicoes = transicoes;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Configurações básicas de renderização
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int raio = 40; // Tamanho do círculo para os estados
        int margem = 200; // Margem entre estados

        // Posição inicial dos estados
        int x = margem;
        int y = 100;

        // Armazenar as posições dos estados para desenhar transições corretamente
        int[] xEstados = new int[estados.size()];
        int[] yEstados = new int[estados.size()];

        // Desenhar os estados como círculos
        for (int i = 0; i < estados.size(); i++) {
            Estado estado = estados.get(i);
            xEstados[i] = x;
            yEstados[i] = y;

            // Se for estado final, desenhe um círculo duplo
            if (estado.isFinal()) {
                g2d.drawOval(x - 5, y - 5, raio + 10, raio + 10); // Círculo externo
            }
            g2d.drawOval(x, y, raio, raio); // Círculo para o estado

            // Escreve o nome do estado no centro do círculo
            g2d.drawString(estado.getNome(), x + raio / 3, y + raio / 2);

            // Atualiza a posição para o próximo estado
            x += margem;
        }

        // Desenhar as transições (setas)
        for (Transicao transicao : transicoes) {
            Estado origem = transicao.getEstadoOrigem();
            Estado destino = transicao.getEstadoDestino();
            char simbolo = transicao.getSimbolo();

            int idxOrigem = estados.indexOf(origem);
            int idxDestino = estados.indexOf(destino);

            // Coordenadas dos estados de origem e destino
            int xOrigem = xEstados[idxOrigem] + raio / 2;
            int yOrigem = yEstados[idxOrigem] + raio / 2;
            int xDestino = xEstados[idxDestino] + raio / 2;
            int yDestino = yEstados[idxDestino] + raio / 2;

            if (origem.equals(destino)) {
                // Desenha um loop para transição de um estado para si mesmo
                g2d.drawArc(xOrigem - 20, yOrigem - 50, 40, 40, 0, 360);
                g2d.drawString(String.valueOf(simbolo), xOrigem, yOrigem - 60); // Posição do símbolo
            } else {
                // Desenha uma linha para transição entre dois estados diferentes
                if (idxOrigem < idxDestino) {
                    // Transição normal (linha reta)
                    g2d.drawLine(xOrigem, yOrigem, xDestino, yDestino);
                    g2d.drawString(String.valueOf(simbolo), (xOrigem + xDestino) / 2, (yOrigem + yDestino) / 2 - 10);
                } else {
                    // Transição de volta (curva)
                    g2d.drawArc(Math.min(xOrigem, xDestino), yOrigem - 20, Math.abs(xOrigem - xDestino), 40, 0, 180);
                    g2d.drawString(String.valueOf(simbolo), (xOrigem + xDestino) / 2, (yOrigem + yDestino) / 2 + 30);
                }
            }

        
        }
    }

 

    // Função para mostrar o painel em uma janela
    public static void exibirAutomatoGrafico(List<Estado> estados, List<Transicao> transicoes) {
        JFrame frame = new JFrame("Diagrama de Estados");
        DiagramaEstados painel = new DiagramaEstados(estados, transicoes);
        frame.add(painel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}




