package pt.isec.laf.jogo.logica.estados;

import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.DadosJogo;

/**
 *
 * @author leandro
 */
public class EscolherModoJogo extends EstadoAdaptador {

    public EscolherModoJogo(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado modoCPUXCPU() {
        return new ModoCPUXCPU(getDadosJogo());
    }

    @Override
    public IEstado modoHomemXCPU() {
        return new ModoPessoaXCPU(getDadosJogo());
    }

    @Override
    public IEstado modoHomemXHomem() {
        return new ModoPessoaXPessoa(getDadosJogo());
    }
}
