package pt.isec.laf.jogo.logica;

import java.util.ArrayList;
import pt.isec.laf.jogo.logica.dados.DadosJogo;
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
        dadosJogo = new DadosJogo();
        estado = new MenuPrincipal(dadosJogo);
    }

    public DadosJogo getDadosJogo() {
        return dadosJogo;
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

    public void jogaPecaEspecial() {
        estado = estado.jogarPecaEspecial();
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

    public void jogarMiniJogoPalavras(String palavras,String palavrasParaEscrever) {
        estado = estado.jogarMiniJogoPalavras(palavras, palavrasParaEscrever);
    }
}
