package pt.isec.laf.jogo.logica.estados;

import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.CPU;
import pt.isec.laf.jogo.logica.dados.DadosJogo;
import pt.isec.laf.jogo.logica.dados.Pessoa;

/**
 *
 * @author leandro
 */
public class ModoPessoaXCPU extends EstadoAdaptador {

    public ModoPessoaXCPU(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado definirNomes(String nome, String nome2) {
        Pessoa pessoa1 = new Pessoa(nome, 1);
        CPU cPU2 = new CPU(nome, 2);
        getDadosJogo().adicionaJogador(pessoa1);
        getDadosJogo().adicionaJogador(cPU2);
        return new EscolherProximoJogador(getDadosJogo());
    }
}
