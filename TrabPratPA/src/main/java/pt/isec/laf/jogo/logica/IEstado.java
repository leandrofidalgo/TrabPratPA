package pt.isec.laf.jogo.logica;

import java.util.ArrayList;
import pt.isec.laf.jogo.logica.dados.DadosJogo;

/**
 *
 * @author leandro
 */
public interface IEstado {
    //Novos estados
    IEstado iniciarJogo(); //menuprincipal
    IEstado carregarJogo(); //menuprincipal
    IEstado modoCPUXCPU(); //escolhermodojogo
    IEstado modoHomemXCPU(); //escolhermodojogo
    IEstado modoHomemXHomem(); //escolhermodojogo
    IEstado definirNomes(String nome, String nome2); //dentro de cada modo de jogo
    IEstado definirProximoJogador();
    IEstado proximaJogada(int coluna);
    IEstado verificarSeAcabou();
    IEstado jogarMiniJogo();
    IEstado jogarPecaEspecial(int coluna);
    //IEstado 
    IEstado retornarMenuPrincipal();
    IEstado naoJogarMiniJogo();
    IEstado jogarMiniJogoCalculos(int valor);
    IEstado jogarMiniJogoPalavras(String palavras, String palavrasParaEscrever);

}
