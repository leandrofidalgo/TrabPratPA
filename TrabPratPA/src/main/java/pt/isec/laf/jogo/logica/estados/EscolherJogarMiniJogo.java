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
        var j = getDadosJogo().retornaJogadorAtual();
        //definir o primeiro mini jogo
        if (getDadosJogo().getNum_jogada() == 4) {
            int num = getDadosJogo().getRandom(0, 1);
            if (num == 0) {
                //calculos
                logicaMiniJogo(true, "Jogo dos cálculos");
                return new MiniJogoCalculos(getDadosJogo());
            } else {
                //palavras
                logicaMiniJogo(false, "Jogo das palavras");
                return new MiniJogoPalavras(getDadosJogo());
            }
        } else {
            if (!getDadosJogo().isProximoMiniJogo()) {
                //calculos
                logicaMiniJogo(true, "Jogo dos cálculos");
                return new MiniJogoCalculos(getDadosJogo());
            } else {
                //palavras
                logicaMiniJogo(false, "Jogo das palavras");
                return new MiniJogoPalavras(getDadosJogo());
            }
        }
    }

    public void logicaMiniJogo(boolean proximoMiniJogo, String messLog) {
        getDadosJogo().incrementaNumMiniJogo();
        getDadosJogo().setProximoMiniJogo(proximoMiniJogo); //significa que o proximo será palavras
        getDadosJogo().getMiniJogo().setNumVezesQueJogouMiniJogo(0);
        getDadosJogo().addMsgLog(messLog);
    }

    @Override
    public IEstado naoJogarMiniJogo() {
        return new ProximaJogada4Linha(getDadosJogo());
    }

}
