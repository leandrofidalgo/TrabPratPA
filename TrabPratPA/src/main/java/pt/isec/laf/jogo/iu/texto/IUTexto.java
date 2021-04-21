package pt.isec.laf.jogo.iu.texto;

import java.util.Scanner;
import pt.isec.laf.jogo.logica.MaquinaDeEstados;
import static pt.isec.laf.jogo.logica.dados.DadosJogo.COLUNAS;
import static pt.isec.laf.jogo.logica.dados.DadosJogo.LINHAS;

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
            System.out.println();
            showMsgLog();
            maquinaDeEstados.getDadosJogo().clearMsgLog();
            if (maquinaDeEstados.isMenuPrincipal()) {
                menuPrincipal();
            } else if (maquinaDeEstados.isEscolherModoJogo()) {
                menuEscolherModoJogo();
            } else if (maquinaDeEstados.isModoCPUXCPU()) {
                menuCPUXCPU();
            } else if (maquinaDeEstados.isModoPessoaXCPU()) {
                menuPessoaXCPU();
            } else if (maquinaDeEstados.isModoPessoaXPessoa()) {
                menuPessoaXPessoa();
            } else if (maquinaDeEstados.isEscolherProximoJogador()) {
                menuEscolherProximoJogador();
            } else if (maquinaDeEstados.isProximaJogada4Linha()) {
                menuProximaJogada();
            } else if (maquinaDeEstados.isEscolherJogarMiniJogo()) {
                menuJogarMiniJogo();
            } else if (maquinaDeEstados.isMiniJogoCalculos()) {
                miniJogoCalculos();
            } else if (maquinaDeEstados.isMiniJogoPalavras()) {
                miniJogoPalavras();
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
        } else {
            System.out.println("Valor inválido!");
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
        if (nome != null && nome2 != null) {
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
        if (nome != null && nome2 != null) {
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
        if (nome != null && nome2 != null) {
            maquinaDeEstados.definirNomes(nome, nome2);
        }
    }

    private void menuEscolherProximoJogador() {
        maquinaDeEstados.escolheProximoJogador();
    }

    private void menuProximaJogada() {
        int valor;
        imprimirTabuleiro();
        System.out.println("------------------------ Menu Jogada ------------------------");
        System.out.println("1-Efetuar uma jogada");
        System.out.println("2-Efetuar a jogada da peça especial");
        System.out.println("2-Retroceder jogada");
        while (!scanner.hasNextInt()) {
            scanner.next();
        }
        valor = scanner.nextInt();
        if (valor == 1) {
            //chamar função para avançar uma jogada
            int coluna;
            System.out.print("Indique a coluna onde deseja colocar a nova peca: ");
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            coluna = scanner.nextInt();
            maquinaDeEstados.jogaPeca(coluna);
        } else if (valor == 2) {
            //chamar funcao para jogar a peca especial
            //TODO perguntar qual é a coluna onde a deseja colocar
            maquinaDeEstados.jogaPecaEspecial();
        } else if (valor == 3) {
            //chamar função para perguntar quantas vezes deseja voltar atrás
            //chamar processo de retroceder jogada
        }
    }

    public void imprimirTabuleiro() {
        StringBuilder sB = new StringBuilder();
        for (int i = 0; i < LINHAS; i++) {
            sB.append("|");
            for (int j = 0; j < COLUNAS; j++) {
                if (maquinaDeEstados.getDadosJogo().getTabuleiro()[i][j] == 0) {
                    sB.append(" |");
                }
                if (maquinaDeEstados.getDadosJogo().getTabuleiro()[i][j] == 1) {
                    sB.append("O|");
                }
                if (maquinaDeEstados.getDadosJogo().getTabuleiro()[i][j] == 2) {
                    sB.append("X|");
                }
            }
            sB.append("\n");
        }
        System.out.println(sB.toString());
    }

    private void menuJogarMiniJogo() {
        int valor;
        System.out.println("Parabéns, tem a opção de jogar um mini jogo para ter a oportunidade de lhe calhar uma peça especial!");
        System.out.println("Deseja jogar o mini jogo?");
        System.out.println("1-Sim");
        System.out.println("2-Nao");
        while (!scanner.hasNextInt()) {
            scanner.next();
        }
        valor = scanner.nextInt();
        if (valor >= 1 && valor <= 2) {
            maquinaDeEstados.jogarMiniJogo();
        }
    }

    private void miniJogoCalculos() {
        //pedir os varios numeros ao utilizador
        int numAcertou = 0;
        /*Thread td1 = new Thread(() -> {
            //Thread secundaria para tratar da obtencao dos calculos do jogador
            while (true) {
                int numAcertou = 0;
        int valor; //valor do calculo
        long timepassed; //time in seconds
        int randomSinal;
        int randomValor1;
        int randomValor2;
        //gerar random para o sinal da operação 1-4
        randomSinal = rand.nextInt(4) + 1;
        //gerar rando para os valores a calcular 0-99
        randomValor1 = rand.nextInt(100);
        randomValor2 = rand.nextInt(100);
        switch (randomSinal) {
            case 1:
                System.out.println(randomValor1 + " + " + randomValor2);
                while (!scanner.hasNextInt()) {
                    scanner.next();
                }
                valor = scanner.nextInt();
                //numAcertou = maquinaDeEstados.miniJogoCalculos(valor, randomValor1, randomValor2, "+");
                if (valor == (randomValor1 + randomValor2)) {
                    numAcertou++;
                }
                break;
            case 2:
                System.out.println(randomValor1 + " - " + randomValor2);
                while (!scanner.hasNextInt()) {
                    scanner.next();
                }
                valor = scanner.nextInt();
                if (valor == (randomValor1 - randomValor2)) {
                    numAcertou++;
                }
                break;
            case 3:
                System.out.println(randomValor1 + " / " + randomValor2);
                while (!scanner.hasNextInt()) {
                    scanner.next();
                }
                valor = scanner.nextInt();
                if (valor == (randomValor1 / randomValor2)) {
                    numAcertou++;
                }
                break;
            case 4:
                System.out.println(randomValor1 + " x " + randomValor2);
                while (!scanner.hasNextInt()) {
                    scanner.next();
                }
                valor = scanner.nextInt();
                if (valor == (randomValor1 * randomValor2)) {
                    numAcertou++;
                }
                break;
            default:
                System.out.println("Existe um problema com o sinal da operação!\n");
                return -1;
        }
            }
        });
        td1.start();

        long starttime = System.currentTimeMillis();
        try {
            //Esperar 30 segundos na thread principal
            contador(starttime);
            //supostamente passou 30sec 
            synchronized (td1) {
                td1.notify();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
    }

    private void miniJogoPalavras() {
        //pedir as palavras.... e ainda fazer toda a logica
    }

    public void showMsgLog() {
        if (maquinaDeEstados.getDadosJogo().getMsgLog().size() > 0) {
            for (String msg : maquinaDeEstados.getDadosJogo().getMsgLog()) {
                System.out.println("---> " + msg);
            }
        }
    }

}
