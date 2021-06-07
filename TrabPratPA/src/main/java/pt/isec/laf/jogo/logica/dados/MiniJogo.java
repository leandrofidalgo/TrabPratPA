package pt.isec.laf.jogo.logica.dados;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leandro
 */
public class MiniJogo implements Serializable {

    private int randomSinal;
    private int randomValor1;
    private int randomValor2;
    private Random rand = new Random();
    private int numVezesQueJogouMiniJogo; //numero de vezes que jogou o mini jogo
    private long startTime;
    private int numVezesQueGanhou; // numerod de vezes que ganhou o mini jogo

    public MiniJogo() {
        this.randomSinal = 0;
        this.randomValor1 = 0;
        this.randomValor2 = 0;
        this.numVezesQueJogouMiniJogo = 0;
        this.startTime = 0;
        this.numVezesQueGanhou = 0;
    }

    public String miniJogoCalculos() {
        if (this.numVezesQueJogouMiniJogo == 0) {
            startTime = System.currentTimeMillis();
        }
        //gerar random para o sinal da operação 1-4
        randomSinal = getRandom(1, 4);
        //gerar rando para os valores a calcular 0-99
        randomValor1 = getRandom(0, 99);
        randomValor2 = getRandom(0, 99);
        switch (randomSinal) {
            case 1:
                return randomValor1 + " + " + randomValor2;
            case 2:
                return randomValor1 + " - " + randomValor2;
            case 3:
                return randomValor1 + " / " + randomValor2;
            case 4:
                return randomValor1 + " x " + randomValor2;
            default:
                return "Existe um problema com o sinal da operação!\n";
        }
    }

    public String miniJogoPalavras(String nomeFicheiro) {
        try {
            //carregar ficheiro e fazer aleatoridade de 5 palavras
            startTime = System.currentTimeMillis();
            String str = "";
            List<String> palavras = Files.readAllLines(Path.of(nomeFicheiro));
            if (palavras == null) {
                return str = "O ficheiro nao existe!";
            }
            for (int i = 0; i < 5; i++) {
                int random = getRandom(0, palavras.size() - 1);
                if (i != 4) {
                    str = str + palavras.get(random) + " ";
                } else {
                    str = str + palavras.get(random);
                }
            }
            return str;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public long getStartTime() {
        return startTime;
    }

    public int getRandomSinal() {
        return randomSinal;
    }

    public int getRandomValor1() {
        return randomValor1;
    }

    public int getRandomValor2() {
        return randomValor2;
    }

    public int getNumVezesQueGanhou() {
        return numVezesQueGanhou;
    }

    public int getNumVezesQueJogouMiniJogo() {
        return numVezesQueJogouMiniJogo;
    }

    public void setNumVezesQueGanhou(int numVezesQueGanhou) {
        this.numVezesQueGanhou = numVezesQueGanhou;
    }

    public void incrementaNumVezesQueGanhou() {
        this.numVezesQueGanhou = this.numVezesQueGanhou + 1;
    }

    public void incrementaNumVezesQueJogouMiniJogo() {
        this.numVezesQueJogouMiniJogo = this.numVezesQueJogouMiniJogo + 1;
    }

    public int getRandom(int min, int max) {
        return rand.nextInt(max - min + 1) + min;
    }

    public void setNumVezesQueJogouMiniJogo(int numVezesQueJogouMiniJogo) {
        this.numVezesQueJogouMiniJogo = numVezesQueJogouMiniJogo;
    }

    public int tempoRestanteEmSegundos(){
        return (int)(30 - (System.currentTimeMillis() - this.startTime)/1000);
    }
}
