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
        dadosJogo.zerarDadosJogo();
    }

    @Override
    public IEstado modoCPUXCPU() {
        getDadosJogo().addMsgLog("Modo CPU X CPU");
        return new ModoCPUXCPU(getDadosJogo());
    }

    @Override
    public IEstado modoHomemXCPU() {
        getDadosJogo().addMsgLog("Modo Pessoa X CPU");
        return new ModoPessoaXCPU(getDadosJogo());
    }

    @Override
    public IEstado modoHomemXHomem() {
        getDadosJogo().addMsgLog("Modo Pessoa X Pessoa");
        return new ModoPessoaXPessoa(getDadosJogo());
    }
}
