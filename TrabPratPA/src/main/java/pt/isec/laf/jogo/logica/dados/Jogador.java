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
    private int numPecasEspeciais;
    private int numAcertou;

    public Jogador(String nome, int num_jogada, int creditos, int peca) {
        this.nome = nome;
        this.num_jogada = num_jogada;
        this.creditos = creditos;
        this.corDaPeca = peca;
    }
    
}
