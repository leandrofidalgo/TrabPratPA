package pt.isec.laf.jogo.logica.dados;

/**
 *
 * @author leandro
 */
public class Jogador {

    private String nome; //os jogadores têm de ter nomes diferentes
    private int num_jogada; //numero da jogada atual
    private int creditos; //numero de creditos do jogador
    //private boolean pessoa; //se true é uma pessoa se false é o computador
    private int corDaPeca; //se 1 = VERDE, se 2 = VERMELHO
    private int numPecasEspeciais; //quantas pecas especiais tem
    private int numAcertou;
    private boolean vezDoJogador; //dewfine a vez do jogador

    public Jogador(String nome, int num_jogada, int creditos, int peca) {
        this.nome = nome;
        this.num_jogada = num_jogada;
        this.creditos = creditos;
        this.corDaPeca = peca;
        vezDoJogador = true;
        //TODO podera ter de ser mudado...
        numPecasEspeciais = 0;
        numAcertou = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getNumPecasEspeciais() {
        return numPecasEspeciais;
    }

    public boolean isVezDoJogador() {
        return vezDoJogador;
    }

    public void setVezDoJogador(boolean vezDoJogador) {
        this.vezDoJogador = vezDoJogador;
    }

}
