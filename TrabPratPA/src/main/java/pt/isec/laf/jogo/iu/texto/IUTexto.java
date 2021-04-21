package pt.isec.laf.jogo.iu.texto;

import java.util.Scanner;
import pt.isec.laf.jogo.logica.MaquinaDeEstados;

/**
 *
 * @author leandro
 */
public class IUTexto {

    private MaquinaDeEstados maquinaDeEstados;
    private Scanner scanner;

    public IUTexto(MaquinaDeEstados jogo) {
        this.maquinaDeEstados = jogo;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        //miniJogoCalculos();
        //miniJogoPalavras();
        //imprimirTabuleiro();
        //inicioDoJogo();
        //menuPrincipal();
        while (maquinaDeEstados.getDadosJogo().isJogoAcabou()) {
            if (maquinaDeEstados.isMenuPrincipal()) {
                menuPrincipal();
            } else if (maquinaDeEstados.isEscolherModoJogo()) {
                menuEscolherModoJogo();
            } else if(maquinaDeEstados.isModoCPUXCPU()){
                menuCPUXCPU();
            }else if(maquinaDeEstados.isModoPessoaXCPU()){
                menuPessoaXCPU();
            }else if(maquinaDeEstados.isModoPessoaXPessoa()){
                menuPessoaXPessoa();
            } else if(maquinaDeEstados.isEscolherProximoJogador()){
                menuEscolheProximoJogador();
            }
        }
    }

    public void menuPrincipal() {
        int valor;
        System.out.print("------------------------------------------------------------");
        System.out.print("------------------------ 4 em Linha ------------------------");
        System.out.print("------------------------------------------------------------");
        System.out.println("------------------------ Menu Principal ------------------------");
        System.out.println("1-Iniciar novo jogo");
        System.out.println("2-Carregar um jogo de um ficheiro");
        System.out.println("3-Terminar o jogo");
        System.out.print("> ");
        System.out.flush(); //TODO provavelmente colocar flush para ajudar
        while (!scanner.hasNextInt()) {
            scanner.next();
        }
        valor = scanner.nextInt();
        if (valor == 1) {
            maquinaDeEstados.iniciarNovoJogo();
        } else if (valor == 2) {

        } else if (valor == 3) {
            maquinaDeEstados.getDadosJogo().setJogoAcabou(true);
        }
    }

    public void menuEscolherModoJogo() {
        int valor;
        System.out.println("------------------------ Menu Escolher Modo Jogo ------------------------");
        System.out.println("1-CPU X CPU");
        System.out.println("2-Homem X CPU");
        System.out.println("3-Homem X Homem");
        System.out.print("> ");
        System.out.flush(); //TODO provavelmente colocar flush para ajudar
        while (!scanner.hasNextInt()) {
            scanner.next();
        }
        valor = scanner.nextInt();
        if (valor >= 1 && valor <= 3) {
            maquinaDeEstados.escolherModoJogo(valor);
        }
    }

    private void menuCPUXCPU() {
        String nome, nome2;
        System.out.println("------------------------ Menu Escolher nome do CPUXCPU ------------------------");
        System.out.print("Indique qual o nome do primeiro CPU: ");
        while (!scanner.hasNext()) {
            scanner.next();
        }
        nome = scanner.next();
        System.out.print("Indique qual o nome do segundo CPU: ");
        while (!scanner.hasNext()) {
            scanner.next();
        }
        nome2 = scanner.next();
        if(nome != null && nome2 != null){
            maquinaDeEstados.definirNomes(nome, nome2);
        }
    }

    private void menuPessoaXCPU() {
        String nome, nome2;
        System.out.println("------------------------ Menu Escolher nome do PessoaXCPU ------------------------");
        System.out.print("Indique qual o nome da pessoa: ");
        while (!scanner.hasNext()) {
            scanner.next();
        }
        nome = scanner.next();
        System.out.print("Indique qual o nome do CPU: ");
        while (!scanner.hasNext()) {
            scanner.next();
        }
        nome2 = scanner.next();
        if(nome != null && nome2 != null){
            maquinaDeEstados.definirNomes(nome, nome2);
        }
    }

    private void menuPessoaXPessoa() {
        String nome, nome2;
        System.out.println("------------------------ Menu Escolher nome do PessoaXPessoa ------------------------");
        System.out.print("Indique qual o nome da primeira pessoa: ");
        while (!scanner.hasNext()) {
            scanner.next();
        }
        nome = scanner.next();
        System.out.print("Indique qual o nome do segunda pessoa: ");
        while (!scanner.hasNext()) {
            scanner.next();
        }
        nome2 = scanner.next();
        if(nome != null && nome2 != null){
            maquinaDeEstados.definirNomes(nome, nome2);
        }
    }

    private void menuEscolheProximoJogador() {
        maquinaDeEstados.escolheProximoJogador();
        //imprimir quem vai ser o primeiro jogador
    }
}
