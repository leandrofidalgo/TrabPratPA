package pt.isec.laf.jogo.logica.estados;

import java.util.ArrayList;
import java.util.Random;
import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.DadosJogo;

/**
 *
 * @author leandro
 */
public abstract class EstadoAdaptador implements IEstado {

    private DadosJogo dadosJogo;

    public EstadoAdaptador(DadosJogo dadosJogo) {
        this.dadosJogo = dadosJogo;
    }

    public DadosJogo getDadosJogo() {
        return dadosJogo;
    }

    public void setDadosJogo(DadosJogo dadosJogo) {
        this.dadosJogo = dadosJogo;
    }

    //funcoes para usar nos estados
    @Override
    public IEstado definirJogadores(DadosJogo dadosJogo, int valor, ArrayList<String> nomes) {
        return this;
    }

    @Override
    public IEstado proximaJogada(DadosJogo dadosJogo) {
        return this;
    }

    @Override
    public IEstado miniJogoCalculos(DadosJogo dadosJogo, int valor, int randomValor1, int randomValor2, String sinal) {
        return this;
    }

    @Override
    public IEstado miniJogoPalavras() {
        return this;
    }

    @Override
    public IEstado verificarSeAlguemGanhou() {
        return this;
    }
}
