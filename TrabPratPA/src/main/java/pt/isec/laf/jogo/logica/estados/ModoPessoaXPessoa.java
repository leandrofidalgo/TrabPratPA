package pt.isec.laf.jogo.logica.estados;

import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.DadosJogo;
import pt.isec.laf.jogo.logica.dados.Pessoa;

/**
 *
 * @author leandro
 */
public class ModoPessoaXPessoa extends EstadoAdaptador {

    public ModoPessoaXPessoa(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado definirNomes(String nome, String nome2) {
        Pessoa pessoa1 = new Pessoa(nome, 1);
        Pessoa pessoa2 = new Pessoa(nome, 2);
        getDadosJogo().adicionaJogador(pessoa1);
        getDadosJogo().adicionaJogador(pessoa2);
        return new EscolherProximoJogador(getDadosJogo());
    }
}
