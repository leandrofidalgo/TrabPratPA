package pt.isec.laf.jogo.logica.dados;

import java.io.Serializable;

/**
 *
 * @author leandro
 */
public class Replay implements Serializable{

    private String tipoReplay; //jogada | minijogo
    private int[][] tabuleiro;
    private boolean ganhouMiniJogo;
    private Jogador jogador;
    
    public static final String JOGADA = "jogada";
    public static final String MINIJOGO = "minijogo";

    public Replay(String tipoReplay, int[][] tabuleiro, boolean ganhouMiniJogo, Jogador jogador) {
        this.tipoReplay = tipoReplay;
        this.tabuleiro = tabuleiro;
        this.ganhouMiniJogo = ganhouMiniJogo;
        this.jogador = jogador;
    }

    public String getTipoReplay() {
        return tipoReplay;
    }

    public int[][] getTabuleiro() {
        return tabuleiro;
    }

    public boolean isGanhouMiniJogo() {
        return ganhouMiniJogo;
    }

    public Jogador getJogador() {
        return jogador;
    }

}
