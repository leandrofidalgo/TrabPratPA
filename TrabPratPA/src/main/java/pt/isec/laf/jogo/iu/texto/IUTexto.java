package pt.isec.laf.jogo.iu.texto;

import java.util.Scanner;
import pt.isec.laf.jogo.logica.MaquinaDeEstados;
import pt.isec.laf.jogo.logica.dados.CPU;
import static pt.isec.laf.jogo.logica.dados.DadosJogo.COLUNAS;
import static pt.isec.laf.jogo.logica.dados.DadosJogo.LINHAS;

/**
 *
 * @author leandro
 */
public class IUTexto {

    private MaquinaDeEstados maquinaDeEstados;
    private Scanner scanner;

    public IUTexto(MaquinaDeEstados maquinaDeEstados) {
        this.maquinaDeEstados = maquinaDeEstados;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        //miniJogoCalculos();
        //miniJogoPalavras();
        //imprimirTabuleiro();
        //inicioDoJogo();
        //menuPrincipal();
        while (!maquinaDeEstados.getDadosJogo().isJogoAcabou()) {
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
            } else if (maquinaDeEstados.isVerificarSeAcabou()) {
                menuVerificarSeAcabou();
            } else if (maquinaDeEstados.isEscolherJogarMiniJogo()) {
                menuJogarMiniJogo();
            } else if (maquinaDeEstados.isMiniJogoCalculos()) {
                miniJogoCalculos();
            } else if (maquinaDeEstados.isMiniJogoPalavras()) {
                miniJogoPalavras();
            } else if (maquinaDeEstados.isFimDoJogo()) {
                menuFimDoJogo();
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
        System.out.println("Indique qual o nome do primeiro CPU: ");
        while (!scanner.hasNext()) {
            scanner.next();
        }
        nome = scanner.next();
        System.out.println("Indique qual o nome do segundo CPU: ");
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
        System.out.println("Indique qual o nome da pessoa: ");
        while (!scanner.hasNext()) {
            scanner.next();
        }
        nome = scanner.next();
        System.out.println("Indique qual o nome do CPU: ");
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
        System.out.println("Indique qual o nome da primeira pessoa: ");
        while (!scanner.hasNext()) {
            scanner.next();
        }
        nome = scanner.next();
        System.out.println("Indique qual o nome do segunda pessoa: ");
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
        int coluna;
        if (maquinaDeEstados.getDadosJogo().getNum_jogada() == 0) {
            imprimirTabuleiro();
        }
        //TODO verificar se é um CPU
        if (maquinaDeEstados.getDadosJogo().retornaJogadorAtual() instanceof CPU) {
            maquinaDeEstados.jogaPeca(0);
        } else {
            System.out.println("------------------------ Menu Jogada ------------------------");
            System.out.println("1-Efetuar uma jogada");
            System.out.println("2-Efetuar a jogada da peça especial");
            System.out.println("3-Retroceder jogada");
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            valor = scanner.nextInt();
            if (valor == 1) {
                //chamar função para avançar uma jogada
                System.out.println("Indique a coluna onde deseja colocar a nova peca: ");
                while (!scanner.hasNextInt()) {
                    scanner.next();
                }
                coluna = scanner.nextInt();
                maquinaDeEstados.jogaPeca(coluna);
            } else if (valor == 2) {
                //chamar funcao para jogar a peca especial
                //TODO perguntar qual é a coluna onde a deseja colocar
                System.out.println("Indique a coluna onde deseja colocar a peca especial: ");
                while (!scanner.hasNextInt()) {
                    scanner.next();
                }
                coluna = scanner.nextInt();
                maquinaDeEstados.jogaPecaEspecial(coluna);
            } else if (valor == 3) {
                int iteracoes;
                //chamar função para perguntar quantas vezes deseja voltar atrás
                //chamar processo de retroceder jogada
                System.out.println("Quantas iteracoes deseja voltar atras (Maximo 5 vezes): ");
                while (!scanner.hasNextInt()) {
                    scanner.next();
                }
                iteracoes = scanner.nextInt();
                //maquinaDeEstados.
            }
        }
        imprimirTabuleiro();
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
        System.out.println("Parabéns, tem a opção de jogar um mini jogo para ter a oportunidade de ganhar uma peça especial!");
        System.out.println("Deseja jogar o mini jogo?");
        System.out.println("1-Sim");
        System.out.println("2-Nao");
        while (!scanner.hasNextInt()) {
            scanner.next();
        }
        valor = scanner.nextInt();
        if (valor == 1) {
            maquinaDeEstados.jogarMiniJogo();
        } else if (valor == 2) {
            maquinaDeEstados.naoJogarMiniJogo();
        }
    }

    private void miniJogoCalculos() {
        int valor; //valor do calculo
        String str = maquinaDeEstados.getDadosJogo().getMiniJogo().miniJogoCalculos();
        System.out.println(str);
        while (!scanner.hasNextInt()) {
            scanner.next();
        }
        valor = scanner.nextInt();
        maquinaDeEstados.jogarMiniJogoCalculos(valor);
    }

    private void miniJogoPalavras() {
        //pedir as palavras.... e ainda fazer toda a logica
        String nomeFicheiro, palavras; //palavra
        //nome do ficheiro
        System.out.println("Introduza o nome do ficheiro com as varias palavras: ");
        while (!scanner.hasNext()) {
            scanner.next();
        }
        nomeFicheiro = scanner.next();

        String palavrasParaEscrever = maquinaDeEstados.getDadosJogo().getMiniJogo().miniJogoPalavras(nomeFicheiro);
        System.out.println(palavrasParaEscrever);
        while (!scanner.hasNext()) {
            scanner.next();
        }
        palavras = scanner.nextLine();
        maquinaDeEstados.jogarMiniJogoPalavras(palavras, palavrasParaEscrever);
    }

    private void menuVerificarSeAcabou() {
        maquinaDeEstados.verificaSeAcabou();
    }

    private void menuFimDoJogo() {
        int valor;
        System.out.println("------------------------ Menu Fim do Jogo ------------------------");
        if (maquinaDeEstados.getDadosJogo().retornarVencedor() == null) {
            System.out.println("Não existiu nenhum vencedor!!");
        } else {
            System.out.println("Parabéns, " + maquinaDeEstados.getDadosJogo().retornarVencedor() + " acabou de ganhar o jogo do 4 em linha");
        }
        System.out.println("O que deseja efetuar agora: ");
        System.out.println("1-Iniciar um novo jogo");
        System.out.println("2-Retornar ao menu principal");
        while (!scanner.hasNextInt()) {
            scanner.next();
        }
        valor = scanner.nextInt();
        if (valor == 1) {
            maquinaDeEstados.iniciarNovoJogo();
        } else if (valor == 2) {
            maquinaDeEstados.retornarMenuPrincipal();
        }
    }

    public void showMsgLog() {
        if (maquinaDeEstados.getDadosJogo().getMsgLog().size() > 0) {
            for (String msg : maquinaDeEstados.getDadosJogo().getMsgLog()) {
                System.out.println("---> " + msg);
            }
        }
    }

}
