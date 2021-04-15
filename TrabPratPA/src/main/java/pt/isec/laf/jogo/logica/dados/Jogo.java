package pt.isec.laf.jogo.logica.dados;

import java.util.ArrayList;

/**
 *
 * @author leandro
 */
public class Jogo {

    public static final int LINHAS = 6;
    public static final int COLUNAS = 7;

    private ArrayList<Jogador> jogadores;
    private int numJogada; //numero de jogadas de todos os jogadores
    private int[][] tabuleiro; //Tabuleiro do jogo

    public Jogo(ArrayList<Jogador> jogadores, int numJogada) {
        this.jogadores = jogadores;
        this.numJogada = numJogada;
        this.tabuleiro = new int[LINHAS][COLUNAS];
    }

    public void preencheTabuleiroVazio() {
        for (int i = 0; i < LINHAS; i++) {
            for (int j = 0; j < COLUNAS; j++) {
                tabuleiro[i][j] = 0;
            }
        }
    }

    public void teste() {
        tabuleiro[0][1] = 2;
        tabuleiro[0][2] = 1;
        tabuleiro[3][4] = 1;
        tabuleiro[5][6] = 2;
        tabuleiro[2][2] = 1;
        tabuleiro[3][2] = 2;
    }

    public void verificarSeAlguemGanhou(){
        
    }
    
    public int getNum_jogada() {
        return numJogada;
    }
    
    public StringBuilder toStringTabuleiro() {
        StringBuilder sB = new StringBuilder();
        for (int i = 0; i < LINHAS; i++) {
            sB.append("|");
            for (int j = 0; j < COLUNAS; j++) {
                //sB.append("|");
                if(tabuleiro[i][j] == 0){
                    sB.append(" |");
                }
                if(tabuleiro[i][j] == 1){
                    sB.append("0|");
                }
                if(tabuleiro[i][j] == 2){
                    sB.append("X|");
                }
                //sB.append("|");
            }
            sB.append("\n");
        }
        return sB;
    }
}
