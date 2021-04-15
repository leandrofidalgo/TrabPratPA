package pt.isec.laf.jogo.logica.dados;

import java.util.ArrayList;

/**
 *
 * @author leandro
 */
public class DadosJogo {
    public static final int LINHAS = 6;
    public static final int COLUNAS = 7;

    private ArrayList<Jogador> jogadores;
    private int numJogada; //numero de jogadas de todos os jogadores
    private int[][] tabuleiro; //Tabuleiro do jogo

    public DadosJogo(ArrayList<Jogador> jogadores, int numJogada) {
        this.jogadores = jogadores;
        this.numJogada = numJogada;
        this.tabuleiro = new int[LINHAS][COLUNAS];
    }

    public DadosJogo() {
        this.tabuleiro = new int[LINHAS][COLUNAS];
    }
    
    public int getNum_jogada() {
        return numJogada;
    }
    
    public StringBuilder toStringTabuleiro() {
        StringBuilder sB = new StringBuilder();
        for (int i = 0; i < LINHAS; i++) {
            sB.append("|");
            for (int j = 0; j < COLUNAS; j++) {
                if(tabuleiro[i][j] == 0){
                    sB.append(" |");
                }
                if(tabuleiro[i][j] == 1){
                    sB.append("O|");
                }
                if(tabuleiro[i][j] == 2){
                    sB.append("X|");
                }
            }
            sB.append("\n");
        }
        return sB;
    }
}
