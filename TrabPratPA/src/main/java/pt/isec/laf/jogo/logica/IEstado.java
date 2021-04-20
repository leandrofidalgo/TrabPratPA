package pt.isec.laf.jogo.logica;

import java.util.ArrayList;
import pt.isec.laf.jogo.logica.dados.DadosJogo;

/**
 *
 * @author leandro
 */
public interface IEstado {
    IEstado definirJogadores(DadosJogo dadosJogo, int valor, ArrayList<String> nomes);
    IEstado proximaJogada(DadosJogo dadosJogo);
    IEstado miniJogoCalculos(DadosJogo dadosJogo, int valor, int randomValor1, int randomValor2, String sinal);
    IEstado miniJogoPalavras();
    IEstado verificarSeAlguemGanhou();
}
