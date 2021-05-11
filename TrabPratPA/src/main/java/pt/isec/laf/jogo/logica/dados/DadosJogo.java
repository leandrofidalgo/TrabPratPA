package pt.isec.laf.jogo.logica.dados;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author leandro
 */
public class DadosJogo implements Serializable {

    public static final int LINHAS = 6;
    public static final int COLUNAS = 7;

    private ArrayList<Jogador> jogadores;
    private int numJogada; //numero de jogadas de todos os jogadores
    private int[][] tabuleiro; //Tabuleiro do jogo //se 0 -> vazio, se 1 -> jogador 1, se 2 -> jogador 2
    private Random rand = new Random();
    private boolean jogoAcabou = false;
    private MiniJogo miniJogo;

    private boolean proximoMiniJogo; //qual o proximo mini jogo que ira ser realizado //false = calculos & true = palavras
    private int numMiniJogos; //quantos mini jogos foram realizados

    //mensagens de log
    private ArrayList<String> logMensagens;

    //guardar jogos para replay
    private ArrayList<Replay> iteracoes;
    ArrayList<ArrayList<Replay>> replay;

    //guarda tabuleiros de cada jogada
    private Stack<int[][]> tabuleiros;

    public DadosJogo(ArrayList<Jogador> jogadores, int numJogada) {
        this.jogadores = jogadores;
        this.numJogada = numJogada;
        this.tabuleiro = new int[LINHAS][COLUNAS];
        this.proximoMiniJogo = false;
        logMensagens = new ArrayList<>();
        iteracoes = new ArrayList<>();
        tabuleiros = new Stack<>();
    }

    public DadosJogo() {
        this.tabuleiro = new int[LINHAS][COLUNAS];
        this.numJogada = 0;
        this.jogadores = new ArrayList<>();
        this.proximoMiniJogo = false;
        logMensagens = new ArrayList<>();
        miniJogo = new MiniJogo();
        iteracoes = new ArrayList<>();
        tabuleiros = new Stack<>();
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

    public ArrayList<Replay> getIteracoes() {
        return iteracoes;
    }

    public ArrayList<ArrayList<Replay>> getReplay() {
        return replay;
    }

    public boolean isProximoMiniJogo() {
        return proximoMiniJogo;
    }

    public Random getRandom() {
        return rand;
    }

    public MiniJogo getMiniJogo() {
        return miniJogo;
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
        this.numMiniJogos = this.numMiniJogos + 1;
    }

    public void setJogoAcabou(boolean jogoAcabou) {
        this.jogoAcabou = jogoAcabou;
    }

    public void setProximoMiniJogo(boolean proximoMiniJogo) {
        this.proximoMiniJogo = proximoMiniJogo;
    }

    public void setTabuleiro(int[][] tabuleiroAnterior) {
        this.tabuleiro = tabuleiroAnterior;
    }

    public void incrementaNumJogadas() {
        this.numJogada = this.numJogada + 1;
    }

    //--------- Funcoes uteis para fazer algo ------------
    public void adicionaJogador(Jogador jogador) {
        jogadores.add(jogador);
        addMsgLog("Jogador adicionado com sucesso!");
    }

    public void adicionaJogada() {
        //verificar se ja tenho 5
        int[][] aux = new int[LINHAS][COLUNAS];
        for (int i = 0; i < LINHAS; i++) {
            System.arraycopy(this.tabuleiro[i], 0, aux[i], 0, COLUNAS);
        }
        if (tabuleiros.size() > 5) {
            tabuleiros.remove(0);
        }
        tabuleiros.push(aux);
        addMsgLog("Tabuleiro adicionado com sucesso!");
    }

    public int[][] getJogadaAnterior(int iteracoes) {
        if (tabuleiros.size() >= iteracoes) {
            for (int i = 0; i < iteracoes; i++) {
                tabuleiros.pop();
            }
            return tabuleiros.pop();
        }
        return null;
    }

    public Jogador retornaJogadorAtual() {
        for (int i = 0; i < 2; i++) {
            if (jogadores.get(i).isVezDoJogador()) {
                return jogadores.get(i);
            }
        }
        return null;
    }

    public Jogador retornarOOutroJogador() {
        for (int i = 0; i < 2; i++) {
            if (!jogadores.get(i).isVezDoJogador()) {
                return jogadores.get(i);
            }
        }
        return null;
    }

    public void setVencedor() {
        Jogador j = retornaJogadorAtual();
        j.setVencedor(true);
    }

    public String retornarVencedor() {
        for (int i = 0; i < 2; i++) {
            if (jogadores.get(i).isVencedor()) {
                return jogadores.get(i).getNome();
            }
        }
        return null;
    }

    public void reverteVezdoJogador() {
        for (int i = 0; i < 2; i++) {
            if (jogadores.get(i).isVezDoJogador()) {
                jogadores.get(i).setVezDoJogador(false);
            } else {
                jogadores.get(i).setVezDoJogador(true);
            }
        }
    }

    public int gerarColunaAleatoria() {
        int coluna = getRandom(0, 6);
        return coluna;
    }

    public boolean verificaSeJogoAcabou() {
        boolean ganhouLinhas = verificaLinhas();
        boolean ganhouColunas = verificaColunas();
        boolean ganhouDiagonais1 = verificaDiagonais();
        boolean ganhouDiagonais2 = verificaDiagonais2();
        return ganhouLinhas || ganhouColunas || ganhouDiagonais1 || ganhouDiagonais2;
    }

    public boolean verificaSeEstaTudoPreenchido() {
        int conta = 0;
        for (int i = 0; i < 7; i++) {
            if (tabuleiro[0][i] == 1 || tabuleiro[0][i] == 2) {
                conta++;
            }
        }
        return conta == 7;
    }

    public int getRandom(int min, int max) {
        return rand.nextInt(max - min + 1) + min;
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
                    if (getTabuleiro()[linha][coluna] == jog.getCorDaPeca()) {
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
        for (int i = 0; (6 - i) < 3; i++) {
            for (int j = 3; j < 7; j++) {
                ganhou = 0;
                for (int k = 0, linha = i, coluna = j; k < 4; k++, linha++, coluna--) {
                    if (getTabuleiro()[linha][coluna] == jog.getCorDaPeca()) {
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

    public void adicionarJogos(String str) {
        int[][] aux = new int[LINHAS][COLUNAS];
        for (int i = 0; i < LINHAS; i++) {
            System.arraycopy(this.tabuleiro[i], 0, aux[i], 0, COLUNAS);
        }
        Replay replay = new Replay(str, aux, false, retornaJogadorAtual());
        iteracoes.add(replay);
        addMsgLog("Jogada adicionada com sucesso!");
    }
    
    public void adicionaMiniJogo(String str){
        Replay replay = new Replay(str, null, true, retornaJogadorAtual());
        iteracoes.add(replay);
    }

    public void setReplay(ArrayList<ArrayList<Replay>> jogos) {
        this.replay = jogos;
    }

}
