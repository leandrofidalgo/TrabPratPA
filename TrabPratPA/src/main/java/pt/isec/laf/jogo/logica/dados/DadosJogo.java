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
    private int[][] tabuleiro; //Tabuleiro do jogo //se 0 -> vazio, se 1 -> jogador 1, se 2 -> jogador 2
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

    public Jogador retornaJogadorAtual() {
        for (int i = 0; i < 2; i++) {
            if (jogadores.get(i).isVezDoJogador()) {
                return jogadores.get(i);
            }
        }
        return null;
    }

    public boolean iniciaProximaJogada(int coluna) {
        //fazer a logica de colocar uma peca
        for (int i = 6 - 1; i >= 0; i--) {
            if (getTabuleiro()[i][coluna] == 0) {
                Jogador jog = retornaJogadorAtual();
                if (jog == null) {
                    return false;
                } else {
                    int corPeca = jog.getCorDaPeca();
                    getTabuleiro()[i][coluna] = corPeca;
                    return true;
                }
            }
        }
        return false;
    }

    public void verificaSeJogoAcabou() {
        boolean ganhouLinhas = verificaLinhas();
        boolean ganhouColunas = verificaColunas();
        boolean ganhouDiagonais1 = verificaDiagonais();
        boolean ganhouDiagonais2 = verificaDiagonais2();
    }

    public int getRandom(int min, int max) {
        return rand.nextInt(max) + min;
    }

    public void zerarDadosJogo() {
        jogadores = new ArrayList<>();
        numJogada = 0;
        tabuleiro = new int[LINHAS][COLUNAS];
    }

    public boolean verificaLinhas() {
        //verificar linha a linha
        int ganhou = 0;
        Jogador jog = retornaJogadorAtual();
        for (int linha = 0; linha < 6; linha++) {
            for (int coluna = 0; coluna < 7; coluna++) {
                if (getTabuleiro()[linha][coluna] == jog.getCorDaPeca()) {
                    ganhou++;
                    if (ganhou == 4) {
                        return true;
                    }
                } else {
                    ganhou = 0;
                }
            }
            ganhou = 0;
        }
        return false;
    }

    public boolean verificaColunas() {
        //verificar coluna a coluna
        int ganhou = 0;
        Jogador jog = retornaJogadorAtual();
        for (int coluna = 0; coluna < 7; coluna++) {
            for (int linha = 0; linha < 6; linha++) {
                if (getTabuleiro()[linha][coluna] == jog.getCorDaPeca()) {
                    ganhou++;
                    if (ganhou == 4) {
                        return true;
                    }
                } else {
                    ganhou = 0;
                }
            }
            ganhou = 0;
        }
        return false;
    }

    public boolean verificaDiagonais() {
        //verificar a diagonal para baixo
        int ganhou;
        Jogador jog = retornaJogadorAtual();

        for (int i = 0; (6 - i) < 3; i++) {
            for (int j = 0; (7 - j) < 3; j++) {
                ganhou = 0;
                for (int k = 0, linha = i, coluna = j; k < 4; k++, linha++, coluna++) {
                    if (getTabuleiro()[linha][coluna] == jog.getCorDaPeca()){
                        ganhou++;
                    }
                }
                if (ganhou == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean verificaDiagonais2() {
        //verificar a diagonal para cima
        int ganhou = 0;
        Jogador jog = retornaJogadorAtual();
        for (int coluna = 0; coluna < 6; coluna++) {
            for (int linha = 0; linha < 7; linha++) {
                if (getTabuleiro()[linha][coluna] == jog.getCorDaPeca()) {
                    ganhou++;
                    if (ganhou == 4) {
                        return true;
                    }
                } else {
                    ganhou = 0;
                }
            }
            ganhou = 0;
        }
        return false;
    }

}
