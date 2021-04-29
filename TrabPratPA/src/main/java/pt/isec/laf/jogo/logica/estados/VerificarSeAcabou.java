package pt.isec.laf.jogo.logica.estados;

import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.DadosJogo;

/**
 *
 * @author leandro
 */
public class VerificarSeAcabou extends EstadoAdaptador {

    public VerificarSeAcabou(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado verificarSeAcabou() {
        boolean ganhou = getDadosJogo().verificaSeJogoAcabou();
        boolean ninguemGanhou = getDadosJogo().verificaSeEstaTudoPreenchido();
        if (ganhou == true) {
            getDadosJogo().setVencedor();
            return new FimDoJogo(getDadosJogo());
        } else {
            if (ninguemGanhou == true) {
                getDadosJogo().addMsgLog("Ninguem ganhou!");
                return new FimDoJogo(getDadosJogo());
            }
            return new EscolherProximoJogador(getDadosJogo());
        }

    }

}
