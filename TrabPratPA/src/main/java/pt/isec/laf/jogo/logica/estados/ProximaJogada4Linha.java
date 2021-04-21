package pt.isec.laf.jogo.logica.estados;

import pt.isec.laf.jogo.logica.IEstado;
import pt.isec.laf.jogo.logica.dados.DadosJogo;

/**
 *
 * @author leandro
 */
public class ProximaJogada4Linha extends EstadoAdaptador {

    public ProximaJogada4Linha(DadosJogo dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IEstado proximaJogada(int coluna) {
        //Efetuar a proxima jogada
        //fazer a logica de colocar uma peca
        
        return this; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IEstado jogarPecaEspecial() {
        //efetuar a jogada da peca especial
        //verificar se o jogador tem a peca especial
        for (int i = 0; i < 2; i++) {
            var j = getDadosJogo().getJogadores().get(i);
            if (j.isVezDoJogador()) {
                if (j.getNumPecasEspeciais() > 0) {

                } else {
                    getDadosJogo().addMsgLog("O jogador nao tem pecas especiais!");
                }
            }
        }
        return super.jogarPecaEspecial(); //To change body of generated methods, choose Tools | Templates.
    }

}
