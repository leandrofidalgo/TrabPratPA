package pt.isec.laf.jogo.logica.dados;

import java.io.Serializable;

/**
 *
 * @author leandro
 */
public class Replay implements Serializable{

    String tipoReplay; //jogada | minijogo
    int[][] tabuleiro;
    boolean ganhouMiniJogo;
    Jogador jogador;

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
