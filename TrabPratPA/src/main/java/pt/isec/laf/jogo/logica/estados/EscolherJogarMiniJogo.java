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
        if (j == 0) {
            int num = getDadosJogo().getRandom(0, 1);
            if (num == 0) {
                getDadosJogo().incrementaNumMiniJogo();
                getDadosJogo().setProximoMiniJogo(false); //significa que é calculos
                getDadosJogo().addMsgLog("Jogo dos calculos");
                return new MiniJogoCalculos(getDadosJogo());
            } else {
                getDadosJogo().incrementaNumMiniJogo();
                getDadosJogo().setProximoMiniJogo(true);  //significa que e palavras
                getDadosJogo().addMsgLog("Jogo das palavras");
                return new MiniJogoPalavras(getDadosJogo());
            }
        } else {
            if (!getDadosJogo().isProximoMiniJogo()) {
                //calculos
                getDadosJogo().incrementaNumMiniJogo();
                getDadosJogo().setProximoMiniJogo(true);
                getDadosJogo().addMsgLog("Jogo dos calculos");
                return new MiniJogoCalculos(getDadosJogo());
            } else {
                //palavras
                getDadosJogo().incrementaNumMiniJogo();
                getDadosJogo().setProximoMiniJogo(false);
                getDadosJogo().addMsgLog("Jogo das palavras");
                return new MiniJogoPalavras(getDadosJogo());
            }
        }

        //return super.jogarMiniJogo(); //To change body of generated methods, choose Tools | Templates.
    }

}
