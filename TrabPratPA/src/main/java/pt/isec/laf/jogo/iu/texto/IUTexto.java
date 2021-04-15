package pt.isec.laf.jogo.iu.texto;

import java.util.ArrayList;
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

    public IUTexto(MaquinaDeEstados jogo) {
        this.maquinaDeEstados = jogo;
        this.scanner = new Scanner(System.in);
    }
    
    public void run() {
        menuPrincipal();
    }
    
    public void inicioDOJogo(){
        System.out.println("------------------------ 4 em Linha ------------------------");
        //Continuar a fazer o inicio do jogo
    }
    
    public void menuPrincipal(){
        int valor;
        System.out.println("\n");
        System.out.println("------------------------ Menu Principal ------------------------");
        System.out.println("1-Iniciar jogo");
        System.out.println("2-Exit");
        System.out.print("> ");
        while(!scanner.hasNextInt()) scanner.next();
        valor = scanner.nextInt();
        if(valor == 1){
            carregaFicheiro();
        }else if(valor == 2){
            System.exit(0);
        }
    }
    
    public void carregaFicheiro(){
        int valor;
        System.out.println("\n");
        System.out.println("------------------------ Carregar Ficheiro ------------------------");
        System.out.println("Deseja carregar um ficheiro?");
        System.out.println("1-Sim");
        System.out.println("2-Não");
        System.out.println("> ");
        while(!scanner.hasNextInt()) scanner.next();
        valor = scanner.nextInt();
        if(valor == 1){
            System.out.println("Qual o nome do ficheiro que deseja carregar: ");
            while(!scanner.hasNext()) scanner.next();
            String ficheiro = scanner.next();
            //TODO
            //carregar ficheiro e depois enviar os dados do jogo para a maquina de estados
        }else if(valor == 2){
            definirJogadorContraJogador();
        }
    }

    public void definirJogadorContraJogador(){
        int valor;
        System.out.println("Indique qual das seguintes opções pretende realizar: ");
        System.out.println("1- CPU vs CPU");
        System.out.println("2- Jogador1 vs CPU");
        System.out.println("3- Jodador1 vs Jogador2");
        System.out.println("> ");
        while(!scanner.hasNextInt()) scanner.next();
        valor = scanner.nextInt();
        if(valor == 1){
            ArrayList<String> nomes = dadosUtilizador();
            Jogador j1 = new Jogador(nomes.get(0), 0, 0, false, 1);
            Jogador j2 = new Jogador(nomes.get(1), 0, 0, false, 2);
            maquinaDeEstados.proximaJogada(j1, j2);
        }else if(valor == 2){
            ArrayList<String> nomes = dadosUtilizador();
            Jogador j1 = new Jogador(nomes.get(0), 0, 0, false, 1);
            Jogador j2 = new Jogador(nomes.get(1), 0, 0, false, 2);
            maquinaDeEstados.proximaJogada(j1, j2);
        }else if(valor == 3){
            ArrayList<String> nomes = dadosUtilizador();
            Jogador j1 = new Jogador(nomes.get(0), 0, 0, false, 1);
            Jogador j2 = new Jogador(nomes.get(1), 0, 0, false, 2);
            maquinaDeEstados.proximaJogada(j1, j2);
        }
    }
    
    public ArrayList<String> dadosUtilizador(){
        ArrayList<String> nomes = new ArrayList<>();
        String nome;
        String nome2;
        System.out.println("Introduza o nome do primeiro jogador: ");
        while(!scanner.hasNext()) scanner.next();
        nome = scanner.next();
        do{
            System.out.println("Introduza o nome do segundo jogador sendo que este tem de ser diferente do nome do primeiro jogador: ");
            while(!scanner.hasNext()) scanner.next();
            nome2 = scanner.next();
        }while(nome2.equals(nome));
        nomes.add(nome);
        nomes.add(nome2);
        return nomes;        
    }

}
