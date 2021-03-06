package pt.isec.laf.jogo.logica.dados;

import java.io.Serializable;

/**
 *
 * @author leandro
 */
public class Jogador implements Serializable {

    private String nome; //os jogadores têm de ter nomes diferentes
    private int numJogada; //numero da jogada atual TODO numero de jogadas ate minijogo
    private int creditos; //numero de creditos do jogador
    //private boolean pessoa; //se true é uma pessoa se false é o computador
    private int corDaPeca; //se 1 = VERDE, se 2 = VERMELHO
    private int numPecasEspeciais; //quantas pecas especiais tem
    private int numAcertou;
    private boolean vezDoJogador; //dewfine a vez do jogador
    private boolean vencedor;

    public Jogador(String nome, int num_jogada, int creditos, int peca) {
        this.nome = nome;
        this.numJogada = num_jogada;
        this.creditos = creditos;
        this.corDaPeca = peca;
        vezDoJogador = true;
        numPecasEspeciais = 0;
        numAcertou = 0;
        vencedor = false;
    }

    public String getNome() {
        return nome;
    }

    public int getNum_jogada() {
        return numJogada;
    }

    public int getNumPecasEspeciais() {
        return numPecasEspeciais;
    }

    public int getCorDaPeca() {
        return corDaPeca;
    }

    public int getCreditos() {
        return creditos;
    }

    public boolean isVencedor() {
        return vencedor;
    }

    public boolean isVezDoJogador() {
        return vezDoJogador;
    }

    public void setVezDoJogador(boolean vezDoJogador) {
        this.vezDoJogador = vezDoJogador;
    }

    public void setNum_jogada(int num_jogada) {
        this.numJogada = num_jogada;
    }

    public void setVencedor(boolean vencedor) {
        this.vencedor = vencedor;
    }

    public void decrementarNumPecasEspeciais() {
        this.numPecasEspeciais = this.numPecasEspeciais - 1;
    }

    public void incrementaNumJogada() {
        this.numJogada = this.numJogada + 1;
    }

    public void incrementaNumPecasEspeciais() {
        this.numPecasEspeciais = this.numPecasEspeciais + 1;
    }

    public boolean decrementarCreditos(int iteracoes) {
        if (this.creditos >= iteracoes) {
            this.creditos = creditos - iteracoes;
            return true;
        }
        return false;
    }
}
