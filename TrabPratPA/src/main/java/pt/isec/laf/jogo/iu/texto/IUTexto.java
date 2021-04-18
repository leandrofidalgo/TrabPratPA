package pt.isec.laf.jogo.iu.texto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import pt.isec.laf.jogo.logica.MaquinaDeEstados;
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
        miniJogoPalavras();
        miniJogoCalculos();
        menuPrincipal();
    }

    public void inicioDOJogo() {
        System.out.println("------------------------ 4 em Linha ------------------------");
        //Continuar a fazer o inicio do jogo
    }

    public void menuPrincipal() {
        int valor;
        do {
            System.out.println("\n");
            System.out.println("------------------------ Menu Principal ------------------------");
            System.out.println("1-Iniciar jogo");
            System.out.println("2-Exit");
            System.out.print("> ");
            System.out.flush(); //provavelmente colocar flush para ajudar
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            valor = scanner.nextInt();
            if (valor == 1) {
                carregaFicheiro();
            } else if (valor == 2) {
                System.exit(0);
            }
        } while (valor != 1 || valor != 2);
    }

    public void carregaFicheiro() {
        int valor;
        do {
            System.out.println("\n");
            System.out.println("------------------------ Carregar Ficheiro ------------------------");
            System.out.println("Deseja carregar um ficheiro?");
            System.out.println("1-Sim");
            System.out.println("2-Não");
            System.out.print("> ");
            System.out.flush();
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            valor = scanner.nextInt();
            if (valor == 1) {
                System.out.println("Qual o nome do ficheiro que deseja carregar: ");
                while (!scanner.hasNext()) {
                    scanner.next();
                }
                String ficheiro = scanner.next();
                //TODO
                //carregar ficheiro e depois enviar os dados do jogo para a maquina de estados
            } else if (valor == 2) {
                definirJogadorContraJogador();
            }
        } while (valor != 1 || valor != 2);
    }

    public void definirJogadorContraJogador() {
        int valor;
        do {
            System.out.println("Indique qual das seguintes opções pretende realizar: ");
            System.out.println("1- CPU vs CPU");
            System.out.println("2- Jogador1 vs CPU");
            System.out.println("3- Jodador1 vs Jogador2");
            System.out.println("> ");
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            valor = scanner.nextInt();
            if (valor == 1) {
                ArrayList<String> nomes = dadosUtilizador();
                Jogador j1 = new Jogador(nomes.get(0), 0, 0, false, 1);
                Jogador j2 = new Jogador(nomes.get(1), 0, 0, false, 2);
                maquinaDeEstados.proximaJogada(j1, j2);
            } else if (valor == 2) {
                ArrayList<String> nomes = dadosUtilizador();
                Jogador j1 = new Jogador(nomes.get(0), 0, 0, false, 1);
                Jogador j2 = new Jogador(nomes.get(1), 0, 0, false, 2);
                maquinaDeEstados.proximaJogada(j1, j2);
            } else if (valor == 3) {
                ArrayList<String> nomes = dadosUtilizador();
                Jogador j1 = new Jogador(nomes.get(0), 0, 0, false, 1);
                Jogador j2 = new Jogador(nomes.get(1), 0, 0, false, 2);
                maquinaDeEstados.proximaJogada(j1, j2);
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
        //TODO: Esperar 30 segundos
        int numAcertou = 0;
        int valor; //valor do calculo
        //gerar random para o sinal da operação 1-4
        int randomSinal = rand.nextInt(4) + 1;
        //gerar rando para os valores a calcular 0-99
        int randomValor1 = rand.nextInt(100);
        int randomValor2 = rand.nextInt(100);
        switch (randomSinal) {
            case 1:
                System.out.println(randomValor1 + " + " + randomValor2);
                while (!scanner.hasNextInt()) {
                    scanner.next();
                }
                valor = scanner.nextInt();
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
                return;
        }
        if (numAcertou >= 5) {
            //TODO ganhar peça especial
            System.out.println("Muito bem conseguiu acertar 5 ou mais vezes nos cálculos.");
            System.out.println("Como bonus irá lhe ser oferecida uma peça especial.");
        }
    }

    public void miniJogoPalavras() {
        //TODO: esperar metade do número de caracteres apresentados, incluindo os espaços em branco
        ArrayList<String> palavras = carregaPalavras();
        if(palavras == null){
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
            if(palavra.equals(palavras.get(randomPal))){
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
}
