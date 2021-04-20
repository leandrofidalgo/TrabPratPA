package pt.isec.laf.jogo.iu.texto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.isec.laf.jogo.logica.MaquinaDeEstados;
import static pt.isec.laf.jogo.logica.dados.DadosJogo.COLUNAS;
import static pt.isec.laf.jogo.logica.dados.DadosJogo.LINHAS;
import pt.isec.laf.jogo.logica.dados.Jogador;

/**
 *
 * @author leandro
 */
public class IUTexto {

    private MaquinaDeEstados maquinaDeEstados;
    private Scanner scanner;
    private Random rand = new Random();

    public IUTexto(MaquinaDeEstados jogo) {
        this.maquinaDeEstados = jogo;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        //miniJogoCalculos();
        //miniJogoPalavras();
        imprimirTabuleiro();
        inicioDoJogo();
        menuPrincipal();
        while (true) {
            proximaJogadaInteface();
        }
    }

    public void inicioDoJogo() {
        System.out.println("------------------------ 4 em Linha ------------------------");
        //TODO Continuar a fazer o inicio do jogo
    }

    public void menuPrincipal() {
        int valor;
        do {
            System.out.println("\n");
            System.out.println("------------------------ Menu Principal ------------------------");
            System.out.println("1-Iniciar novo jogo");
            System.out.println("2-Carregar um jogo de um ficheiro");
            System.out.println("3-Exit");
            System.out.print("> ");
            System.out.flush(); //TODO provavelmente colocar flush para ajudar
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            valor = scanner.nextInt();
            if (valor == 1) {
                definirJogadorContraJogador();
            } else if (valor == 2) {
                carregaFicheiro();
            } else if (valor == 3) {
                System.exit(0);
            }
        } while (valor != 1 || valor != 2);
    }

    public void carregaFicheiro() {
        int valor;
        System.out.println("\n");
        System.out.println("------------------------ Carregar Ficheiro ------------------------");
        System.out.println("Qual o nome do ficheiro que deseja carregar: ");
        System.out.print("> ");
        System.out.flush();
        while (!scanner.hasNext()) {
            scanner.next();
        }
        String ficheiro = scanner.next();
        //TODO carregar ficheiro e depois enviar os dados do jogo para a maquina de estados e de seguida começar nova jogada
    }

    public void definirJogadorContraJogador() {
        int valor;
        do {
            System.out.println("Indique qual das seguintes opções pretende realizar: ");
            System.out.println("1- CPU vs CPU");
            System.out.println("2- Jogador1 vs CPU");
            System.out.println("3- Jodador1 vs Jogador2");
            System.out.print("> ");
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            valor = scanner.nextInt();
            if (valor == 1) {
                ArrayList<String> nomes = dadosUtilizador();
                //TODO qual jogador vai ser o primeiro e depois usar a classe pessoa e computador depois na proxima jogada fazer qual vai ser primeiro
                maquinaDeEstados.definirJogadores(valor, nomes);
            } else if (valor == 2) {
                ArrayList<String> nomes = dadosUtilizador();
                maquinaDeEstados.definirJogadores(valor, nomes);
            } else if (valor == 3) {
                ArrayList<String> nomes = dadosUtilizador();
                maquinaDeEstados.definirJogadores(valor, nomes);
            }
        } while (valor != 1 || valor != 2 || valor != 3);
    }

    public ArrayList<String> dadosUtilizador() {
        ArrayList<String> nomes = new ArrayList<>();
        String nome;
        String nome2;
        System.out.println("Introduza o nome do primeiro jogador: ");
        while (!scanner.hasNext()) {
            scanner.next();
        }
        nome = scanner.next();
        do {
            System.out.println("Introduza o nome do segundo jogador sendo que este tem de ser diferente do nome do primeiro jogador: ");
            while (!scanner.hasNext()) {
                scanner.next();
            }
            nome2 = scanner.next();
        } while (nome2.equals(nome));
        nomes.add(nome);
        nomes.add(nome2);
        return nomes;
    }

    public void miniJogoCalculos() {
        //isto vai ter de ir para outro lado
        int numAcertou = 0;

        Thread td1 = new Thread(() -> {
            //Thread secundaria para tratar da obtencao dos calculos do jogador
            while (true) {
                miniJogoCalculosTemporizador();
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
        }
        /*if (numAcertou == -1) {
            System.out.println("Existe um problema com o sinal da operação!\n");
        } else if (numAcertou >= 5) {
            //TODO ganhar peça especial
            System.out.println("Muito bem conseguiu acertar 5 ou mais vezes nos cálculos.");
            System.out.println("Como bonus irá lhe ser oferecida uma peça especial.");
        } else {
            System.out.println("Nao conseguiu ganhar a peça especial!");
        }*/
    }

    public void contador(long starttime) throws InterruptedException {
        long timepassed;
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            timepassed = System.currentTimeMillis() - starttime;
            if (timepassed <= 30000) {
                System.out.println(timepassed / 1000);
            } else if (timepassed > 30000) {
                return;
            }
        }
    }

    public synchronized int miniJogoCalculosTemporizador() {
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
        return 0;
    }

    public void miniJogoPalavras() {
        //TODO: esperar metade do número de caracteres apresentados, incluindo os espaços em branco
        ArrayList<String> palavras = carregaPalavras();
        if (palavras == null) {
            System.out.println("Não existem palavras no ficheiro!\n");
        }
        int numPalavras = palavras.size();
        String palavra = "";
        int aux = 0;
        do {
            int randomPal = getRandom(1, numPalavras);
            aux++;
            System.out.println(palavras.get(randomPal));
            while (!scanner.hasNext()) {
                scanner.next();
            }
            palavra = scanner.next();
            if (palavra.equals(palavras.get(randomPal))) {
                System.out.println("Acertou!");
            }
        } while (aux != numPalavras);
    }

    public ArrayList<String> carregaPalavras() {
        //String ficheiro = "C:\\Users\\lfida\\Desktop\\ISEC 2020-2021\\Programação Avançada\\TrabPrat\\TrabPratPA\\TrabPratPA\\";
        try {
            BufferedReader buffRead = new BufferedReader(new FileReader("Palavras.txt"));
            ArrayList<String> palavras = new ArrayList<>();
            String linha = "";
            while (true) {
                if (linha != null) {
                    //System.out.println(linha);

                } else {
                    break;
                }
                linha = buffRead.readLine();
                palavras.add(linha);
            }
            buffRead.close();
            return palavras;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Erro na leitura do ficheiro!\n");
        }
        return null;
    }

    public int getRandom(int min, int max) {
        return rand.nextInt(max) + min;
    }

    public void proximaJogadaInteface() {
        imprimirTabuleiro();
        System.out.println();
        maquinaDeEstados.proximaJogada();
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
        System.out.println(sB);
    }

    public void verificarSeAlguemGanhou() {
        verificaLinhas();
    }

    public void verificaLinhas() {
        int[][] tabuleiro = new int[6][7];
        int aux;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                /*aux = tabuleiro[i];
                if(tabuleiro[i])*/
            }
        }

    }
}
