package pt.isec.laf.jogo.logica.dados;

import java.util.ArrayList;
import java.util.Random;

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
    private Random rand = new Random();

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

    public Random getRandom() {
        return rand;
    }

    public int[][] getTabuleiro() {
        return tabuleiro;
    }

    public void adicionaJogador(Jogador jogador) {
        jogadores.add(jogador);
    }
}
