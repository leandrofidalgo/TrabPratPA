package pt.isec.laf.jogo.logica;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import pt.isec.laf.jogo.logica.dados.Jogador;
import pt.isec.laf.jogo.logica.dados.Replay;

/**
 *
 * @author leandro
 */
public class MaquinaDeEstadosObservavel {

    private MaquinaDeEstados maquinaDeEstados;
    private PropertyChangeSupport pcs;

    public MaquinaDeEstadosObservavel(MaquinaDeEstados maquinaDeEstados) {
        this.maquinaDeEstados = maquinaDeEstados;
        this.pcs = new PropertyChangeSupport(maquinaDeEstados);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(propertyName, listener);
    }

    //-------------------------------------verificar estado atual-----------------------------
    public boolean isMenuPrincipal() {
        return maquinaDeEstados.isMenuPrincipal();
    }

    public boolean isEscolherModoJogo() {
        return maquinaDeEstados.isEscolherModoJogo();
    }

    public boolean isModoCPUXCPU() {
        return maquinaDeEstados.isModoCPUXCPU();
    }

    public boolean isModoPessoaXCPU() {
        return maquinaDeEstados.isModoPessoaXCPU();
    }

    public boolean isModoPessoaXPessoa() {
        return maquinaDeEstados.isModoPessoaXPessoa();
    }

    public boolean isEscolherProximoJogador() {
        return maquinaDeEstados.isEscolherProximoJogador();
    }

    public boolean isProximaJogada4Linha() {
        return maquinaDeEstados.isProximaJogada4Linha();
    }

    public boolean isVerificarSeAcabou() {
        return maquinaDeEstados.isVerificarSeAcabou();
    }

    public boolean isEscolherJogarMiniJogo() {
        return maquinaDeEstados.isEscolherJogarMiniJogo();
    }

    public boolean isMiniJogoCalculos() {
        return maquinaDeEstados.isMiniJogoCalculos();
    }

    public boolean isMiniJogoPalavras() {
        return maquinaDeEstados.isMiniJogoPalavras();
    }

    public boolean isFimDoJogo() {
        return maquinaDeEstados.isFimDoJogo();
    }

    //-------------------------------------atualizar estados-----------------------------
    public void iniciarNovoJogo() {
        maquinaDeEstados.iniciarNovoJogo();
        pcs.firePropertyChange("estado", null, null);
    }

    public void escolherModoJogo(int valor) {
        maquinaDeEstados.escolherModoJogo(valor);
        if (valor >= 1 && valor <= 3) {
            pcs.firePropertyChange("estado", null, null);
        }
    }

    public void definirNomes(String nome, String nome2) {
        maquinaDeEstados.definirNomes(nome, nome2);
        pcs.firePropertyChange("estado", null, null);
    }

    public void escolheProximoJogador() {
        maquinaDeEstados.escolheProximoJogador();
        pcs.firePropertyChange("estado", null, null);
    }

    public void jogaPeca(int coluna) {
        maquinaDeEstados.jogaPeca(coluna);
        pcs.firePropertyChange("estado", null, null);
    }

    public void verificaSeAcabou() {
        maquinaDeEstados.verificaSeAcabou();
        pcs.firePropertyChange("estado", null, null);
    }

    public void jogarMiniJogo() {
        maquinaDeEstados.jogarMiniJogo();
        pcs.firePropertyChange("estado", null, null);
    }

    public void jogaPecaEspecial(int coluna) {
        maquinaDeEstados.jogaPecaEspecial(coluna);
        pcs.firePropertyChange("estado", null, null);
    }

    public void carregarJogo(String nomeFicheiro) {
        maquinaDeEstados.carregarJogo(nomeFicheiro);
        pcs.firePropertyChange("estado", null, null);
    }

    public void guardarJogo(String nomeFicheiro) {
        maquinaDeEstados.guardarJogo(nomeFicheiro);
        pcs.firePropertyChange("estado", null, null);
    }

    public void retornarMenuPrincipal() {
        maquinaDeEstados.retornarMenuPrincipal();
        pcs.firePropertyChange("estado", null, null);
    }

    public void naoJogarMiniJogo() {
        maquinaDeEstados.naoJogarMiniJogo();
        pcs.firePropertyChange("estado", null, null);
    }

    public void jogarMiniJogoCalculos(int valor) {
        maquinaDeEstados.jogarMiniJogoCalculos(valor);
        pcs.firePropertyChange("estado", null, null);
    }

    public void jogarMiniJogoPalavras(String palavras, String palavrasParaEscrever) {
        maquinaDeEstados.jogarMiniJogoPalavras(palavras, palavrasParaEscrever);
        pcs.firePropertyChange("estado", null, null);
    }

    public void voltarAtras(int iteracoes) {
        maquinaDeEstados.voltarAtras(iteracoes);
        pcs.firePropertyChange("estado", null, null);
    }

    public void guardarDadosJogoReplay() {
        maquinaDeEstados.guardarDadosJogoReplay();
        //pcs.firePropertyChange("estado", null, null);
    }

    public void replayJogo() {
        maquinaDeEstados.replayJogo();
    }

    public void terminarJogo() {
        maquinaDeEstados.terminarJogo();
        pcs.firePropertyChange("estado", null, null);
    }

    public void guardarDadosJogo(String nomeFicheiro) {
        maquinaDeEstados.guardarDadosJogo(nomeFicheiro);
        pcs.firePropertyChange("estado", null, null);

    }

    public boolean isJogoAcabou() {
        return maquinaDeEstados.isJogoAcabou();
    }

    public void apagarLog() {
        maquinaDeEstados.apagarLog();
    }

    public String jogardorAtual() {
        return maquinaDeEstados.jogadorAtualString();
    }

    public HashMap<String, ArrayList<Replay>> getReplay() {
        return maquinaDeEstados.getReplay();
    }

    public void setJogoAcabou(boolean b) {
        maquinaDeEstados.setJogoAcabou(b);
    }

    public int numJogada() {
        return maquinaDeEstados.numJogada();
    }

    public void adicionaJogada() {
        maquinaDeEstados.adicionaJogada();
    }

    public Jogador jogadorAtual() {
        return maquinaDeEstados.jogadorAtual();
    }

    public String getPerguntaCalculos() {
        return maquinaDeEstados.getPerguntaCalculos();
    }

    public String getPerguntaPalavras() {
        return maquinaDeEstados.getPerguntaPalavras();
    }

    public String vencedor() {
        return maquinaDeEstados.vencedor();
    }

    public ArrayList<Replay> getReplayKey(String nomeKey) {
        return maquinaDeEstados.getReplayKey(nomeKey);
    }

    public int[][] getTabuleiro() {
        return maquinaDeEstados.getTabuleiro();
    }

    public ArrayList<String> getLogs() {
        return maquinaDeEstados.getLogs();
    }

    public int getNumJogadasMiniJogos(){
        return maquinaDeEstados.getNumJogadasMiniJogos();
    }

    public int tempoMiniJogo() {
        return maquinaDeEstados.tempoMiniJogo();
    }
}
