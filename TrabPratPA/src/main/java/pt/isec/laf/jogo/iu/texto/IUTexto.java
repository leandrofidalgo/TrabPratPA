package pt.isec.laf.jogo.iu.texto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import pt.isec.laf.jogo.logica.MaquinaDeEstados;
import pt.isec.laf.jogo.logica.dados.CPU;
import static pt.isec.laf.jogo.logica.dados.DadosJogo.COLUNAS;
import static pt.isec.laf.jogo.logica.dados.DadosJogo.LINHAS;
import pt.isec.laf.jogo.logica.dados.Jogador;
import pt.isec.laf.jogo.logica.dados.Replay;
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
        while (!maquinaDeEstados.isJogoAcabou()) {
            System.out.println();
            showMsgLog();
            maquinaDeEstados.apagarLog();
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
                maquinaDeEstados.escolheProximoJogador();
            } else if (maquinaDeEstados.isProximaJogada4Linha()) {
                menuProximaJogada();
            } else if (maquinaDeEstados.isVerificarSeAcabou()) {
                maquinaDeEstados.verificaSeAcabou();
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
        String nomeFicheiro;
        System.out.print("------------------------------------------------------------");
        System.out.print("------------------------ 4 em Linha ------------------------");
        System.out.print("------------------------------------------------------------");
        System.out.println("----------------------- Menu Principal -----------------------");
        System.out.println("1-Iniciar novo jogo");
        System.out.println("2-Carregar um jogo de um ficheiro");
        System.out.println("3-Replay de um jogo");
        System.out.println("4-Terminar o jogo");
        System.out.print("> ");
        System.out.flush();
        while (!scanner.hasNextInt()) {
            scanner.next();
        }
        valor = scanner.nextInt();
        if (valor == 1) {
            maquinaDeEstados.iniciarNovoJogo();
        } else if (valor == 2) {
            System.out.println("Introduza o nome do ficheiro de onde deseja carregar o jogo: ");
            while (!scanner.hasNext()) {
                scanner.next();
            }
            nomeFicheiro = scanner.next();
            maquinaDeEstados.carregarJogo(nomeFicheiro);
            String nomeJogador = maquinaDeEstados.jogadorAtualString();
            System.out.println("O jogador que irá jogar será: " + nomeJogador);
            imprimirTabuleiro();
        } else if (valor == 3) {
            maquinaDeEstados.replayJogo();
            //buscar os replays disponiveis
            var replay = maquinaDeEstados.getReplay();
            String[] arr = new String[replay.keySet().size()];
            System.arraycopy(replay.keySet().toArray(), 0, arr, 0, replay.keySet().size());
            Arrays.sort(arr);
            System.out.println("Jogos disponiveis para replay:");
            for (int i = 0; i < replay.keySet().size(); i++) {
                System.out.println("" + (i + 1) + "-" + arr[i]);
            }
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            valor = scanner.nextInt();
            if (!(valor <= 0 || valor > replay.keySet().size())) {
                //ir buscar o replay todo do jogo
                String r = arr[valor - 1];
                String str = replayParaImprimir(r);
                System.out.println(str);
            } else {
                System.out.println("Valor inválido!");
            }
        } else if (valor == 4) {
            maquinaDeEstados.setJogoAcabou(true);
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

    private void menuProximaJogada() {
        int valor, coluna;
        String nomeFicheiro;
        if (maquinaDeEstados.numJogada() == 0) {
            imprimirTabuleiro();
            //adicionar o tabuleiro
            maquinaDeEstados.adicionaJogada();
        }
        if (maquinaDeEstados.jogadorAtual() instanceof CPU) {
            maquinaDeEstados.jogaPeca(0);
        } else {
            System.out.println("------------------------ Menu Jogada ------------------------");
            System.out.println("1-Efetuar uma jogada");
            System.out.println("2-Efetuar a jogada da peça especial");
            System.out.println("3-Guardar o estado atual do jogo num ficheiro");
            System.out.println("4-Retroceder jogada");
            System.out.println("5-Terminar o jogo");
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            valor = scanner.nextInt();
            if (valor == 1) {
                System.out.println("Indique a coluna onde deseja colocar a nova peca: ");
                while (!scanner.hasNextInt()) {
                    scanner.next();
                }
                coluna = scanner.nextInt();
                if (coluna > 0 && coluna < 8) {
                    maquinaDeEstados.jogaPeca(coluna);
                } else {
                    System.out.println("Coluna inexistente!");
                }
            } else if (valor == 2) {
                System.out.println("Indique a coluna onde deseja colocar a peca especial: ");
                while (!scanner.hasNextInt()) {
                    scanner.next();
                }
                coluna = scanner.nextInt();
                maquinaDeEstados.jogaPecaEspecial(coluna);
            } else if (valor == 3) {
                System.out.println("Introduza o nome do ficheiro onde deseja guardar o estado atual do jogo: ");
                while (!scanner.hasNext()) {
                    scanner.next();
                }
                nomeFicheiro = scanner.next();
                maquinaDeEstados.guardarJogo(nomeFicheiro);
            } else if (valor == 4) {
                int iteracoes;
                System.out.println("Quantas iteracões deseja voltar atras (Maximo 5 vezes): ");
                while (!scanner.hasNextInt()) {
                    scanner.next();
                }
                iteracoes = scanner.nextInt();
                maquinaDeEstados.voltarAtras(iteracoes);
            } else if (valor == 5) {
                maquinaDeEstados.terminarJogo();
            }
        }
        imprimirTabuleiro();
    }

    private void menuJogarMiniJogo() {
        int valor;
        System.out.println("Parabéns, tem a opção de jogar um mini jogo para ter a oportunidade de ganhar uma peça especial!");
        System.out.println("Deseja jogar o mini jogo?");
        System.out.println("1-Sim");
        System.out.println("2-Não");
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
        String str = maquinaDeEstados.getPerguntaCalculos();
        System.out.println(str);
        while (!scanner.hasNextInt()) {
            scanner.next();
        }
        valor = scanner.nextInt();
        maquinaDeEstados.jogarMiniJogoCalculos(valor);
    }

    private void miniJogoPalavras() {
        //pedir as palavras.... e ainda fazer toda a logica
        String palavras;
        String palavrasParaEscrever = maquinaDeEstados.getPerguntaPalavras();
        System.out.println(palavrasParaEscrever);
        while (!scanner.hasNext()) {
            scanner.next();
        }
        palavras = scanner.next();
        maquinaDeEstados.jogarMiniJogoPalavras(palavras, palavrasParaEscrever);
    }

    private void menuFimDoJogo() {
        int valor;
        if (maquinaDeEstados.vencedor() == null) {
            System.out.println("Não existiu nenhum vencedor!!");
        } else {
            System.out.println("Parabéns, " + maquinaDeEstados.vencedor() + " acabou de ganhar o jogo do 4 em linha");
        }
        System.out.println("------------------------ Menu Fim do Jogo ------------------------");
        System.out.println("Deseja guardar o Jogo para mais tarde fazer replay?");
        System.out.println("1-Sim");
        System.out.println("2-Não");
        while (!scanner.hasNextInt()) {
            scanner.next();
        }
        valor = scanner.nextInt();
        if (valor == 1) {
            maquinaDeEstados.guardarDadosJogoReplay();
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

    public String replayParaImprimir(String nomeKey) {
        StringBuilder sB = new StringBuilder();
        ArrayList<Replay> replay = maquinaDeEstados.getReplayKey(nomeKey);
        for (int i = 0; i < replay.size(); i++) {
            if (replay.get(i).getTipoReplay().equals(Replay.JOGADA)) {
                if (replay.get(i).getJogador() instanceof CPU) {
                    sB.append("Jogada efetuada pelo CPU ").append(replay.get(i).getJogador().getNome());
                } else {
                    sB.append("Jogada efetuada pelo jogador ").append(replay.get(i).getJogador().getNome());
                }
                sB.append("\n");
                sB.append(buscarTabuleiro(replay.get(i).getTabuleiro()));
            } else if (replay.get(i).getTipoReplay().equals(Replay.MINIJOGO)) {
                sB.append("O jogador ").append(replay.get(i).getJogador().getNome()).append(" ganhou o minijogo!");
            }
            sB.append("\n");
        }
        return sB.toString();
    }

    public String buscarTabuleiro(int[][] tabuleiro) {
        StringBuilder sB = new StringBuilder();
        for (int i = 0; i < LINHAS; i++) {
            sB.append("|");
            for (int j = 0; j < COLUNAS; j++) {
                if (tabuleiro[i][j] == 0) {
                    sB.append(" |");
                }
                if (tabuleiro[i][j] == 1) {
                    sB.append("O|");
                }
                if (tabuleiro[i][j] == 2) {
                    sB.append("X|");
                }
            }
            sB.append("\n");
        }
        return sB.toString();
    }

    public void imprimirTabuleiro() {
        StringBuilder sB = new StringBuilder();
        int[][] tabuleiro = maquinaDeEstados.getTabuleiro();
        for (int i = 0; i < LINHAS; i++) {
            sB.append("|");
            for (int j = 0; j < COLUNAS; j++) {
                if (tabuleiro[i][j] == 0) {
                    sB.append(" |");
                }
                if (tabuleiro[i][j] == 1) {
                    sB.append("O|");
                }
                if (tabuleiro[i][j] == 2) {
                    sB.append("X|");
                }
            }
            sB.append("\n");
        }
        System.out.println(sB.toString());
    }

    public void showMsgLog() {
        ArrayList<String> logs = maquinaDeEstados.getLogs();
        if (logs.size() > 0) {
            for (String msg : logs) {
                System.out.println("---> " + msg);
            }
        }
    }

}
