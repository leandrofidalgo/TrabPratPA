package pt.isec.laf.jogo.logica;

import java.util.ArrayList;
import java.util.HashMap;
import pt.isec.laf.jogo.logica.dados.DadosJogo;
import pt.isec.laf.jogo.logica.dados.Jogador;
import pt.isec.laf.jogo.logica.dados.Replay;
import pt.isec.laf.jogo.logica.estados.*;

/**
 *
 * @author leandro
 */
public class MaquinaDeEstados {

    private DadosJogo dadosJogo;
    //private Jogador j1, j2;
    private IEstado estado;

    public MaquinaDeEstados() {
        this.dadosJogo = new DadosJogo();
        this.estado = new MenuPrincipal(dadosJogo);
    }

    private DadosJogo getDadosJogo() {
        return this.dadosJogo;
    }

    private void setDadosJogo(DadosJogo dadosJogo) {
        this.dadosJogo = dadosJogo;
    }

    //-------------------------------------verificar estado atual-----------------------------
    public boolean isMenuPrincipal() {
        return estado instanceof MenuPrincipal;
    }

    public boolean isEscolherModoJogo() {
        return estado instanceof EscolherModoJogo;
    }

    public boolean isModoCPUXCPU() {
        return estado instanceof ModoCPUXCPU;
    }

    public boolean isModoPessoaXCPU() {
        return estado instanceof ModoPessoaXCPU;
    }

    public boolean isModoPessoaXPessoa() {
        return estado instanceof ModoPessoaXPessoa;
    }

    public boolean isEscolherProximoJogador() {
        return estado instanceof EscolherProximoJogador;
    }

    public boolean isProximaJogada4Linha() {
        return estado instanceof ProximaJogada4Linha;
    }

    public boolean isVerificarSeAcabou() {
        return estado instanceof VerificarSeAcabou;
    }

    public boolean isEscolherJogarMiniJogo() {
        return estado instanceof EscolherJogarMiniJogo;
    }

    public boolean isMiniJogoCalculos() {
        return estado instanceof MiniJogoCalculos;
    }

    public boolean isMiniJogoPalavras() {
        return estado instanceof MiniJogoPalavras;
    }

    public boolean isFimDoJogo() {
        return estado instanceof FimDoJogo;
    }

    //-------------------------------------atualizar estados-----------------------------
    public void iniciarNovoJogo() {
        estado = estado.iniciarJogo();
    }

    public void escolherModoJogo(int valor) {
        if (valor == 1) {
            estado = estado.modoCPUXCPU();
        } else if (valor == 2) {
            estado = estado.modoHomemXCPU();
        } else if (valor == 3) {
            estado = estado.modoHomemXHomem();
        }
    }

    public void definirNomes(String nome, String nome2) {
        estado = estado.definirNomes(nome, nome2);
    }

    public void escolheProximoJogador() {
        estado = estado.definirProximoJogador();
    }

    public void jogaPeca(int coluna) {
        estado = estado.proximaJogada(coluna);
    }

    public void verificaSeAcabou() {
        estado = estado.verificarSeAcabou();
    }

    public void jogarMiniJogo() {
        estado = estado.jogarMiniJogo();
    }

    public void jogaPecaEspecial(int coluna) {
        estado = estado.jogarPecaEspecial(coluna);
    }

    public void carregarJogo(String nomeFicheiro) {
        estado = estado.carregarJogo(nomeFicheiro);
        setDadosJogo(estado.getDadosJogo());
    }

    public void guardarJogo(String nomeFicheiro) {
        estado = estado.guardarJogo(nomeFicheiro);
    }

    public void retornarMenuPrincipal() {
        estado = estado.retornarMenuPrincipal();
    }

    public void naoJogarMiniJogo() {
        estado = estado.naoJogarMiniJogo();
    }

    public void jogarMiniJogoCalculos(int valor) {
        estado = estado.jogarMiniJogoCalculos(valor);
    }

    public void jogarMiniJogoPalavras(String palavras, String palavrasParaEscrever) {
        estado = estado.jogarMiniJogoPalavras(palavras, palavrasParaEscrever);
    }

    public void voltarAtras(int iteracoes) {
        estado = estado.voltarAtras(iteracoes);
    }

    public void guardarDadosJogoReplay() {
        estado = estado.guardarDadosJogo();
    }

    public void replayJogo() {
        estado = estado.replay();
    }

    public void terminarJogo() {
        estado = estado.terminarJogo();
    }

    public void guardarDadosJogo(String nomeFicheiro) {
        estado = estado.guardarJogo(nomeFicheiro);
    }
    
    
    public boolean isJogoAcabou() {
        return getDadosJogo().isJogoAcabou();
    }

    public void apagarLog() {
        getDadosJogo().clearMsgLog();
    }

    public String jogadorAtualString() {
         return getDadosJogo().retornaJogadorAtual().getNome();
    }

    public HashMap<String, ArrayList<Replay>> getReplay() {
        return getDadosJogo().getReplay();
    }

    public void setJogoAcabou(boolean b) {
        getDadosJogo().setJogoAcabou(b);
    }

    public int numJogada() {
        return getDadosJogo().getNum_jogada();
    }

    public void adicionaJogada() {
        getDadosJogo().adicionaJogada();
    }

    public Jogador jogadorAtual() {
        return getDadosJogo().retornaJogadorAtual();
    }

    public String getPerguntaCalculos() {
        return getDadosJogo().getMiniJogo().miniJogoCalculos();
    }

    public String getPerguntaPalavras() {
        return getDadosJogo().getMiniJogo().miniJogoPalavras("Palavras.txt");
    }

    public String vencedor() {
        return getDadosJogo().retornarVencedor();
    }

    public ArrayList<Replay> getReplayKey(String nomeKey) {
        return getDadosJogo().getReplay(nomeKey);
    }

    public int[][] getTabuleiro() {
        return getDadosJogo().getTabuleiro();
    }

    public ArrayList<String> getLogs() {
        return getDadosJogo().getMsgLog();
    }

    public int getNumJogadasMiniJogos(){
        return getDadosJogo().getMiniJogo().getNumVezesQueJogouMiniJogo();
    }
}
