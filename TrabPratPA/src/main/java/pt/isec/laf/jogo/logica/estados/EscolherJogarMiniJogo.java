package pt.isec.laf.jogo.logica.estados;

import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.DadosJogo;

/**
 *
 * @author leandro
 */
public class EscolherJogarMiniJogo extends EstadoAdaptador {

    public EscolherJogarMiniJogo(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado jogarMiniJogo() {
        //fazer sort e escolher um dos jogos, o primeiro mini jogo sera definido assim
        var j = getDadosJogo().getNumMiniJogos();
        //definir o primeiro mini jogo
        //TODO adicionar num pecas especiais
        if (j == 0) {
            int num = getDadosJogo().getRandom(0, 1);
            if (num == 0) {
                logicaMiniJogo(true, "Jogo dos calculos");
                return new MiniJogoCalculos(getDadosJogo());
            } else {
                logicaMiniJogo(false, "Jogo das palavras");
                return new MiniJogoPalavras(getDadosJogo());
            }
        } else {
            if (!getDadosJogo().isProximoMiniJogo()) {
                //calculos
                logicaMiniJogo(true, "Jogo dos calculos");
                return new MiniJogoCalculos(getDadosJogo());
            } else {
                //palavras
                logicaMiniJogo(false, "Jogo das palavras");
                return new MiniJogoPalavras(getDadosJogo());
            }
        }
        //return super.jogarMiniJogo(); //To change body of generated methods, choose Tools | Templates.
    }

    public void logicaMiniJogo(boolean proximoMiniJogo, String messLog) {
        getDadosJogo().incrementaNumMiniJogo();
        getDadosJogo().setProximoMiniJogo(proximoMiniJogo); //significa que o proximo será palavras
        getDadosJogo().addMsgLog(messLog);
    }
}
