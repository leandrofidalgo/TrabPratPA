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

    @Override
    public IEstado iniciarJogo() {
        return this;
    }

    @Override
    public IEstado carregarJogo(String nomeFicheiro) {
        return this;
    }

    @Override
    public IEstado modoCPUXCPU() {
        return this;
    }

    @Override
    public IEstado modoHomemXCPU() {
        return this;
    }

    @Override
    public IEstado modoHomemXHomem() {
        return this;
    }

    @Override
    public IEstado definirNomes(String nome, String nome2) {
        return this;
    }

    @Override
    public IEstado definirProximoJogador() {
        return this;
    }

    @Override
    public IEstado proximaJogada(int coluna) {
        return this;
    }

    @Override
    public IEstado verificarSeAcabou() {
        return this;
    }

    @Override
    public IEstado jogarPecaEspecial(int coluna) {
        return this;
    }

    @Override
    public IEstado jogarMiniJogo() {
        return this;
    }

    @Override
    public IEstado retornarMenuPrincipal() {
        return this;
    }

    @Override
    public IEstado naoJogarMiniJogo() {
        return this;
    }

    @Override
    public IEstado jogarMiniJogoCalculos(int valor) {
        return this;
    }

    @Override
    public IEstado jogarMiniJogoPalavras(String palavras, String palavrasParaEscrever) {
        return this;
    }

    @Override
    public IEstado voltarAtras(int iteracoes) {
        return this;
    }

    @Override
    public IEstado guardarJogo(String nomeFicheiro) {
        return this;
    }

    @Override
    public IEstado guardarDadosJogo() {
        return this;
    }

    @Override
    public IEstado replay() {
        return this;
    }

    @Override
    public IEstado terminarJogo() {
        return this;
    }

}
