package pt.isec.laf.jogo.logica;

import java.util.ArrayList;
import pt.isec.laf.jogo.logica.dados.DadosJogo;
import pt.isec.laf.jogo.logica.dados.Jogador;
import pt.isec.laf.jogo.logica.estados.DefinirJogadores;
import pt.isec.laf.jogo.logica.estados.EsperaJogada4Linha;

/**
 *
 * @author leandro
 */
public class MaquinaDeEstados {

    private DadosJogo dadosJogo;
    private Jogador j1, j2;
    private IEstado estado;

    public DadosJogo getDadosJogo(){
        return dadosJogo;
    }
    
    public MaquinaDeEstados() {
        dadosJogo = new DadosJogo();
    }

    public void definirJogadores(int valor, ArrayList<String> nomes) {
        estado = estado.definirJogadores(dadosJogo, valor, nomes);
    }

    public void proximaJogada() {
        estado = estado.proximaJogada(dadosJogo);
    }

    public void miniJogoCalculos(int valor, int randomValor1, int randomValor2, String sinal) {

    }
}
