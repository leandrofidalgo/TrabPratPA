package pt.isec.laf.jogo;

import java.util.ArrayList;
import pt.isec.laf.jogo.logica.dados.Jogador;
import pt.isec.laf.jogo.logica.dados.Jogo;

/**
 *
 * @author leandro
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Comecei o jogo!");
        Jogador jogador1 = new Jogador("Pedro Parvo", 0, 5, true);
        Jogador jogador2 = new Jogador("Maquina", 0, 5, false);
        ArrayList<Jogador> jogadores = new ArrayList<>();
        jogadores.add(jogador1);
        jogadores.add(jogador2);
        Jogo jogo = new Jogo(jogadores, 0);
        System.out.println("A iniciar o jogo...\nJogada numero: " + jogo.getNum_jogada());
        jogo.preencheTabuleiroVazio();
        jogo.teste();
        StringBuilder sB = jogo.toStringTabuleiro();
        System.out.println(sB);
    }
}
