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

    private boolean proximoMiniJogo; //qual o proximo mini jogo que ira ser realizado //false = calculos & true = palavras
    private int numMiniJogos; //quantos mini jogos foram realizados

    //mensagens de log
    private ArrayList<String> logMensagens;

    public DadosJogo(ArrayList<Jogador> jogadores, int numJogada) {
        this.jogadores = jogadores;
        this.numJogada = numJogada;
        this.tabuleiro = new int[LINHAS][COLUNAS];
        this.proximoMiniJogo = false;
    }

    public DadosJogo() {
        this.tabuleiro = new int[LINHAS][COLUNAS];
    }

    //----------------- Getters e Setters -------------------
    public void clearMsgLog() {
        logMensagens.clear();
    }

    public void addMsgLog(String msg) {
        logMensagens.add(msg);
    }

    public ArrayList<String> getMsgLog() {
        return logMensagens;
    }

    public int getNum_jogada() {
        return numJogada;
    }

    public int getNumMiniJogos() {
        return numMiniJogos;
    }

    public boolean isProximoMiniJogo() {
        return proximoMiniJogo;
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

    public void incrementaNumMiniJogo() {
        this.numMiniJogos++;
    }

    public void setJogoAcabou(boolean jogoAcabou) {
        this.jogoAcabou = jogoAcabou;
    }

    public void setProximoMiniJogo(boolean proximoMiniJogo) {
        this.proximoMiniJogo = proximoMiniJogo;
    }

    //--------- Funcoes uteis para fazer algo ------------
    public void adicionaJogador(Jogador jogador) {
        jogadores.add(jogador);
        addMsgLog("Jogador adicionado com sucesso!");
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
