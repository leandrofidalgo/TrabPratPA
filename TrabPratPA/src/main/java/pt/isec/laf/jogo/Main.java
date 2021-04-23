package pt.isec.laf.jogo;

import pt.isec.laf.jogo.iu.texto.IUTexto;
import pt.isec.laf.jogo.logica.MaquinaDeEstados;

/**
 *
 * @author leandro
 */
public class Main {
    public static void main(String[] args) {
        IUTexto iU = new IUTexto(new MaquinaDeEstados());
        iU.run();
        /*System.out.println("Comecei o jogo!");
        Jogador jogador1 = new Jogador("Pedro Parvo", 0, 5, true, 1);
        Jogador jogador2 = new Jogador("Maquina", 0, 5, false, 2);
        ArrayList<Jogador> jogadores = new ArrayList<>();
        jogadores.add(jogador1);
        jogadores.add(jogador2);
        MaquinaDeEstados jogo = new MaquinaDeEstados(jogadores, 0);
        System.out.println("A iniciar o jogo...\nJogada numero: " + jogo.getNum_jogada());
        jogo.teste();
        StringBuilder sB = jogo.toStringTabuleiro();
        System.out.println(sB);*/
    }
}
