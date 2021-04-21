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
    private boolean jogoAcabou = false;

    public DadosJogo(ArrayList<Jogador> jogadores, int numJogada) {
        this.jogadores = jogadores;
        this.numJogada = numJogada;
        this.tabuleiro = new int[LINHAS][COLUNAS];
    }

    public DadosJogo() {
        this.tabuleiro = new int[LINHAS][COLUNAS];
    }

    //----------------- Getters e Setters -------------------
    public int getNum_jogada() {
        return numJogada;
    }

    public Random getRandom() {
        return rand;
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public int[][] getTabuleiro() {
        return tabuleiro;
    }

    public boolean isJogoAcabou() {
        return jogoAcabou;
    }

    public void setJogoAcabou(boolean jogoAcabou) {
        this.jogoAcabou = jogoAcabou;
    }

    //--------- Funcoes uteis para fazer algo ------------
    public void adicionaJogador(Jogador jogador) {
        jogadores.add(jogador);
    }

    public int getRandom(int min, int max) {
        return rand.nextInt(max) + min;
    }

    public void zerarDadosJogo() {
        jogadores = new ArrayList<>();
        numJogada = 0;
        tabuleiro = new int[LINHAS][COLUNAS];
    }
}
