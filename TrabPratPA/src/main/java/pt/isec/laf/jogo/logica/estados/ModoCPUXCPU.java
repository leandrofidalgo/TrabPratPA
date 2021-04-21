package pt.isec.laf.jogo.logica.estados;

import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.CPU;
import pt.isec.laf.jogo.logica.dados.DadosJogo;

/**
 *
 * @author leandro
 */
public class ModoCPUXCPU extends EstadoAdaptador {

    public ModoCPUXCPU(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado definirNomes(String nome, String nome2) {
        CPU cPU1 = new CPU(nome, 1);
        CPU cPU2 = new CPU(nome, 2);
        getDadosJogo().adicionaJogador(cPU1);
        getDadosJogo().adicionaJogador(cPU2);
        return new EscolherProximoJogador(getDadosJogo());
    }
}
