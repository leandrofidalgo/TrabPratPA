package pt.isec.laf.jogo.logica.estados;

import java.util.ArrayList;
import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.CPU;
import pt.isec.laf.jogo.logica.dados.DadosJogo;
import pt.isec.laf.jogo.logica.dados.Pessoa;

/**
 *
 * @author leandro
 */
public class DefinirJogadores extends EstadoAdaptador {

    public DefinirJogadores(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado definirJogadores(DadosJogo dadosJogo, int valor, ArrayList<String> nomes) {
        //gerar random para o sinal da operação 1-2
        int randomJogador = dadosJogo.getRandom().nextInt(2) + 1;
        if (valor == 1) {
            CPU comp1 = new CPU(nomes.get(0), 1);
            CPU comp2 = new CPU(nomes.get(1), 2);
            if (randomJogador == 1) {
                dadosJogo.adicionaJogador(comp1);
                dadosJogo.adicionaJogador(comp2);
            } else {
                dadosJogo.adicionaJogador(comp2);
                dadosJogo.adicionaJogador(comp1);
            }
        } else if (valor == 2) {
            Pessoa pess1 = new Pessoa(nomes.get(0), 1);
            CPU comp2 = new CPU(nomes.get(1), 2);
            if (randomJogador == 1) {
                dadosJogo.adicionaJogador(pess1);
                dadosJogo.adicionaJogador(comp2);
            } else {
                dadosJogo.adicionaJogador(comp2);
                dadosJogo.adicionaJogador(pess1);
            }
        } else if (valor == 3) {
            Pessoa pess1 = new Pessoa(nomes.get(0), 1);
            Pessoa pess2 = new Pessoa(nomes.get(1), 2);
            if (randomJogador == 1) {
                dadosJogo.adicionaJogador(pess1);
                dadosJogo.adicionaJogador(pess2);
            } else {
                dadosJogo.adicionaJogador(pess2);
                dadosJogo.adicionaJogador(pess1);
            }
        }
        return new EsperaJogada4Linha(dadosJogo);
    }
}
